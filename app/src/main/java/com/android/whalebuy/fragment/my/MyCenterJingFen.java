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
 *个人中心
 *
 *
 */
public class MyCenterJingFen extends BaseFragment implements View.OnClickListener,IFragmentListener {
    View mRootView;

    private TextView tvFanKui;
    private TextView tvGuiZe;
    private TextView tvJingFen;

    public static MyCenterJingFen create() {
        return new MyCenterJingFen();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.my_center_jing, container, false);



        tvJingFen = (TextView) (mRootView.findViewById(R.id.tv_jingfen));// 鲸分
        tvFanKui = (TextView) (mRootView.findViewById(R.id.tv_left));// 返回
        tvFanKui.setOnClickListener(this);

        tvGuiZe = (TextView) (mRootView.findViewById(R.id.tv_guize));// 鲸分提现
        tvGuiZe.setOnClickListener(this);



        return mRootView;
    }

    @Override
    public void fragmentWillShow() {

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){

           case R.id.tv_left:
               Toast.makeText(getActivity(), "返回", Toast.LENGTH_SHORT).show();
//               getFragmentManager().beginTransaction().add(R.id.container
//                       ,MyActivitySetting.create())
//                       .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();

           break;
            case R.id.tv_guize:
                Toast.makeText(getActivity(), "跳转规则", Toast.LENGTH_SHORT).show();

                break;

        }

    }
}
