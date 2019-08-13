package com.android.whalebuy.fragment.my;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.whalebuy.R;
import com.android.whalebuy.common.BaseFragment;

public class FragmentOrderType extends BaseFragment implements OnClickListener {
    public static final int ORDER_TYPE_ONE_TIME = 1; //按次订购
    public static final int ORDER_TYPE_MONTH = 2; //包月订购

    public static interface IOrderTypeSelected {
        void onOrderTypeSelected(int orderType);
    }

    private View mRootView;
    private IOrderTypeSelected mOrderTypeListen;
    private int mMemType; //会员的类型

    public static FragmentOrderType create(IOrderTypeSelected listen, int memType) {
        FragmentOrderType fragment = new FragmentOrderType();
        fragment.mOrderTypeListen = listen;
        fragment.mMemType = memType;

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_order_type, container, false);

        mRootView.setOnClickListener(this);
        mRootView.findViewById(R.id.ID_TXT_ONE_TIME).setOnClickListener(this);

        TextView txtMonth = (TextView) (mRootView.findViewById(R.id.ID_TXT_MONTH));
        txtMonth.setOnClickListener(this);
        txtMonth.setText("钻石包月会员 15元");
        /**
         if(DataModel.UserInfo.MEM_DIAMOND == mMemType)
         {
         txtMonth.setText("钻石会员 15元");
         }
         else if(DataModel.UserInfo.MEM_GOLD == mMemType)
         {
         txtMonth.setText("黄金会员 5元");
         }
         else if(DataModel.UserInfo.MEM_PLATINUM == mMemType)
         {
         txtMonth.setText("白金会员 10元");
         }
         **/

        return mRootView;
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        if (R.id.ID_TXT_ONE_TIME == arg0.getId()) {
            mOrderTypeListen.onOrderTypeSelected(ORDER_TYPE_ONE_TIME);
            getFragmentManager().popBackStack();
        } else if (R.id.ID_TXT_MONTH == arg0.getId()) {
            //中国移动支付
            mOrderTypeListen.onOrderTypeSelected(ORDER_TYPE_MONTH);
            getFragmentManager().popBackStack();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}
