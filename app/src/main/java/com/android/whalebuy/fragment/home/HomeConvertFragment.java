package com.android.whalebuy.fragment.home;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.whalebuy.IConstants;
import com.android.whalebuy.R;
import com.android.whalebuy.common.BaseFragment;
import com.android.whalebuy.listener.IFragmentListener;

/**
 * 兑换
 */
public class HomeConvertFragment extends BaseFragment implements View.OnClickListener, IFragmentListener {

    private  View mRootView;
    private TextView tvQuanFan;
    private TextView tvQuanDuiHuan;
    private Button btNews;
    private Button btSearch;

    public static HomeConvertFragment create() {
        return new HomeConvertFragment();
    }

    @Override
    public void fragmentWillShow() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.top_dialog, container, false);
////
//        tvQuanFan = (TextView) mRootView.findViewById(R.id.id_tv_quanfan); //全反
//        tvQuanFan.setOnClickListener(this);
//        tvQuanDuiHuan = (TextView) mRootView.findViewById(R.id.id_tv_duihuan); //兑换
//        tvQuanDuiHuan.setOnClickListener(this);
//
//
//        tvQuanDuiHuan.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
//        tvQuanFan.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//常规

        return mRootView;
    }

    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.id_tv_quanfan:
//                Toast.makeText(getActivity(), "dianjijj", Toast.LENGTH_SHORT).show();
//
//                getFragmentManager().beginTransaction().add(R.id.container
//                        , HomeFragment.create())
//                        .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();
//
//
//                tvQuanDuiHuan.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//加粗
//                tvQuanFan.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//常规
//
//            break;
//
//            case R.id.id_tv_duihuan:
//                Toast.makeText(getActivity(), "兑换", Toast.LENGTH_SHORT).show();
//                tvQuanDuiHuan.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
//                tvQuanFan.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//常规
//                break;
//            case R.id.id_bt_xiaoxi:
//                Toast.makeText(getActivity(), "消息", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.id_tv_sousuo:
//                Toast.makeText(getActivity(), "搜索", Toast.LENGTH_SHORT).show();
//
//                break;
//            case R.id.tv_right:
//                Toast.makeText(getActivity(), "搜索", Toast.LENGTH_SHORT).show();
//            case R.id.tv_left:
//                Toast.makeText(getActivity(), "返回", Toast.LENGTH_SHORT).show();


        }

    }

}




