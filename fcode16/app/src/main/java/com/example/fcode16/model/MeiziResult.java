package com.example.fcode16.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wuzhuojun on 2016/8/4 0004.
 */
public class MeiziResult {
    public boolean error;
    public @SerializedName("results")
    List<MeiziModel> beauties;
}
