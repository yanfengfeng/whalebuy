package com.android.whalebuy;


/**
 * 本模块主要定义程序中要使用的常量
 * @author 
 *
 */
public interface IConstants
{
	final boolean DEBUG = false;
	
	final String TAG = "sports_log";
	
    //final String DWG_PATH = "/MiniCAD/dwg/"; //dwg文件保存路径
    final String REQUEST_SERVER_URL = "http://app.jingmai.shop"; //服务器请求地址
    
     final String FRAGMENT_MAIN_THREAD = "com.android.whalebuy.FRAGMENT_MAIN_THREAD"; //主线fragment
     final String FRAGMENT_LOGIN = "com.android.whalebuy.FRAGMENT_LOGIN"; //登录fragment

    final int ERROR_NETWORK = 100; //网络错误
    final int ERROR_SERVER = 101; //服务器异常
    final int ERROR_UNKNOWN = 1001; //未知错误

    final String FIRST_START = "first_start"; // 是否第一次启动
    //    登录注册

    final static String URL_SEND_CODE = "http://app.jingmai.shop/api/login/sendCode"; //发送验证码
    final static String URL_MOBILEREGISTER = "http://app.jingmai.shop/api/login/mobileRegister"; //注册

    final static String URL_MOBILEFORGETPWD = "http://app.jingmai.shop/api/login/forgetPwd"; //忘记密码
    final static String URL_MOBILELOGIN = "http://app.jingmai.shop/api/login/login"; //登录


    final static String URL_HOMETUIJIAN= "http://app.jingmai.shop/api/main/index";


    //个人中心



    final static String URL_SIGNSCORE = " http://app.jingmai.shop/api/user/signScore"; //签到




    final static String URL_HOME = "http://app.jingmai.shop/api/main/main"; //首页


    final static String PLATFORM = "android"; // 平台
   
}
