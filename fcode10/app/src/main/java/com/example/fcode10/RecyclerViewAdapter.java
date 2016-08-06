package com.example.fcode10;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wuzhuojun on 2016/8/3 0003.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context mContext;
    private List<TopicModel> mDatas;

    public RecyclerViewAdapter(Context context) {
        mContext = context;
        mDatas = new ArrayList<TopicModel>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.topic_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvTopicTitle.setText(mDatas.get(position).getTitle());

        String url = mDatas.get(position).getPic_path();
        if(null != url)
        {
            Uri uri = Uri.parse(url);
            holder.imgView.setImageURI(uri);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addData(List<TopicModel> list) {
        mDatas.addAll(list);
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.text_topic_title)
        TextView tvTopicTitle;

        @BindView(R.id.image_view)
        SimpleDraweeView imgView;

        public MyViewHolder(View view)
        {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
