package com.zhuwenhao.demo

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class WebActivity : AppCompatActivity() {

    var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        initView()
    }

    private fun initView() {
        toolbar.setNavigationIcon(R.drawable.ic_close)
        setSupportActionBar(toolbar)

        setWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebView() {
        val webSettings = web_view.settings
        webSettings.javaScriptEnabled = true
        webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK

        web_view.webViewClient = WebViewClient()

        web_view.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (newProgress == 100)
                    progress_bar.visibility = View.GONE
                else {
                    if (progress_bar.visibility == View.GONE)
                        progress_bar.visibility = View.VISIBLE
                    progress_bar.progress = newProgress
                }
            }

            override fun onReceivedTitle(view: WebView?, title: String?) {
                url = view!!.url
                toolbar.title = title
            }
        }
        web_view.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK && web_view.canGoBack()) {
                web_view.goBack()
                true
            } else
                false
        }

        web_view.loadUrl(intent.dataString)
    }

    override fun onDestroy() {
        super.onDestroy()
        web_view.destroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_web, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.menu_refresh -> web_view.reload()
            R.id.menu_copy_url -> {
                val manager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                manager.primaryClip = ClipData.newPlainText(null, url)
                Snackbar.make(web_view, R.string.copy_success, Snackbar.LENGTH_SHORT).show()
            }
            R.id.menu_open_in_browser -> {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}