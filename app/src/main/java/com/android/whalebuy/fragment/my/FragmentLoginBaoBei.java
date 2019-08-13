//package com.android.whalebuy.fragment.my;
//
//import android.app.AlertDialog;
//import android.app.ProgressDialog;
//import android.content.DialogInterface;
//import android.content.DialogInterface.OnDismissListener;
//import android.os.Bundle;
//import android.os.CountDownTimer;
//import android.preference.PreferenceActivity;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//import com.android.whalebuy.IConstants;
//import com.android.whalebuy.R;
//import com.android.whalebuy.common.BaseFragment;
//import com.android.whalebuy.kernel.DataPackage;
//import com.android.whalebuy.kernel.DataParse;
//import com.android.whalebuy.kernel.KernelException;
//import com.android.whalebuy.widget.RegexUtil;
//import com.android.whalebuy.widget.ToastUtil;
//import com.loopj.android.http.AsyncHttpClient;
//import com.loopj.android.http.JsonHttpResponseHandler;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//public class FragmentLoginBaoBei extends BaseFragment implements OnClickListener, OnDismissListener {
//	private View mRootView; // 根视图
//	private AsyncHttpClient mHttpClient;
//	private ProgressDialog mProgressDag;
//	private FragmentLogin.ILoginSuccess mLoginSuccessListen;
//
//	private TimeCount mTimer;
//	private boolean mIsLogin = true; //是否正在登录
//	private String mCheckCode = ""; //验证码
//
//	public static FragmentLoginBaoBei create(FragmentLogin.ILoginSuccess listen) {
//		FragmentLoginBaoBei fragment = new FragmentLoginBaoBei();
//		fragment.mLoginSuccessListen = listen;
//		return fragment;
//	}
//
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		mRootView = inflater.inflate(R.layout.fragment_login_bb, container, false);
//		TextView txtView = (TextView) (mRootView.findViewById(R.id.ID_TXT_TITLE));
//		txtView.setText(R.string.login);
//
//		mTimer = new TimeCount(60000, 1000);// 构造CountDownTimer倒计时对象
//		mRootView.findViewById(R.id.ID_BTN_CODE).setOnClickListener(this);
//		mRootView.findViewById(R.id.ID_BTN_LOGIN).setOnClickListener(this);
//
//		mProgressDag = new ProgressDialog(getActivity());
//		mProgressDag.setMessage(getString(R.string.loading));
//		mProgressDag.setOnDismissListener(this);
//		mProgressDag.setCancelable(true);
//		mHttpClient = new AsyncHttpClient(); // 请求类
//
//		return mRootView;
//	}
//
//	private void requestGetAuthCode() { // 验证码
//
//		mProgressDag.show();
//		mHttpClient.get(getActivity(), IConstants.REQUEST_SERVER_URL, DataPackage.getCommonMessage("wocode"),
//				new JsonHttpResponseHandler() {
//			@Override
//			public void onSuccess(int statusCode, PreferenceActivity.Header[] headers, JSONObject response) {
//						mProgressDag.dismiss();
//
//						try {
//							mCheckCode = DataParse.parseCheckcode(response);
//							Button button = (Button)(mRootView.findViewById(R.id.ID_BTN_CODE));
//							button.setText(mCheckCode);
//						} catch (KernelException e) {
//							  Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
//						}
//						catch (Exception e)
//						{
//							Toast.makeText(getActivity(), R.string.error_server, Toast.LENGTH_SHORT).show();
//						}
//
//					}
//
//					@Override
//					public void onFailure(int statusCode, PreferenceActivity.Header[] headers, Throwable throwable, JSONObject errorResponse) {
//						mProgressDag.dismiss();
//						Toast.makeText(getActivity(), R.string.error_network, Toast.LENGTH_SHORT).show();
//						Log.i(IConstants.TAG, "requestGetAuthCode.onFailure statusCode:" + statusCode
//								+ ", errorResponse:" + errorResponse);
//					}
//
//					@Override
//					public void onFailure(int statusCode, PreferenceActivity.Header[] headers, String responseString, Throwable throwable) {
//						mProgressDag.dismiss();
//						Toast.makeText(getActivity(), R.string.error_network, Toast.LENGTH_SHORT).show();
//						Log.i(IConstants.TAG, "requestGetAuthCode.onFailure statusCode:" + statusCode
//								+ ", responseString:" + responseString);
//					}
//				});
//
//	}
//
//	/**
//	 * 登录失败
//	 */
//	private void loginError()
//	{
//		AlertDialog.Builder alertBuild = new AlertDialog.Builder(getActivity());
//		alertBuild.setMessage("用户密码错误，是否重置密码？");
//		alertBuild.setTitle(R.string.app_name);
//		alertBuild.setIcon(R.drawable.ic_launcher);
//		alertBuild.setPositiveButton("确定",
//		        new DialogInterface.OnClickListener()
//		        {
//			        @Override
//			        public void onClick(DialogInterface dialog, int which)
//			        {
//				        // TODO Auto-generated method stub
//			        	mIsLogin = false;
//			        	mCheckCode  = "";
//			        	EditText edtPhone = (EditText)(mRootView.findViewById(R.id.ID_EDIT_PHONE));
//			        	edtPhone.setEnabled(false);
//
//			        	EditText edtPwd = (EditText)(mRootView.findViewById(R.id.ID_EDIT_PWD));
//						EditText edtCode = (EditText)(mRootView.findViewById(R.id.ID_EDIT_CODE));
//						Button button = (Button)(mRootView.findViewById(R.id.ID_BTN_CODE));
//
//						edtPwd.setText("");
//						edtCode.setText("");
//						button.setText("显示验证码");
//
//			        	TextView txtView = (TextView) (mRootView.findViewById(R.id.ID_TXT_TITLE));
//			    		txtView.setText("重置密码");
//			        }
//		        });
//		alertBuild.setNegativeButton("取消", null);
//		alertBuild.create().show();
//	}
//
//	private void userLogin(String phone, String password) {  // 登录
//
//		mProgressDag.show();
//		mHttpClient.post(getActivity(), IConstants.REQUEST_SERVER_URL, DataPackage.userLoginByPassword(phone, password, mIsLogin),
//				new JsonHttpResponseHandler() {
//					@Override
//					public void onSuccess(int statusCode, PreferenceActivity.Header[] headers, JSONObject response) {
//						mProgressDag.dismiss();
//
//						try {
//							DataParse.parseUserInfo(response);
//							getFragmentManager().popBackStack();
//
//						} catch (KernelException e) {
//							  //Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
//							  loginError();
//						} catch (JSONException e) {
//							 Toast.makeText(getActivity(), R.string.error_server, Toast.LENGTH_SHORT).show();
//						}
//					}
//
//					@Override
//					public void onFailure(int statusCode, PreferenceActivity.Header[] headers, Throwable throwable, JSONObject errorResponse) {
//						mProgressDag.dismiss();
//						Toast.makeText(getActivity(), R.string.error_network, Toast.LENGTH_SHORT).show();
//					}
//
//					@Override
//					public void onFailure(int statusCode, PreferenceActivity.Header[] headers, String responseString, Throwable throwable) {
//						mProgressDag.dismiss();
//						Toast.makeText(getActivity(), R.string.error_network, Toast.LENGTH_SHORT).show();
//					}
//				});
//	}
//
//	@Override
//	public void onDismiss(DialogInterface dialog) {
//		if (mProgressDag == dialog) {
//			mHttpClient.cancelAllRequests(true);
//		}
//	}
//
//	@Override
//	public void onDestroyView() {
//		super.onDestroyView();
//		mHttpClient.cancelAllRequests(true);
//	}
//
//
//	// 生成验证码倒计时的类
//		class TimeCount extends CountDownTimer {
//
//			public TimeCount(long millisInFuture, long countDownInterval) {
//				// 参数依次为总时长和计时时间间隔
//				super(millisInFuture, countDownInterval);
//			}
//
//			// 计时过程显示
//			@Override
//			public void onTick(long millisUntilFinished) {
//				Button btnSendCode = (Button)(mRootView.findViewById(R.id.ID_BTN_CODE));
//				btnSendCode.setClickable(false);
//				btnSendCode.setText(millisUntilFinished / 2000 + "秒");
//
//			}
//
//			// 计时完毕时触发
//			@Override
//			public void onFinish() {
//				Button btnSendCode = (Button)(mRootView.findViewById(R.id.ID_BTN_CODE));
//				btnSendCode.setText("重新验证");
//				btnSendCode.setClickable(true);// 重新点击获取验证码
//			}
//
//		}
//
//
//
//	@Override
//	public void onClick(View v) {
//		EditText edtPhone = (EditText)(mRootView.findViewById(R.id.ID_EDIT_PHONE));
//		String phone = edtPhone.getText().toString().trim();
//
//		switch (v.getId()) {
//		case R.id.ID_BTN_CODE:
//			if (!(RegexUtil.isValidMobiNumber(phone))) {
//				ToastUtil.showInfo(getActivity(), "请输入正确的11位手机号码！", false);
//				return;
//			}
//
//			if (!TextUtils.isEmpty(phone)) {
//				requestGetAuthCode();
//			}
//			break;
//
//		case R.id.ID_BTN_LOGIN:
//			EditText edtPwd = (EditText)(mRootView.findViewById(R.id.ID_EDIT_PWD));
//			EditText edtCode = (EditText)(mRootView.findViewById(R.id.ID_EDIT_CODE));
//
//			String code = edtCode.getText().toString().trim();
//			String passwd = edtPwd.getText().toString().trim();
//			if ("".equals(phone)) {
//				ToastUtil.showInfo(getActivity(), "手机号不能为空！", false);
//				return;
//			}
//
//			if ("".equals(code)) {
//				ToastUtil.showInfo(getActivity(), "验证码不能为空！", false);
//				return;
//			}
//
//			if ("".equals(passwd)) {
//				ToastUtil.showInfo(getActivity(), "密码不能为空！", false);
//				return;
//			}
//
//			if (!(RegexUtil.isValidMobiNumber(phone))) {
//				ToastUtil.showInfo(getActivity(), "请输入正确的11位手机号码！", false);
//				return;
//			}
//
//			if (!(RegexUtil.isValidMobiCode(code))) {
//				ToastUtil.showInfo(getActivity(), "请输入正确的6位验证码！", false);
//				return;
//			}
//
//			if (false == code.equals(mCheckCode))
//			{
//				ToastUtil.showInfo(getActivity(), "验证码错误, 请输入正确的验证码", false);
//				return;
//			}
//
//			userLogin(phone, passwd);
//			break;
//
//		}
//
//	}
//}
