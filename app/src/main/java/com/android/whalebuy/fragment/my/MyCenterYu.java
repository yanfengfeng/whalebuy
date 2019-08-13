package com.android.whalebuy.fragment.my;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.whalebuy.R;
import com.android.whalebuy.common.BaseFragment;
import com.android.whalebuy.listener.IFragmentListener;

/**
 *余额
 *
 *
 */
public class MyCenterYu extends BaseFragment implements View.OnClickListener,IFragmentListener {
    View mRootView;

    private TextView tvFanKui;
    private TextView tvGuiZe;
    private TextView tvYu;
    private LinearLayout llTiXian;

    public static MyCenterYu create() {
        return new MyCenterYu();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.my_center_yu, container, false);

        tvFanKui = (TextView) (mRootView.findViewById(R.id.tv_left));// 返回
        tvFanKui.setOnClickListener(this);


        tvYu = (TextView) (mRootView.findViewById(R.id.tv_yu));// 余额

        tvGuiZe = (TextView) (mRootView.findViewById(R.id.tv_right));// 提现规则s
        tvGuiZe.setOnClickListener(this);

        llTiXian = (LinearLayout) (mRootView.findViewById(R.id.ll_tixian));// 余额提现
        llTiXian.setOnClickListener(this);

        return mRootView;
    }

    @Override
    public void fragmentWillShow() {

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){

           case R.id.tv_left:
               Toast.makeText(getActivity(), "返回", Toast.LENGTH_SHORT).show();
//               getFragmentManager().beginTransaction().add(R.id.container
//                       ,MyActivitySetting.create())
//                       .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();

           break;
            case R.id.tv_right:
                Toast.makeText(getActivity(), "跳转规则", Toast.LENGTH_SHORT).show();

                break;
            case R.id.ll_tixian:
                Toast.makeText(getActivity(), "跳转余额提现", Toast.LENGTH_SHORT).show();

                break;


        }

    }
}
