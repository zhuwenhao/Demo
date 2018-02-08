package com.zhuwenhao.demo.ui.activities.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.r0adkll.slidr.Slidr
import com.r0adkll.slidr.model.SlidrConfig
import com.r0adkll.slidr.model.SlidrListener

abstract class BaseSwipeBackActivity : AppCompatActivity(), SlidrListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = SlidrConfig.Builder()
                .edge(true)
                .listener(this)
                .build()
        Slidr.attach(this, config)
    }

    override fun onSlideClosed() {

    }

    override fun onSlideStateChanged(state: Int) {

    }

    override fun onSlideChange(percent: Float) {

    }

    override fun onSlideOpened() {

    }
}