package com.android.whalebuy.fragment.my;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;
import com.android.whalebuy.IConstants;
import com.android.whalebuy.R;
import com.android.whalebuy.common.BaseFragment;
import com.android.whalebuy.kernel.DataPackage;
import com.android.whalebuy.kernel.DataParse;
import com.android.whalebuy.kernel.KernelException;
import com.android.whalebuy.widget.RegexUtil;
import com.android.whalebuy.widget.ToastUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 *  忘记密码
 *
 *
 */


public class LoginForgetFragment extends BaseFragment implements OnClickListener, OnDismissListener {
    public static interface ILoginSuccess {
        void onLoginSuccess();
    }

    private View mRootView; // 根视图
    private AsyncHttpClient mHttpClient;
    private ProgressDialog mProgressDag;
    private ILoginSuccess mLoginSuccessListen;

    private TimeCount time;
    private EditText edtPhone;
    private EditText edtCode;
    private EditText edPsw;
    private EditText edPsw1;
    private TextView tvFanHui;
    private Button btnSendCode;

    private TextView tvSendCode;

//    private TextView tvWeiXin;
//    private TextView tvYiJian;

    private Button btnSuccess;
    private String phone, passwd,tvCood,passwd1;
    private ImageView imgUnLook, imgLook,imgUnLook1, imgLook1;

//    public static LoginRegisterFragment create(ILoginSuccess listen) {
public static LoginForgetFragment create( ) {
        LoginForgetFragment fragment = new LoginForgetFragment();
//        fragment.mLoginSuccessListen = listen;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.login_forget, container, false);

        time = new TimeCount(60000, 1000);// 构造CountDownTimer倒计时对象


        tvFanHui = (TextView) (mRootView.findViewById(R.id.id_tv_left));// 取消
        tvFanHui.setOnClickListener(this);

        edtPhone = (EditText) mRootView.findViewById(R.id.id_edit_phone); // 电话
        edPsw = (EditText) mRootView.findViewById(R.id.id_edit_pwd); //  输入密码
        edtCode = (EditText) mRootView.findViewById(R.id.id_edit_code); // 输入验证码
        edPsw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        edPsw.setTransformationMethod(PasswordTransformationMethod.getInstance());

        edPsw1 = (EditText) mRootView.findViewById(R.id.id_edit_pwd1); // 再输入密码
        edPsw1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        edPsw1.setTransformationMethod(PasswordTransformationMethod.getInstance());



        tvSendCode = (TextView) mRootView.findViewById(R.id.id_tv_code); // 发送验证码
        tvSendCode.setOnClickListener(this);


        btnSuccess = (Button) mRootView.findViewById(R.id.id_btn_zhuce); // 注册
        btnSuccess.setOnClickListener(this);

        imgUnLook = (ImageView) mRootView.findViewById(R.id.id_btn_unlook); // 不可见
        imgUnLook.setOnClickListener(this);

        imgLook = (ImageView) mRootView.findViewById(R.id.id_btn_look); // 可见
        imgLook.setOnClickListener(this);


        imgUnLook1 = (ImageView) mRootView.findViewById(R.id.id_btn_unlook1); // 不可见
        imgUnLook1.setOnClickListener(this);

        imgLook1 = (ImageView) mRootView.findViewById(R.id.id_btn_look1); // 可见
        imgLook1.setOnClickListener(this);
        mProgressDag = new ProgressDialog(getActivity());
        mProgressDag.setMessage(getString(R.string.loading));
        mProgressDag.setOnDismissListener(this);
        mProgressDag.setCancelable(true);
        mHttpClient = new AsyncHttpClient(); // 请求类

        return mRootView;
    }


    private void requestGetAuthCode() { // 验证码

        mProgressDag.show();
        mHttpClient.post(getActivity(), IConstants.URL_SEND_CODE  , DataPackage.getCheckCode(phone),

                new JsonHttpResponseHandler() {


                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        mProgressDag.dismiss();
                        try {
                            DataParse.checkException(response);

                            int code= response.optInt("code");
                            String  msg =response.optString("msg");

                                time.start();
                                ToastUtil.showInfo(getActivity()  , msg, false);


                        } catch (KernelException e) {
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);

                        mProgressDag.dismiss();
                        Toast.makeText(getActivity(), R.string.error_network, Toast.LENGTH_SHORT).show();
                    }





                });

    }


    private void userMobileRevise() {  // 修改
        edtPhone = (EditText) mRootView.findViewById(R.id.id_edit_phone); // 电话
        edPsw = (EditText) mRootView.findViewById(R.id.id_edit_pwd); // 输入密码
        edtCode = (EditText) mRootView.findViewById(R.id.id_edit_code); // 输入验证码

        String tvCood= edtCode.getText().toString();
        String phone = edtPhone.getText().toString();
        String password = edPsw1.getText().toString();

        mProgressDag.show();
        mHttpClient.post(getActivity(), IConstants.URL_MOBILEFORGETPWD, DataPackage.userMobileForgetPwd(phone,tvCood ,password),
                new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        mProgressDag.dismiss();

                        try {

                            DataParse.checkException(response);
                            int code= response.optInt("code");
                        //    String  result =response.optString("result");
                            String  msg =response.optString("msg");
//                            if(code==200){

                                ToastUtil.showInfo(getActivity(), msg, false);
                                time.start();
//                            }else{
//
//                                    ToastUtil.showInfo(getActivity()  , msg, false);
//
//                            }
                         //   getFragmentManager().popBackStack();

                        } catch (KernelException e) {
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);

                        mProgressDag.dismiss();
                        Toast.makeText(getActivity(), R.string.error_network, Toast.LENGTH_SHORT).show();
                    }

                });
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (mProgressDag == dialog) {
            mHttpClient.cancelAllRequests(true);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHttpClient.cancelAllRequests(true);
    }


    // 生成验证码倒计时的类
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            // 参数依次为总时长和计时时间间隔
            super(millisInFuture, countDownInterval);

        }

        // 计时过程显示
        @Override
        public void onTick(long millisUntilFinished) {
            tvSendCode.setClickable(false);
            tvSendCode.setText(millisUntilFinished / 1000 + "秒");

        }

        // 计时完毕时触发
        @Override
        public void onFinish() {
            tvSendCode.setText("重新验证");
            tvSendCode.setClickable(true);// 重新点击获取验证码
        }

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.id_tv_left:
                 Toast.makeText(getActivity(), "取消", Toast.LENGTH_SHORT).show();
                getFragmentManager().popBackStack();

                break;
            case R.id.id_tv_code:

                //发送验证码
           //     ToastUtil.showInfo(getActivity(), "验证码！", false);

                phone = edtPhone.getText().toString().trim();
//                edtCode.setTransformationMethod(PasswordTransformationMethod.getInstance());
                tvCood=edtCode.getText().toString().trim();

                if (!(RegexUtil.isValidMobiNumber(phone))) {
                    ToastUtil.showInfo(getActivity(), "请输入正确的11位手机号码！", false);
                    return;
                }

                if (!TextUtils.isEmpty(phone)) {

                    requestGetAuthCode();

                }
                break;


            case R.id.id_btn_unlook:
                //密码不可见
                imgUnLook.setVisibility(View.GONE);
                imgLook.setVisibility(View.VISIBLE);
                edPsw.setTransformationMethod(HideReturnsTransformationMethod.getInstance()); //密码不可见


                break;

            case R.id.id_btn_look:
                //密码可见
                imgUnLook.setVisibility(View.VISIBLE);
                imgLook.setVisibility(View.GONE);
                edPsw.setTransformationMethod(PasswordTransformationMethod.getInstance());//密码可见

                break;

            case R.id.id_btn_look1:
                //密码可见
                imgUnLook1.setVisibility(View.VISIBLE);
                imgLook1.setVisibility(View.GONE);
                edPsw1.setTransformationMethod(PasswordTransformationMethod.getInstance());//密码可见


                break;


            case R.id.id_btn_unlook1:
                //密码不可见
                imgUnLook1.setVisibility(View.GONE);
                imgLook1.setVisibility(View.VISIBLE);
                edPsw1.setTransformationMethod(HideReturnsTransformationMethod.getInstance()); //密码不可见


                break;
            case R.id.id_btn_zhuce:
                tvCood=edtCode.getText().toString().trim();
                phone = edtPhone.getText().toString().trim();
                passwd = edPsw.getText().toString().trim();

                passwd1 = edPsw1.getText().toString().trim();
                if ("".equals(phone)) {
                ToastUtil.showInfo(getActivity(), "手机号不能为空！", false);
                return;
            }
                if ("".equals(passwd)) {
                    ToastUtil.showInfo(getActivity(), "密码不能为空！", false);
                    return;
                }
                if ("".equals(passwd1)) {
                    ToastUtil.showInfo(getActivity(), "密码不能为空！", false);
                    return;
                }
                if ("".equals(tvCood)) {
                    ToastUtil.showInfo(getActivity(), "验证码不能为空！", false);
                    return;
                }
                int passwdLen = passwd.length();
                if (passwdLen < 6 || passwdLen > 12) {
                    ToastUtil.showInfo(getActivity(), "密码长度为6～12位，请重新输入!", false);
                    return;
                }

                if (!(RegexUtil.isPassword(passwd))) {
                    ToastUtil.showInfo(getActivity(), "请输入正确的密码！", false);
                    return;
                }

                if (!(passwd.equals(passwd1))) {
                    ToastUtil.showInfo(getActivity(), "两次密码不一致！", false);
                    return;
                }

                if (!(RegexUtil.isValidMobiNumber(phone))) {
                    ToastUtil.showInfo(getActivity(), "请输入正确的11位手机号码！", false);
                    return;
                }

                userMobileRevise();
                break;

        }

    }
}
