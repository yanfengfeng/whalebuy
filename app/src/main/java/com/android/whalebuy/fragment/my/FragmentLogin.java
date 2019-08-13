//package com.android.whalebuy.fragment.my;
//
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
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//public class FragmentLogin extends BaseFragment implements OnClickListener, OnDismissListener {
//    public static interface ILoginSuccess {
//        void onLoginSuccess();
//    }
//
//    private View mRootView; // 根视图
//    private AsyncHttpClient mHttpClient;
//    private ProgressDialog mProgressDag;
//    private ILoginSuccess mLoginSuccessListen;
//
//    private TimeCount time;
//    private EditText edtPhone;
//    private EditText edtCode;
//    private Button btnSendCode;
//    private Button btnSuccess;
//    private String phone, passwd;
//
//    public static FragmentLogin create(ILoginSuccess listen) {
//        FragmentLogin fragment = new FragmentLogin();
//        fragment.mLoginSuccessListen = listen;
//        return fragment;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        mRootView = inflater.inflate(R.layout.fragment_login, container, false);
//
//        TextView txtView = (TextView) (mRootView.findViewById(R.id.ID_TXT_TITLE));
//        txtView.setText(R.string.login);
//
//        time = new TimeCount(60000, 1000);// 构造CountDownTimer倒计时对象
//        edtPhone = (EditText) mRootView.findViewById(R.id.ET_PHONE); // 电话
//        edtCode = (EditText) mRootView.findViewById(R.id.ET_INPUT_CODE); // 输入验证码
//        btnSendCode = (Button) mRootView.findViewById(R.id.BTN_CODE); // 发送短信
//        btnSendCode.setOnClickListener(this);
//        btnSuccess = (Button) mRootView.findViewById(R.id.LOGIN_BTN_SUCCESS); // 登陆
//        btnSuccess.setOnClickListener(this);
//
//        mProgressDag = new ProgressDialog(getActivity());
//        mProgressDag.setMessage(getString(R.string.loading));
//        mProgressDag.setOnDismissListener(this);
//        mProgressDag.setCancelable(true);
//        mHttpClient = new AsyncHttpClient(); // 请求类
//
//        return mRootView;
//    }
//
//    private void requestGetAuthCode() { // 验证码
//
//        mProgressDag.show();
//        mHttpClient.post(getActivity(), "http://120.55.26.106:8099/xiaowocode/newlogin.php"
//                , DataPackage.getCheckCode(phone),
//                new JsonHttpResponseHandler() {
//
////                    @Override
////                    public void onSuccess(int statusCode, PreferenceActivity.Header[] headers, JSONObject response) {
////                        mProgressDag.dismiss();
////
////                        try {
////                            DataParse.checkException(response);
////                            ToastUtil.showInfo(getActivity(), "验证码已经发送到您的手机", false);
////                            time.start();
////                        } catch (KernelException e) {
////                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
////                        }
////
////                    }
////
////                    @Override
////                    public void onFailure(int statusCode, PreferenceActivity.Header[] headers, Throwable throwable, JSONObject errorResponse) {
////                        mProgressDag.dismiss();
////                        Toast.makeText(getActivity(), R.string.error_network, Toast.LENGTH_SHORT).show();
////                        Log.i(IConstants.TAG, "requestGetAuthCode.onFailure statusCode:" + statusCode
////                                + ", errorResponse:" + errorResponse);
////                    }
////
////                    @Override
////                    public void onFailure(int statusCode, PreferenceActivity.Header[] headers, String responseString, Throwable throwable) {
////                        mProgressDag.dismiss();
////                        Toast.makeText(getActivity(), R.string.error_network, Toast.LENGTH_SHORT).show();
////                        Log.i(IConstants.TAG, "requestGetAuthCode.onFailure statusCode:" + statusCode
////                                + ", responseString:" + responseString);
////                    }
//                });
//
//    }
//
//    private void userLogin() {  // 登录
//        edtPhone = (EditText) mRootView.findViewById(R.id.ET_PHONE); // 电话
//        edtCode = (EditText) mRootView.findViewById(R.id.ET_INPUT_CODE); // 输入验证码
//        String phone = edtPhone.getText().toString();
//        String password = edtCode.getText().toString();
//
//        mProgressDag.show();
//        mHttpClient.post(getActivity(), IConstants.REQUEST_SERVER_URL, DataPackage.userLogin(phone, password),
//                new JsonHttpResponseHandler() {
////                    @Override
////                    public void onSuccess(int statusCode, PreferenceActivity.Header[] headers, JSONObject response) {
////                        mProgressDag.dismiss();
////
////                        try {
////                            DataParse.parseUserInfo(response);
////
////                            getFragmentManager().popBackStack();
////
////                        } catch (KernelException e) {
////                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
////                        } catch (JSONException e) {
////                            Toast.makeText(getActivity(), R.string.error_server, Toast.LENGTH_SHORT).show();
////                        }
////                    }
////
////                    @Override
////                    public void onFailure(int statusCode, PreferenceActivity.Header[] headers, Throwable throwable, JSONObject errorResponse) {
////                        mProgressDag.dismiss();
////                        Toast.makeText(getActivity(), R.string.error_network, Toast.LENGTH_SHORT).show();
////                    }
////
////                    @Override
////                    public void onFailure(int statusCode, PreferenceActivity.Header[] headers, String responseString, Throwable throwable) {
////                        mProgressDag.dismiss();
////                        Toast.makeText(getActivity(), R.string.error_network, Toast.LENGTH_SHORT).show();
////                    }
//                });
//    }
//
//    @Override
//    public void onDismiss(DialogInterface dialog) {
//        if (mProgressDag == dialog) {
//            mHttpClient.cancelAllRequests(true);
//        }
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        mHttpClient.cancelAllRequests(true);
//    }
//
//
//    // 生成验证码倒计时的类
//    class TimeCount extends CountDownTimer {
//
//        public TimeCount(long millisInFuture, long countDownInterval) {
//            // 参数依次为总时长和计时时间间隔
//            super(millisInFuture, countDownInterval);
//
//        }
//
//        // 计时过程显示
//        @Override
//        public void onTick(long millisUntilFinished) {
//            btnSendCode.setClickable(false);
//            btnSendCode.setText(millisUntilFinished / 1000 + "秒");
//
//        }
//
//        // 计时完毕时触发
//        @Override
//        public void onFinish() {
//            btnSendCode.setText("重新验证");
//            btnSendCode.setClickable(true);// 重新点击获取验证码
//        }
//
//    }
//
//
//    @Override
//    public void onClick(View v) {
//
//        switch (v.getId()) {
//            case R.id.BTN_CODE:
//                phone = edtPhone.getText().toString().trim();
//
//                if (!(RegexUtil.isValidMobiNumber(phone))) {
//                    ToastUtil.showInfo(getActivity(), "请输入正确的11位手机号码！", false);
//                    return;
//                }
//
//                if (!TextUtils.isEmpty(phone)) {
//                    requestGetAuthCode();
//                }
//
//                break;
//
//            case R.id.LOGIN_BTN_SUCCESS:
//
//                phone = edtPhone.getText().toString().trim();
//                passwd = edtCode.getText().toString().trim();
//                if ("".equals(phone)) {
//                    ToastUtil.showInfo(getActivity(), "手机号不能为空！", false);
//                    return;
//                }
//
//                if (passwd.length() < 6) {
//                    ToastUtil.showInfo(getActivity(), "请输入正确的6位验证码！", false);
//                    return;
//                }
//
//                if (!(RegexUtil.isValidMobiNumber(phone))) {
//                    ToastUtil.showInfo(getActivity(), "请输入正确的11位手机号码！", false);
//                    return;
//                }
//
//                userLogin();
//                break;
//
//        }
//
//    }
//}
