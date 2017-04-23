package com.zhuwenhao.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.internal.MDButton;
import com.github.magiepooh.recycleritemdecoration.ItemDecorations;
import com.zhuwenhao.demo.adapter.BandwagonAdapter;
import com.zhuwenhao.demo.entity.Bandwagon;
import com.zhuwenhao.demo.listener.OnItemClickListener;
import com.zhuwenhao.demo.listener.OnMoveAndSwipedListener;
import com.zhuwenhao.demo.utils.AppUtils;
import com.zhuwenhao.demo.utils.DatabaseUtils;
import com.zhuwenhao.demo.utils.ItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BandwagonActivity extends AppCompatActivity implements OnMoveAndSwipedListener, TextWatcher {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;

    private AutoCompleteTextView textTitle;

    private AutoCompleteTextView textVeId;

    private AutoCompleteTextView textApiKey;

    private MDButton positiveAction;

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

        bandwagonList = DatabaseUtils.getBandwagonList(this);

        RecyclerView.ItemDecoration itemDecoration = ItemDecorations.vertical(this)
                .last(R.drawable.item_decoration_h_8)
                .create();

        adapter = new BandwagonAdapter(bandwagonList);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(context, BandwagonDetailActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelperCallback(this));
        helper.attachToRecyclerView(recyclerView);
    }

    @OnClick(R.id.fab_add)
    public void onViewClicked(View view) {
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title("添加主机")
                .customView(R.layout.dialog_bandwagon_add, true)
                .positiveText("确定")
                .negativeText("取消")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (DatabaseUtils.addBandwagon(context, new Bandwagon(textTitle.getText().toString(), textVeId.getText().toString(), textApiKey.getText().toString()))) {
                            bandwagonList.clear();
                            for (Bandwagon bandwagon : DatabaseUtils.getBandwagonList(context)) {
                                bandwagonList.add(bandwagon);
                            }
                            adapter.notifyDataSetChanged();
                            AppUtils.showToast(context, "添加成功");
                        } else {
                            AppUtils.showToast(context, "添加失败");
                        }
                    }
                }).build();

        positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
        textTitle = (AutoCompleteTextView) dialog.getCustomView().findViewById(R.id.dialog_text_title);
        textTitle.addTextChangedListener(this);
        textVeId = (AutoCompleteTextView) dialog.getCustomView().findViewById(R.id.dialog_text_ve_id);
        textVeId.addTextChangedListener(this);
        textApiKey = (AutoCompleteTextView) dialog.getCustomView().findViewById(R.id.dialog_text_api_key);
        textApiKey.addTextChangedListener(this);
        dialog.show();
        positiveAction.setEnabled(false);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        DatabaseUtils.updateSort(this, bandwagonList.get(fromPosition).getId(), bandwagonList.get(fromPosition).getSort(), bandwagonList.get(toPosition).getId(), bandwagonList.get(toPosition).getSort());
        Collections.swap(bandwagonList, fromPosition, toPosition);
        adapter.notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemSwiped(int position) {
        bandwagonList.remove(position);
        adapter.notifyItemRemoved(position);
        Snackbar.make(fabAdd, "Click", Snackbar.LENGTH_LONG).setAction("done", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).addCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
                switch (event) {
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
        }).show();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        positiveAction.setEnabled(textTitle.getText().toString().trim().length() > 0 && textVeId.getText().toString().trim().length() > 0 && textApiKey.getText().toString().trim().length() > 0);
    }

    @Override
    public void afterTextChanged(Editable s) {

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
