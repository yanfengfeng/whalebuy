package com.android.whalebuy.common;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import com.android.whalebuy.R;
import com.android.whalebuy.widget.ToastUtils;


public class BaseFragment extends Fragment
{

	@Override
    public void onStart()
    {
	    // TODO Auto-generated method stub
	    super.onStart();
	    
	    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
		getView().setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub
			}
		});
		Button btnBack = (Button)(getView().findViewById(R.id.ID_BTN_LEFT));
		if(null != btnBack && View.VISIBLE == btnBack.getVisibility())
		{
			btnBack.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View arg0)
				{
					// TODO Auto-generated method stub
					getFragmentManager().popBackStack();
				}
			});
		}
    }

	@Override
    public void onStop()
    {
	    // TODO Auto-generated method stub
	    super.onStop();
	    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(getView().getWindowToken(), 0); 
    }

    protected void toast(String msg){
		ToastUtils.showLong(getContext(),msg);
	}

}
