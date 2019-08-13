package com.android.whalebuy.fragment.home;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;
import com.android.whalebuy.R;
import com.android.whalebuy.common.BaseFragment;
import com.android.whalebuy.widget.ToastUtil;
import com.loopj.android.http.AsyncHttpClient;

/**
 *
 * 筛选
 *
 *
 */

public class ShaiXuanFragment extends BaseFragment implements OnClickListener, OnDismissListener {
    @Override
    public void onDismiss(DialogInterface dialog) {

    }

    public static interface ILoginSuccess {
        void onLoginSuccess();
    }

    private View mRootView; // 根视图
    private AsyncHttpClient mHttpClient;
    private ProgressDialog mProgressDag;
    private ILoginSuccess mLoginSuccessListen;

    private EditText edgw;

    private TextView tvFanHui;

    Button  btfd;

    private String gaveWord;
    private ImageView imgUnLook, imgLook;

public static ShaiXuanFragment create( ) {
        ShaiXuanFragment fragment = new ShaiXuanFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.shaixuan, container, false);


//
//        tvFanHui = (TextView) (mRootView.findViewById(R.id.tv_left));// 取消
//        tvFanHui.setOnClickListener(this);
//
//        edgw = (EditText) mRootView.findViewById(R.id.edtxt_gave_word); //  输入反馈
//
//        btfd=(Button) mRootView.findViewById(R.id.feedback_btn); //
//        btfd.setOnClickListener(this);

        mProgressDag = new ProgressDialog(getActivity());
        mProgressDag.setMessage(getString(R.string.loading));
        mProgressDag.setOnDismissListener(this);
        mProgressDag.setCancelable(true);
        mHttpClient = new AsyncHttpClient(); // 请求类
        return mRootView;
    }




    private void userMobileRegister() {  // 反馈
//        edgw = (EditText) mRootView.findViewById(R.id.edtxt_gave_word); // 反馈
//
//        String gaveWord = edgw.getText().toString();
//
//       mProgressDag.show();
//        mHttpClient.post(getActivity(), IConstants.URL_MOBILEREGISTER, DataPackage.userMobileRegister(gaveWord),
//                new JsonHttpResponseHandler() {
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                        super.onSuccess(statusCode, headers, response);
//                        mProgressDag.dismiss();
//
//                        try {
//
//                            DataParse.checkException(response);
//                            String  msg =response.optString("msg");
//                            ToastUtil.showInfo(getActivity(), msg, false);
//                         //   getFragmentManager().popBackStack();
//
//
//                        } catch (KernelException e) {
//                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    @Override
//                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                        super.onFailure(statusCode, headers, throwable, errorResponse);
//
//                        mProgressDag.dismiss();
//                        Toast.makeText(getActivity(), R.string.error_network, Toast.LENGTH_SHORT).show();
//                    }
//
//                });
    }

//
//    private void requestFeedBack() {
//
//        String token = SharedPreferencesManager.getValue(IConstants.URL_TONKEN);
//        Log.i("2222222222222222", token + "he" + mContent);
//        RequestParams requestParams = new RequestParams();
//        requestParams.add("token", token);
//        requestParams.add("content", mContent);
//
//        Log.i("2222222222222222", token + "he" + mContent);
//        HttpUtil.get(IConstants.URL_USER_FEEDBACK, requestParams, new AsyncHttpResponseHandler() {
//
//            @Override
//            public void onSuccess(String response) {
//
//                Log.i("_+++返回回来的参数是+++_", response.toString());
//
//                try {
//                    JSONObject object = new JSONObject(response);
//
//                    int status = object.getInt("status");
//                    if (status == IConstants.ERROR_CODE_SUCCESS) {
//
//                        edContent.setText("");
//                        mContent = "";
//                        ToastUtil.showInfo(getApplicationContext(), "反馈成功", false);
//                        return;
//                    }
//
//                    String message = object.optString("message");
//                    Toast.makeText(FeedbackActivity.this, message, Toast.LENGTH_SHORT).show();
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//        });
//
//    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {
//            case R.id.id_tv_left:
//            Toast.makeText(getActivity(), "取消", Toast.LENGTH_SHORT).show();
//                getFragmentManager().popBackStack();
//
//                break;
//
//            case R.id.feedback_btn:
//                gaveWord = edgw.getText().toString().trim();
//                if ("".equals(gaveWord)) {
//                    ToastUtil.showInfo(getActivity(), "反馈不能为空！", false);
//                return;
//            }
//
//
//                userMobileRegister();
//                break;

        }

    }
}
