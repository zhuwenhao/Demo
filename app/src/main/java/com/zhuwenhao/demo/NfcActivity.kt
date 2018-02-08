package com.zhuwenhao.demo

import android.os.Bundle
import android.view.MenuItem
import com.zhuwenhao.demo.ui.activities.base.BaseSubActivity
import kotlinx.android.synthetic.main.layout_toolbar.*

class NfcActivity : BaseSubActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nfc)
        initView()
    }

    private fun initView() {
        setSupportActionBar(toolbar)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}