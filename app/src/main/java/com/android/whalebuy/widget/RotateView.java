/**
 * @TODO
 * Author 苟俊 jndx_taibai@163.com
 * Date  @2014-11-18
 * Copyright 2014 www.daoxila.com. All rights reserved.
 */
package com.android.whalebuy.widget;

import java.lang.reflect.Field;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import com.android.whalebuy.R;


public class RotateView extends SurfaceView implements SurfaceHolder.Callback, OnTouchListener, Runnable {

	private float lastX = 0, lastY = 0;
	private int center_x, center_y;// 旋转中心坐标
	private int statusBarHeight;
	private float degree = 0;
	private int bottomMargin = 90;// 120dp
	private final float ROTATE_DEGREE = 60;// 相邻两张图片夹角
	private final float DEFAULT_ROTATE_DEGREE = 5;// 每次滑动单位距离触发的旋转角度
	private RotateStatus mCurrentRotateStatus, lastRotateStatus;
	private boolean isDrawFinished = false;
	private int currentPageItem = -1;// 当前可见的页数
	private float sx, bg_scale_x, bg_scale_y;// 缩放比例
	private Context context;
	private boolean isThreadAlive = true;
	private SurfaceHolder surfaceHolder;
	private SparseArray<RotateBitmap> sparseArray;
	private int[] bitmapRes = new int[] { R.drawable.start_0, R.drawable.start_1, R.drawable.start_2, R.drawable.start_3 }; //4张图片的时候
//	private int[] bitmapRes = new int[] { R.drawable.t1, R.drawable.t2, R.drawable.t3};
	private int splash_background = R.drawable.splash_backgroud;
	private final int HANDLER_ONROTATE = 0x01, HANDLER_ONROTATEFINISHED = 0x02;
	private Bitmap background;
	private OnRotateListener onRotateListener;

	public RotateView(Context context) {
		super(context);
		init(context);
	}

	public RotateView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public RotateView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		this.context = context;
		statusBarHeight = getStatuBarHeight();
		center_x = getWindowWidth() / 2;
		center_y = getWindowHeight() - statusBarHeight;
		background = getDecodedBitmap(splash_background);
		setLongClickable(true);
		setOnTouchListener(this);
		surfaceHolder = this.getHolder();
		surfaceHolder.addCallback(this);
		sparseArray = new SparseArray<RotateBitmap>();
		for (int i = 0; i < bitmapRes.length; i++) {
			Bitmap bitmap = getDecodedBitmap(bitmapRes[i]);
			RotateBitmap scrollBitmap = new RotateBitmap(bitmap, 0);
			sparseArray.put(i, scrollBitmap);
		}
		bg_scale_x = getWindowWidth() / (float) background.getWidth();
		bg_scale_y = getWindowHeight() / (float) background.getHeight();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.SurfaceHolder.Callback#surfaceCreated(android.view.SurfaceHolder )
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		
		isThreadAlive = true;
		// setZOrderOnTop(true);
		// getHolder().setFormat(PixelFormat.TRANSLUCENT);
		new Thread(this).start();
		if (degree != 0) {
			holder.addCallback(this);
			drawBitmaps(degree);
			//LogUtil.d("tag", "surfaceCreated drawBitmaps");
		}
	//	LogUtil.d("tag", "surfaceCreated " + degree);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.SurfaceHolder.Callback#surfaceChanged(android.view.SurfaceHolder , int, int, int)
	 */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		
		//LogUtil.d("tag", "surfaceChanged " + degree);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.SurfaceHolder.Callback#surfaceDestroyed(android.view. SurfaceHolder)
	 */
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
		isThreadAlive = false;
		holder.removeCallback(this);
		//LogUtil.d("tag", "surfaceDestroyed " + degree);
	}

	private MotionEvent mDownEvent;

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			lastX = event.getX();
			lastY = event.getY();
			mDownEvent = MotionEvent.obtain(event);//TODO:This MotionEvent should be recycled after use with #recycle()
			break;
		case MotionEvent.ACTION_MOVE:
			final float eventX = event.getX();
			final float eventY = event.getY();
			// float xDiff = eventX - mDownEvent.getX();
			// float yDiff = eventY - mDownEvent.getY();
			float xDiff = eventX - lastX;
			float yDiff = eventY - lastY;
//			double zDiff = Math.sqrt(yDiff * yDiff + xDiff * xDiff);
			if (xDiff < -10) {
				setScrollStatus(RotateStatus.left);
				isDrawFinished = false;
			} else if (xDiff > 10) {
				setScrollStatus(RotateStatus.right);
				isDrawFinished = false;
			} else {
				// setScrollStatus(RotateStatus.stop);
			}
			lastX = event.getX();
			lastY = event.getY();
			break;
		case MotionEvent.ACTION_UP:
			lastX = event.getX();
			lastY = event.getY();
			setScrollStatus(RotateStatus.stop);
			isDrawFinished = false;
			break;

		default:
			break;
		}

		return true;
	}

	@Override
	public void run() {
		// this.postInvalidate();
		while (isThreadAlive) {
			try {
				if (!isDrawFinished) {
					synchronized (surfaceHolder) {
						// LogUtil.d("tag", "before degree:" + degree);
						if (mCurrentRotateStatus == RotateStatus.right) {
							degree += DEFAULT_ROTATE_DEGREE;
							lastRotateStatus = RotateStatus.right;
						} else if (mCurrentRotateStatus == RotateStatus.left) {
							degree -= DEFAULT_ROTATE_DEGREE;
							lastRotateStatus = RotateStatus.left;
							if (currentPageItem == bitmapRes.length - 1) {// 在最后一页滑动
								Message message = new Message();
								message.what = HANDLER_ONROTATEFINISHED;
								mHandler.sendMessage(message);
							}
						} else if (mCurrentRotateStatus == RotateStatus.stop) {
							float startDegree = degree;
							float m = startDegree;
							if (lastRotateStatus == RotateStatus.left) {
								degree = (float) (-Math.ceil(Math.abs(degree) / ROTATE_DEGREE) * ROTATE_DEGREE);
								for (; m >= degree; m -= 5) {
									// LogUtil.d("tag", "guodu" + m);
									drawBitmaps(m);
									// Thread.sleep(10);
								}
								degree = (float) (-Math.ceil(Math.abs(m + 5) / ROTATE_DEGREE) * ROTATE_DEGREE);
								isDrawFinished = true;
								continue;
							} else if (lastRotateStatus == RotateStatus.right) {
								degree = -((int) (Math.abs(degree) / ROTATE_DEGREE) * ROTATE_DEGREE);
								for (; m <= degree; m += 5) {
									// LogUtil.d("tag", "guodu" + m);
									drawBitmaps(m);
									// Thread.sleep(10);
								}
								degree = -((int) (Math.abs(m - 5) / ROTATE_DEGREE) * ROTATE_DEGREE);
								isDrawFinished = true;
								continue;
							}
							// LogUtil.d("tag", "after degree:" + degree);

						}

						degree %= 360;
						if (degree >= 0) {
							degree = 0;
						} else {
							if (degree <= -(ROTATE_DEGREE * (bitmapRes.length - 1))) {
								degree = -(ROTATE_DEGREE * (bitmapRes.length - 1));
							}
						}
						// LogUtil.d("tag", "degree:" + degree);
						drawBitmaps(degree);
					}
					isDrawFinished = true;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	private Matrix bg_matrix = new Matrix();
	private Matrix matrix = new Matrix();

	private void drawBitmaps(float degree) {
		if (onRotateListener != null) {
			int page = (int) (Math.abs(degree) / ROTATE_DEGREE);
			if (currentPageItem != page) {
				currentPageItem = page;
				Message message = new Message();
				message.what = HANDLER_ONROTATE;
				message.obj = currentPageItem;
				mHandler.sendMessage(message);
			}
		}
		if (degree <= 0) {
			surfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
			Canvas canvas = surfaceHolder.lockCanvas();

			// 清空Canvas
			Paint paint = new Paint();
			paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
			canvas.drawPaint(paint);

			// 绘制背景
			bg_matrix.reset();
			bg_matrix.postScale(bg_scale_x, bg_scale_y);
			canvas.drawBitmap(background, bg_matrix, null);
			for (int i = 0; i < bitmapRes.length; i++) {
				RotateBitmap scrollBitmap = sparseArray.get(i);
				Bitmap currentBitmap = scrollBitmap.getBitmap();
				if (currentBitmap != null) {
					if (i - currentPageItem != 2) {
						if (currentBitmap.isRecycled()) {
							// LogUtil.d("tag", "reload bitmap:" + degree);
							currentBitmap = getDecodedBitmap(bitmapRes[i]);
							sparseArray.put(i, new RotateBitmap(currentBitmap, scrollBitmap.getRotateDegree()));
						}
						sx = getWindowWidth() / (float) currentBitmap.getWidth();
						matrix.reset();
						matrix.postScale(sx, sx);
						matrix.postRotate(degree + i * ROTATE_DEGREE, center_x, center_y);
						matrix.postTranslate(0, getWindowHeight() - currentBitmap.getHeight() - statusBarHeight - getPixelFromDip(bottomMargin));
						canvas.drawBitmap(currentBitmap, matrix, null);
						if (mCurrentRotateStatus != RotateStatus.stop && currentPageItem == i) {
						}
					} else if (!currentBitmap.isRecycled()) {// 旋转超过固定角度则释放
						// LogUtil.d("tag", "recycle:" + degree);
						// currentBitmap.recycle();
					}

				}
			}
			surfaceHolder.unlockCanvasAndPost(canvas);
		}
	}

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case HANDLER_ONROTATE:
				if (onRotateListener != null) {
					if (msg.obj instanceof Integer) {
						onRotateListener.onRotate((Integer) msg.obj);
					}
				}
				break;
			case HANDLER_ONROTATEFINISHED:
				if (onRotateListener != null) {
					onRotateListener.onRotateFinished();
				}
				break;

			default:
				break;
			}
		}

	};

	public int getPixelFromDip(float dip) {
		return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics()) + 0.5f);
	}

	public int getWindowHeight() {
		Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		return display.getHeight();
	}

	public int getWindowWidth() {
		Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		return display.getWidth();
	}

	public int getStatuBarHeight() {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, sbar = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			sbar = context.getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return sbar;
	}

	private Bitmap getDecodedBitmap(int resid) {
		BitmapFactory.Options op = new BitmapFactory.Options();
		op.inPreferredConfig = Bitmap.Config.RGB_565;
		op.inPurgeable = true;
		op.inInputShareable = true;
		op.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(getResources(), resid, op);

		// 设置缩放比
		op.inSampleSize = (int) (op.outWidth / getWindowWidth());
		op.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(getResources(), resid, op);
	}

	public void onResume() {
		surfaceHolder.addCallback(this);
	}

	public void freeMemory(){
		try {
			background.recycle();
			background = null;
			for(int i=0;i<sparseArray.size();i++) {
				sparseArray.get(i).getBitmap().recycle();
			}
			sparseArray = null;
			System.gc();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the mScrollStatus
	 */
	public RotateStatus getScrollStatus() {
		return mCurrentRotateStatus;
	}

	/**
	 * @param mScrollStatus
	 *            the mScrollStatus to set
	 */
	public void setScrollStatus(RotateStatus mScrollStatus) {
		this.mCurrentRotateStatus = mScrollStatus;
	}

	/**
	 * @return the onRotateListener
	 */
	public OnRotateListener getOnRotateListener() {
		return onRotateListener;
	}

	/**
	 * @param onRotateListener
	 *            the onRotateListener to set
	 */
	public void setOnRotateListener(OnRotateListener onRotateListener) {
		this.onRotateListener = onRotateListener;
	}

	public interface OnRotateListener {
		void onRotate(int position);

		void onRotateFinished();// 最后一页后再尝试旋转，回调
	}

}
