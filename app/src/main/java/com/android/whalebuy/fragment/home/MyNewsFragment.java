package com.android.whalebuy.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.android.whalebuy.R;
import com.android.whalebuy.common.BaseFragment;
import com.android.whalebuy.listener.IFragmentListener;

/**
 * 消息
 */
public class MyNewsFragment extends BaseFragment implements View.OnClickListener, IFragmentListener {

    private  View mRootView;
    private TextView tvFanHui;
    private TextView tvShanChu;

    private TextView tvNewJiaoyi;
    private TextView tvNewHuoDong;
    private TextView tvNewDaiYan;


    private TextView tvNewJiaoyiNub;
    private TextView tvNewHuoDongNub;
    private TextView tvNewDaiYanNub;

    public static MyNewsFragment create() {
        return new MyNewsFragment();
    }

    @Override
    public void fragmentWillShow() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      //  mRootView = inflater.inflate(R.layout.home1, container, false);

        mRootView = inflater.inflate(R.layout.home_news, container, false);
//
        tvFanHui = (TextView) mRootView.findViewById(R.id.id_tv_left); //返回
        tvFanHui.setOnClickListener(this);


        tvShanChu = (TextView) mRootView.findViewById(R.id.id_tv_new_shanchu); // 消息删除
        tvShanChu.setOnClickListener(this);



        tvNewJiaoyi = (TextView) mRootView.findViewById(R.id.id_tv_new_jiaoyi); // 交易消息
        tvNewJiaoyi.setOnClickListener(this);
        tvNewJiaoyiNub = (TextView) mRootView.findViewById(R.id.tv_jynews_nub); //


        tvNewDaiYan = (TextView) mRootView.findViewById(R.id.id_tv_new_daiyan); // 代言消息
        tvNewDaiYan.setOnClickListener(this);
        tvNewDaiYanNub = (TextView) mRootView.findViewById(R.id.tv_daiyan_news_nub); //

        tvNewHuoDong = (TextView) mRootView.findViewById(R.id.id_tv_new_huodong); // 活动消息
        tvNewHuoDong.setOnClickListener(this);
        tvNewHuoDongNub = (TextView) mRootView.findViewById(R.id.tv_huodong_news_nub); //



        return mRootView;
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_tv_left:
                Toast.makeText(getActivity(), "返回", Toast.LENGTH_SHORT).show();


            break;

            case R.id.id_tv_new_shanchu:
                Toast.makeText(getActivity(), "删除", Toast.LENGTH_SHORT).show();
                break;



            case R.id.id_tv_new_jiaoyi:
                Toast.makeText(getActivity(), "交易", Toast.LENGTH_SHORT).show();


                tvNewJiaoyi.setTextColor(getResources().getColor(
                    R.color.news_lv));
                tvNewDaiYan.setTextColor(getResources().getColor(
                        R.color.black));
                tvNewHuoDong.setTextColor(getResources().getColor(
                        R.color.black));


                break;
            case R.id.id_tv_new_daiyan:
                Toast.makeText(getActivity(), "代言", Toast.LENGTH_SHORT).show();


                tvNewJiaoyi.setTextColor(getResources().getColor(
                        R.color.black));
                tvNewDaiYan.setTextColor(getResources().getColor(
                        R.color.news_lan));
                tvNewHuoDong.setTextColor(getResources().getColor(
                        R.color.black));

                break;
            case R.id.id_tv_new_huodong:
                Toast.makeText(getActivity(), "活动", Toast.LENGTH_SHORT).show();


                tvNewJiaoyi.setTextColor(getResources().getColor(
                        R.color.black));
                tvNewDaiYan.setTextColor(getResources().getColor(
                        R.color.black));
                tvNewHuoDong.setTextColor(getResources().getColor(
                        R.color.news_zi));
                break;


        }

    }


}




