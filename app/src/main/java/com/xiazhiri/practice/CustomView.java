package com.xiazhiri.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.xiazhiri.practice.util.L;

/**
 * Created by liuwencai on 16/4/3.
 */
public class CustomView extends View {
    private float lastX;
    private float lastY;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {
            width = 300;
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            height = 300;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float rawX = event.getRawX();
        float rawY = event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = rawX;
                lastY = rawY;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = (int) (rawX - lastX);
                int offsetY = (int) (rawY - lastY);

                //layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);

                offsetLeftAndRight(offsetX);
                offsetTopAndBottom(offsetY);

                //setTranslationX(offsetX);
                //setTranslationY(offsetY);

                lastX = rawX;
                lastY = rawY;
                break;
        }
        L.e(String.format("%.0f %.0f\n%d %d %d %d\n%.0f %.0f",
                getX(), getY(),
                getLeft(), getRight(), getTop(), getBottom(),
                getTranslationX(), getTranslationY()
        ));
        return true;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        L.e();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        L.e();
    }
}
