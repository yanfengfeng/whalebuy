package com.android.whalebuy.fragment.my;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.whalebuy.IConstants;
import com.android.whalebuy.R;
import com.android.whalebuy.common.BaseFragment;
import com.android.whalebuy.listener.IFragmentListener;

/**
 *填写收货信息
 *
 *
 */
public class WriteAddressFragment extends BaseFragment implements View.OnClickListener,IFragmentListener {
    View mRootView;

    private TextView tvFanHui;
    private TextView tvJia;
    private LinearLayout llLost;
    private Button btTianJia;
    public static WriteAddressFragment create() {
        return new WriteAddressFragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.add_address_information, container, false);


//        tvFanHui = (TextView) (mRootView.findViewById(R.id.tv_left));// 返回
//        tvFanHui.setOnClickListener(this);
//
//        tvJia = (TextView) (mRootView.findViewById(R.id.tv_right));// +
//        tvJia.setOnClickListener(this);
//
//        llLost = (LinearLayout) (mRootView.findViewById(R.id.id_ll_lost));// 无地址显示
//
//
//        btTianJia = (Button) (mRootView.findViewById(R.id.id_bt_tianjia));// 添加地址
//        btTianJia.setOnClickListener(this);


        return mRootView;
    }

    @Override
    public void fragmentWillShow() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.id_bt_tianjia:
//
//                Toast.makeText(getActivity(), "添加地址", Toast.LENGTH_SHORT).show();
//                getFragmentManager().beginTransaction().add(R.id.container
//                        ,SettingMyInformationFragment.create())
//                        .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();
//
//                break;
//            case R.id.tv_right:
//                Toast.makeText(getActivity(), "添加地址", Toast.LENGTH_SHORT).show();
//
//                break;
//            case R.id.tv_left:
//                Toast.makeText(getActivity(), "点击返回", Toast.LENGTH_SHORT).show();
//
//                break;


        }

    }
}
