package com.xiazhiri.practice;

import android.content.Context;
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
}
