package com.example.l.yeardemo4.presenter;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.example.l.yeardemo4.activity.MainActivity;
import com.example.l.yeardemo4.bean.Meau;
import com.example.l.yeardemo4.core.DataCall;
import com.example.l.yeardemo4.model.RequestModel;

public class RequestPresenter {
    private DataCall dataCall;

    public RequestPresenter(DataCall dataCall) {
        this.dataCall = dataCall;
    }
    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            Meau meau = (Meau)msg.obj;
            if (meau.getCode()==0){
                dataCall.requestSuccess(meau);
            }else {
                dataCall.requestFail(meau);
            }
        }
    };
    public void getRequest(final String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Meau data = RequestModel.getData(url);
                Message message = handler.obtainMessage();
                Log.i("aaa1",data.toString());
                message.obj = data;
                handler.sendMessage(message);
            }
        }).start();
    }
    public void unBindCall(){
        this.dataCall = dataCall;
    }
}
