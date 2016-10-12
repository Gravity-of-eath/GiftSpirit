package com.yaokun.giftspirit.http;

import com.google.gson.Gson;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.Request;

/**
 * Created by 姚 坤 on 2016/9/27.
 */
public class HttpUtils {
    public static void GetData(String url, final DataBack dataBack) {
        if (url != null) {
            OkHttpClient client = new OkHttpClient();
            Request request=new Request.Builder().url(url).build();
            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onFailure(Call call, IOException e) {
                    dataBack.DataBack(null);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    dataBack.DataBack(response.body().string());
                }
            });

        }else {
            throw new IllegalArgumentException("url is null ? "+url);
        }
    }

}
