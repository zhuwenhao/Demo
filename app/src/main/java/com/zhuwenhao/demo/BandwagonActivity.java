package com.zhuwenhao.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zhuwenhao.demo.adapter.BandwagonAdapter;
import com.zhuwenhao.demo.entity.Bandwagon;
import com.zhuwenhao.demo.listener.OnItemClickListener;
import com.zhuwenhao.demo.listener.OnMoveAndSwipedListener;
import com.zhuwenhao.demo.utils.ItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BandwagonActivity extends AppCompatActivity implements OnMoveAndSwipedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private Context context;

    private BandwagonAdapter adapter;

    private List<Bandwagon> bandwagonList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bandwagon);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setSupportActionBar(toolbar);

        context = BandwagonActivity.this;

        for (int i = 0; i < 13; i++) {
            bandwagonList.add(new Bandwagon("标题" + (i + 1)));
        }

        adapter = new BandwagonAdapter(bandwagonList);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(context, BandwagonDetailActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelperCallback(this));
        helper.attachToRecyclerView(recyclerView);
    }

    @OnClick(R.id.fab_add)
    public void onViewClicked(View view) {
        Snackbar snackbar = Snackbar.make(view, "Click", Snackbar.LENGTH_LONG).setAction("done", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).addCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
                switch (event) {
                    case Snackbar.Callback.DISMISS_EVENT_ACTION:
                        Toast.makeText(BandwagonActivity.this, "通过Action的点击事件退出", Toast.LENGTH_SHORT).show();
                        break;
                    case Snackbar.Callback.DISMISS_EVENT_CONSECUTIVE:
                        Toast.makeText(BandwagonActivity.this, "由于新的Snackbar显示而退出", Toast.LENGTH_SHORT).show();
                        break;
                    case Snackbar.Callback.DISMISS_EVENT_MANUAL:
                        Toast.makeText(BandwagonActivity.this, "通过调用dismiss()方法退出", Toast.LENGTH_SHORT).show();
                        break;
                    case Snackbar.Callback.DISMISS_EVENT_SWIPE:
                        Toast.makeText(BandwagonActivity.this, "右滑退出", Toast.LENGTH_SHORT).show();
                        break;
                    case Snackbar.Callback.DISMISS_EVENT_TIMEOUT:
                        Toast.makeText(BandwagonActivity.this, "自然退出", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        Button action = (Button) snackbar.getView().findViewById(android.support.design.R.id.snackbar_action);
        action.setAllCaps(false);
        snackbar.show();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(bandwagonList, fromPosition, toPosition);
        adapter.notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemSwiped(int position) {
        bandwagonList.remove(position);
        adapter.notifyItemRemoved(position);
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
