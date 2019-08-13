package com.android.whalebuy.widget;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexUtil {


	public static boolean isValidMobiNumber(String paramString) {
		if (paramString == null){
			return false;
		}
		
		String regex = "^1[34587]\\d{9}$";
		if (paramString.matches(regex)) {
			return true;
		}
		return false;
	}

	public static boolean isValidMobiCode(String paramString) {
		String regex = "^\\d{6}$";
		if (paramString.matches(regex)) {
			return true;
		}
		return false;
	}
	
	



	/**
	 * 验证密码是否是数字与字母的组合
	 *
	 * @param password 密码
	 * @return 返回值
	 */
	public static boolean isPassword(String password) {
		boolean ret;
//        Pattern p = Pattern.compile("\\w{6,16}+");

		//字母加数字组合
//        Pattern p = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z" +
//                "!@#$%\\^&*(){}\\[\\]+-=|;:'<,>.?/]{6,12}$");

		//随意纯字母或者纯数字或者组合
		Pattern p = Pattern.compile("^[0-9A-Za-z" +
				"!@#$%\\^&*(){}\\[\\]+-=|;:'<,>.?/]{6,12}$");
		Matcher m = p.matcher(password);
		ret = m.matches();
		return ret;
	}




	
	/**
	 * 检查手机号是否合法
	 * 
	 * @param strings
	 * @return
	 */
	public static boolean isPhoneNum(String strings) {
		Pattern pattern = Pattern.compile("^1(3|4|5|7|8)\\d{9}$");
		Matcher matcher = pattern.matcher(strings);
		return matcher.matches();
	}

	/**
	 * 检查字符串是否为邮箱
	 * 
	 * @param strings
	 * @return
	 */
	public static boolean isEmail(String strings) {
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$");
		Matcher matcher = pattern.matcher(strings);
		return matcher.matches();
	}

	/**
	 * 验证车牌号是否合法
	 * 
	 * @param strings
	 * @return
	 */
	public static boolean isLicense(String strings) {
		Pattern pattern = Pattern.compile("^[\u4e00-\u9fa5]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{5}$");
		Matcher matcher = pattern.matcher(strings);
		return matcher.matches();
	}

	
}
