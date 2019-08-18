package com.android.whalebuy.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @类功能说明:(Fragment的一些常用方法封装)
 * @author yff
 * @date 2016年10月18日 下午2:32:03
 * @version V2.0
 */
public class FragmentUtil {

	int fragmentId = 0;

	List<Fragment> list_frl = new ArrayList<Fragment>();

	private int cuurent = 0x001;
	
	FragmentActivity activity;
	
	public FragmentUtil(FragmentActivity activity){
		this.activity = activity;
	}
	
	
	/**
	 * 设置fragment 的资源id
	 *
	 */
	public void setFragmentResId(int resId) {
		this.fragmentId = resId;
	}

	/**
	 * 添加需要显示fragment,需要要按照顺序添加,否则会和你顺序不对于
	 */
	public void addFragment(Fragment fragment) {
		list_frl.add(fragment);
	}

	/**
	 * 需要显示fragment
	 * 
	 * @param index
	 */
	public void showFrl(int index) {
		if (fragmentId == 0 || activity == null) {
			Log.e("FragmentUtil","======ParentFragment 的资源id  is  null or activity is null=============");
			return;
		}

		if (cuurent != 0x001 && getCurrentFrl() == list_frl.get(index)) {
			return;
		}
		
		FragmentManager manage = activity.getSupportFragmentManager();
		FragmentTransaction transaction = manage.beginTransaction();
		Fragment frl = list_frl.get(index);

		if(!frl.isAdded()){
			transaction.add(fragmentId, frl);
		}else if(frl.isVisible()){
			return;
		}else{
			frl.onResume();
		}

		for (int i = 0; i < list_frl.size(); i++) {
			FragmentTransaction ft = activity.getSupportFragmentManager()
					.beginTransaction();
			if (index == i) {
				ft.show(list_frl.get(i));
			} else {
				ft.hide(list_frl.get(i));
			}
			ft.commit();
		}
		transaction.commit();
		cuurent = index;
	}

	/**
	 * 获取当前前显示分fragment
	 * 
	 * @return
	 */
	public Fragment getCurrentFrl() {

		return list_frl.get(cuurent);
	}
}
