package com.zhuwenhao.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.github.clans.fab.FloatingActionMenu;
import com.zhuwenhao.demo.custom.MultipleStatusView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MultipleStatusViewActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;

    @BindView(R.id.fab_menu)
    FloatingActionMenu fabMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_status_view);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setSupportActionBar(toolbar);

        multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multipleStatusView.showLoading();
                multipleStatusView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        multipleStatusView.showContent();
                    }
                }, 2333);
            }
        });

        fabMenu.setClosedOnTouchOutside(true);
    }

    @OnClick({R.id.fab_empty, R.id.fab_error, R.id.fab_no_network})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab_empty:
                multipleStatusView.showEmpty();
                break;
            case R.id.fab_error:
                multipleStatusView.showError();
                break;
            case R.id.fab_no_network:
                multipleStatusView.showNoNetwork();
                break;
        }
        fabMenu.close(true);
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
