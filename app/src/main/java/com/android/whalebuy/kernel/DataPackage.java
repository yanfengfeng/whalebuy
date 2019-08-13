package com.android.whalebuy.kernel;

import android.util.Log;
import com.android.whalebuy.IConstants;
import com.android.whalebuy.data.DataModel;
import com.loopj.android.http.RequestParams;

public class DataPackage {




	public static RequestParams userLoginByPassword(String account, String password, boolean login) {
		RequestParams result = new RequestParams();

		result.put("action", "login");
		result.put("mobile", account); // 手机号
		result.put("password", password); // 验证码
		result.put("type", (login ? 0 : 1)); // 验证码
		
		Log.i(IConstants.TAG, "DataPackage.userLoginByPassword:" + result);

		return result;
	}

	/**
	 * 1.1 获取手机验证码
	 *
	 * @param account
	 * @return
	 */

	public static RequestParams getCheckCode(String account) {
		RequestParams result = new RequestParams();
		result.put("mobile", account);
		return result;

	}


	/**
	 * 2.1 注册
	 *
	 * @param
	 * @param
	 * @return
	 */
	public static RequestParams userMobileRegister(String mobile, String checkCode, String password) {
		RequestParams result = new RequestParams();
		result.put("mobile", mobile); // 手机号
		result.put("code", checkCode); // 验证码
		result.put("password", password); // 密码
		Log.i(IConstants.TAG, "注册:" + result);

		return result;
	}




	/**
	 * 3.1  忘记密码
	 *
	 * @param
	 * @param
	 * @return
	 */
	public static RequestParams userMobileForgetPwd(String mobile, String checkCode, String fpassword) {
		RequestParams result = new RequestParams();
		result.put("mobile", mobile); // 手机号
		result.put("code", checkCode); // 验证码
		result.put("fpassword", fpassword); // 密码
		Log.i(IConstants.TAG, "DataPackage.userLogin:" + result);

		return result;
	}


	/**
	 * 4.1  忘记密码
	 *
	 * @param
	 * @param
	 * @return
	 */
	public static RequestParams userMobileLogin(String mobile,int status, String checkCode, String password) {
		RequestParams result = new RequestParams();
		result.put("mobile", mobile); // 手机号
		result.put("status", status); // 验证码
		result.put("code", checkCode); // 验证码
		result.put("password", password); // 密码
		Log.i(IConstants.TAG, "DataPackage.userLogin:" + result);

		return result;
	}


	
//	public static RequestParams getCheckCode(String account) {
//		RequestParams result = new RequestParams();
//		result.put("action", "newlogin");
//		String phoneEn = "";
//		try
//		{
//			String plainText = new StringBuilder().append(account).append('#').append(System.currentTimeMillis())
//					.append('#').append(KernelManager.getRandom(0, 100000)).toString();
//			phoneEn = AESHelper.encrypt("zg+[*|esp2rt@$^w", plainText);
//
//			//Log.i(IConstants.TAG, "plainText:" + plainText + ", phoneEn:" + phoneEn);
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		result.put("phone", phoneEn);
//
//		Log.i(IConstants.TAG, "getCheckCode:" + result);
//		return result;
//	}




	/**
	 * 推荐
	 *
	 * @param
	 * @return
	 */
	public static RequestParams getHomeTuiJian(int offset) {

		RequestParams result = new RequestParams();
		result.put("offset", offset);
		//result.put("keyword", keywoid);

		return result;
	}



	/**
	 * 签到
	 *
	 * @param
	 * @return
	 */
	public static RequestParams getSignScore() {
		DataModel.UserInfo myInfo = DataModel.UserInfo.getUserInfo();
		RequestParams result = new RequestParams();
		result.put("uid", myInfo.ycUserId);

		return result;
	}




	/**
	 * 获取非用户的消息
	 * 
	 * @param
	 * @return
	 */
	public static RequestParams getCommonMessage(String action) {
		RequestParams result = new RequestParams();
		result.put("action", action);

		return result;
	}

	/**
	 * 3.1 获取用户信息
	 * 
	 * @return
	 */
	public static RequestParams getUserInfo() {
		DataModel.UserInfo myInfo = DataModel.UserInfo.getUserInfo();
		RequestParams result = new RequestParams();
		result.put("id", myInfo.ycUserId);
		result.put("token", myInfo.ycToken);
		result.put("nickname", myInfo.ycUserName);
		result.put("mobile", myInfo.ycAccount);
		result.put("level", myInfo.ycUserLevel);
		result.put("avatar", myInfo.ycUserAvatar);
		
		Log.i(IConstants.TAG, "getUserInfo:" + result);
		return result;
	}




	/**
	 * 6.1 获取视频列表
	 * 
	 * @param pageIndex
	 * @param catId
	 * @return
	 */
	public static RequestParams getVideoList(int pageIndex, String catId) {
		RequestParams result = new RequestParams();
		result.put("action", "videolist");
		result.put("catid", catId);
		result.put("pageindex", pageIndex);
		result.put("pagesize", 20);

		return result;
	}

	/**
	 * 7.1 用户播放视频
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public static RequestParams playVideo(String videoId) {
		DataModel.UserInfo myInfo = DataModel.UserInfo.getUserInfo();
		RequestParams result = new RequestParams();
		result.put("action", "playvideo");
		result.put("userid", myInfo.ycUserId);
		result.put("usertoken", myInfo.ycKey);
		result.put("videoid", videoId);
		
		Log.i(IConstants.TAG, "playVideo.result:" + result);

		return result;
	}

	/**
	 * 9.1 用户发起订购
	 * 
	 * @param videoId
	 * @return
	 */
	public static RequestParams orderApply(int type, boolean isMonth, String videoId ) {
		DataModel.UserInfo myInfo = DataModel.UserInfo.getUserInfo();
		RequestParams result = new RequestParams();

		result.put("action", "orderrequest");
		result.put("membertype", type);
		result.put("userid", myInfo.ycUserId);
		result.put("usertoken", myInfo.ycKey);
		result.put("paytype", isMonth ? 0 : 1);
		result.put("videoid", videoId);
		result.put("bizid", "xiaowo");
		result.put("imei", KernelManager._GetObject().getIMEI());
		
		Log.i(IConstants.TAG, "orderApply.req:" + result);
		return result;
	}
	
	public static RequestParams disorderApply() {
		DataModel.UserInfo myInfo = DataModel.UserInfo.getUserInfo();
		RequestParams result = new RequestParams();

		result.put("action", "disorder");
		result.put("membertype", myInfo.ycMemberType);
		result.put("userid", myInfo.ycUserId);
		result.put("usertoken", myInfo.ycKey);

		return result;
	}
	
	/**
	 * 13.1 wo计费订购同步
	 * @param orderId
	 * @return
	 */
	public static RequestParams wopaySync(String orderId)
	{
		DataModel.UserInfo myInfo = DataModel.UserInfo.getUserInfo();
		RequestParams result = new RequestParams();

		result.put("action", "wopaysuccess");
		result.put("orderId", orderId);
		result.put("userid", myInfo.ycUserId);
		result.put("usertoken", myInfo.ycKey);
		Log.i(IConstants.TAG, "wopaySync: " + result);
		
		return result;
	}
	/**
	 * 14.1 更新检查
	 * @return
	 */
	public static RequestParams updateCheck()
	{
		RequestParams result = new RequestParams();

		result.put("action", "updatecheck");
		result.put("channel", KernelManager.getAppMetaDataValue("app_channel", ""));
		Log.i(IConstants.TAG, "updateCheck: " + result);
		
		return result;
	}

	/**
	 * 17.1 获取指定页面的地址
	 * @param page
	 * @return
	 */
	public static RequestParams getPageUrl(String page) {
		RequestParams result = new RequestParams();

		result.put("action", "pageUrl");
		result.put("pages", page);
		Log.i(IConstants.TAG, "getPageUrl: " + result);

		return result;
	}
}
