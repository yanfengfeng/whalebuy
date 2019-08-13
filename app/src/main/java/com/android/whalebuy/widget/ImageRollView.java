package com.android.whalebuy.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import com.android.whalebuy.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片轮播
 * 
 * @author Captain
 * 
 */
public class ImageRollView extends RelativeLayout
{
	public static class RollItem
	{
		public String imageUrl;
	}
	
	private ArrayList<RollItem> mImageList;
	private CirclePageIndicator mPointsLayout;
	private ImagePagerAdapter imagePagerAdapter;
	private long lastMoveTime = 0;
	private ViewPager pager;
	private int currentPage = 0;
	private Context context;
	private OnImageRollViewClickListener mOnImageRollViewClickListener;

	public ImageRollView(Context context) {
		super(context);
		setupChildView(context);
	}

	public ImageRollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setupChildView(context);
	}

	private void setupChildView(Context context) {
		this.context = context;
		mImageList = new ArrayList<RollItem>();
//		View layout = LayoutInflater.from(context).inflate(
//				R.layout.view_image_select, null);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.viepagerlayout, this, true);
		pager = (ViewPager) layout.findViewById(R.id.pager);
		mPointsLayout = (CirclePageIndicator) layout.findViewById(R.id.indicator);
//		addView(layout);
	}

	public void initImageRollView(List<RollItem> imageUrls, boolean autoPlay) {
		initImageRollView(imageUrls, autoPlay, ScaleType.FIT_XY);
	}

	/**
	 * 初始化图片滚动控件
	 * 
	 * @param imageUrls
	 *            图片地址集
	 * @param autoPlay
	 *            是否自动播放
	 */
	public void initImageRollView(List<RollItem> imageUrls,
			boolean autoPlay, ScaleType scaleType) {
		mImageList.clear();
		mImageList.addAll(imageUrls);
		initImageView(scaleType);
		imagePagerAdapter = new ImagePagerAdapter();
		pager.setAdapter(imagePagerAdapter);
		mPointsLayout.setViewPager(pager);
		pager.setCurrentItem(0, true);
		// 防止多次调用导致播放混乱
		handler.removeCallbacks(autoPalyRunnable);
		lastMoveTime = System.currentTimeMillis();
		if (autoPlay) {
			autoPlayImages();
		}
	}

	/**
	 * 显示下一页
	 */
	public void showNext() {
		playImageByItem(pager.getCurrentItem() + 1);
	}

	/**
	 * 显示上一页
	 */
	public void showPrev() {
		playImageByItem(pager.getCurrentItem() - 1);
	}

	/**
	 * 
	 * @return 当前所在页
	 */
	public int getCurrentItem() {
		return pager.getCurrentItem();
	}

	/**
	 * 显隐导航点显隐，默认显示
	 * 
	 * @param show
	 */
	public void showIndicator(boolean show) {
		mPointsLayout.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	private Handler handler = new Handler(Looper.getMainLooper());
	private Runnable autoPalyRunnable = new Runnable() {

		@Override
		public void run() {
			autoPlayImages();
		}
	};

	/**
	 * 是否可以自动播放
	 * @author bigjoe
	 * @param flag
	 */
	public void canPlay(boolean flag){
		if (!flag){
			handler.removeCallbacks(autoPalyRunnable);
		}else{
			handler.postDelayed(autoPalyRunnable, 2000);
		}
	}
	
	private void autoPlayImages() {
		if (System.currentTimeMillis() - lastMoveTime > 800) {
			try {
				showNext();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		handler.postDelayed(autoPalyRunnable, 2000);
	}

	public void playImageByItem(int index) {
		//LogUtil.d("ImageRollView", "playImageByItem, index=" + index);
			if (index > imagePagerAdapter.getCount() - 1) {
				pager.setCurrentItem(0, true);
				AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
				animation.setDuration(500);
				pager.setAnimation(animation);
			} else if (index == -1) {
				pager.setCurrentItem(imagePagerAdapter.getCount() - 1, true);
				AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
				animation.setDuration(500);
				pager.setAnimation(animation);
			} else {
				pager.setCurrentItem(index, true);
				// Animation animation = AnimationUtils.loadAnimation(getContext(),
				// R.anim.slide_right_in);
				// pager.setAnimation(animation);
			}
		
	}

	int lastState = 0;

	private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) {
			
			currentPage = arg0;
		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			
			switch (arg0) {
			case ViewPager.SCROLL_STATE_DRAGGING:
			case ViewPager.SCROLL_STATE_SETTLING:
				lastState = arg0;
				break;
			case ViewPager.SCROLL_STATE_IDLE:
				if (lastState == ViewPager.SCROLL_STATE_DRAGGING) {
					if (pager.getCurrentItem() == mImageList.size() - 1) {
						pager.setCurrentItem(0);
					} else if (pager.getCurrentItem() == 0) {
						pager.setCurrentItem(mImageList.size());
					}
				}
				break;
			}
		}
	};

	int lastX = 0;

	private OnTouchListener onTouchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				lastX = (int) event.getX();
				break;
			case MotionEvent.ACTION_MOVE:
				lastMoveTime = System.currentTimeMillis();
				if ((lastX - event.getX()) > 0
						&& (currentPage == mImageList.size() - 1)) {
					// 最后一划
				}
				break;
			}
			Log.d("tag", "on touch:" + getWidth() + "," + getHeight());
			return false;
		}
	};
	
	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (mOnImageRollViewClickListener != null)
			{
				mOnImageRollViewClickListener.onClick(v, getCurrentItem(), (RollItem)(v.getTag()));
			}
			
		}
	};
	
	/**
	 * @return the mImageUrls
	 */
	public ArrayList<RollItem> getImageUrls() {
		return mImageList;
	}

	/**
	 * @param mImageUrls
	 *            the mImageUrls to set
	 */
	public void setImageUrls(ArrayList<RollItem> mImageUrls) {
		this.mImageList.addAll(mImageUrls);
	}

	/**
	 * @param mOnImageRollViewClickListener
	 *            the mOnImageRollViewClickListener to set
	 */
	public void setOnImageRollViewClickListener(
			OnImageRollViewClickListener mOnImageRollViewClickListener) {
		this.mOnImageRollViewClickListener = mOnImageRollViewClickListener;
	}

	public interface OnImageRollViewClickListener {
		void onClick(View view, int position, RollItem item);
	}

	private ArrayList<ImageView> imageViews;

	void initImageView(ScaleType scaleType) {
		imageViews = new ArrayList<ImageView>();
		for (int i = 0; i < mImageList.size(); i++) {
			ImageView imageView = new ImageView(getContext());
			imageView.setScaleType(scaleType);
			// imageView.setAdjustViewBounds(true);
			LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);
			imageView.setLayoutParams(params);
			imageViews.add(imageView);
		}
	}

	private class ImagePagerAdapter extends PagerAdapter {

		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.product)
				.showImageForEmptyUri(R.drawable.product)
				.showImageOnFail(R.drawable.product).cacheInMemory()
				.cacheOnDisc().bitmapConfig(Bitmap.Config.RGB_565).build();

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView((View) object);
			// ((ViewPager)
			// container).removeView(container.getChildAt(position));
		}

		@Override
		public void finishUpdate(View container) {

		}

		@Override
		public int getCount() {
			return mImageList.size();
		}

		@Override
		public Object instantiateItem(ViewGroup view, int position) {
			// View layout =
			// LayoutInflater.from(getContext()).inflate(R.layout.viewpager_image,
			// null);
			// ImageView imageView = (ImageView)
			// layout.findViewById(R.id.image);
			ImageView imageView = imageViews.get(position);

			ImageLoader imageLoader = ImageLoader.getInstance();
			if (!imageLoader.isInited()){ //by bigjoe 解决重复init的问题
				imageLoader.init(ImageLoaderConfiguration.createDefault(context));
			}
			RollItem item = mImageList.get(position);
			imageLoader.displayImage(item.imageUrl, imageView,
					options);
			imageView.setOnTouchListener(onTouchListener);
			imageView.setClickable(true);
			imageView.setOnClickListener(onClickListener);
			imageView.setTag(item);
			
			((ViewPager) view).addView(imageView);
			
			return imageView;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view.equals(object);
		}

		@Override
		public void restoreState(Parcelable state, ClassLoader loader) {

		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View container) {

		}
	}

}
