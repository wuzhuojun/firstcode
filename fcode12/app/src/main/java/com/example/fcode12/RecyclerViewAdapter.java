package com.example.fcode12;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wuzhuojun on 2016/8/3 0003.
 */
public class RecyclerViewAdapter extends UltimateViewAdapter<RecyclerViewAdapter.MyViewHolder> {
    private Context mContext;
    private List<UserInfoModel> mDatas;

    public RecyclerViewAdapter(Context context) {
        mContext = context;
        mDatas = new ArrayList<UserInfoModel>();
    }

    /**
     * 设置 item的布局
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.user_item, parent, false));
        return holder;
    }

    @Override
    public MyViewHolder newFooterHolder(View view) {
        return null;
    }

    @Override
    public MyViewHolder newHeaderHolder(View view) {
        return null;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent) {
        return null;
    }

    /**
     * item元素 与 数据绑定
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvTopicTitle.setText(mDatas.get(position).getNickname());
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getAdapterItemCount() {
        return 0;
    }

    @Override
    public long generateHeaderId(int position) {
        return 0;
    }

    public void addDataAll(List<UserInfoModel> list) {
        mDatas.addAll(list);
    }

    public void addDataItem(UserInfoModel item) {
        mDatas.add(item);
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.text_user_name)
        TextView tvTopicTitle;

        public MyViewHolder(View view)
        {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
