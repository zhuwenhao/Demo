package com.zhuwenhao.demo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

public class NetworkUtils {

    public static final int TYPE_NONE = -1;

    public static final int TYPE_WIFI = 0;

    public static final int TYPE_MOBILE = 1;

    public static final int TYPE_MOBILE_2G = 2;

    public static final int TYPE_MOBILE_3G = 3;

    public static final int TYPE_MOBILE_4G = 4;

    public static final int TYPE_OTHER = 5;

    /**
     * 是否有网络连接
     *
     * @param context context
     * @return boolean
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable();
    }

    /**
     * 获取当前网络连接的类型
     * TYPE MOBILE UNKNOWN {IWLAN LTE_CA}
     *
     * @param context context
     * @return int
     */
    public static int getConnectedType(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return TYPE_WIFI;
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                switch (networkInfo.getSubtype()) {
                    //2G
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                    case TelephonyManager.NETWORK_TYPE_GSM:
                        return TYPE_MOBILE_2G;
                    //3G
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    case TelephonyManager.NETWORK_TYPE_EHRPD:
                    case TelephonyManager.NETWORK_TYPE_HSPAP:
                    case TelephonyManager.NETWORK_TYPE_TD_SCDMA:
                        return TYPE_MOBILE_3G;
                    //4G
                    case TelephonyManager.NETWORK_TYPE_LTE:
                        return TYPE_MOBILE_4G;
                    default:
                        //中国联通 电信 3G制式
                        if (networkInfo.getSubtypeName().equalsIgnoreCase("WCDMA") || networkInfo.getSubtypeName().equalsIgnoreCase("CDMA2000"))
                            return TYPE_MOBILE_3G;
                        else
                            return TYPE_MOBILE;
                }
            } else {
                return TYPE_OTHER;
            }
        } else {
            return TYPE_NONE;
        }
    }
}
