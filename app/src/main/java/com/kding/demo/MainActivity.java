package com.kding.demo;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nineoldandroids.view.ViewHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private android.support.v4.view.ViewPager mViewPager;
    private TextView text1;
    private TextView text2;
    private TextView text3;
    private View page1;
    private View page2;
    private View page3;
//    private int[] mImgIds = new int[]{R.drawable.guide_image1,
//            R.drawable.guide_image2, R.drawable.guide_image3};
    private List<View> views = new ArrayList<View>();

    public boolean isShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        CuteProgress cuteProgress = (CuteProgress)findViewById(R.id.cute);
        cuteProgress.startAnim();

        final TextView block = (TextView) findViewById(R.id.block);


//        final PackageManager packageManager = getPackageManager();
//
//        findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                packageManager.setApplicationEnabledSetting("com.cmt.yi.yimama", PackageManager.COMPONENT_ENABLED_STATE_DEFAULT,PackageManager.DONT_KILL_APP);
//            }
//        });
//        findViewById(R.id.hide).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                packageManager.setApplicationEnabledSetting("com.cmt.yi.yimama", PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
//            }
//        });
        block.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,Main4Activity.class));
//                startActivity(new Intent(MainActivity.this,Main3Activity.class));
//                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
//                intent.putExtra("x", ViewHelper.getX(block));
//                intent.putExtra("y", ViewHelper.getY(block));
//                intent.putExtra("w", block.getWidth());
//                intent.putExtra("h", block.getHeight());
//                startActivity(intent);
            }
        });

//        initData();

        mViewPager = (android.support.v4.view.ViewPager) findViewById(R.id.id_viewpager);

        page1 = LayoutInflater.from(this).inflate(R.layout.page1, null);
        text1= (TextView)page1.findViewById(R.id.text1);
        page2 = LayoutInflater.from(this).inflate(R.layout.page2, null);
        text2= (TextView) page2.findViewById(R.id.text2);
        page3 = LayoutInflater.from(this).inflate(R.layout.page3, null);
        text3= (TextView)page3.findViewById(R.id.text3);

        views.add(page1);
        views.add(page2);
        views.add(page3);
//        mViewPager.addView(page1);
//        mViewPager.addView(page2);
//        mViewPager.addView(page3);

        
        mViewPager.setPageTransformer(true,new DepthPageTransformer());
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(views.get(position));
                return views.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View)object);
            }
        });

    }

    public class DepthPageTransformer implements android.support.v4.view.ViewPager.PageTransformer {
        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        public void transformPage(View page, float position) {
            int pageWidth = page.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                if (page.equals(page1)){
                    text1.setAlpha(1);
                }else if (page.equals(page2)){
                    text2.setAlpha(1);
                }else if (page.equals(page3)){
                    text3.setAlpha(1);
                }
            } else if (position<=0){
                if (page.equals(page1)){
                    text1.setAlpha(Math.abs(1+position));
                }else if (page.equals(page2)){
                    text2.setAlpha(Math.abs(1+position));
                }else if (page.equals(page3)){
                    text3.setAlpha(Math.abs(1+position));
                }
            }else if (position <= 1) { // (0,1]
                if (page.equals(page1)){
                    text1.setAlpha(Math.abs(1-position));
                }else if (page.equals(page2)){
                    text2.setAlpha(Math.abs(1-position));
                }else if (page.equals(page3)){
                    text3.setAlpha(Math.abs(1-position));
                }
            } else { // (1,+Infinity]
                if (page.equals(page1)){
                    text1.setAlpha(1);
                }else if (page.equals(page2)){
                    text2.setAlpha(1);
                }else if (page.equals(page3)){
                    text3.setAlpha(1);
                }
            }
        }
    }

//    private void initData() {
//        for (int imgId : mImgIds) {
//            ImageView imageView = new ImageView(getApplicationContext());
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setImageResource(imgId);
//            mImageViews.add(imageView);
//        }
//    }
}
