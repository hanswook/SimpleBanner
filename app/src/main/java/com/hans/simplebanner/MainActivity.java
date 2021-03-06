package com.hans.simplebanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.Toast;

import com.han.widget.simplebanner.BannerView;
import com.hans.simplebanner.image.GlideApp;
import com.han.widget.simplebanner.ImageLoader;
import com.han.widget.simplebanner.OnItemClickListener;
import com.han.widget.simplebanner.SimpleBanner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String url;
    SimpleBanner simpleBanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        url = "https://ws1.sinaimg.cn/large/610dc034ly1fj3w0emfcbj20u011iabm.jpg";
        testBanner();
//        testBannerView();


    }

    private void testBannerView() {
        BannerView bv = findViewById(R.id.bannerview);
        GlideApp.with(MainActivity.this).load(url).into(bv.getImage());
        bv.getTextView().setText("adasdasdasdsdasds");
    }

    private void testBanner() {
        simpleBanner= findViewById(R.id.simple);
        List<String> imageUrls = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            imageUrls.add("https://ws1.sinaimg.cn/large/610dc034ly1fj3w0emfcbj20u011iabm.jpg");
            titleList.add("一份为高中生准备的机器学习与人工智能入门指南");
        }
        simpleBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, "position:" + position, Toast.LENGTH_SHORT).show();
            }
        });


        simpleBanner.setImageLoader(new ImageLoader() {
            @Override
            public void loadImage(String url, ImageView imageView) {
                GlideApp.with(MainActivity.this).load(url).into(imageView);
            }
        });
        simpleBanner.initBanner(imageUrls,titleList);


    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
