package com.app.coolweather.util;

/**
 * Created by Administrator on 2016/10/10.
 */

public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
