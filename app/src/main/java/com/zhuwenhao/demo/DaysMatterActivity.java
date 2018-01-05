package com.zhuwenhao.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.magiepooh.recycleritemdecoration.ItemDecorations;
import com.zhuwenhao.demo.adapter.DaysMatterAdapter;
import com.zhuwenhao.demo.entity.DaysMatter;
import com.zhuwenhao.demo.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DaysMatterActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.img_top)
    ImageView imgTop;

    @BindView(R.id.text_title)
    TextView textTitle;

    @BindView(R.id.text_days)
    TextView textDays;

    @BindView(R.id.text_target_date)
    TextView textTargetDate;

    @BindView(R.id.text_week)
    TextView textWeek;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private DaysMatterAdapter adapter;

    private List<DaysMatter> daysMatterList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days_matter);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        Glide.with(this).load(R.drawable.bg_days_matter).apply(RequestOptions.centerCropTransform()).into(imgTop);

        daysMatterList.add(new DaysMatter("测试", "2018-02-05", false, false));
        daysMatterList.add(new DaysMatter("测试", "2018-01-02", false, false));
        daysMatterList.add(new DaysMatter("测试", "2018-02-14", false, false));
        daysMatterList.add(new DaysMatter("", "2018-02-15", true, true));
        daysMatterList.add(new DaysMatter("测试", "2018-01-05", false, false));
        daysMatterList.add(new DaysMatter("测试", "2018-01-06", false, false));
        daysMatterList.add(new DaysMatter("测试", "2018-08-05", false, false));

        for (DaysMatter daysMatter : daysMatterList) {
            if (daysMatter.isPinOnTop()) {
                textTitle.setText(daysMatter.getTitle(this));
                textDays.setText(String.valueOf(daysMatter.getDays()));
                textTargetDate.setText(daysMatter.getTargetDateText());
                textWeek.setText(daysMatter.getTargetDate().dayOfWeek().getAsShortText());
                break;
            }
        }

        adapter = new DaysMatterAdapter(this, daysMatterList);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(ItemDecorations.vertical(this).type(0, R.drawable.item_decoration_h_1_left_72).create());
        recyclerView.setAdapter(adapter);
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
