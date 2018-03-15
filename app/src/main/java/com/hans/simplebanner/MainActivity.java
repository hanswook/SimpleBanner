package com.hans.simplebanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.hans.simplebanner.image.GlideApp;
import com.widget.simplebanner.ImageLoader;
import com.widget.simplebanner.OnItemClickListener;
import com.widget.simplebanner.SimpleBanner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SimpleBanner simpleBanner = findViewById(R.id.simple);
        List<String> imageUrls = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            imageUrls.add("https://ws1.sinaimg.cn/large/610dc034ly1fj3w0emfcbj20u011iabm.jpg");
        }

        simpleBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, "position:" + position, Toast.LENGTH_LONG).show();
            }
        });
        simpleBanner.initBanner(imageUrls);
        simpleBanner.setImageLoader(new ImageLoader() {
            @Override
            public void loadImage(String url, ImageView imageView) {
                GlideApp.with(MainActivity.this).load(url).into(imageView);
            }
        });



    }
}
