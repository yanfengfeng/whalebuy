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
import com.android.whalebuy.IConstants;
import com.android.whalebuy.R;
import com.android.whalebuy.common.BaseFragment;
import com.android.whalebuy.listener.IFragmentListener;

/**
 * B类详情
 */
public class DetailFragment extends BaseFragment implements View.OnClickListener, IFragmentListener {

    private  View mRootView;
    private TextView tvFanHui;
    private TextView tvFenXiang;

    private TextView tvPrice;
    private TextView tvOldPrice;
    private TextView tvTitle;
    private TextView tvXiaoLiang;

    private ImageView imgDaiYan;

    public static DetailFragment create() {
        return new DetailFragment();
    }

    @Override
    public void fragmentWillShow() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.goods_details, container, false);
//

        tvFanHui = (TextView) mRootView.findViewById(R.id.tv_left); //返回
        tvFanHui.setOnClickListener(this);
        tvFenXiang = (TextView) mRootView.findViewById(R.id.tv_right); //分享
        tvFenXiang.setOnClickListener(this);
        tvPrice = (TextView) mRootView.findViewById(R.id.tv_price); //价格
        tvOldPrice = (TextView) mRootView.findViewById(R.id.tv_old_price); //原价
        tvTitle = (TextView) mRootView.findViewById(R.id.id_tv_title); //标题
        tvXiaoLiang = (TextView) mRootView.findViewById(R.id.tv_xiaoliang); //销量

        imgDaiYan = (ImageView) mRootView.findViewById(R.id.img_daiyan); //代言
        imgDaiYan.setOnClickListener(this);



        return mRootView;
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_daiyan:
                Toast.makeText(getActivity(), "代言", Toast.LENGTH_SHORT).show();


            break;

            case R.id.id_tv_duihuan:
                Toast.makeText(getActivity(), "兑换", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_bt_xiaoxi:
                Toast.makeText(getActivity(), "消息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_tv_sousuo:
                Toast.makeText(getActivity(), "搜索", Toast.LENGTH_SHORT).show();

                break;
            case R.id.tv_right:
                Toast.makeText(getActivity(), "分享", Toast.LENGTH_SHORT).show();
            case R.id.tv_left:
                Toast.makeText(getActivity(), "返回", Toast.LENGTH_SHORT).show();


        }

    }

}




