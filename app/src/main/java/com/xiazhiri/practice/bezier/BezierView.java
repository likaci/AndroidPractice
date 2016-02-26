package com.xiazhiri.practice.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/**
 * Created by liuwencai on 16/2/25.
 */
public class BezierView extends View {
    Paint paint;
    Paint paint2;
    float time;
    int r = 100;
    int size;

    Path path = new Path();
    Point center = new Point(200, 200);

    public BezierView(Context context) {
        this(context, null);
    }

    public BezierView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);

        paint2 = new Paint();
        paint2.setColor(Color.RED);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setStrokeWidth(2);
        paint2.setAntiAlias(true);

        Anim anim = new Anim();
        anim.setDuration(2000);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE);
        anim.setRepeatMode(Animation.INFINITE);
        startAnimation(anim);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        r = (int) (40 * Math.sin(time * Math.PI));
        size = 25 + (int) (8 * Math.abs(Math.cos(time * Math.PI)));

        float x = (float) (Math.cos(time * 2 * Math.PI) * r + center.x);
        float y = (float) (Math.sin(time * 2 * Math.PI) * r + center.y);
        canvas.drawCircle(x, y, size, paint);

        float x2 = (float) (-Math.cos(time * 2 * Math.PI) * r + center.x);
        float y2 = (float) (-Math.sin(time * 2 * Math.PI) * r + center.y);
        canvas.drawCircle(x2, y2, size, paint2);

        canvas.drawCircle(center.x, center.y, 2, paint2);

        canvas.drawLine(x, y, x2, y2, paint2);

    }

    public class Anim extends Animation {
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            time = interpolatedTime;
            invalidate();
        }
    }

}
