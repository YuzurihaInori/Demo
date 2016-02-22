package com.kding.demo;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

public class Main2Activity extends AppCompatActivity {

    private TextView block;
    private float x;
    private float y;
    private int w;
    private int h;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        block = (TextView) findViewById(R.id.block);


        Intent intent = getIntent();
        x = intent.getFloatExtra("x", 0);
        y = intent.getFloatExtra("y", 0);
        w = intent.getIntExtra("w", 0);
        h = intent.getIntExtra("h", 0);

        Log.e("x","x == "+x+"  y == "+y );
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        float endX = ViewHelper.getX(block);
        float endY = ViewHelper.getY(block);
        int endW = block.getWidth();
        int endH = block.getHeight();
        Log.e("w","endW == "+endW+"  endH == "+endH );
        ObjectAnimator xAnima = ObjectAnimator.ofFloat(block, "translationX", 0f, endW/2).setDuration(5000);
        ObjectAnimator yAnima =ObjectAnimator.ofFloat(block,"translationY",0f,endH/2).setDuration(5000);
        ObjectAnimator wAnima = ObjectAnimator.ofFloat(block,"scaleX",(float) w/(float) endW,1f).setDuration(5000);
//       ObjectAnimator.ofFloat(block,"alpha",0f,1f).setDuration(5000).start();
        ObjectAnimator hAnima = ObjectAnimator.ofFloat(block,"scaleY",(float) h/(float)endH,1f).setDuration(5000);

        AnimatorSet set = new AnimatorSet();
        set.play(xAnima).with(yAnima);
        set.play(wAnima).with(hAnima);
        set.play(xAnima).before(wAnima);
        set.start();
    }
}
