package com.yaokun.giftspirit.http;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by 姚 坤 on 2016/9/20.
 */
public class NetUitls {
    public static boolean isnet(Activity context) {
        ConnectivityManager con = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
        boolean wifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        boolean internet = con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        if (!wifi && !internet) {
            return false;
        } else {
            return true;
        }
    }
}
