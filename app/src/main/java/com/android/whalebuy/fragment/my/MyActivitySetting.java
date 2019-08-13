package com.android.whalebuy.fragment.my;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.android.whalebuy.IConstants;
import com.android.whalebuy.R;
import com.android.whalebuy.common.BaseFragment;
import com.android.whalebuy.listener.IFragmentListener;

/**
 *设置
 *
 *
 */
public class MyActivitySetting extends BaseFragment implements View.OnClickListener,IFragmentListener {
    View mRootView;

    private TextView tvUserName,tvBianJi,tvHuiYuan;
    private TextView tvDaiFuKuan,tvDaiFaHuo,tvDaiShouHuo,tvPingJia,tvShouHou,tvHongBao,tvShouCang,tvXiangYao;
    private TextView tvMyDiZhi;
    private TextView tvMyKeFu;
    private TextView tvFanKui;
    private TextView tvGuanYu;
    private TextView tvQingLi;

    private TextView tvTuiChu;


    private TextView  tvset;
    public static MyActivitySetting create() {
        return new MyActivitySetting();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_setting, container, false);

        tvUserName = (TextView) (mRootView.findViewById(R.id.id_tv_user_name));// 用户名
        tvHuiYuan = (TextView) (mRootView.findViewById(R.id.id_tv_huiyuan));// 会员

        tvBianJi = (TextView) (mRootView.findViewById(R.id.tv_bianji));// 编辑
        tvBianJi.setOnClickListener(this);

        tvMyDiZhi = (TextView) (mRootView.findViewById(R.id.tv_go_shouhuo));// 收货地址
        tvMyDiZhi.setOnClickListener(this);

        tvQingLi = (TextView) (mRootView.findViewById(R.id.tv_qingli));//清理缓存
        tvQingLi.setOnClickListener(this);
        tvMyKeFu = (TextView) (mRootView.findViewById(R.id.tv_kefu));// 客服
        tvMyKeFu.setOnClickListener(this);
        tvFanKui = (TextView) (mRootView.findViewById(R.id.tv_fankui));// 意见反馈
        tvFanKui.setOnClickListener(this);
        tvGuanYu = (TextView) (mRootView.findViewById(R.id.tv_about));// 关于我们
        tvGuanYu.setOnClickListener(this);


        tvTuiChu = (TextView) (mRootView.findViewById(R.id.tv_tuichu));// 退出
        tvTuiChu.setOnClickListener(this);

        return mRootView;
    }

    @Override
    public void fragmentWillShow() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_bianji:
                Toast.makeText(getActivity(), "编辑", Toast.LENGTH_SHORT).show();
                getFragmentManager().beginTransaction().add(R.id.container
                        ,SettingMyInformationFragment.create())
                        .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();

                break;
            case R.id.tv_go_shouhuo:
                Toast.makeText(getActivity(), "跳转收货地址列表", Toast.LENGTH_SHORT).show();
                getFragmentManager().beginTransaction().add(R.id.container
                        ,MyAddressFragment.create())
                        .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();
                break;
            case R.id.tv_qingli:
                Toast.makeText(getActivity(), "点击清理", Toast.LENGTH_SHORT).show();

                break;

            case R.id.tv_kefu:
                Toast.makeText(getActivity(), "跳转客服界面", Toast.LENGTH_SHORT).show();

                break;

            case R.id.tv_fankui:
                Toast.makeText(getActivity(), "跳转意见反馈界面", Toast.LENGTH_SHORT).show();

                break;
            case R.id.tv_about:
                Toast.makeText(getActivity(), "跳转关于我们", Toast.LENGTH_SHORT).show();

                break;

            case R.id.tv_tuichu:
                Toast.makeText(getActivity(), "点击退出", Toast.LENGTH_SHORT).show();

                break;
        }

    }
}
