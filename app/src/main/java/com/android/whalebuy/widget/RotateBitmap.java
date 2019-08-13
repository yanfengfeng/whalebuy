/**
 * @TODO
 * Author 苟俊 jndx_taibai@163.com
 * Date  @2014-11-19
 * Copyright 2014 www.daoxila.com. All rights reserved.
 */
package com.android.whalebuy.widget;

import android.graphics.Bitmap;

/**
 * @author captain
 * 
 */
public class RotateBitmap {
	Bitmap bitmap;
	float rotateDegree;

	/**
	 * 
	 */
	public RotateBitmap() {
		super();
	}

	public RotateBitmap(Bitmap bitmap, float rotateDegree) {
		super();
		this.bitmap = bitmap;
		this.rotateDegree = rotateDegree;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public float getRotateDegree() {
		return rotateDegree;
	}

	public void setRotateDegree(float rotateDegree) {
		this.rotateDegree = rotateDegree;
	}

}
