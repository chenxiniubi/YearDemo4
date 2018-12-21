package com.example.l.yeardemo4.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.l.yeardemo4.R;
import com.example.l.yeardemo4.adapter.MyListViewErAdapter;
import com.example.l.yeardemo4.adapter.MyListViewYiAdapter;
import com.example.l.yeardemo4.bean.Meau;
import com.example.l.yeardemo4.core.DataCall;
import com.example.l.yeardemo4.presenter.RequestPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DataCall{

    String url = "http://www.zhaoapi.cn/product/getCarts?uid=71";

    private ListView mListview01;
    private ListView mListview02;
    /**
     * 总价:0￥
     */
    private TextView mZongjia;
    private RequestPresenter requestPresenter;

    private List<Meau.DataBean> list = new ArrayList<>();
    private List<Meau.DataBean.ListBean> list1 = new ArrayList<>();
    private MyListViewYiAdapter adapter;
    private MyListViewErAdapter adapter1;
    private List<Meau.DataBean.ListBean> beans;
    private List<Meau.DataBean> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mListview01 = findViewById(R.id.listview01);
        mListview02 = findViewById(R.id.listview02);
        mZongjia = findViewById(R.id.zongjia);

        requestPresenter = new RequestPresenter(this);
        requestPresenter.getRequest(url);

        adapter = new MyListViewYiAdapter(this, list);
        adapter1 = new MyListViewErAdapter(this, list1);
        mListview01.setAdapter(adapter);
        mListview02.setAdapter(adapter1);

        mListview01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "点击了" + position, Toast.LENGTH_LONG).show();
                List<Meau.DataBean.ListBean> beans1 = data.get(position).getList();
                list1.clear();
                adapter1.notifyDataSetChanged();
                list1.addAll(beans1);
                adapter.notifyDataSetChanged();
                adapter1.notifyDataSetChanged();
            }
        });
//        加
        adapter1.setOnNumListener(new MyListViewErAdapter.OnNumListener() {
            @Override
            public void onNum() {
                calculatePrice(data);
            }
        });
    }

    @Override
    public void requestSuccess(Meau meau) {
        data = meau.getData();

        Log.i("abc", data.toString());
        calculatePrice(data);

        list.addAll(data);
        adapter.notifyDataSetChanged();
        beans = data.get(1).getList();
        list1.addAll(beans);
        adapter1.notifyDataSetChanged();

        Toast.makeText(this, "成功" + meau.getData(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void requestFail(Meau meau) {
        Toast.makeText(this, "失败", Toast.LENGTH_LONG).show();
    }

    /**
     * @author dingtao
     * @date 2018/12/18 7:01 PM
     * 计算总价格
     */
    private void calculatePrice(List<Meau.DataBean> shopList){
        double totalPrice=0;
        int totalNum = 0;
        for (int i = 0; i < shopList.size(); i++){
            //循环的商家
          Meau.DataBean shop = shopList.get(i);
            for (int j = 0; j < shop.getList().size(); j++) {
                Meau.DataBean.ListBean goods = shop.getList().get(j);
                //计算价格
                totalPrice = totalPrice + goods.getNum() * goods.getPrice();
                totalNum+=goods.getNum();//计数
            }
        }
        mZongjia.setText("价格："+totalPrice+"个数:"+totalNum);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        requestPresenter.unBindCall();
    }
}
