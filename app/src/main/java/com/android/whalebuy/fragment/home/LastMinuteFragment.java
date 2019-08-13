package com.android.whalebuy.fragment.home;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.android.whalebuy.R;
import com.android.whalebuy.common.BaseFragment;
import com.android.whalebuy.listener.IFragmentListener;

/**
 * 限时抢购
 */
public class LastMinuteFragment extends BaseFragment implements View.OnClickListener, IFragmentListener {

    private  View mRootView;
    private TextView tvNow;
    private TextView tvSoon;
    private TextView tvTomorrow;

    private Button btNews;
    private Button btSearch;

    private ImageView imgleft,imgright;



    public static LastMinuteFragment create() {
        return new LastMinuteFragment();
    }

    @Override
    public void fragmentWillShow() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_last_time_list, container, false);



        imgleft = (ImageView) mRootView.findViewById(R.id.img_left); //返回
        imgleft.setOnClickListener(this);

        imgright = (ImageView) mRootView.findViewById(R.id.img_right); //消息
        imgright.setOnClickListener(this);

        tvNow = (TextView) mRootView.findViewById(R.id.tv_now); //正在
        tvNow.setOnClickListener(this);

        tvSoon = (TextView) mRootView.findViewById(R.id.tv_soon); //即将
        tvSoon.setOnClickListener(this);

        tvTomorrow = (TextView) mRootView.findViewById(R.id.tv_tomorrow); //明日
        tvTomorrow.setOnClickListener(this);







        return mRootView;
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_left:
                Toast.makeText(getActivity(), "返回", Toast.LENGTH_SHORT).show();


            break;

            case R.id.img_right:
                Toast.makeText(getActivity(), "消息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_now:
                Toast.makeText(getActivity(), "正在", Toast.LENGTH_SHORT).show();


                tvNow.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
                tvSoon.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//常规
                tvTomorrow.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//常规

                break;


            case R.id.tv_soon:
                Toast.makeText(getActivity(), "即将", Toast.LENGTH_SHORT).show();


                tvNow.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//
                tvSoon.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//
                tvTomorrow.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));

                break;
            case R.id.tv_tomorrow:

                tvNow.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//
                tvSoon.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//
                tvTomorrow.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                Toast.makeText(getActivity(), "明日", Toast.LENGTH_SHORT).show();

                break;


        }

    }


}


