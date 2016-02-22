package com.kding.demo;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.TypeEvaluator;
import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by Toast-pc on 2016/2/19.
 */
public class CuteProgress extends View {

    private Paint paint;
    private RectF rectF;
    public Progress progress;
    private float curStartAngle;
    private float startAngle;
    private static final int DEFAULT_MIN_WIDTH = 400; //View默认最小宽度
    private float currentAngle;

    public CuteProgress(Context context) {
        super(context);
        init();
    }

    public CuteProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public CuteProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);

        progress = new Progress();
        progress.setSweepAngle(20f);

        rectF = new RectF();
        rectF.top = 10;
        rectF.left = 10;
        rectF.right = 100;
        rectF.bottom = 100;

        this.setPivotX(55f);
        this.setPivotY(55f);
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
//        Log.e("--------------","================");
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        System.out.println("onDraw = "+startAngle +"%%%%"+progress.getSweepAngle());
//        canvas.drawCircle(100,100,50,paint);
//        canvas.drawRect(rectF,paint);
        canvas.drawArc(rectF, curStartAngle, progress.getSweepAngle(), false, paint);
//        canvas.rotate(currentAngle, 0, 0);
//        if (currentAngle >= 360f){
//            currentAngle = currentAngle - 360f;
//        } else{
//            currentAngle = currentAngle + 2f;
//        }
    }

    public void startAnim() {

        Progress progress1 = new Progress();
        progress1.setSweepAngle(315f);

        Progress progress2 = new Progress();
        progress2.setSweepAngle(20f);
//        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(),progress2,progress1);
//        ObjectAnimator anim = ObjectAnimator.ofFloat(progress,"sweepAngle",45f,270f);
        ObjectAnimator anim = ObjectAnimator.ofObject(this, "progress", new PointEvaluator(), progress2, progress1);
        anim.setDuration(500);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator anim2 = ObjectAnimator.ofObject(this, "progress", new PointEvaluator2(), progress1, progress2);
        anim2.setDuration(800);
        anim2.setInterpolator(new AccelerateDecelerateInterpolator());
//        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                setProgress((Progress) valueAnimator.getAnimatedValue());
//                Log.e("tag     ", ""+progress.getSweepAngle());
//            }
//        });


        ObjectAnimator rotationAnim = ObjectAnimator.ofFloat(this, "rotation", 0f, 360f);
        rotationAnim.setDuration(2000);
        rotationAnim.setRepeatCount(Animation.INFINITE);
        rotationAnim.setInterpolator(new LinearInterpolator());
        rotationAnim.start();

        final AnimatorSet set = new AnimatorSet();
        set.play(anim).before(anim2);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                set.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.start();
    }

    public class PointEvaluator implements TypeEvaluator {
        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            Progress startPoint = (Progress) startValue;
            Progress endPoint = (Progress) endValue;
            float x = startPoint.getSweepAngle() + fraction * (endPoint.getSweepAngle() - startPoint.getSweepAngle());
//            Log.e("evaluate  === ",""+fraction);
            Progress progress2 = new Progress();
            progress2.setSweepAngle(x);
            return progress2;
        }

    }

    public class PointEvaluator2 implements TypeEvaluator {
        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            Progress startPoint = (Progress) startValue;
            Progress endPoint = (Progress) endValue;
            float x = startPoint.getSweepAngle() - fraction * (Math.abs(endPoint.getSweepAngle() - startPoint.getSweepAngle()));
            curStartAngle = startAngle + fraction * (Math.abs(endPoint.getSweepAngle() - startPoint.getSweepAngle()));
//            Log.e("start  === ",""+curStartAngle);
            Progress progress3 = new Progress();
            progress3.setSweepAngle(x);
            if (fraction == 1) {
//                Log.e("----","==========");
                startAngle = curStartAngle % 360;
            }
            return progress3;
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
    }

    private int measure(int origin) {
        int result = DEFAULT_MIN_WIDTH;
        int specMode = MeasureSpec.getMode(origin);
        int specSize = MeasureSpec.getSize(origin);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    public static class Progress {
        private float sweepAngle;

        public float getSweepAngle() {
            return sweepAngle;
        }

        public void setSweepAngle(float sweepAngle) {
            this.sweepAngle = sweepAngle;
        }
    }
}
