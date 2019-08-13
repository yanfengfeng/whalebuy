package com.android.whalebuy.fragment.my;

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
 *  关于我们
 *
 *
 */

public class AboutUsFragment extends BaseFragment implements OnClickListener, OnDismissListener {
    @Override
    public void onDismiss(DialogInterface dialog) {

    }

    public static interface ILoginSuccess {
        void onLoginSuccess();
    }

    private View mRootView; // 根视图
    private ILoginSuccess mLoginSuccessListen;

    private LinearLayout  llgengxin;
    private LinearLayout  llpingfen;
    private LinearLayout  llxieyi;
    private LinearLayout  llyinsi;
    private TextView tvFanHui;



public static AboutUsFragment create( ) {
        AboutUsFragment fragment = new AboutUsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_about_us, container, false);



        tvFanHui = (TextView) (mRootView.findViewById(R.id.tv_top_left));// 取消
        tvFanHui.setOnClickListener(this);

        llgengxin = (LinearLayout) (mRootView.findViewById(R.id.ll_gengxin));//
        llgengxin.setOnClickListener(this);
        llpingfen = (LinearLayout) (mRootView.findViewById(R.id.ll_pingfen));//
        llpingfen.setOnClickListener(this);
        llxieyi = (LinearLayout) (mRootView.findViewById(R.id.ll_xieyi));//
        llxieyi.setOnClickListener(this);
        llyinsi = (LinearLayout) (mRootView.findViewById(R.id.ll_yinsi));//
        llyinsi.setOnClickListener(this);


        return mRootView;
    }





    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_top_left:
            Toast.makeText(getActivity(), "取消", Toast.LENGTH_SHORT).show();
                getFragmentManager().popBackStack();

                break;

            case R.id.ll_gengxin:
                Toast.makeText(getActivity(), "更新", Toast.LENGTH_SHORT).show();
                break;

            case R.id.ll_pingfen:
                Toast.makeText(getActivity(), "评分", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_xieyi:
                Toast.makeText(getActivity(), "协议", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_yinsi:
                Toast.makeText(getActivity(), "隐私", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
