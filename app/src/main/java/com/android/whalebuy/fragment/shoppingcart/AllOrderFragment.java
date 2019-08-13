package com.android.whalebuy.fragment.shoppingcart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.whalebuy.R;
import com.android.whalebuy.common.BaseFragment;
import com.android.whalebuy.fragment.my.MyCenterYu;
import com.android.whalebuy.listener.IFragmentListener;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * 购物车
 *
 *
 */
public class AllOrderFragment extends BaseFragment implements View.OnClickListener,IFragmentListener {

    View mRootView;
    private TabLayout tlActivitytwoTitle;
    private List<Fragment> fragmentList;
    private String[] arrTitle=new String[]{"全部","待付款","待评价","退款"};


    private TextView tvFanKui;
    private TextView tvGuiZe;

    public static AllOrderFragment create() {
        return new AllOrderFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.activity_two, container, false);



        return mRootView;
    }

    @Override
    public void fragmentWillShow() {

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){

//            case R.id.tv_left:
//                Toast.makeText(getActivity(), "返回", Toast.LENGTH_SHORT).show();
////               getFragmentManager().beginTransaction().add(R.id.container
////                       ,MyActivitySetting.create())
////                       .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();
//
//                break;
//            case R.id.tv_right:
//                Toast.makeText(getActivity(), "跳转规则", Toast.LENGTH_SHORT).show();
//
//                break;
//            case R.id.ll_tixian:
//                Toast.makeText(getActivity(), "跳转余额提现", Toast.LENGTH_SHORT).show();

//                break;


        }

    }
}
