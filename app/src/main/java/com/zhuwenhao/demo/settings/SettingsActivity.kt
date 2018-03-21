package com.zhuwenhao.demo.settings

import android.os.Bundle
import android.support.v4.app.Fragment
import com.zhuwenhao.demo.R
import com.zhuwenhao.demo.ui.activities.base.BaseFragmentSubActivity
import kotlinx.android.synthetic.main.layout_toolbar.*

class SettingsActivity : BaseFragmentSubActivity() {

    override fun provideFragmentContainer(): Int {
        return R.id.layout_settings_fragment_container
    }

    override fun provideInitFragment(): Fragment {
        return SettingsFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        initView()
    }

    private fun initView() {
        setSupportActionBar(toolbar)
    }
}