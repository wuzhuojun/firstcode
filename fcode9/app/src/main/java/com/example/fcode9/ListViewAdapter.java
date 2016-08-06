package com.example.fcode9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wuzhuojun on 2016/8/3 0003.
 */
public class ListViewAdapter extends BaseAdapter {
    protected ArrayList<TopicModel> mDatas = new ArrayList<TopicModel>();
    Context mContext;

    public ListViewAdapter(Context context)
    {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.topic_item, null);
            holder = new ViewHolder(convertView);

            //将画好的item缓存起来，滑动的时候优先加载缓存，提高list的性能
            convertView.setTag(holder);
        }else {
            //从缓存里面直接取出来
            holder = (ViewHolder)convertView.getTag();
        }

        holder.mTopicTitle.setText(mDatas.get(position).getTitle());

        return convertView;
    }


    static class ViewHolder{
        @BindView(R.id.text_topic_title)
        TextView mTopicTitle;

        //注意需要绑定后才能使用 Bf注解
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
