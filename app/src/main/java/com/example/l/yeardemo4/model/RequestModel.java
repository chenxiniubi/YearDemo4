package com.example.l.yeardemo4.model;

import android.view.Menu;

import com.example.l.yeardemo4.bean.Meau;
import com.example.l.yeardemo4.htpp.Utils;
import com.google.gson.Gson;

public class RequestModel {
    public static Meau getData(String url){
        String s = Utils.get(url);
        Gson gson = new Gson();
        Meau meau = gson.fromJson(s,Meau.class);
        return meau;
    }
}
