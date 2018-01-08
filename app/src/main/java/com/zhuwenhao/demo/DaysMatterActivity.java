package com.zhuwenhao.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.pm.ShortcutInfoCompat;
import android.support.v4.content.pm.ShortcutManagerCompat;
import android.support.v4.graphics.drawable.IconCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.magiepooh.recycleritemdecoration.ItemDecorations;
import com.zhuwenhao.demo.adapter.DaysMatterAdapter;
import com.zhuwenhao.demo.custom.MultipleStatusView;
import com.zhuwenhao.demo.entity.DaysMatter;
import com.zhuwenhao.demo.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DaysMatterActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.img_top)
    ImageView imgTop;

    @BindView(R.id.layout_text)
    LinearLayout layoutText;

    @BindView(R.id.text_title)
    TextView textTitle;

    @BindView(R.id.text_days)
    TextView textDays;

    @BindView(R.id.text_target_date)
    TextView textTargetDate;

    @BindView(R.id.text_week)
    TextView textWeek;

    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private Context context;

    private DaysMatterAdapter adapter;

    private List<DaysMatter> daysMatterList = new ArrayList<>();

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            getDaysMatterList(true);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getAction() != null && getIntent().getAction().equals(Intent.ACTION_CREATE_SHORTCUT)) { //创建快捷方式
            setResult(RESULT_OK, ShortcutManagerCompat.createShortcutResultIntent(this,
                    new ShortcutInfoCompat.Builder(this, "DaysMatter")
                            .setIntent(new Intent(this, DaysMatterActivity.class).setAction(Intent.ACTION_VIEW))
                            .setIcon(IconCompat.createWithResource(this, R.drawable.ic_hourglass_shortcut))
                            .setLongLabel(getString(R.string.days_matter))
                            .setShortLabel(getString(R.string.days_matter))
                            .build()));
            finish();
        } else {
            setContentView(R.layout.activity_days_matter);
            ButterKnife.bind(this);
            initView();
        }
    }

    private void initView() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        context = DaysMatterActivity.this;

        Glide.with(this).load(R.drawable.bg_days_matter).apply(RequestOptions.centerCropTransform()).into(imgTop);

        multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDaysMatterList(false);
            }
        });

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);

        adapter = new DaysMatterAdapter(this, daysMatterList);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(ItemDecorations.vertical(this).type(0, R.drawable.item_decoration_h_1_left_72).create());
        recyclerView.setAdapter(adapter);

        getDaysMatterList(false);
    }

    private void getDaysMatterList(final boolean refresh) {
        if (!refresh)
            multipleStatusView.showLoading();

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                daysMatterList.clear();

                daysMatterList.add(new DaysMatter("测试", "2018-02-05", false, false));
                daysMatterList.add(new DaysMatter("测试", "2018-01-02", false, false));
                daysMatterList.add(new DaysMatter("测试", "2018-02-14", false, false));
                daysMatterList.add(new DaysMatter("", "2018-02-15", true, true));
                daysMatterList.add(new DaysMatter("测试", "2018-01-05", false, false));
                daysMatterList.add(new DaysMatter("测试", "2018-01-06", false, false));
                daysMatterList.add(new DaysMatter("测试", "2018-08-05", false, false));

                int pinOnTop = -1;
                for (int i = 0; i < daysMatterList.size(); i++) {
                    if (daysMatterList.get(i).isPinOnTop())
                        pinOnTop = i;
                }
                if (pinOnTop == -1)
                    pinOnTop = 0;

                emitter.onNext(pinOnTop);
            }
        }).delay(500, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer position) throws Exception {
                        adapter.notifyDataSetChanged();

                        if (refresh)
                            swipeRefreshLayout.setRefreshing(false);

                        if (daysMatterList.size() == 0) {
                            layoutText.setVisibility(View.GONE);
                            multipleStatusView.showEmpty();
                        } else {
                            textTitle.setText(daysMatterList.get(position).getTitle(context));
                            textDays.setText(String.valueOf(daysMatterList.get(position).getDays()));
                            textTargetDate.setText(daysMatterList.get(position).getTargetDateText());
                            textWeek.setText(daysMatterList.get(position).getTargetDate().dayOfWeek().getAsShortText());
                            layoutText.setVisibility(View.VISIBLE);
                            multipleStatusView.showContent();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (refresh)
                            swipeRefreshLayout.setRefreshing(false);
                        else
                            multipleStatusView.showError();
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_days_matter, menu);
        return true;
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
