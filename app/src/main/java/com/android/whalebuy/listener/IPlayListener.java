package com.android.whalebuy.listener;

public interface IPlayListener
{
	/**
	 * 获取节目的播放地址成功
	 */
	void onGetPlayUrlSuccess(String playUrl);
	
	/**
	 * 获取节目的播放地址失败
	 */
	void onGetPlayUrlFail();
}
