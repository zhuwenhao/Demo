package com.zhuwenhao.demo.fragment;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhuwenhao.demo.R;
import com.zhuwenhao.demo.utils.NetworkUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DeviceInfoFragment extends Fragment {

    @BindView(R.id.text_model)
    TextView textModel;

    @BindView(R.id.text_manufacturer)
    TextView textManufacturer;

    @BindView(R.id.text_board)
    TextView textBoard;

    @BindView(R.id.text_device)
    TextView textDevice;

    @BindView(R.id.text_product)
    TextView textProduct;

    @BindView(R.id.text_android_version)
    TextView textAndroidVersion;

    @BindView(R.id.text_code_name)
    TextView textCodeName;

    @BindView(R.id.text_api_level)
    TextView textApiLevel;

    @BindView(R.id.text_resolution)
    TextView textResolution;

    @BindView(R.id.text_available_resolution)
    TextView textAvailableResolution;

    @BindView(R.id.text_density)
    TextView textDensity;

    @BindView(R.id.text_exact_density)
    TextView textExactDensity;

    @BindView(R.id.text_network_type)
    TextView textNetworkType;

    @BindView(R.id.text_ssid)
    TextView textSSID;

    @BindView(R.id.text_ip_address)
    TextView textIpAddress;

    @BindView(R.id.text_mac_address)
    TextView textMacAddress;

    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_device_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        textModel.setText(Build.MODEL);
        textManufacturer.setText(Build.MANUFACTURER);
        textBoard.setText(Build.BOARD);
        textDevice.setText(Build.DEVICE);
        textProduct.setText(Build.PRODUCT);

        textAndroidVersion.setText(Build.VERSION.RELEASE);
        textCodeName.setText(getCodeName());
        textApiLevel.setText(String.valueOf(Build.VERSION.SDK_INT));

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        textResolution.setText(dm.widthPixels + " x " + dm.heightPixels + " px");
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        textAvailableResolution.setText(dm.widthPixels + " x " + dm.heightPixels + " px");
        textDensity.setText(String.valueOf(dm.densityDpi + " dp"));
        textExactDensity.setText(dm.xdpi + " x " + dm.ydpi + " dp");

        textNetworkType.setText(NetworkUtils.formatNetworkType(getContext()));
        switch (NetworkUtils.getNetworkType(getContext())) {
            case NetworkUtils.TYPE_NONE:
            case NetworkUtils.TYPE_OTHER:
                getActivity().findViewById(R.id.row_ssid).setVisibility(View.GONE);
                getActivity().findViewById(R.id.row_ip_address).setVisibility(View.GONE);
                getActivity().findViewById(R.id.row_mac_address).setVisibility(View.GONE);
                break;
            case NetworkUtils.TYPE_WIFI:
                WifiManager wifiManager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                textSSID.setText(wifiInfo.getSSID().replace("\"", ""));
                textIpAddress.setText(NetworkUtils.formatIpAddress(wifiInfo.getIpAddress()));
                textMacAddress.setText(NetworkUtils.getMacAddress());
                break;
            case NetworkUtils.TYPE_MOBILE:
            case NetworkUtils.TYPE_MOBILE_2G:
            case NetworkUtils.TYPE_MOBILE_3G:
            case NetworkUtils.TYPE_MOBILE_4G:
                getActivity().findViewById(R.id.row_ssid).setVisibility(View.GONE);
                // TODO: 2017/9/22 get IP and MAC
                break;
        }
    }

    private String getCodeName() {
        switch (Build.VERSION.SDK_INT) {
            case 21:
            case 22:
                return "Lollipop";
            case 23:
                return "Marshmallow";
            case 24:
            case 25:
                return "Nougat";
            case 26:
                return "Oreo";
            default:
                return "";
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
