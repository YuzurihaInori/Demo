package com.kding.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class Main4Activity extends AppCompatActivity {

    private ImageView imgPic1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        imgPic1 = (ImageView)findViewById(R.id.img_pic1);

        Picasso.with(this).load(R.drawable.inage1).resize(300,300).into(imgPic1);
//        Glide.with(this).load(R.drawable.inage1).centerCrop().into(imgPic1);
    }
}
