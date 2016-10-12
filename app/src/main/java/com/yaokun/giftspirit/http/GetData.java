package com.yaokun.giftspirit.http;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 姚 坤 on 2016/9/27.
 */
public interface GetData {
    @GET("/majax.action?method=getGiftList")
    Call<String> getHomedata(@Query("pageno")String pageno);

    @POST("/majax.action?method=getGiftInfo")
    Call<String> getDetailData(@Query("id")String id);

}