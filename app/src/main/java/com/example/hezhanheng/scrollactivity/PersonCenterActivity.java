package com.example.hezhanheng.scrollactivity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hezhanheng.scrollactivity.views.MyScrollView;

public class PersonCenterActivity extends AppCompatActivity implements MyScrollView.OnScrollListener, View.OnClickListener {
    private RelativeLayout top_bg;
    private ImageView back,back2;
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
        top_bg = (RelativeLayout) findViewById(R.id.top_bg);
        back = (ImageView) findViewById(R.id.back);
        back2=(ImageView)  findViewById(R.id.back2);
        back.setOnClickListener(this);
        title=(TextView)findViewById(R.id.title);
        shopping_cart = (ImageView) findViewById(R.id.shopping_cart);
        scroller = (MyScrollView) findViewById(R.id.scroller);
        top_bg.getBackground().setAlpha(255);
        scroller.setScrolListener(this);
    } 	@Override
    public void onScroll(int scrollY) {
        if(scrollY < 100){
            back.setVisibility(View.VISIBLE);
            back2.setVisibility(View.GONE);
            top_bg.getBackground().setAlpha(255);
            top_bg.setBackgroundColor(getResources().getColor(R.color.blue_deep));
//            back.getBackground().setAlpha(255);
//            shopping_cart.getBackground().setAlpha(255);
            title.setVisibility(View.INVISIBLE);
        }else if(scrollY >= 100 && scrollY < 500){
            back.setVisibility(View.VISIBLE);
            back2.setVisibility(View.GONE);
            top_bg.setBackgroundColor(getResources().getColor(R.color.blue_deep));
            top_bg.getBackground().setAlpha(255-(scrollY-100)/3);
            title.setVisibility(View.INVISIBLE);
//            back.getBackground().setAlpha(255 - (scrollY-100)/3);
//            shopping_cart.getBackground().setAlpha(255 - (scrollY-100)/3);

        }else{
            back.setVisibility(View.GONE);
            back2.setVisibility(View.VISIBLE);
            top_bg.getBackground().setAlpha(0);
            top_bg.setBackgroundColor(Color.rgb(255, 255, 255));
            title.setVisibility(View.VISIBLE);
//            top_bg.setBackgroundColor(getResources().getColor(R.color.white));
//        back.getBackground().setAlpha(0);
//        shopping_cart.getBackground().setAlpha(0);
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
