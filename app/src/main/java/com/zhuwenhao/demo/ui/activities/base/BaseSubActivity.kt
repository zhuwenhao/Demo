package com.zhuwenhao.demo.ui.activities.base

import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.jaeger.library.StatusBarUtil
import com.zhuwenhao.demo.R

abstract class BaseSubActivity : BaseSwipeBackActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setColorForSwipeBack(this, ContextCompat.getColor(this, R.color.colorPrimaryDark), 0)
    }
}