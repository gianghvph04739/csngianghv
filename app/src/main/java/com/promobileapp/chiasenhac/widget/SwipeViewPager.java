package com.promobileapp.chiasenhac.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

import com.promobileapp.chiasenhac.R;

public class SwipeViewPager extends ViewPager {
    private boolean mSwipeEnable;

    public SwipeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SwipeViewPager);
        try {
            mSwipeEnable = a.getBoolean(R.styleable.SwipeViewPager_swipeEnable, true);
        } finally {
            a.recycle();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return mSwipeEnable && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mSwipeEnable && super.onInterceptTouchEvent(ev);
    }

    public void setSwipeEnable(boolean swipeEnable) {
        mSwipeEnable = swipeEnable;
    }
}
