package com.han.widget.simplebanner;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by hans
 * date: 2018/2/28 16:31.
 * e-mail: hxxx1992@163.com
 */

public class BannerPagerAdapter extends PagerAdapter {

    public BannerPagerAdapter(List<ImageView> datas) {
        this.datas = datas;
    }

    private List<ImageView> datas;

    private OnItemClickListener listener;

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // 把position对应位置的ImageView添加到ViewPager中
        ImageView iv = datas.get(position % datas.size());
        final int nowPosition = position % datas.size();
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(" adapter", "listener:" + (listener == null));
                //回调
                if (listener != null) {
                    listener.onItemClick(nowPosition);
                }
            }
        });
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(datas.get(position % datas.size()));
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        Log.e(" adapter", "listener:" + (listener == null));
        Log.e(" adapter", "onItemClickListener:" + (onItemClickListener == null));

        this.listener = onItemClickListener;
        Log.e(" adapter", "listener:" + (listener == null));
    }
}
