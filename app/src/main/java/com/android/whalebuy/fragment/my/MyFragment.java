package com.android.whalebuy.fragment.my;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.android.whalebuy.IConstants;
import com.android.whalebuy.R;
import com.android.whalebuy.common.BaseFragment;
import com.android.whalebuy.data.DataModel;
import com.android.whalebuy.fragment.shoppingcart.AllOrderFragment;
import com.android.whalebuy.fragment.shoppingcart.OneStyleActivity;
import com.android.whalebuy.fragment.shoppingcart.TwoStyleActivity;
import com.android.whalebuy.kernel.DataPackage;
import com.android.whalebuy.kernel.DataParse;
import com.android.whalebuy.kernel.KernelException;
import com.android.whalebuy.listener.IFragmentListener;
import com.android.whalebuy.listener.ITokenListener;
import com.android.whalebuy.widget.ToastUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.apache.http.Header;
import org.json.JSONObject;

/**
 *个人中心
 *
 *
 */
public class MyFragment extends BaseFragment implements View.OnClickListener,IFragmentListener, LoginFragment.ILoginSuccess , DialogInterface.OnDismissListener {
    View mRootView;

    private TextView tvUserName,tvQiandao,tvHuiYuan,tvYu,tvYuTiXian,tvJingFen,tvJingFenTiXian;
    private TextView tvDaiFuKuan,tvDaiFaHuo,tvDaiShouHuo,tvPingJia,tvShouHou,tvHongBao,tvShouCang,tvXiangYao;

    private TextView  tvset;
    private TextView  tvLookQuanBu;

    private AsyncHttpClient mHttpClient;
    private ProgressDialog mProgressDag;
    public static MyFragment create() {
        return new MyFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.my_center, container, false);

        tvUserName = (TextView) (mRootView.findViewById(R.id.id_tv_user_name));// 用户名
        tvHuiYuan = (TextView) (mRootView.findViewById(R.id.id_tv_huiyuan));// 会员
        tvYu = (TextView) (mRootView.findViewById(R.id.id_tv_yu));// 余额
        tvJingFen = (TextView) (mRootView.findViewById(R.id.id_tv_jingfen));// 鲸分

        tvQiandao = (TextView) (mRootView.findViewById(R.id.id_tv_qiandao));// 签到
        tvQiandao.setOnClickListener(this);

        tvYuTiXian = (TextView) (mRootView.findViewById(R.id.id_tv_yu_tx));// 余额提现
        tvYuTiXian.setOnClickListener(this);

        tvJingFenTiXian = (TextView) (mRootView.findViewById(R.id.id_tv_jingfen_tx));// 鲸分提现
        tvJingFenTiXian.setOnClickListener(this);

        tvLookQuanBu = (TextView) (mRootView.findViewById(R.id.id_tv_look_dd));// 查看订单
        tvLookQuanBu.setOnClickListener(this);

        tvDaiFuKuan = (TextView) (mRootView.findViewById(R.id.id_tv_daifukuan));// 待付款
        tvDaiFuKuan.setOnClickListener(this);

        tvDaiFaHuo = (TextView) (mRootView.findViewById(R.id.id_tv_daifahuo));// 待发货
        tvDaiFaHuo.setOnClickListener(this);

        tvDaiShouHuo = (TextView) (mRootView.findViewById(R.id.id_tv_daishouhuo));// 待收货
        tvDaiShouHuo.setOnClickListener(this);

        tvPingJia = (TextView) (mRootView.findViewById(R.id.id_tv_pingjia));// 评价
        tvPingJia.setOnClickListener(this);

        tvShouHou = (TextView) (mRootView.findViewById(R.id.id_tv_shouhou));// 售后
        tvShouHou.setOnClickListener(this);

        tvHongBao = (TextView) (mRootView.findViewById(R.id.id_tv_hongbao));// 红包数

        tvShouCang = (TextView) (mRootView.findViewById(R.id.id_tv_shoucang));// 收藏
        tvShouCang.setOnClickListener(this);
        tvXiangYao = (TextView) (mRootView.findViewById(R.id.id_tv_xiangyao));// 想要
        tvXiangYao.setOnClickListener(this);


        tvset = (TextView) (mRootView.findViewById(R.id.id_tv_setting));// 设置
        tvset.setOnClickListener(this);





        mProgressDag = new ProgressDialog(getActivity());
        mProgressDag.setMessage(getString(R.string.loading));
        mProgressDag.setOnDismissListener(this);
        mProgressDag.setCancelable(true);
//        mHttpClient = new AsyncHttpClient(); // 请求类
//
        return mRootView;
    }

    @Override
    public void fragmentWillShow() {

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){

           case R.id.id_tv_setting:
               Toast.makeText(getActivity(), "设置", Toast.LENGTH_SHORT).show();
               getFragmentManager().beginTransaction().add(R.id.container
                       ,MySettingFragment.create())
                       .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();

           break;
            case R.id.id_tv_shoucang:
                Toast.makeText(getActivity(), "跳转收藏列表", Toast.LENGTH_SHORT).show();

                break;
            case R.id.id_tv_xiangyao:
                Toast.makeText(getActivity(), "跳转想要列表", Toast.LENGTH_SHORT).show();

                break;

            case R.id.id_tv_look_dd:
                Toast.makeText(getActivity(), "跳转全部订单", Toast.LENGTH_SHORT).show();

//                getFragmentManager().beginTransaction().add(R.id.container
//                        ,AllOrderFragment.create())
//                        .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();

                Intent intent = new Intent();
                intent.setClass(getActivity(), TwoStyleActivity.class);
                startActivity(intent);



                break;

            case R.id.id_tv_yu_tx:
                Toast.makeText(getActivity(), "点击余额提现", Toast.LENGTH_SHORT).show();
                getFragmentManager().beginTransaction().add(R.id.container
                        ,MyCenterYu.create())
                        .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();

                break;
            case R.id.id_tv_jingfen_tx:
                Toast.makeText(getActivity(), "点击鲸分提现", Toast.LENGTH_SHORT).show();
                getFragmentManager().beginTransaction().add(R.id.container
                        ,MyCenterJingFen.create())
                        .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();

                break;
            case R.id.id_tv_qiandao:
                requestGetSign();

//                getFragmentManager().beginTransaction().add(R.id.container, LoginFragment.create(this))
//                        .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();

             //   Toast.makeText(getActivity(), "点击签到", Toast.LENGTH_SHORT).show();

                break;
            case R.id.id_tv_daifukuan:
                Toast.makeText(getActivity(), "跳转待付款订单", Toast.LENGTH_SHORT).show();


                                getFragmentManager().beginTransaction().add(R.id.container, LoginFragment.create(this))
                        .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();
                break;
            case R.id.id_tv_daifahuo:
                Toast.makeText(getActivity(), "跳转待发货订单", Toast.LENGTH_SHORT).show();

                break;
            case R.id.id_tv_daishouhuo:
                Toast.makeText(getActivity(), "跳转待收货订单", Toast.LENGTH_SHORT).show();

                break;
            case R.id.id_tv_pingjia:
                Toast.makeText(getActivity(), "跳转评价订单", Toast.LENGTH_SHORT).show();

                break;
            case R.id.id_tv_shouhou:
                Toast.makeText(getActivity(), "跳转售后订单", Toast.LENGTH_SHORT).show();

                break;
//            case R.id.id_tv_look_dd:
//                Toast.makeText(getActivity(), "跳转全部订单", Toast.LENGTH_SHORT).show();
//
//                break;
//            case R.id.id_tv_look_dd:
//                Toast.makeText(getActivity(), "跳转全部订单", Toast.LENGTH_SHORT).show();
//
//                break;

        }

    }



    // 签到
    private void requestGetSign() {

        mProgressDag.show();
        mHttpClient.post(getActivity(), IConstants.URL_SIGNSCORE  , DataPackage.getSignScore(),
                new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        mProgressDag.dismiss();
                        try {
                            DataParse.checkException(response);

                            int code= response.optInt("code");

                            String  msg =response.optString("msg");

                             ToastUtil.showInfo(getActivity(), msg, false);



                        } catch (KernelException e) {
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);

                        mProgressDag.dismiss();
                        Toast.makeText(getActivity(), R.string.error_network, Toast.LENGTH_SHORT).show();
                    }





                });

    }












    @Override
    public void onLoginSuccess() {

        DataModel.UserInfo myInfo = DataModel.UserInfo.getUserInfo();


        tvUserName.setText(myInfo.ycUserName);
        tvHuiYuan.setText(myInfo.ycUserLevel);

        Toast.makeText(getActivity(), myInfo.ycUserName+myInfo.ycUserLevel, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDismiss(DialogInterface dialog) {

    }
}
