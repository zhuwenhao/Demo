package com.zhuwenhao.demo

import android.content.Intent
import android.net.Uri
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
import com.zhuwenhao.demo.fragment.Fragment1
import com.zhuwenhao.demo.fragment.Fragment2
import com.zhuwenhao.demo.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Drawer.OnDrawerItemClickListener {

    companion object {
        private const val DRAWER_BANDWAGON = 1L
        private const val DRAWER_SUBWAY = 2L
        private const val DRAWER_NFC = 3L
        private const val DRAWER_DAYS_MATTER = 4L
    }

    private lateinit var drawer: Drawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        setSupportActionBar(toolbar)

        val titles = ArrayList<String>()
        titles.add(getString(R.string.one))
        titles.add(getString(R.string.two))
        titles.add(getString(R.string.device_info))
        for (title in titles) {
            tabLayout.addTab(tabLayout.newTab().setText(title))
        }

        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(Fragment1())
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
                                .withIdentifier(DRAWER_NFC)
                                .withName(R.string.nfc)
                                .withIcon(AppCompatResources.getDrawable(this, R.drawable.ic_nfc))
                                .withIconTintingEnabled(true)
                                .withSelectable(false),
                        PrimaryDrawerItem()
                                .withIdentifier(DRAWER_DAYS_MATTER)
                                .withName(R.string.days_matter)
                                .withIcon(AppCompatResources.getDrawable(this, R.drawable.ic_hourglass))
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
                intent.data = Uri.parse(Constants.SUBWAY_URL_DEFAULT)
                startActivity(intent)
            }
            DRAWER_NFC -> startActivity(Intent(this, NfcActivity::class.java))
            DRAWER_DAYS_MATTER -> startActivity(Intent(this, DaysMatterActivity::class.java))
        }
        Handler().post { drawer.closeDrawer() }
        return false
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen)
            drawer.closeDrawer()
        else
            super.onBackPressed()
    }
}