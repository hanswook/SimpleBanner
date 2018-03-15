SimpleBanner

设置条目点击事件。
SimpleBanner.setOnItemClickListener();

设置图片加载，使用项目当前使用的第三方图片控件。
SimpleBanner.setImageLoader();

传入图片列表和标题列表。长度需要一样。类型为List<String>
simpleBanner.initBanner(imageUrls,titleList);


```
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
```
