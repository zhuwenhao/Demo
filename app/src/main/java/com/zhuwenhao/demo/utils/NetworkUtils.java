package com.zhuwenhao.demo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.zhuwenhao.demo.R;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.util.Collections;
import java.util.List;

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
     * 获取当前网络类型
     * TYPE MOBILE UNKNOWN {IWLAN LTE_CA}
     *
     * @param context context
     * @return int
     */
    public static int getNetworkType(Context context) {
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

    /**
     * 格式化网络类型
     *
     * @param context context
     * @return String
     */
    public static String formatNetworkType(Context context) {
        switch (getNetworkType(context)) {
            case TYPE_NONE:
                return context.getString(R.string.disconnected);
            case TYPE_WIFI:
                return "Wifi";
            case TYPE_MOBILE:
                return "Mobile";
            case TYPE_MOBILE_2G:
                return "2G";
            case TYPE_MOBILE_3G:
                return "3G";
            case TYPE_MOBILE_4G:
                return "4G";
            case TYPE_OTHER:
                return context.getString(R.string.other);
            default:
                return "";
        }
    }

    /**
     * 格式化IP地址
     *
     * @param ipAddress ipAddress
     * @return ipAddress
     */
    public static String formatIpAddress(int ipAddress) {
        if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
            ipAddress = Integer.reverseBytes(ipAddress);
        }

        byte[] ipByteArray = BigInteger.valueOf(ipAddress).toByteArray();

        String ipAddressString;
        try {
            ipAddressString = InetAddress.getByAddress(ipByteArray).getHostAddress();
        } catch (UnknownHostException e) {
            ipAddressString = null;
        }
        return ipAddressString;
    }

    /**
     * 获取MAC地址
     *
     * @return String
     */
    public static String getMacAddress() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface ni : all) {
                if (!ni.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = ni.getHardwareAddress();
                if (macBytes == null)
                    return "";

                StringBuilder sb = new StringBuilder();
                for (byte b : macBytes) {
                    sb.append(String.format("%02X:", b));
                }

                if (sb.length() > 0)
                    sb.deleteCharAt(sb.length() - 1);
                return sb.toString();
            }
        } catch (Exception e) {
            return "";
        }
        return "02:00:00:00:00:00";
    }
}
