package com.android.whalebuy.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.android.whalebuy.IConstants;
import com.android.whalebuy.fragment.my.MyActivitySetting;
import com.android.whalebuy.fragment.publics.FragmentFunctionPage;
import com.android.whalebuy.kernel.DataPackage;
import com.android.whalebuy.kernel.DataParse;
import com.android.whalebuy.kernel.KernelException;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.apache.http.Header;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;
import com.android.whalebuy.R;
import com.android.whalebuy.data.DataModel;
import com.android.whalebuy.kernel.KernelManager;
import com.loopj.android.http.AsyncHttpClient;
import org.json.JSONObject;

public class ActivityMain extends FragmentActivity {

	private DataModel.UpdateInfo mUpdateInfo; // 更新信息

	private boolean mNextBackExit = false; // 再按一次back键退出
	private AsyncHttpClient mHttpClient;
	private final String TAG = "siqiang";
	private ProgressDialog mProgressDag;

	// private static final String RESOURCE_ID = "601208413";// 节目id，需要替换成自己的参数

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mProgressDag = new ProgressDialog(this);
		mProgressDag.setMessage("正在联网，请稍后...");
		mProgressDag.setCancelable(true);

		mHttpClient = new AsyncHttpClient();

		getSupportFragmentManager().beginTransaction().add(R.id.container, FragmentFunctionPage.create()).commit();

		//checkUpdate();

	}


	/**
	 * 提示用户升级
	 */
	@SuppressLint("NewApi")
	private void showUpdate() {
		AlertDialog.Builder alertBuild = new AlertDialog.Builder(this);
		alertBuild.setMessage(mUpdateInfo.updateInfo);
		alertBuild.setTitle("版本更新");
		alertBuild.setIcon(R.drawable.ic_launcher);
		alertBuild.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				try {
					Uri uri = Uri.parse(mUpdateInfo.downloadUrl);
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(intent);
				} catch (Exception e) {
					e.printStackTrace();
				}
				ActivityMain.this.finish();
				KernelManager._GetObject().exitApp();
			}
		});

		alertBuild.setOnDismissListener(new DialogInterface.OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface arg0) {
				// TODO Auto-generated method stub
				ActivityMain.this.finish();
				KernelManager._GetObject().exitApp();
			}
		});
		alertBuild.create().show();
	}

	/**
	 * 更新检查
	 */
	private void checkUpdate() {
		mHttpClient.post(this, IConstants.REQUEST_SERVER_URL, DataPackage.updateCheck(), new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				try {
					mUpdateInfo = DataParse.parseUpdateInfo(response);
					if (KernelManager._GetObject().getMyVersionCode() < mUpdateInfo.versionCode) {
						// 有新的版本
						showUpdate();
					}
				} catch (KernelException e) {
					// TODO Auto-generated catch block
					Toast.makeText(ActivityMain.this, e.getMessage(), Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				Toast.makeText(ActivityMain.this, R.string.error_network, Toast.LENGTH_SHORT).show();
				Log.i(IConstants.TAG, "checkUpdate.statusCode:" + statusCode + " errorResponse:" + errorResponse);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				Toast.makeText(ActivityMain.this, R.string.error_network, Toast.LENGTH_SHORT).show();
				Log.i(IConstants.TAG, "checkUpdate.statusCode:" + statusCode + " responseString:" + responseString);
			}
		});
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		final int FRAGMENT_COUNT = getSupportFragmentManager().getBackStackEntryCount();
		if (FRAGMENT_COUNT > 0) {
			super.onBackPressed();
		} else if (mNextBackExit) {
			// 退出应用
			finish();
			KernelManager._GetObject().exitApp();
		} else {
			mNextBackExit = true;
			Toast.makeText(getApplicationContext(), R.string.exit_tip, Toast.LENGTH_SHORT).show();
			findViewById(R.id.container).postDelayed(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					mNextBackExit = false;
				}
			}, 2000);
		}
	}

}