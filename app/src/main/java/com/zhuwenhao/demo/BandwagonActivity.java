package com.zhuwenhao.demo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.internal.MDButton;
import com.github.magiepooh.recycleritemdecoration.ItemDecorations;
import com.zhuwenhao.demo.adapter.BandwagonAdapter;
import com.zhuwenhao.demo.entity.Bandwagon;
import com.zhuwenhao.demo.listener.OnItemClickListener;
import com.zhuwenhao.demo.listener.OnItemEditClickListener;
import com.zhuwenhao.demo.listener.OnMoveAndSwipedListener;
import com.zhuwenhao.demo.ui.activities.base.BaseSubActivity;
import com.zhuwenhao.demo.utils.Constants;
import com.zhuwenhao.demo.utils.DatabaseUtils;
import com.zhuwenhao.demo.utils.ItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BandwagonActivity extends BaseSubActivity implements OnMoveAndSwipedListener, TextWatcher {

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

    private List<Bandwagon> deleteList = new ArrayList<>();

    private Snackbar snackbar;

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
        updatePosition();

        RecyclerView.ItemDecoration itemDecoration = ItemDecorations.vertical(this)
                .last(R.drawable.item_decoration_h_8)
                .create();

        adapter = new BandwagonAdapter(bandwagonList);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(context, BandwagonDetailActivity.class);
                intent.putExtra("bandwagon", bandwagonList.get(position));
                startActivity(intent);
            }
        });
        adapter.setOnItemEditClickListener(new OnItemEditClickListener() {
            @Override
            public void onItemEditClick(int position) {
                showDialog(true, position);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelperCallback(this));
        helper.attachToRecyclerView(recyclerView);
    }

    private void deleteBandwagon() {
        for (Bandwagon bandwagon : deleteList) {
            DatabaseUtils.deleteBandwagon(this, bandwagon.getId());
        }
        deleteList.clear();
        updatePosition();
    }

    private void updatePosition() {
        for (int i = 0; i < bandwagonList.size(); i++) {
            bandwagonList.get(i).setPosition(i);
        }
    }

    @OnClick(R.id.fab_add)
    public void onViewClicked(View view) {
        showDialog(false, -1);
    }

    private void showDialog(final boolean isEdit, final int position) {
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title(isEdit ? R.string.edit_bandwagon : R.string.add_bandwagon)
                .customView(R.layout.dialog_bandwagon_add, true)
                .positiveText(android.R.string.ok)
                .negativeText(android.R.string.cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (isEdit) {
                            DatabaseUtils.updateBandwagon(context, new Bandwagon(bandwagonList.get(position).getId(), textTitle.getText().toString(), textVeId.getText().toString(), textApiKey.getText().toString()));
                        } else {
                            DatabaseUtils.addBandwagon(context, new Bandwagon(textTitle.getText().toString(), textVeId.getText().toString(), textApiKey.getText().toString()));
                        }
                        bandwagonList.clear();
                        bandwagonList.addAll(DatabaseUtils.getBandwagonList(context));
                        updatePosition();
                        adapter.notifyDataSetChanged();
                    }
                }).build();

        positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
        if (dialog.getCustomView() != null) //Disable inspection
            textTitle = dialog.getCustomView().findViewById(R.id.dialog_text_title);
        textTitle.addTextChangedListener(this);
        textVeId = dialog.getCustomView().findViewById(R.id.dialog_text_ve_id);
        textVeId.addTextChangedListener(this);
        textApiKey = dialog.getCustomView().findViewById(R.id.dialog_text_api_key);
        textApiKey.addTextChangedListener(this);
        if (isEdit) {
            textTitle.setText(bandwagonList.get(position).getTitle());
            textVeId.setText(bandwagonList.get(position).getVeId());
            textApiKey.setText(bandwagonList.get(position).getApiKey());
        }
        dialog.show();
        positiveAction.setEnabled(isEdit);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (snackbar != null) {
            snackbar.dismiss();
        }
        DatabaseUtils.updateSort(this, bandwagonList.get(fromPosition).getId(), bandwagonList.get(toPosition).getSort(), bandwagonList.get(toPosition).getId(), bandwagonList.get(fromPosition).getSort());

        int fromSort = bandwagonList.get(fromPosition).getSort();
        bandwagonList.get(fromPosition).setSort(bandwagonList.get(toPosition).getSort());
        bandwagonList.get(toPosition).setSort(fromSort);

        Collections.swap(bandwagonList, fromPosition, toPosition);
        updatePosition();
        adapter.notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemSwiped(int position) {
        deleteList.add(bandwagonList.get(position));

        bandwagonList.remove(position);
        adapter.notifyItemRemoved(position);
        snackbar = Snackbar.make(fabAdd, String.format(getResources().getString(R.string.removed), deleteList.size()), Snackbar.LENGTH_LONG).setAction(R.string.undo, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //排序
                Collections.sort(deleteList, new Comparator<Bandwagon>() {
                    @Override
                    public int compare(Bandwagon b1, Bandwagon b2) {
                        return b1.getPosition().compareTo(b2.getPosition());
                    }
                });
                for (int i = 0; i < deleteList.size(); i++) {
                    bandwagonList.add(deleteList.get(i).getPosition(), deleteList.get(i));
                    adapter.notifyItemInserted(deleteList.get(i).getPosition());
                }
                deleteList.clear();
            }
        }).addCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
                switch (event) {
                    case Snackbar.Callback.DISMISS_EVENT_MANUAL:
                        deleteBandwagon();
                        break;
                    case Snackbar.Callback.DISMISS_EVENT_SWIPE:
                        deleteBandwagon();
                        break;
                    case Snackbar.Callback.DISMISS_EVENT_TIMEOUT:
                        deleteBandwagon();
                        break;
                }
            }
        });
        snackbar.show();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_help:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(Constants.BANDWAGON_URL_HELP));
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
