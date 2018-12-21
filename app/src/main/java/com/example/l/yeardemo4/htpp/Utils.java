package com.example.l.yeardemo4.htpp;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Utils {
    public static String get(String url){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder().url(url).get().build();
        try {
            Response execute = okHttpClient.newCall(build).execute();
            return execute.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
