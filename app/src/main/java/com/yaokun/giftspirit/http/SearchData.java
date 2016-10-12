package com.yaokun.giftspirit.http;

import com.yaokun.giftspirit.bean.GiftBean;
import com.yaokun.giftspirit.bean.SearchDataBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**&key
 * Created by 姚 坤 on 2016/10/9.
 */
public interface SearchData {
    @FormUrlEncoded()//调用自己编码将查询关键字编码
    @POST("/majax.action?method=searchGift")
    Call<GiftBean>getSearchData(@Field("key") String key);
}
