package com.zhuwenhao.demo.movie;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhuwenhao.demo.R;

import java.util.List;

public class InTheatersAdapter extends BaseQuickAdapter<Subject, BaseViewHolder> {

    public InTheatersAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Subject item) {
        Glide.with(mContext).load(item.getImages().getLarge()).into((ImageView) helper.getView(R.id.img_subject_cover));
        helper.setText(R.id.text_title, item.getTitle());
        if (item.getRating().getAverage() == 0) {
            helper.setText(R.id.text_douban_rating, R.string.no_rating);
            helper.setVisible(R.id.text_rating, false);
        } else {
            helper.setText(R.id.text_douban_rating, R.string.douban_rating);
            helper.setVisible(R.id.text_rating, true);
            helper.setText(R.id.text_rating, String.valueOf(item.getRating().getAverage()));
        }
        StringBuilder sb = new StringBuilder();
        sb.append(item.getYear());
        sb.append(" / ");
        for (String genre : item.getGenres()) {
            sb.append(genre);
            sb.append(" ");
        }
        helper.setText(R.id.text_year_genres, sb.toString());
        sb = new StringBuilder();
        for (Subject.Director director : item.getDirectors()) {
            sb.append(director.getName());
            sb.append(" ");
        }
        helper.setText(R.id.text_directors, mContext.getString(R.string.item_directors, sb.toString()));
        sb = new StringBuilder();
        if (item.getCasts().size() > 0) {
            sb.append(mContext.getString(R.string.item_casts));
            for (Subject.Cast cast : item.getCasts()) {
                sb.append(cast.getName());
                sb.append(" ");
            }
        }
        helper.setText(R.id.text_casts, sb.toString());
    }
}