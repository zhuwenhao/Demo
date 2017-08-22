package com.zhuwenhao.demo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.zhuwenhao.demo.adapter.ViewPagerAdapter;
import com.zhuwenhao.demo.fragment.DeviceInfoFragment;
import com.zhuwenhao.demo.fragment.Fragment1;
import com.zhuwenhao.demo.fragment.Fragment2;
import com.zhuwenhao.demo.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.layout_welcome)
    View layoutWelcome;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private static boolean isShowWelcome = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        if (isShowWelcome) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            layoutWelcome.setVisibility(View.VISIBLE);
            layoutWelcome.postDelayed(new Runnable() {
                @Override
                public void run() {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                    layoutWelcome.setVisibility(View.GONE);
                    isShowWelcome = false;
                }
            }, 2333);
        }

        List<String> titles = new ArrayList<>();
        titles.add(getString(R.string.one));
        titles.add(getString(R.string.two));
        titles.add(getString(R.string.device_info));
        for (String title : titles) {
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new Fragment1());
        fragmentList.add(new Fragment2());
        fragmentList.add(new DeviceInfoFragment());

        viewPager.setOffscreenPageLimit(2);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.nav_bandwagon:
                startActivity(new Intent(this, BandwagonActivity.class));
                break;
            case R.id.nav_subway:
                intent = new Intent(this, WebActivity.class);
                intent.setData(Uri.parse(Constants.SUBWAY_URL_DEFAULT));
                startActivity(intent);
                break;
            case R.id.nav_solid_colors:
                startActivity(new Intent(this, SolidColorsActivity.class));
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (!isShowWelcome) {
            super.onBackPressed();
        }
    }
}
