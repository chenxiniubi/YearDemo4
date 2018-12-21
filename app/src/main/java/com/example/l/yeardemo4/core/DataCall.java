package com.example.l.yeardemo4.core;


import com.example.l.yeardemo4.bean.Meau;

public interface DataCall {
    void requestSuccess(Meau meau);
    void requestFail(Meau meau);
}
