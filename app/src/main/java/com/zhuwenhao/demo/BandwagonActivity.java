package com.zhuwenhao.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;

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
