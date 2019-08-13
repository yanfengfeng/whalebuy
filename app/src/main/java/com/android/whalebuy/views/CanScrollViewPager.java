package com.android.whalebuy.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CanScrollViewPager extends ViewPager
{
	private boolean mCanScroll = false;

	public CanScrollViewPager(Context context)
	{
		super(context);
	}

	public CanScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setCanScroll(boolean isCanScroll) 
	{
		this.mCanScroll = isCanScroll;
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0)
	{
		// TODO Auto-generated method stub
		if (mCanScroll) 
		{
			return super.onTouchEvent(arg0);
		} 
		
		return false;

	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		// TODO Auto-generated method stub
		if (mCanScroll) {
			return super.onInterceptTouchEvent(arg0);
		} else {
			return false;
		}

	}
}