package com.widget.simplebanner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hans
 * date: 2018/3/14 19:03.
 * e-mail: hxxx1992@163.com
 */

public class SimpleBanner extends FrameLayout {


    private List<ImageView> imageList;
    private List<String> imageUrlList;

    private ViewPager viewPager;

    private TextView textView;

    private LinearLayout linearLayout;
    private ImageLoader imageLoader;

    private BannerPagerAdapter bannerPagerAdapter;


    public SimpleBanner(@NonNull Context context) {
        this(context, null);
    }

    public SimpleBanner(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleBanner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    public void initBanner(List<String> imageUrlList) {
        this.imageUrlList = imageUrlList;
        initData();
    }

    private void initView() {
        Log.e("SB", "initView");
        if (getChildCount() <= 0) {
            View.inflate(getContext(), R.layout.simple_banner_layout, this);
            viewPager = findViewById(R.id.sb_viewpager);
            textView = findViewById(R.id.sb_title);
            linearLayout = findViewById(R.id.sb_point_layout);
        }
        imageList = new ArrayList<>();
        bannerPagerAdapter = new BannerPagerAdapter(imageList);
    }

    private int lastPosition = 0;

    private void initData() {

        for (int i = 0; i < 15; i++) {

            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            if (imageLoader != null) {
                imageLoader.loadImage(imageUrlList.get(i), imageView);
            }

            imageList.add(imageView);

            PointView pv = new PointView(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DensityUtils
                    .dp2px(getContext(), 10), DensityUtils.dp2px(getContext(),
                    10));

            //除第一个以外，其他小白点都需要设置左边距
            if (i != 0) {
                layoutParams.leftMargin = DensityUtils.dp2px(getContext(), 10 / 2);
                pv.setEnabled(false);
            }

            pv.setLayoutParams(layoutParams);
            linearLayout.addView(pv);
        }


        viewPager.setAdapter(bannerPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((PointView) (linearLayout.getChildAt(lastPosition % imageList.size()))).setDefaultColor();
                ((PointView) (linearLayout.getChildAt(position % imageList.size()))).setChangeColor();
                lastPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imageList.size());

    }

    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
        if (imageLoader == null) {
            throw new IllegalArgumentException("图片加载器不能为空");
        }
        this.imageLoader = imageLoader;
        if (imageUrlList != null) {
            int imageSize = imageUrlList.size();
            for (int i = 0; i < imageSize; i++) {
                imageLoader.loadImage(imageUrlList.get(i), imageList.get(i));
            }
        }
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        Log.e("SB", "bannerPagerAdapter:" + (bannerPagerAdapter == null));
        bannerPagerAdapter.setOnItemClickListener(onItemClickListener);
    }
}
