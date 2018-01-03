package com.zhuwenhao.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.zhuwenhao.demo.custom.MultipleStatusView;
import com.zhuwenhao.demo.entity.Bandwagon;
import com.zhuwenhao.demo.entity.BandwagonInfo;
import com.zhuwenhao.demo.utils.AppUtils;
import com.zhuwenhao.demo.utils.Constants;
import com.zhuwenhao.demo.utils.DatabaseUtils;
import com.zhuwenhao.demo.utils.MaterialDialogUtils;
import com.zhuwenhao.demo.utils.NetworkUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.joda.time.DateTime;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class BandwagonDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.text_hostname)
    TextView textHostname;

    @BindView(R.id.text_vm_type)
    TextView textVmType;

    @BindView(R.id.text_node_location)
    TextView textNodeLocation;

    @BindView(R.id.text_os)
    TextView textOs;

    @BindView(R.id.text_ip_addresses)
    TextView textIpAddresses;

    @BindView(R.id.text_ssh_port)
    TextView textSshPort;

    @BindView(R.id.text_status)
    TextView textStatus;

    @BindView(R.id.text_cpu_load)
    TextView textCpuLoad;

    @BindView(R.id.text_used_ram)
    TextView textUsedRam;

    @BindView(R.id.text_total_ram)
    TextView textTotalRam;

    @BindView(R.id.pb_ram)
    ProgressBar pbRam;

    @BindView(R.id.text_used_swap)
    TextView textUsedSwap;

    @BindView(R.id.text_total_swap)
    TextView textTotalSwap;

    @BindView(R.id.pb_swap)
    ProgressBar pbSwap;

    @BindView(R.id.text_used_disk)
    TextView textUsedDisk;

    @BindView(R.id.text_total_disk)
    TextView textTotalDisk;

    @BindView(R.id.pb_disk)
    ProgressBar pbDisk;

    @BindView(R.id.text_used_data)
    TextView textUsedData;

    @BindView(R.id.text_total_data)
    TextView textTotalData;

    @BindView(R.id.pb_data)
    ProgressBar pbData;

    @BindView(R.id.text_resets)
    TextView textResets;

    private Context context;

    private Bandwagon bandwagon;

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            getServiceInfo(true);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bandwagon_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        bandwagon = (Bandwagon) getIntent().getSerializableExtra("bandwagon");
        toolbar.setTitle(bandwagon.getTitle());
        setSupportActionBar(toolbar);

        context = BandwagonDetailActivity.this;

        getServiceInfo(false);

        multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getServiceInfo(false);
            }
        });

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
    }

    private void getServiceInfo(final boolean refresh) {
        if (!NetworkUtils.isNetworkConnected(this)) {
            multipleStatusView.showNoNetwork();
            return;
        }

        if (!refresh) {
            multipleStatusView.showLoading();
        }

        String url = Constants.BANDWAGON_URL_API + "getLiveServiceInfo";
        OkHttpUtils.post().url(url)
                .addParams("veid", bandwagon.getVeId())
                .addParams("api_key", bandwagon.getApiKey())
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                AppUtils.showToast(context, e.getMessage());
                if (refresh) {
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    multipleStatusView.showError();
                }
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    Gson gson = new Gson();
                    BandwagonInfo bandwagonInfo = gson.fromJson(response, BandwagonInfo.class);
                    if (bandwagonInfo.getError() == 0) {
                        textHostname.setText(bandwagonInfo.getHostname());
                        textVmType.setText(bandwagonInfo.getVmType());
                        textNodeLocation.setText(bandwagonInfo.getNodeLocation());
                        textOs.setText(bandwagonInfo.getOs());
                        textIpAddresses.setText(bandwagonInfo.getIpAddresses());
                        textSshPort.setText(bandwagonInfo.getSshPort());

                        if (bandwagonInfo.getVmType().equals("ovz")) {
                            textStatus.setText(AppUtils.firstLetterToUpper(bandwagonInfo.getBandwagonStatus().getStatus()));
                            textCpuLoad.setText(String.valueOf(bandwagonInfo.getBandwagonStatus().getNpRoc() + " processes; LA: " + bandwagonInfo.getBandwagonStatus().getLoadAverage()));

                            textUsedRam.setText(AppUtils.conversionByte(bandwagonInfo.getBandwagonStatus().getUsedRam()));
                            textTotalRam.setText(AppUtils.conversionByte(bandwagonInfo.getPlanRam()));
                            pbRam.setProgress((int) (bandwagonInfo.getBandwagonStatus().getUsedRam() / (float) bandwagonInfo.getPlanRam() * 100));

                            textUsedSwap.setText(AppUtils.conversionByte(bandwagonInfo.getBandwagonStatus().getUsedSwap()));
                            textTotalSwap.setText(AppUtils.conversionByte(bandwagonInfo.getPlanSwap()));
                            pbSwap.setProgress((int) (bandwagonInfo.getBandwagonStatus().getUsedSwap() / (float) bandwagonInfo.getPlanSwap() * 100));

                            textUsedDisk.setText(AppUtils.conversionByte(bandwagonInfo.getBandwagonQuota().getOccupiedKB()));
                            textTotalDisk.setText(AppUtils.conversionByte(bandwagonInfo.getPlanDisk()));
                            pbDisk.setProgress((int) (bandwagonInfo.getBandwagonQuota().getOccupiedKB() / (float) bandwagonInfo.getPlanDisk() * 100));
                        } else {
                            textStatus.setText(bandwagonInfo.getVeStatus());
                            textCpuLoad.setText(String.valueOf("LA: " + bandwagonInfo.getLoadAverage()));

                            textUsedRam.setText(AppUtils.conversionByte(bandwagonInfo.getPlanRam() - bandwagonInfo.getMemAvailableKB()));
                            textTotalRam.setText(AppUtils.conversionByte(bandwagonInfo.getPlanRam()));
                            pbRam.setProgress((int) ((bandwagonInfo.getPlanRam() - bandwagonInfo.getMemAvailableKB()) / (float) bandwagonInfo.getPlanRam() * 100));

                            textUsedSwap.setText(AppUtils.conversionByte(bandwagonInfo.getSwapTotalKB() - bandwagonInfo.getSwapAvailableKB()));
                            textTotalSwap.setText(AppUtils.conversionByte(bandwagonInfo.getSwapTotalKB()));
                            pbSwap.setProgress((int) ((bandwagonInfo.getSwapTotalKB() - bandwagonInfo.getSwapAvailableKB()) / (float) bandwagonInfo.getSwapTotalKB() * 100));

                            textUsedDisk.setText(AppUtils.conversionByte(bandwagonInfo.getVeUsedDiskSpaceB()));
                            textTotalDisk.setText(AppUtils.conversionByte(bandwagonInfo.getPlanDisk()));
                            pbDisk.setProgress((int) (bandwagonInfo.getVeUsedDiskSpaceB() / (float) bandwagonInfo.getPlanDisk() * 100));
                        }

                        textUsedData.setText(AppUtils.conversionByte(bandwagonInfo.getDataCounter()));
                        textTotalData.setText(AppUtils.conversionByte(bandwagonInfo.getPlanMonthlyData()));
                        pbData.setProgress((int) (bandwagonInfo.getDataCounter() / (float) bandwagonInfo.getPlanMonthlyData() * 100));

                        textResets.setText(String.format(getResources().getString(R.string.resets), new DateTime(bandwagonInfo.getDataNextReset() * 1000).toString("yyyy-MM-dd")));

                        bandwagon.setBandwagonInfo(bandwagonInfo);
                        DatabaseUtils.updateBandwagonInfo(context, bandwagon);

                        if (toolbar.getMenu().size() == 0) {
                            toolbar.inflateMenu(R.menu.menu_bandwagon_detail);
                        }

                        if (refresh) {
                            swipeRefreshLayout.setRefreshing(false);
                        } else {
                            multipleStatusView.showContent();
                        }
                    } else {
                        AppUtils.showToast(context, bandwagonInfo.getMessage());
                        if (refresh) {
                            swipeRefreshLayout.setRefreshing(false);
                        } else {
                            multipleStatusView.showError();
                        }
                    }
                } catch (Exception e) {
                    AppUtils.showToast(context, e.getMessage());
                    if (refresh) {
                        swipeRefreshLayout.setRefreshing(false);
                    } else {
                        multipleStatusView.showError();
                    }
                }
            }
        });
    }

    private void doStartOrStopOrRebootOrKill(String url) {
        if (!NetworkUtils.isNetworkConnected(this)) {
            AppUtils.showToast(this, R.string.no_network);
            return;
        }

        final MaterialDialog dialog = MaterialDialogUtils.showProgressDialog(this, false);

        url = Constants.BANDWAGON_URL_API + url;
        OkHttpUtils.post().url(url)
                .addParams("veid", bandwagon.getVeId())
                .addParams("api_key", bandwagon.getApiKey())
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dialog.dismiss();
                AppUtils.showToast(context, e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                dialog.dismiss();

                try {
                    Gson gson = new Gson();
                    BandwagonInfo bandwagonInfo = gson.fromJson(response, BandwagonInfo.class);
                    if (bandwagonInfo.getError() == 0) {
                        swipeRefreshLayout.post(new Runnable() {
                            @Override
                            public void run() {
                                swipeRefreshLayout.setRefreshing(true);
                                onRefreshListener.onRefresh();
                            }
                        });
                    } else {
                        AppUtils.showToast(context, bandwagonInfo.getMessage());
                    }
                } catch (Exception e) {
                    AppUtils.showToast(context, e.getMessage());
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_start:
                doStartOrStopOrRebootOrKill("start");
                break;
            case R.id.menu_stop:
                doStartOrStopOrRebootOrKill("stop");
                break;
            case R.id.menu_reboot:
                doStartOrStopOrRebootOrKill("restart");
                break;
            case R.id.menu_kill:
                doStartOrStopOrRebootOrKill("kill");
                break;
            case R.id.menu_reset_root_password:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
