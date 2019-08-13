package com.android.whalebuy.listener;

public interface ITokenListener
{
	/**
	 * token获取成功
	 */
	void onTokenSuccess();
	
	/**
	 * token获取失败
	 */
	void onTokenFail();
}
