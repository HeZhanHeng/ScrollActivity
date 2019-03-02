package com.example.hezhanheng.scrollactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hezhanheng.scrollactivity.views.MyScrollView;

public class PersonCenterActivity extends AppCompatActivity implements MyScrollView.OnScrollListener, View.OnClickListener {
    private LinearLayout top_bg;
    private ImageView back;
    private TextView title;
    private ImageView shopping_cart;
    private MyScrollView scroller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_center);
        initView();
    }
    private void initView() {
        top_bg = (LinearLayout) findViewById(R.id.top_bg);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        title=(TextView)findViewById(R.id.title);
        shopping_cart = (ImageView) findViewById(R.id.shopping_cart);
        scroller = (MyScrollView) findViewById(R.id.scroller);
        top_bg.getBackground().setAlpha(0);
        scroller.setScrolListener(this);
    } 	@Override
    public void onScroll(int scrollY) {
        if(scrollY < 100){
            top_bg.getBackground().setAlpha(0);
            back.getBackground().setAlpha(255);
            shopping_cart.getBackground().setAlpha(255);
            title.setVisibility(View.INVISIBLE);
        }else if(scrollY >= 100 && scrollY < 860){
            top_bg.getBackground().setAlpha((scrollY-100)/3);
//            back.getBackground().setAlpha(255 - (scrollY-100)/3);
            shopping_cart.getBackground().setAlpha(255 - (scrollY-100)/3);

        }else{
            top_bg.getBackground().setAlpha(255);
            title.setVisibility(View.VISIBLE);
//        back.getBackground().setAlpha(0);
        shopping_cart.getBackground().setAlpha(0);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}
