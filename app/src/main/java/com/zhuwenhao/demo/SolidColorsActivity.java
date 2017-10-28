package com.zhuwenhao.demo;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.afollestad.materialdialogs.util.DialogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SolidColorsActivity extends AppCompatActivity implements ColorChooserDialog.ColorCallback {

    @BindView(R.id.img_colors)
    ImageView imgColors;

    @BindView(R.id.fab_edit)
    FloatingActionButton fabEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solid_colors);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

    }

    private void hideBars() {
        fabEdit.hide();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @OnClick({R.id.img_colors, R.id.fab_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_colors:
                if (getWindow().getDecorView().getSystemUiVisibility() == View.SYSTEM_UI_FLAG_VISIBLE) {
                    hideBars();
                } else {
                    fabEdit.show();
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                }
                break;
            case R.id.fab_edit:
                new ColorChooserDialog.Builder(this, R.string.solid_colors)
                        .titleSub(R.string.solid_colors)
                        .preselect(DialogUtils.resolveColor(this, R.attr.colorPrimary))
                        .show(this);
                break;
        }
    }

    @Override
    public void onColorSelection(@NonNull ColorChooserDialog dialog, @ColorInt int selectedColor) {
        imgColors.setBackgroundColor(selectedColor);
    }

    @Override
    public void onColorChooserDismissed(@NonNull ColorChooserDialog dialog) {

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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        hideBars();
    }
}
