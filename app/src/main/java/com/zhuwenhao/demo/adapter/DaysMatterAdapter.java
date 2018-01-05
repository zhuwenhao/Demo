package com.zhuwenhao.demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhuwenhao.demo.R;
import com.zhuwenhao.demo.entity.DaysMatter;
import com.zhuwenhao.demo.listener.OnItemClickListener;

import java.util.List;

public class DaysMatterAdapter extends RecyclerView.Adapter<DaysMatterAdapter.DaysMatterViewHolder> {

    private Context context;

    private List<DaysMatter> daysMatterList;

    private OnItemClickListener onItemClickListener;

    public DaysMatterAdapter(Context context, List<DaysMatter> daysMatterList) {
        this.context = context;
        this.daysMatterList = daysMatterList;
    }

    @Override
    public DaysMatterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_days_matter, parent, false);
        return new DaysMatterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DaysMatterViewHolder holder, int position) {
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(holder.getAdapterPosition());
                }
            });
        }

        holder.textWeek.setText(daysMatterList.get(position).getTargetDate().dayOfWeek().getAsShortText());
        holder.textWeek.setBackgroundResource(daysMatterList.get(position).getBackgroundRes());
        holder.textTitle.setText(daysMatterList.get(position).getTitle(context));
        holder.textTargetDate.setText(daysMatterList.get(position).getTargetDateText());
        holder.textDays.setText(String.valueOf(daysMatterList.get(position).getDays()));
    }

    @Override
    public int getItemCount() {
        return daysMatterList.size();
    }

    class DaysMatterViewHolder extends RecyclerView.ViewHolder {

        TextView textWeek;

        TextView textTitle;

        TextView textTargetDate;

        TextView textDays;

        DaysMatterViewHolder(View itemView) {
            super(itemView);
            textWeek = itemView.findViewById(R.id.text_week);
            textTitle = itemView.findViewById(R.id.text_title);
            textTargetDate = itemView.findViewById(R.id.text_target_date);
            textDays = itemView.findViewById(R.id.text_days);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
