package com.example.fcode16.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fcode16.R;
import com.example.fcode16.model.MeiziModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wuzhuojun on 2016/8/4 0004.
 */
public class MeiziRecyViewAdapter extends RecyclerView.Adapter {
    List<MeiziModel> images;

    //画每个item
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MeiziViewHolder(view);
    }

    //画每个item的子元素
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MeiziViewHolder debounceViewHolder = (MeiziViewHolder) holder;
        MeiziModel image = images.get(position);
        Glide.with(holder.itemView.getContext()).load(image.url).into(debounceViewHolder.imageIv);
        debounceViewHolder.descriptionTv.setText(image.desc);
    }

    @Override
    public int getItemCount() {
        return images == null ? 0 : images.size();
    }

    public void setImages(List<MeiziModel> images) {
        this.images = images;
        notifyDataSetChanged();
    }

    static class MeiziViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageIv)
        ImageView imageIv;
        @BindView(R.id.descriptionTv)
        TextView descriptionTv;

        public MeiziViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
