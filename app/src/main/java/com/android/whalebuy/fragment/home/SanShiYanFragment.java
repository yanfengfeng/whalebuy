package com.android.whalebuy.fragment.home;

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
 * 30元抢购
 */
public class SanShiYanFragment extends BaseFragment implements View.OnClickListener, IFragmentListener {

    private  View mRootView;
    private TextView tvNow;
    private TextView tvSoon;
    private TextView tvTomorrow;

    private Button btNews;
    private Button btSearch;

    private ImageView imgleft,imgright;



    public static SanShiYanFragment create() {
        return new SanShiYanFragment();
    }

    @Override
    public void fragmentWillShow() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_sanshiyuan_list, container, false);


        imgleft = (ImageView) mRootView.findViewById(R.id.img_left); //返回
        imgleft.setOnClickListener(this);

        imgright = (ImageView) mRootView.findViewById(R.id.img_right); //消息
        imgright.setOnClickListener(this);





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




