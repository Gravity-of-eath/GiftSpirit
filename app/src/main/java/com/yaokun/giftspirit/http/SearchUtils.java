package com.yaokun.giftspirit.http;

import com.yaokun.giftspirit.inteface.Contast;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 姚 坤 on 2016/10/9.
 */
public class SearchUtils {
    private static SearchData searchdata;

    public static SearchData getSearchUtils() {
        if (searchdata == null) {
            searchdata = new Retrofit.Builder()
                    .baseUrl(Contast.BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(SearchData.class);
        }
        return searchdata;

    }
}
