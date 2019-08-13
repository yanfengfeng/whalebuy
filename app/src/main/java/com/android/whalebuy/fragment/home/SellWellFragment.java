package com.android.whalebuy.fragment.home;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.whalebuy.R;
import com.android.whalebuy.common.BaseFragment;
import com.android.whalebuy.listener.IFragmentListener;

/**
 * 热销
 */
public class SellWellFragment extends BaseFragment implements View.OnClickListener, IFragmentListener {

    private  View mRootView;
    private TextView tvFuZhuang;
    private TextView tvMeiZhuang;
    private TextView tvJuJia;
    private TextView tvJuShiPin;

    private Button btNews;
    private Button btSearch;

    private ImageView imgleft,imgright;



    public static SellWellFragment create() {
        return new SellWellFragment();
    }

    @Override
    public void fragmentWillShow() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_sellwell_list, container, false);


        imgleft = (ImageView) mRootView.findViewById(R.id.img_left); //返回
        imgleft.setOnClickListener(this);

        imgright = (ImageView) mRootView.findViewById(R.id.img_right); //消息
        imgright.setOnClickListener(this);


        tvFuZhuang = (TextView) mRootView.findViewById(R.id.id_tv_fuzhuang); //服装
        tvFuZhuang.setOnClickListener(this);
        tvMeiZhuang = (TextView) mRootView.findViewById(R.id.id_tv_meizhuang); //美妆
        tvMeiZhuang.setOnClickListener(this);
        tvJuJia = (TextView) mRootView.findViewById(R.id.id_tv_jujia); //居家
        tvJuJia.setOnClickListener(this);
        tvJuShiPin = (TextView) mRootView.findViewById(R.id.id_tv_shipin); //食品
        tvJuShiPin.setOnClickListener(this);


        return mRootView;
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_left:
                Toast.makeText(getActivity(), "返回", Toast.LENGTH_SHORT).show();

//                tvQuanDuiHuan.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//加粗
//                tvQuanFan.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//常规


            break;

            case R.id.img_right:
                Toast.makeText(getActivity(), "消息", Toast.LENGTH_SHORT).show();
//                tvQuanDuiHuan.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
//                tvQuanFan.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//常规
                break;

            case R.id.id_tv_fuzhuang:
                Toast.makeText(getActivity(), "服装", Toast.LENGTH_SHORT).show();
                tvFuZhuang.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
                tvMeiZhuang.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//常规
                tvJuJia.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//常规
                tvJuShiPin.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//常规
                break;
            case R.id.id_tv_meizhuang:
                Toast.makeText(getActivity(), "美妆", Toast.LENGTH_SHORT).show();



                tvFuZhuang.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//常规
                tvMeiZhuang.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
                tvJuJia.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//常规
                tvJuShiPin.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//常规
//                tvQuanDuiHuan.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
//                tvQuanFan.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//常规
                break;
            case R.id.id_tv_jujia:



                tvFuZhuang.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//常规
                tvMeiZhuang.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//常规
                tvJuJia.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
                tvJuShiPin.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//常规
                Toast.makeText(getActivity(), "居家", Toast.LENGTH_SHORT).show();
//                tvQuanDuiHuan.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
//                tvQuanFan.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//常规
                break;
            case R.id.id_tv_shipin:


                tvFuZhuang.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//常规
                tvMeiZhuang.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//常规
                tvJuJia.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//常规
                tvJuShiPin.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
                Toast.makeText(getActivity(), "食品", Toast.LENGTH_SHORT).show();
//                tvQuanDuiHuan.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
//                tvQuanFan.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//常规
                break;


        }

    }

//    /**
//     * 搜索界面
//     */
//    public static class SearchFragment extends BaseFragment implements View.OnClickListener, IFragmentListener {
//
//        private  View mRootView;
//        private TextView tvFanHui;
//        private EditText edSearch;
//        private TextView tvSearch;
//        public static SearchFragment create(HomeFragment homeFragment) {
//            return new SearchFragment();
//        }
//
//        @Override
//        public void fragmentWillShow() {
//
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//            mRootView = inflater.inflate(R.layout.search_fragment, container, false);
//    //
//            tvFanHui = (TextView) mRootView.findViewById(R.id.tv_left); //返回
//            tvFanHui.setOnClickListener(this);
//
//            edSearch = (EditText) mRootView.findViewById(R.id.ed_center); //搜索框
//
//            tvSearch = (TextView) mRootView.findViewById(R.id.tv_right); //搜索
//            tvSearch.setOnClickListener(this);
//
//
//
//
//            return mRootView;
//        }
//
//        public void onClick(View v) {
//            switch (v.getId()){
//                case R.id.tv_left:
//                    Toast.makeText(getActivity(), "返回", Toast.LENGTH_SHORT).show();
//
//
//                break;
//
//                case R.id.tv_right:
//                    Toast.makeText(getActivity(), "搜索", Toast.LENGTH_SHORT).show();
//                    break;
//
//
//            }
//
//        }
//    }
}




