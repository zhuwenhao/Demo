package com.zhuwenhao.demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhuwenhao.demo.R;
import com.zhuwenhao.demo.entity.Bandwagon;
import com.zhuwenhao.demo.listener.OnItemClickListener;

import java.util.List;

public class BandwagonAdapter extends RecyclerView.Adapter<BandwagonAdapter.BandwagonViewHolder> {

    private List<Bandwagon> bandwagonList;

    private OnItemClickListener listener;

    public BandwagonAdapter(List<Bandwagon> bandwagonList) {
        this.bandwagonList = bandwagonList;
    }

    @Override
    public BandwagonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bandwagon, parent, false);
        return new BandwagonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BandwagonViewHolder holder, int position) {
        if (listener != null) {
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(holder.getAdapterPosition());
                }
            });
        }
        holder.textTitle.setText(bandwagonList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return bandwagonList.size();
    }

    class BandwagonViewHolder extends RecyclerView.ViewHolder {

        View view;

        TextView textTitle;

        BandwagonViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            textTitle = (TextView) itemView.findViewById(R.id.text_title);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
