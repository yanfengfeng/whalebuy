package com.android.whalebuy.fragment.my;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
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

public class LoginFragment extends BaseFragment implements OnClickListener, OnDismissListener {


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
    private EditText edZhangHao;
    private Button btnSendCode;

    private TextView tvSendCode;

    private TextView tvWeiXin;
    private TextView tvYiJian;
    private TextView tvWangJiPsw;


    private Button btnSuccess_phone;
    private Button btnSuccess_zh;
    private String phone, passwd,tvCood,tv_ZhangHao;
    private ImageView imgUnLook, imgLook;


    private TextView tvFanHui;
    private TextView tvQuZhuCe;

    private LinearLayout ll_Login_phone;
    private LinearLayout ll_Login_zh;

    private TextView tvLogin_phone;
    private TextView tvLogin_zh;

    private CheckBox cbJi;

    private View v_phone;

    private View v_zh;


    public static LoginFragment create(ILoginSuccess listen) {
        LoginFragment fragment = new LoginFragment();
        fragment.mLoginSuccessListen = listen;
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.login_denglu_zh, container, false);

//        TextView txtView = (TextView) (mRootView.findViewById(R.id.ID_TXT_TITLE));
//        txtView.setText(R.string.login);




        time = new TimeCount(60000, 1000);// 构造CountDownTimer倒计时对象

        tvFanHui = (TextView) mRootView.findViewById(R.id.id_tv_left); // 返回
        tvFanHui.setOnClickListener(this);
        tvQuZhuCe = (TextView) mRootView.findViewById(R.id.id_tv_qu_zhuce); // 去注册
        tvQuZhuCe.setOnClickListener(this);

        v_zh = (View) mRootView.findViewById(R.id.id_v_zh);
        v_phone = (View) mRootView.findViewById(R.id.id_v_phone);

        tvLogin_phone = (TextView) mRootView.findViewById(R.id.id_tv_phone_login); // 手机登录
        tvLogin_phone.setOnClickListener(this);

        tvLogin_zh = (TextView) mRootView.findViewById(R.id.id_tv_zh_login); // 账号登录
        tvLogin_zh.setOnClickListener(this);


        ll_Login_phone = (LinearLayout) mRootView.findViewById(R.id.ll_login_phone); // 手机登录界面
        ll_Login_zh = (LinearLayout) mRootView.findViewById(R.id.ll_login_zhanghao); // 账号登录界面


        // 手机登录


        edtPhone = (EditText) mRootView.findViewById(R.id.id_edit_phone); // 电话
        tvSendCode = (TextView) mRootView.findViewById(R.id.id_tv_code); // 获取验证码
        tvSendCode.setOnClickListener(this);
        edtCode = (EditText) mRootView.findViewById(R.id.id_edit_code); // 输入验证码
        btnSuccess_phone = (Button) mRootView.findViewById(R.id.id_btn_phone_zhuce); // 登录
        btnSuccess_phone.setOnClickListener(this);




        //账号登录

        edZhangHao = (EditText) mRootView.findViewById(R.id.ed_zh_zhanghao); // 账号
        edPsw = (EditText) mRootView.findViewById(R.id.id_edit_pwd); //  输入密码
        edPsw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        edPsw.setTransformationMethod(PasswordTransformationMethod.getInstance());
        imgUnLook = (ImageView) mRootView.findViewById(R.id.id_btn_unlook); // 不可见
        imgUnLook.setOnClickListener(this);
        imgLook = (ImageView) mRootView.findViewById(R.id.id_btn_look); // 可见
        imgLook.setOnClickListener(this);
        cbJi = (CheckBox) mRootView.findViewById(R.id.id_cb_fwd); // 记住密码
        tvWangJiPsw = (TextView) mRootView.findViewById(R.id.id_tv_wj_fwd); // 忘记密码
        tvWangJiPsw.setOnClickListener(this);
        btnSuccess_zh = (Button) mRootView.findViewById(R.id.btn_zh_zhuce); // 登录
        btnSuccess_zh.setOnClickListener(this);




        tvWeiXin = (TextView) mRootView.findViewById(R.id.id_tv_weixin); // 微信
        tvWeiXin.setOnClickListener(this);
        tvYiJian = (TextView) mRootView.findViewById(R.id.id_tv_yijian); // 一键登录
        tvYiJian.setOnClickListener(this);



//        btnSuccess = (Button) mRootView.findViewById(R.id.id_btn_zhuce); // 注册
//        btnSuccess.setOnClickListener(this);



        mProgressDag = new ProgressDialog(getActivity());
        mProgressDag.setMessage(getString(R.string.loading));
        mProgressDag.setOnDismissListener(this);
        mProgressDag.setCancelable(true);
        mHttpClient = new AsyncHttpClient(); // 请求类

        return mRootView;
    }

    // 验证码
  private void requestGetAuthCode() {

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

                            String  result =response.optString("result");


                            if(code==200){

                                ToastUtil.showInfo(getActivity(), "验证码已经发送到您的手机", false);
                                time.start();
                            }else{

                                ToastUtil.showInfo(getActivity()  , result, false);

                            }



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
    //登录
    private void userLogin(String phone,int status) {
        edtPhone = (EditText) mRootView.findViewById(R.id.id_edit_phone); // 电话
        edPsw = (EditText) mRootView.findViewById(R.id.id_edit_pwd); // 输入密码
        edtCode = (EditText) mRootView.findViewById(R.id.id_edit_code); // 输入验证码

         String tvCood= edtCode.getText().toString();
         String password = edPsw.getText().toString();

        mProgressDag.show();
        mHttpClient.post(getActivity(), IConstants.URL_MOBILELOGIN, DataPackage.userMobileLogin(phone,status,tvCood ,password),
                new JsonHttpResponseHandler() {


                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        mProgressDag.dismiss();

                        try {

                            DataParse.parseUserInfo(response);

                            int code= response.optInt("code");
                            String  msg =response.optString("msg");

                            if(code==200){
                                mLoginSuccessListen.onLoginSuccess();
                                getFragmentManager().popBackStack();
                                ToastUtil.showInfo(getActivity(), "登录成功", false);

                            }

                        } catch (KernelException e) {
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
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
                //返回
                ToastUtil.showInfo(getActivity(), "返回！", false);
                break;

            case R.id.id_tv_qu_zhuce:
                //去注册
                ToastUtil.showInfo(getActivity(), "去注册！", false);

                getFragmentManager().beginTransaction().add(R.id.container
                        ,LoginRegisterFragment.create())
                        .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();
                break;

            case R.id.id_tv_phone_login:
                //手机登录
                ToastUtil.showInfo(getActivity(), "手机登录！", false);

                ll_Login_phone.setVisibility(View.VISIBLE);
                ll_Login_zh.setVisibility(View.GONE);

                v_phone.setVisibility(View.VISIBLE);
                v_zh.setVisibility(View.INVISIBLE);

                tvLogin_phone.setTextColor(Color.parseColor("#FF4AA8FF"));
                tvLogin_zh.setTextColor(Color.parseColor("#000000"));
                break;

            case R.id.id_tv_zh_login:
                //账号登录
                ToastUtil.showInfo(getActivity(), "账号登录！", false);

                ll_Login_phone.setVisibility(View.GONE);
                ll_Login_zh.setVisibility(View.VISIBLE);

                tvLogin_phone.setTextColor(Color.parseColor("#000000"));
                tvLogin_zh.setTextColor(Color.parseColor("#FF4AA8FF"));

                v_phone.setVisibility(View.INVISIBLE);
                v_zh.setVisibility(View.VISIBLE);
                break;

            //手机登录

            case R.id.id_tv_code:

                //发送验证码
                //     ToastUtil.showInfo(getActivity(), "验证码！", false);

                phone = edtPhone.getText().toString().trim();
                tvCood=edtCode.getText().toString().trim();

                if (!(RegexUtil.isValidMobiNumber(phone))) {
                    ToastUtil.showInfo(getActivity(), "请输入正确的11位手机号码！", false);
                    return;
                }

                if (!TextUtils.isEmpty(phone)) {

                    requestGetAuthCode();

                }
                break;

            case R.id.id_btn_phone_zhuce:

                ToastUtil.showInfo(getActivity(), "电话登录", false);
                phone=edtPhone.getText().toString().trim();
                passwd = edtCode.getText().toString().trim();
                if ("".equals(phone)) {
                    ToastUtil.showInfo(getActivity(), "手机号不能为空！", false);
                    return;
                }

               if (passwd.length() < 6) {
                    ToastUtil.showInfo(getActivity(), "请输入正确的6位验证码！", false);
                    return;
                }

                if (!(RegexUtil.isValidMobiNumber(phone))) {
                    ToastUtil.showInfo(getActivity(), "请输入正确的11位手机号码！", false);
                    return;
                }

                userLogin(phone,0);
                break;

          //  账号登录


            case R.id.id_cb_fwd:
                //记住密码
                ToastUtil.showInfo(getActivity(), "记住密码！", false);
                break;

            case R.id.id_tv_wj_fwd:
                //忘记密码
                ToastUtil.showInfo(getActivity(), "忘记密码！", false);

                getFragmentManager().beginTransaction().add(R.id.container
                        ,LoginForgetFragment.create())
                        .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();

                break;

            case R.id.id_btn_look:
                //密码可见
                imgUnLook.setVisibility(View.VISIBLE);
                imgLook.setVisibility(View.GONE);
               edPsw.setTransformationMethod(PasswordTransformationMethod.getInstance());//密码可见

                break;

            case R.id.id_btn_unlook:
                //密码不可见
                imgUnLook.setVisibility(View.GONE);
                imgLook.setVisibility(View.VISIBLE);
                edPsw.setTransformationMethod(HideReturnsTransformationMethod.getInstance()); //密码不可见


                break;
            case R.id.btn_zh_zhuce:

                phone = edZhangHao.getText().toString().trim();
                passwd = edPsw.getText().toString().trim();
                if ("".equals(phone)) {
                ToastUtil.showInfo(getActivity(), "账号不能为空！", false);
                return;
            }
                if ("".equals(passwd)) {
                    ToastUtil.showInfo(getActivity(), "密码不能为空！", false);
                    return;
                }

                if (!(RegexUtil.isPassword(passwd))) {
                    ToastUtil.showInfo(getActivity(), "请输入正确的密码！", false);
                    return;
                }

//                if (!(RegexUtil.isValidMobiNumber(phone))) {
//                    ToastUtil.showInfo(getActivity(), "请输入正确的11位手机号码！", false);
//                    return;
//                }

                userLogin(phone,1);
                break;

            case R.id.id_tv_weixin:
                //微信登录
                ToastUtil.showInfo(getActivity(), "微信登录！", false);
                break;
            case R.id.id_tv_yijian:
                //一键登录




                ToastUtil.showInfo(getActivity(), "一键登录！", false);
                break;


        }

    }
}
