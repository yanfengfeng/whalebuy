package com.android.whalebuy.fragment.publics;

import com.android.whalebuy.R;
import com.android.whalebuy.activity.ActivityMain;
import com.android.whalebuy.kernel.KernelManager;
import com.android.whalebuy.widget.ToastUtil;


//import com.hezhi.android.fanqievideo.common.PsPushUserData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;

/**
 * app 入口
 * 
 */
public class ActivityWelcome extends Activity {
	private CountDownTimer mTimer;

	private int msec;
	private ImageView imgStart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_welcome);
		ToastUtil.showInfo(ActivityWelcome.this, "广告FFFF运转了", false);
//		requestAD();

		KernelManager._GetObject().init(getApplicationContext());

		// 启动一个计时器,n秒后自动消失
		mTimer = new CountDownTimer(2000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {

			}

			@Override
			public void onFinish() {
				// 如果首次启动展示启动页, 否则进入首页
//				SharedPreferences preference = getSharedPreferences(
//						IConstants.CONFIG_FILE, Context.MODE_PRIVATE);
//
//				boolean firstStart = preference.getBoolean(
//						IConstants.FIRST_START, true);
//				if (firstStart) {
//					SharedPreferences.Editor preferenceEditor = preference
//							.edit();
//					preferenceEditor.putBoolean(IConstants.FIRST_START, false);
//					preferenceEditor.commit();
//
//					// startActivity(new Intent(getApplicationContext(),
//					// ActivityStart.class));
//				}
				// else {
				//
				// String estate_id =
				// PsPushUserData.getData(ActivityWelcome.this, "estate_id");
				// if (!estate_id.equals("")){
				// startActivity(new Intent(getApplicationContext(),
				// TabHostActivity.class));
				// }else{
				// ////选社区
				// startActivity(new Intent(getApplicationContext(),
				// ChooseEstateActivity.class));
				// }
				//
				// }

				 startActivity(new Intent(getApplicationContext(), ActivityMain.class));

				finish();
			}
		};

		mTimer.start();

	}

//	private void requestAD() {
//		ToastUtil.showInfo(ActivityWelcome.this, "广告运转了", false);
//
//		HttpUtil.get(IConstants.URL_OP_AD, new AsyncHttpResponseHandler() {
//			@Override
//			public void onSuccess(String response) {
//
//				Log.i("_+++返回回来的广告参数是+++_", response.toString());
//
//				try {
//					JSONObject object = new JSONObject(response);
//
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//
//			}
//
//		});
//
//	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mTimer.cancel();
	}
}
