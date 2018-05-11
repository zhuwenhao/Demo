package com.zhuwenhao.demo

import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.content.res.AppCompatResources
import android.view.View
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import com.zhuwenhao.demo.adapter.ViewPagerAdapter
import com.zhuwenhao.demo.fragment.DeviceInfoFragment
import com.zhuwenhao.demo.fragment.Fragment2
import com.zhuwenhao.demo.movie.InTheatersFragment
import com.zhuwenhao.demo.settings.SettingsActivity
import com.zhuwenhao.demo.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), Drawer.OnDrawerItemClickListener {

    companion object {
        private const val DRAWER_BANDWAGON = 1L
        private const val DRAWER_SUBWAY = 2L
        private const val DRAWER_DAYS_MATTER = 3L
        private const val DRAWER_SETTINGS = 4L
    }

    private lateinit var drawer: Drawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        createDynamicShortcuts()
    }

    private fun initView() {
        setSupportActionBar(toolbar)

        val titles = ArrayList<String>()
        titles.add(getString(R.string.in_theaters))
        titles.add(getString(R.string.movie_coming_soon))
        titles.add(getString(R.string.device_info))
        for (title in titles) {
            tabLayout.addTab(tabLayout.newTab().setText(title))
        }

        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(InTheatersFragment())
        fragmentList.add(Fragment2())
        fragmentList.add(DeviceInfoFragment())

        viewPager.offscreenPageLimit = 2

        val adapter = ViewPagerAdapter(supportFragmentManager, fragmentList, titles)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        drawer = DrawerBuilder().withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggleAnimated(true)
                .withCloseOnClick(false)
                .withHeader(R.layout.layout_drawer_header)
                .addDrawerItems(
                        PrimaryDrawerItem()
                                .withIdentifier(DRAWER_BANDWAGON)
                                .withName(R.string.bandwagon)
                                .withIcon(AppCompatResources.getDrawable(this, R.drawable.ic_computer))
                                .withIconTintingEnabled(true)
                                .withSelectable(false),
                        PrimaryDrawerItem()
                                .withIdentifier(DRAWER_SUBWAY)
                                .withName(R.string.subway)
                                .withIcon(AppCompatResources.getDrawable(this, R.drawable.ic_subway))
                                .withIconTintingEnabled(true)
                                .withSelectable(false),
                        PrimaryDrawerItem()
                                .withIdentifier(DRAWER_DAYS_MATTER)
                                .withName(R.string.days_matter)
                                .withIcon(AppCompatResources.getDrawable(this, R.drawable.ic_hourglass))
                                .withIconTintingEnabled(true)
                                .withSelectable(false)
                )
                .addStickyDrawerItems(
                        PrimaryDrawerItem()
                                .withIdentifier(DRAWER_SETTINGS)
                                .withName(R.string.settings)
                                .withIcon(AppCompatResources.getDrawable(this, R.drawable.ic_settings))
                                .withIconTintingEnabled(true)
                                .withSelectable(false)
                )
                .withSelectedItem(-1)
                .withOnDrawerItemClickListener(this)
                .build()
    }

    override fun onItemClick(view: View?, position: Int, drawerItem: IDrawerItem<*, *>): Boolean {
        when (drawerItem.identifier) {
            DRAWER_BANDWAGON -> startActivity(Intent(this, BandwagonActivity::class.java))
            DRAWER_SUBWAY -> {
                val intent = Intent(this, WebActivity::class.java)
                intent.data = Uri.parse(Constants.SUBWAY_URL)
                startActivity(intent)
            }
            DRAWER_DAYS_MATTER -> startActivity(Intent(this, DaysMatterActivity::class.java))
            DRAWER_SETTINGS -> startActivity(Intent(this, SettingsActivity::class.java))
        }
        Handler().post { drawer.closeDrawer() }
        return false
    }

    private fun createDynamicShortcuts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1)
            return

        val shortcutManager = getSystemService(ShortcutManager::class.java)

        val bandwagon = ShortcutInfo.Builder(this, "bandwagon")
                .setIcon(Icon.createWithResource(this, R.drawable.ic_computer_shortcut))
                .setDisabledMessage(getString(R.string.bandwagon))
                .setLongLabel(getString(R.string.bandwagon))
                .setShortLabel(getString(R.string.bandwagon))
                .setIntent(Intent(Intent.ACTION_VIEW, Uri.EMPTY, this, BandwagonActivity::class.java)
                        .setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
                .build()
        val subway = ShortcutInfo.Builder(this, "subway")
                .setIcon(Icon.createWithResource(this, R.drawable.ic_subway_shortcut))
                .setDisabledMessage(getString(R.string.subway))
                .setLongLabel(getString(R.string.subway))
                .setShortLabel(getString(R.string.subway))
                .setIntent(Intent(Intent.ACTION_VIEW, Uri.parse(Constants.SUBWAY_URL), this, WebActivity::class.java)
                        .setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
                .build()
        val daysMatter = ShortcutInfo.Builder(this, "days_matter")
                .setIcon(Icon.createWithResource(this, R.drawable.ic_hourglass_shortcut))
                .setDisabledMessage(getString(R.string.days_matter))
                .setLongLabel(getString(R.string.days_matter))
                .setShortLabel(getString(R.string.days_matter))
                .setIntent(Intent(Intent.ACTION_VIEW, Uri.EMPTY, this, DaysMatterActivity::class.java)
                        .setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
                .build()
        shortcutManager.dynamicShortcuts = Arrays.asList(bandwagon, subway, daysMatter)
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen)
            drawer.closeDrawer()
        else
            super.onBackPressed()
    }
}