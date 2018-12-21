package com.example.l.yeardemo4.core;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.l.yeardemo4.R;


public class MyView extends LinearLayout implements View.OnClickListener {

    private TextView mAddBtn,mSubBtn;
    private TextView mNumText;
    private MyViewListener myViewListener;

    public MyView(Context context) {
        super(context);
        initView();
    }

    public MyView(Context context,AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyView(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    private void initView(){
        //加载layout布局，第三个参数ViewGroup一定写成this
        View view = View.inflate(getContext(), R.layout.car_add_sub_layout,this);

        mAddBtn = view.findViewById(R.id.btn_add);
        mSubBtn = view.findViewById(R.id.btn_sub);
        mNumText = view.findViewById(R.id.text_number);
        mAddBtn.setOnClickListener(this);
        mSubBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int number = Integer.parseInt(mNumText.getText().toString());

        switch (v.getId()){
            case R.id.btn_add:
                number++;
                mNumText.setText(number+"");
                break;
            case R.id.btn_sub:
                if (number==0){
                    Toast.makeText(getContext(),"数量不能小于0",Toast.LENGTH_LONG).show();
                    return;
                }
                number--;
                mNumText.setText(number+"");
                break;
        }
        if (myViewListener != null){
            myViewListener.myView(number);
        }
    }
    public void setCount(int count){
        mNumText.setText(count+"");
    }
    public void setMyViewListener(MyViewListener myViewListener) {
        this.myViewListener = myViewListener;
    }

    public interface MyViewListener{
        void myView(int count);
    }
}
