// (c)2016 Flipboard Inc, All Rights Reserved.

package com.example.fcode16.network;

import com.example.fcode16.model.MeiziResult;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GankApi {
    @GET("data/福利/{number}/{page}")
    Observable<MeiziResult> getBeauties(@Path("number") int number, @Path("page") int page);
}
