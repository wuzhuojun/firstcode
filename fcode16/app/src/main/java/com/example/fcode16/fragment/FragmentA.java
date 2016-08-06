package com.example.fcode16.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fcode16.R;
import com.example.fcode16.adapter.MeiziRecyViewAdapter;
import com.example.fcode16.model.MeiziModel;
import com.example.fcode16.model.MeiziResult;
import com.example.fcode16.model.MeiziResultToItemsMapper;
import com.example.fcode16.network.Network;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wuzhuojun on 2016/8/3 0003.
 */
public class FragmentA extends BaseFragment {
    private int page = 0;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.meizi_recy_view)
    RecyclerView meiziView;

    MeiziRecyViewAdapter adapter = new MeiziRecyViewAdapter();

    Observer<List<MeiziModel>> observer = new Observer<List<MeiziModel>>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(getActivity(), R.string.loading_failed, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNext(List<MeiziModel> images) {
            swipeRefreshLayout.setRefreshing(false);
            adapter.setImages(images);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //返回在本Fragment实现类所 画好的 view
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        ButterKnife.bind(this, view);

        meiziView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        meiziView.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setEnabled(false);

        return view;
    }

    //查看下一页数据
    @OnClick(R.id.nextPageBt)
    void ShowNextPage()
    {
        loadPage(++page);
    }

    private void loadPage(int page) {
        swipeRefreshLayout.setRefreshing(true);
        unsubscribe();
        subscription = Network.getGankApi()
                .getBeauties(10, page)
                .map(MeiziResultToItemsMapper.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
