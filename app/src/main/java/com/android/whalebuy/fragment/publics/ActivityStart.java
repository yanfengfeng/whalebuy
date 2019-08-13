package com.android.whalebuy.fragment.publics;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.whalebuy.R;
import com.android.whalebuy.activity.ActivityMain;
import com.android.whalebuy.kernel.KernelManager;
import com.android.whalebuy.widget.RotateView;

/**
 * 首次启动的引导页
 * 
 */
public class ActivityStart extends Activity {

	private RotateView rotateView;
//	private boolean isFromUserCenter = false;//是否从个人中心过来，而不是从首次启动过来
	private boolean firstLunch = true;


	class StartItemAdapter extends PagerAdapter {
		private List<ImageView> mImageViews;

		public StartItemAdapter() {
			mImageViews = new ArrayList<ImageView>();
			ImageView viewItem = null;
			LayoutInflater inflater = getLayoutInflater();

			for (int index = 0; index < 4; index++) {
				viewItem = (ImageView) (inflater.inflate(R.layout.item_start_0, null, false));
				// viewItem =(ImageView)(inflater.inflate(getResources().getIdentifier("item_start_" + index, "layout",
				 //KernelManager._GetObject().getMyPackName()), null));
				//viewItem.setImageDrawable(getResources().getDrawable(drawableId));
				mImageViews.add(viewItem);
			}
		}

		@Override
		public int getCount() {
			return mImageViews.size();
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
			// Log.e(getClass() + ".destroyItem()", "object:" + object +
			// " list(p):" + mImageViews.get(position));
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView viewItem = mImageViews.get(position);
			viewItem.setImageResource(getResources().getIdentifier("start_" + position, "drawable", KernelManager._GetObject().getMyPackName()));
			container.addView(viewItem);
			if (position == (mImageViews.size() - 1)) {
				viewItem.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
//						if (!isFromUserCenter)
							// getFragmentManager().beginTransaction().remove(FragmentStart.this).commit();
							startActivity(new Intent(getApplicationContext(), ActivityMain.class));
						ActivityStart.this.finish();
					}
				});
			}
			return viewItem;
		}
	}

//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.fragment_start);
//		isFromUserCenter = getIntent().getBooleanExtra("ismy", false);
//
//		// ViewPager viewPager = (ViewPager)(findViewById(R.id.ID_VIEWPAGER));
//		// viewPager.setAdapter(new StartItemAdapter());
//
//		final Button btnEnter = (Button) findViewById(R.id.enterButton); //开启品生活之旅
//		btnEnter.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				finish();
//				if (!isFromUserCenter){
////					startActivity(new Intent(getApplicationContext(), ChooseEstateActivity.class));//选社区
//
//					startActivity(new Intent(getApplicationContext(), ActivityMain.class));
//				}
//			}
//		});
//
//		//标题图片
//		final ImageView titleImageView = (ImageView) findViewById(R.id.titleView);
//		final int[] titleImageRes = new int[] { R.drawable.title1, R.drawable.title2, R.drawable.title3, R.drawable.title4 };  4张图片的时候
//		//final int[] titleImageRes = new int[] { R.drawable.title1, R.drawable.title2, R.drawable.title3 };
//		final LinearLayout pointLayout = (LinearLayout) findViewById(R.id.pagerIndicator);
//
//		for (int i = 0; i < titleImageRes.length; i++) {
//			LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//			layoutParams.leftMargin = 30;
//			ImageView pointView = new ImageView(ActivityStart.this);
//			pointView.setLayoutParams(layoutParams);
////			pointView.setImageResource(R.drawable.splash_indicator_point_bg);
//			pointLayout.addView(pointView, i);
//		}
		
//		//旋转动画
//		rotateView = (RotateView) findViewById(R.id.rotateView);
//		final AlphaAnimation visable_alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
//		visable_alphaAnimation.setDuration(100);
//		final AlphaAnimation gone_alphaAnimation = new AlphaAnimation(1.0f, 0.1f);
//		gone_alphaAnimation.setDuration(100);
//		rotateView.setOnRotateListener(new RotateView.OnRotateListener() {
//
//			@Override
//			public void onRotate(int position) {
//
//				titleImageView.setImageResource(titleImageRes[position]);
//				for (int i = 0; i < titleImageRes.length; i++) {
//					pointLayout.getChildAt(i).setSelected(i == position ? true : false);
//				}
//
//				if (position == titleImageRes.length - 1) {
//					btnEnter.setVisibility(View.VISIBLE);
//					pointLayout.setVisibility(View.INVISIBLE);
//					btnEnter.startAnimation(visable_alphaAnimation);
//				} else {
//					pointLayout.setVisibility(View.VISIBLE);
//					if (btnEnter.getVisibility() == View.VISIBLE) {
//						btnEnter.setVisibility(View.INVISIBLE);
//						btnEnter.startAnimation(gone_alphaAnimation);
//					}
//				}
//			}
//
//			@Override
//			public void onRotateFinished() {
//
//			}
//		});

//	}

	@Override
	protected void onResume() {
		super.onResume();
		if (!firstLunch) {
			rotateView.onResume();
			firstLunch = false;
		}
	}

	@Override
	protected void onDestroy() {
		rotateView.freeMemory();
		rotateView = null;
		super.onDestroy();
	}
}
