package com.example.hezhanheng.scrollactivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener,ViewPagerListener {
    private ViewPager vp;
    private int[] imageIdArray;//图片资源的数组
    private List<View> viewList;//图片资源的集合
    private ViewGroup vg;//放置圆点
    //实例化原点View
    private ImageView iv_point;
    private ImageView[] ivPointArray;
    private boolean isLooper;
    //最后一页的按钮
    private ImageButton ib_start;
    //
    private String[] mTitles = new String[]{"消息", "通讯录", "发现", "个人中心"};
    private SimpleViewPagerIndicator mIndicator;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private TabFragment[] mFragments = new TabFragment[mTitles.length];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListenerConstans.mQunZuPager = this;
        //加载ViewPager
        initViewPager();
        //加载底部圆点
        initPoint();
        //修改添加设置ViewPager的当前页，为了保证左右轮播
        vp.setCurrentItem(0);
        initViews();
        initDatas();
        initEvents();
        //开启一个线程，用于循环
        new Thread(new Runnable() {
            @Override
            public void run() {
                isLooper = true;
                while (isLooper) {
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //这里是设置当前页的下一页
                            vp.setCurrentItem(vp.getCurrentItem() + 1);
                        }
                    });
                }
            }
        }).start();

    }

    private void initPoint() {
        //这里实例化LinearLayout
        vg = (ViewGroup) findViewById(R.id.guide_ll_point);
        //根据ViewPager的item数量实例化数组
        ivPointArray = new ImageView[viewList.size()];
        //循环新建底部圆点ImageView，将生成的ImageView保存到数组中
        int size = viewList.size();
        for (int i = 0; i < size; i++) {
            iv_point = new ImageView(this);
            iv_point.setLayoutParams(new ViewGroup.LayoutParams(20, 20));
            iv_point.setPadding(30, 0, 30, 0);//left,top,right,bottom
            ivPointArray[i] = iv_point;
            //第一个页面需要设置为选中状态，这里采用两张不同的图片
            if (i == 0) {
                iv_point.setBackgroundResource(R.drawable.full_holo);
            } else {
                iv_point.setBackgroundResource(R.drawable.empty_holo);
            }
            //将数组中的ImageView加入到ViewGroup
            vg.addView(ivPointArray[i]);
        }
    }

    /**
     * 加载图片ViewPager
     */
    private void initViewPager() {
        vp = (ViewPager) findViewById(R.id.guide_vp);
        //实例化图片资源
        imageIdArray = new int[]{R.drawable.nba1, R.drawable.nba2, R.drawable.nba3};
        viewList = new ArrayList<>();
        //获取一个Layout参数，设置为全屏
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        //循环创建View并加入到集合中
        int len = imageIdArray.length;
        for (int i = 0; i < len; i++) {
            //new ImageView并设置全屏和图片资源
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setBackgroundResource(imageIdArray[i]);

            //将ImageView加入到集合中
            viewList.add(imageView);
        }

        //View集合初始化好后，设置Adapter
        vp.setAdapter(new GuidePageAdapter(viewList));
        //设置滑动监听
        vp.setOnPageChangeListener(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 滑动后的监听
     *
     * @param position
     */
    @Override
    public void onPageSelected(int position) {

        //修改全部的position长度
        int newPosition = position % viewList.size();

        //循环设置当前页的标记图
        int length = imageIdArray.length;
        for (int i = 0; i < length; i++) {
            ivPointArray[newPosition].setBackgroundResource(R.drawable.full_holo);
            if (newPosition != i) {
                ivPointArray[i].setBackgroundResource(R.drawable.empty_holo);
            }
        }

//        //循环设置当前页的标记图
//        int length = imageIdArray.length;
//        for (int i = 0;i<length;i++){
//            ivPointArray[position].setBackgroundResource(R.mipmap.full_holo);
//            if (position != i){
//                ivPointArray[i].setBackgroundResource(R.mipmap.empty_holo);
//            }
//        }

        //判断是否是最后一页，若是则显示按钮
//        if (position == imageIdArray.length - 1){
//            ib_start.setVisibility(View.VISIBLE);
//        }else {
//            ib_start.setVisibility(View.GONE);
//        }
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onDestroy() {// 注意结束的时候关闭线程
        isLooper = false;
        super.onDestroy();
    }

    private void initEvents() {
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                Log.e("page====", position + "");
            }
            @Override
            public void onPageScrolled(int position, float positionOffset,

                                       int positionOffsetPixels)

            {

                mIndicator.scroll(position, positionOffset);

            }


            @Override

            public void onPageScrollStateChanged(int state)

            {


            }

        });


    }


    private void initDatas()

    {

        mIndicator.setTitles(mTitles);

        for (int i = 0; i < mTitles.length; i++) {
            mFragments[i] = TabFragment.newInstance(mTitles[i]);
        }
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())

        {

            @Override

            public int getCount()

            {

                return mTitles.length;

            }


            @Override

            public Fragment getItem(int position)
            {
                return mFragments[position];

            }


        };


        mViewPager.setAdapter(mAdapter);

        mViewPager.setCurrentItem(0);

    }


    private void initViews()

    {
        vp=(ViewPager)findViewById(R.id.guide_vp) ;
        mIndicator = (SimpleViewPagerIndicator) findViewById(R.id.id_stickynavlayout_indicator);
        mViewPager = (ViewPager) findViewById(R.id.id_stickynavlayout_viewpager);



		/*

		RelativeLayout ll = (RelativeLayout) findViewById(R.id.id_stickynavlayout_topview);

		TextView tv = new TextView(this);

		tv.setText("我的动态添加的");

		tv.setBackgroundColor(0x77ff0000);

		ll.addView(tv, new RelativeLayout.LayoutParams(

				RelativeLayout.LayoutParams.MATCH_PARENT, 600));

		*/

    }


    @Override

    public void setCurrentItem(int page) {

        mViewPager.setCurrentItem(page);

    }

    public class GuidePageAdapter extends PagerAdapter {

        private List<View> viewList;

        public GuidePageAdapter(List<View> viewList) {
            super();
            this.viewList = viewList;
        }

        /**
         * @return 返回页面的个数
         */
        @Override
        public int getCount() {
             if (viewList != null) {
            return viewList.size();
                //第一处修改，设置轮播最大值，等于无限循环(有问题，child不能移除)
//                return Integer.MAX_VALUE;
            }
            return 0;
        }

        /**
         * 判断对象是否生成界面
         *
         * @param view
         * @param object
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * 初始化position位置的界面
         *
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            //第二处修改，当前要显示的数据索引为集合长度
            int newPosition = position % viewList.size();
//            container.removeView(viewList.get(position));
            container.addView(viewList.get(newPosition));
            return viewList.get(newPosition);

//        container.addView(viewList.get(position));
//        return viewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            //第三处修改，移除的索引为集合的长度
            int newPosition = position % viewList.size();
            container.removeView(viewList.get(newPosition));

//        container.removeView(viewList.get(position));
        }
    }
}


