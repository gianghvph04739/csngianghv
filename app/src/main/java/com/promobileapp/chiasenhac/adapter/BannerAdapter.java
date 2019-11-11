package com.promobileapp.chiasenhac.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.promobileapp.chiasenhac.R;
import com.promobileapp.chiasenhac.base.GlideApp;
import com.promobileapp.chiasenhac.model.Banner;

import java.util.ArrayList;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.RecyclerViewHolder> {

    private Context context;
    private ArrayList<Banner> lstBanner;
    private BannerOnClickListener listener;

    public BannerAdapter(Context context, ArrayList<Banner> lstBanner, BannerOnClickListener listener) {
        this.context = context;
        this.lstBanner = lstBanner;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_banner, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Banner item = lstBanner.get(position);
        GlideApp.with(context).
                load(item.thumbNail).
                into(holder.imgThumb);
        holder.tvName.setText(item.album);
        holder.tvQuality.setText(item.quality);
        holder.tvArtist.setText(item.single);
    }

    @Override
    public int getItemCount() {
        return lstBanner.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView imgThumb;
        TextView tvName;
        TextView tvArtist;
        TextView tvQuality;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            imgThumb = (ImageView) itemView.findViewById(R.id.img_banner);
            tvArtist = (TextView) itemView.findViewById(R.id.tv_artist);
            tvQuality = (TextView) itemView.findViewById(R.id.tv_quality);
            tvName = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

    public interface BannerOnClickListener {
        void onBannerItemClick(int position);
    }
}
