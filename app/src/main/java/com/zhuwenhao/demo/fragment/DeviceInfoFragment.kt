package com.zhuwenhao.demo.fragment

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhuwenhao.demo.R
import com.zhuwenhao.demo.utils.NetworkUtils
import kotlinx.android.synthetic.main.fragment_device_info.*
import java.text.DecimalFormat

class DeviceInfoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_device_info, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        textModel.text = Build.MODEL
        textManufacturer.text = Build.MANUFACTURER
        textBoard.text = Build.BOARD
        textDevice.text = Build.DEVICE
        textProduct.text = Build.PRODUCT

        textAndroidVersion.text = Build.VERSION.RELEASE
        textCodeName.text = getCodeName()
        textApiLevel.text = Build.VERSION.SDK_INT.toString()

        val dm = DisplayMetrics()
        activity.windowManager.defaultDisplay.getRealMetrics(dm)
        textResolution.text = getString(R.string.resolution_value, dm.widthPixels, dm.heightPixels)
        activity.windowManager.defaultDisplay.getMetrics(dm)
        textAvailableResolution.text = getString(R.string.resolution_value, dm.widthPixels, dm.heightPixels)
        textDensity.text = getString(R.string.density_value, dm.densityDpi)
        textExactDensity.text = getString(R.string.exact_density_value, DecimalFormat("0.000").format(dm.xdpi), DecimalFormat("0.000").format(dm.ydpi))

        textNetworkType.text = NetworkUtils.formatNetworkType(context)
        when (NetworkUtils.getNetworkType(context)) {
            NetworkUtils.TYPE_NONE,
            NetworkUtils.TYPE_OTHER -> {
                rowSSID.visibility = View.GONE
                rowIpAddress.visibility = View.GONE
                rowMacAddress.visibility = View.GONE
            }
            NetworkUtils.TYPE_WIFI -> {
                val wifiInfo = (activity.getSystemService(Context.WIFI_SERVICE) as WifiManager).connectionInfo
                textSSID.text = wifiInfo.ssid.replace("\"", "")
                textIpAddress.text = NetworkUtils.formatIpAddress(wifiInfo.ipAddress) //NetworkUtils.getIpAddress() 效果一样
                textMacAddress.text = NetworkUtils.getMacAddress()
            }
            NetworkUtils.TYPE_MOBILE,
            NetworkUtils.TYPE_MOBILE_2G,
            NetworkUtils.TYPE_MOBILE_3G,
            NetworkUtils.TYPE_MOBILE_4G -> {
                rowSSID.visibility = View.GONE
                textIpAddress.text = NetworkUtils.getIpAddress()
                textMacAddress.text = NetworkUtils.getMacAddress()
            }
        }
    }

    private fun getCodeName(): String {
        return when (Build.VERSION.SDK_INT) {
            21, 22 -> "Lollipop"
            23 -> "Marshmallow"
            24, 25 -> "Nougat"
            26 -> "Oreo"
            else -> ""
        }
    }
}