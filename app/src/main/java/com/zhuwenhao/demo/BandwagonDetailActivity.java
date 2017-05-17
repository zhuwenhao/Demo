package com.zhuwenhao.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhuwenhao.demo.custom.MultipleStatusView;
import com.zhuwenhao.demo.entity.Bandwagon;
import com.zhuwenhao.demo.entity.BandwagonInfo;
import com.zhuwenhao.demo.utils.AppUtils;
import com.zhuwenhao.demo.utils.DatabaseUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

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

    @BindView(R.id.text_used_ram)
    TextView textUsedRam;

    @BindView(R.id.text_total_ram)
    TextView textTotalRam;

    @BindView(R.id.text_used_swap)
    TextView textUsedSwap;

    @BindView(R.id.text_total_swap)
    TextView textTotalSwap;

    @BindView(R.id.text_used_disk)
    TextView textUsedDisk;

    @BindView(R.id.text_total_disk)
    TextView textTotalDisk;

    @BindView(R.id.text_used_data)
    TextView textUsedData;

    @BindView(R.id.text_total_data)
    TextView textTotalData;

    private Context context;

    private Bandwagon bandwagon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bandwagon_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        toolbar.setTitle(getIntent().getStringExtra("title"));
        setSupportActionBar(toolbar);

        context = BandwagonDetailActivity.this;

        bandwagon = new Bandwagon();
        bandwagon.setId(getIntent().getIntExtra("id", -1));
        bandwagon.setVeId(getIntent().getStringExtra("veId"));
        bandwagon.setApiKey(getIntent().getStringExtra("apiKey"));

        getServiceInfo();

        multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getServiceInfo();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getServiceInfo() {
        multipleStatusView.showLoading();

        String url = "https://api.64clouds.com/v1/getLiveServiceInfo";
        OkHttpUtils.post().url(url)
                .addParams("veid", bandwagon.getVeId())
                .addParams("api_key", bandwagon.getApiKey())
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                multipleStatusView.showError();
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    Gson gson = new Gson();
                    BandwagonInfo bandwagonInfo = gson.fromJson(response, BandwagonInfo.class);
                    if (bandwagonInfo.getError() == 0) {
                        textHostname.setText(bandwagonInfo.getHostname());
                        textNodeLocation.setText(bandwagonInfo.getNodeLocation());
                        textOs.setText(bandwagonInfo.getOs());
                        textIpAddresses.setText(bandwagonInfo.getIpAddresses());
                        textSshPort.setText(bandwagonInfo.getSshPort());
                        textStatus.setText(AppUtils.firstLetterToUpper(bandwagonInfo.getBandwagonStatus().getStatus()) + " (" + bandwagonInfo.getBandwagonStatus().getNpRoc() + " processes; LA: " + bandwagonInfo.getBandwagonStatus().getLoadAverage() + ")");
                        textTotalRam.setText(AppUtils.conversionByte(bandwagonInfo.getPlanRam()));
                        textTotalSwap.setText(AppUtils.conversionByte(bandwagonInfo.getPlanSwap()));
                        textTotalDisk.setText(AppUtils.conversionByte(bandwagonInfo.getPlanDisk()));
                        textUsedData.setText(AppUtils.conversionByte(bandwagonInfo.getDataCounter()));
                        textTotalData.setText(AppUtils.conversionByte(bandwagonInfo.getPlanMonthlyData()));

                        bandwagon.setBandwagonInfo(bandwagonInfo);
                        DatabaseUtils.updateBandwagonInfo(context, bandwagon);

                        multipleStatusView.showContent();
                    } else {
                        AppUtils.showToast(context, bandwagonInfo.getMessage());
                        multipleStatusView.showError();
                    }
                } catch (Exception e) {
                    multipleStatusView.showError();
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
        }
        return super.onOptionsItemSelected(item);
    }
}
