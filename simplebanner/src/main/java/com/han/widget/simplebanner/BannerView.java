package com.han.widget.simplebanner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.han.widget.R;

/**
 * Created by hans
 * date: 2018/3/15 14:57.
 * e-mail: hxxx1992@163.com
 */

public class BannerView extends RelativeLayout {

    private ImageView imageView;

    private TextView textView;


    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        if (getChildCount() <= 0) {
            View.inflate(getContext(), R.layout.banner_view_layout, this);
            imageView = findViewById(R.id.bv_image);
            textView = findViewById(R.id.bv_text);
        }

    }

    public ImageView getImage() {
        return imageView;
    }

    public TextView getTextView() {
        return textView;
    }


}
