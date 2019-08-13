package com.android.whalebuy.fragment.my;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.whalebuy.R;
import com.android.whalebuy.common.BaseFragment;
import com.android.whalebuy.data.DataModel;

public class FragmentPayType extends BaseFragment implements OnClickListener
{
	public static interface IPayTypeSelected
	{
		void onPayTypeSelected(int payType);
	}
	
	private View mRootView;
	private IPayTypeSelected mPayTypeListen;
	private int mMemType; //会员的类型
	
	public static FragmentPayType create(IPayTypeSelected listen, int memType)
	{
		FragmentPayType fragment = new FragmentPayType();
		fragment.mPayTypeListen = listen;
		fragment.mMemType = memType;
		
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		mRootView = inflater.inflate(R.layout.fragment_pay_type, container, false);
		
		mRootView.setOnClickListener(this);
		mRootView.findViewById(R.id.ID_LAYOUT_PAY_ZFB).setOnClickListener(this);
		mRootView.findViewById(R.id.ID_LAYOUT_PAY_CM).setOnClickListener(this);
		mRootView.findViewById(R.id.ID_LAYOUT_PAY_LT).setOnClickListener(this);
		mRootView.findViewById(R.id.ID_LAYOUT_PAY_LT_ONE).setOnClickListener(this);
		
		TextView txtWoMonth = (TextView)(mRootView.findViewById(R.id.ID_TXT_PAY_LT));
		TextView txtWoOne = (TextView)(mRootView.findViewById(R.id.ID_TXT_PAY_LT_ONE));
		TextView txtView = (TextView)(mRootView.findViewById(R.id.ID_TXT_PAY_CM));
		
		if(DataModel.UserInfo.MEM_GOLD == mMemType)
		{
			txtView.setText("中国移动 (特惠0.1元)");
			txtWoMonth.setText("联通WO 5元 (连续包月)");
			txtWoOne.setText("联通WO 5元 (仅购买一个月)");
		}
		else if(DataModel.UserInfo.MEM_DIAMOND == mMemType)
		{
			txtWoMonth.setText("联通WO 15元 (连续包月)");
			txtWoOne.setText("联通WO 15元 (仅购买一个月)");
		}
		else if(DataModel.UserInfo.MEM_PLATINUM == mMemType)
		{
			txtWoMonth.setText("联通WO 8元 (连续包月)");
			txtWoOne.setText("联通WO 8元 (仅购买一个月)");
		}
		else
		{
			txtView.setText("中国移动");
		}
		
		
		
		return mRootView;
	}

	@Override
	public void onClick(View arg0)
	{
		// TODO Auto-generated method stub
		if(R.id.ID_LAYOUT_PAY_ZFB == arg0.getId())
		{
			mPayTypeListen.onPayTypeSelected(DataModel.UserInfo.PAY_TYPE_ZFB);
			getFragmentManager().popBackStack();
		}
		else if(R.id.ID_LAYOUT_PAY_CM == arg0.getId())
		{
			//中国移动支付
			mPayTypeListen.onPayTypeSelected(DataModel.UserInfo.PAY_TYPE_YD);
			getFragmentManager().popBackStack();
		}
		else if(R.id.ID_LAYOUT_PAY_LT == arg0.getId())
		{
			//联通wo计费
			mPayTypeListen.onPayTypeSelected(DataModel.UserInfo.PAY_TYPE_WOSTORE);
			getFragmentManager().popBackStack();
		}
		else if(R.id.ID_LAYOUT_PAY_LT_ONE == arg0.getId())
		{
			//wo计费单月计费
			mPayTypeListen.onPayTypeSelected(DataModel.UserInfo.PAY_TYPE_ONE_WOSTORE);
			getFragmentManager().popBackStack();
		}
		else
		{
			getFragmentManager().popBackStack();
		}
	}
}
