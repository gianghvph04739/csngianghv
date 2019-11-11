package com.promobileapp.chiasenhac.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.promobileapp.chiasenhac.R;
import com.promobileapp.chiasenhac.base.GlideApp;
import com.promobileapp.chiasenhac.model.TopChart;
import com.promobileapp.chiasenhac.utils.AppConstants;

import java.util.ArrayList;

public class TopChartAdaper extends RecyclerView.Adapter<TopChartAdaper.TopChartViewHolder> {

    private Context context;
    private OnTopChartClickListener listener;
    private ArrayList<TopChart> lstChart = new ArrayList<>();

    public TopChartAdaper(Context context, OnTopChartClickListener listener) {
        this.context = context;
        this.listener = listener;
        lstChart.add(new TopChart(R.drawable.top_chart_vn, context.getString(R.string.viet_nam), AppConstants.CHART_VN));
        lstChart.add(new TopChart(R.drawable.top_chart_us, context.getString(R.string.us), AppConstants.CHART_VN));
        lstChart.add(new TopChart(R.drawable.top_chart_kr, context.getString(R.string.korean), AppConstants.CHART_VN));
        lstChart.add(new TopChart(R.drawable.top_chart_jp, context.getString(R.string.japan), AppConstants.CHART_VN));
    }

    @NonNull
    @Override
    public TopChartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_chart, parent, false);
        return new TopChartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopChartViewHolder holder, int position) {
        TopChart item = lstChart.get(position);
        GlideApp.with(context)
                .load(item.resource)
                .into(holder.imgThumb);
        holder.tvTitle.setText(item.national);
    }

    @Override
    public int getItemCount() {
        return lstChart.size();
    }

    public class TopChartViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgThumb;
        public TextView tvTitle;

        public TopChartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgThumb = (ImageView) itemView.findViewById(R.id.imgThumb);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }

    public interface OnTopChartClickListener {

    }
}
