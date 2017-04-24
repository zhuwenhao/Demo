package com.zhuwenhao.demo.adapter;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhuwenhao.demo.R;
import com.zhuwenhao.demo.entity.Bandwagon;
import com.zhuwenhao.demo.listener.OnItemClickListener;
import com.zhuwenhao.demo.listener.OnItemEditClickListener;

import java.util.List;

public class BandwagonAdapter extends RecyclerView.Adapter<BandwagonAdapter.BandwagonViewHolder> {

    private List<Bandwagon> bandwagonList;

    private OnItemClickListener listener;

    private OnItemEditClickListener editListener;

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
        if (editListener != null) {
            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editListener.onItemEditClick(holder.getAdapterPosition());
                }
            });
        }
        holder.textNodeLocation.setText(bandwagonList.get(position).getNodeLocation());
        holder.textOs.setText(bandwagonList.get(position).getOs());
        holder.textIpAddresses.setText(bandwagonList.get(position).getIpAddresses());
    }

    @Override
    public int getItemCount() {
        return bandwagonList.size();
    }

    class BandwagonViewHolder extends RecyclerView.ViewHolder {

        View view;

        TextView textTitle;

        AppCompatImageView edit;

        TextView textNodeLocation;

        TextView textOs;

        TextView textIpAddresses;

        BandwagonViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            textTitle = (TextView) itemView.findViewById(R.id.text_title);
            edit = (AppCompatImageView) itemView.findViewById(R.id.edit);
            textNodeLocation = (TextView) itemView.findViewById(R.id.text_node_location);
            textOs = (TextView) itemView.findViewById(R.id.text_os);
            textIpAddresses = (TextView) itemView.findViewById(R.id.text_ip_addresses);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnItemEditClickListener(OnItemEditClickListener editListener) {
        this.editListener = editListener;
    }
}
