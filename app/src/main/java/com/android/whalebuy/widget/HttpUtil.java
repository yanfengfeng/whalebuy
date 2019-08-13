package com.android.whalebuy.widget;



import com.android.whalebuy.IConstants;
import com.android.whalebuy.kernel.KernelManager;
import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class HttpUtil {
	
	private static String HOST = getApiHost();
	
	public static String getApiHost(){
		if (GlobalValue.PSH_SERVER_HOST ==null)			{
			GlobalValue.PSH_SERVER_HOST = KernelManager.getAppMetaDataValue("PSH_SERVER_HOST", "http://app.jingmai.shop");
		}
		
		return GlobalValue.PSH_SERVER_HOST;
	}
	
	public static String getH5Host(){
		if (GlobalValue.PSH_SERVER_HOST_H5 == null)			{ 
			GlobalValue.PSH_SERVER_HOST_H5 = KernelManager.getAppMetaDataValue("PSH_SERVER_HOST_H5", "http://app.jingmai.shop");
		}
		
		return GlobalValue.PSH_SERVER_HOST_H5;
		
	}
	
	
	private static AsyncHttpClient client = new AsyncHttpClient();
	
	static {
		client.setTimeout(5000);
		// client.addHeader("Content-type", "application/json");
		client.setUserAgent("PSH-Android-" + KernelManager._GetObject().getMyVersionCode());
	}
	
	private static MyLogger myLogger = MyLogger.getLogger("HttpUtil");

	private static String getAbsoluteUrl(String relativeUrl) {   
		if (HOST == null){
			HOST = KernelManager.getAppMetaDataValue("PSH_SERVER_HOST", "http://app.jingmai.shop");
		}
		return HOST + relativeUrl;
	}

	/**
	 * 用一个完整URL获取一个String对象
	 * 
	 * @param url
	 * @param responseHandler
	 */
	public static void get(String url, AsyncHttpResponseHandler responseHandler) {
		myLogger.i(getAbsoluteUrl(url));
		get(url,null,responseHandler);
	}

	/**
	 * @return
	 */
//	public static String getAppKey(String url){
//		String xxx = url + IConstants.PLATFORM + KernelManager._GetObject().getMyVersionCode() + IConstants.APP_KEY;
//		String key = StringUtil.getMD5(xxx.getBytes());
//		return key;
//	}
	
	
	public static void get(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		
		String absUrl = getAbsoluteUrl(url);
		String queryString = (params != null ? params.toString() : "");
		myLogger.d(absUrl + "?" + queryString);
		client.get(getAbsoluteUrl(url), params, responseHandler);
	}

	/**
	 * 不带参数，获取json对象或者数组
	 * 
	 * @param urlString
	 * @param res
	 */
	public static void get(String urlString, JsonHttpResponseHandler res) {
		client.get(urlString, res);

	}
	

	/**
	 * 带参数，获取json对象或者数组
	 * 
	 * @param
	 * @param params
	 * @param res
	 */
	public static void get(String url, RequestParams params,
			JsonHttpResponseHandler res) {
		
		if (params != null) {
			params.put("platform", IConstants.PLATFORM);
			params.put("version ", AppInfoUtils.getVersionName());
		}
		
		client.get(url, params, res);

	}

	/**
	 * 下载数据使用，会返回byte数据
	 * @param uString
	 * @param bHandler
	 */
	public static void get(String uString, BinaryHttpResponseHandler bHandler) {
		client.get(uString, bHandler);
	}

	public static AsyncHttpClient getClient() {
		return client;
	}

	public static void post(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		
		if (params == null){
			params = new RequestParams();
		}
		
		
		myLogger.i("post url:" + getAbsoluteUrl(url) + "||参数:"
				+ (params != null ? params.toString() : "params is null"));
		client.post(getAbsoluteUrl(url), params, responseHandler);
	}

	public static JSONArray getJsonArrayFromResponse(JSONObject response,
			String dataFieldStr) {
		JSONArray data = null;
		if (response != null) {
			try {
				JSONObject body = response.getJSONObject("data");
				if (body != null) {
					data = body.getJSONArray(dataFieldStr);
				}
			} catch (Exception e) {
			}
		}
		return data;
	}

	public static JSONObject getJsonObjectFromResponse(JSONObject response,
			String dataFieldStr) {
		JSONObject data = null;
		if (response != null) {
			try {
				JSONObject body = response.getJSONObject("data");
				if (body != null) {
					data = body.getJSONObject(dataFieldStr);
				}
			} catch (Exception e) {
			}
		}
		return data;
	}

}
