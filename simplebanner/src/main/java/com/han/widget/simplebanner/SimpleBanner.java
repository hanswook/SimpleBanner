package com.han.widget.simplebanner;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.han.widget.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hans
 * date: 2018/3/14 19:03.
 * e-mail: hxxx1992@163.com
 */

public class SimpleBanner extends FrameLayout {


    private List<BannerView> bannerList;
    private List<String> imageUrlList, bannerTitleList;
    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private ImageLoader imageLoader;
    private BannerPagerAdapter bannerPagerAdapter;
    private int lastPosition = 0;


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


    public void initBanner(List<String> imageUrlList, List<String> bannerTitleList) {
        this.imageUrlList = imageUrlList;
        this.bannerTitleList = bannerTitleList;
        if (imageUrlList == null || bannerTitleList == null || imageUrlList.size() == 0 || bannerTitleList
                .size() == 0) {
            throw new IllegalArgumentException("传入图片地址或广告内容不能为空");
        }

        if (imageUrlList.size() != bannerTitleList.size()) {
            throw new IllegalArgumentException("传入图片地址或广告内容数量必须一致");
        }
        initData();
    }

    private void initView() {
        if (getChildCount() <= 0) {
            View.inflate(getContext(), R.layout.simple_banner_layout, this);
            viewPager = findViewById(R.id.sb_viewpager);
            linearLayout = findViewById(R.id.sb_point_layout);
        }
        bannerList = new ArrayList<>();
        bannerPagerAdapter = new BannerPagerAdapter(bannerList);
    }


    private void initData() {
        for (int i = 0; i < imageUrlList.size(); i++) {
            BannerView bannerView = new BannerView(getContext());
            if (imageLoader != null) {
                imageLoader.loadImage(imageUrlList.get(i), bannerView.getImage());
            }
            bannerView.getTextView().setText(bannerTitleList.get(i));
            PointView pv = new PointView(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DensityUtils
                    .dp2px(getContext(), 10), DensityUtils.dp2px(getContext(),
                    10));

            //除第一个以外，其他小白点都需要设置左边距
            if (i != 0) {
                layoutParams.leftMargin = DensityUtils.dp2px(getContext(), 10 / 2);
            }
            pv.setLayoutParams(layoutParams);
            linearLayout.addView(pv);
            bannerList.add(bannerView);
        }


        viewPager.setAdapter(bannerPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((PointView) (linearLayout.getChildAt(lastPosition % bannerList.size()))).setDefaultColor();
                ((PointView) (linearLayout.getChildAt(position % bannerList.size()))).setChangeColor();
                lastPosition = position;
                for (int i = 0; i < imageUrlList.size(); i++) {
                }
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % bannerList.size());

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
                imageLoader.loadImage(imageUrlList.get(i), bannerList.get(i).getImage());
            }
        }
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        bannerPagerAdapter.setOnItemClickListener(onItemClickListener);
    }


}
