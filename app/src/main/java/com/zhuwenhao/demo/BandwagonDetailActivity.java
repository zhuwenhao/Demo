package com.zhuwenhao.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhuwenhao.demo.custom.MultipleStatusView;
import com.zhuwenhao.demo.entity.Bandwagon;
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

    @BindView(R.id.text_info)
    TextView textInfo;

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
        setSupportActionBar(toolbar);

        context = BandwagonDetailActivity.this;

        bandwagon = new Bandwagon();
        bandwagon.setId(getIntent().getExtras().getInt("id", -1));
        bandwagon.setVeId(getIntent().getExtras().getString("veId", ""));
        bandwagon.setApiKey(getIntent().getExtras().getString("apiKey", ""));

        getServiceInfo();

        multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getServiceInfo();
            }
        });
    }

    private void getServiceInfo() {
        multipleStatusView.showLoading();

        String url = "https://api.64clouds.com/v1/getServiceInfo";
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
                multipleStatusView.showContent();

                textInfo.setText(response);

                Gson gson = new Gson();
                Bandwagon bwg = gson.fromJson(response, Bandwagon.class);
                bwg.setId(bandwagon.getId());
                DatabaseUtils.updateBandwagonInfo(context, bwg);
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
