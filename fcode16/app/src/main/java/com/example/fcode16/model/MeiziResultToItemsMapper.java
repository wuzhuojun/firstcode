// (c)2016 Flipboard Inc, All Rights Reserved.

package com.example.fcode16.model;

import java.util.List;

import rx.functions.Func1;

/**
 * 将网络返回的JSON数据 转化为界面中item格式的数据
 */
public class MeiziResultToItemsMapper implements Func1<MeiziResult, List<MeiziModel>> {
    private static MeiziResultToItemsMapper INSTANCE = new MeiziResultToItemsMapper();

    private MeiziResultToItemsMapper() {
    }

    public static MeiziResultToItemsMapper getInstance() {
        return INSTANCE;
    }

    /**
     * 这里可以做格式转化
     * 将网络数据的字段格式 进行整理 转化为 易于在界面上展示的 list格式
     * */
    @Override
    public List<MeiziModel> call(MeiziResult gankBeautyResult) {
        List<MeiziModel> gankBeauties = gankBeautyResult.beauties;

        return gankBeauties;
    }
}
