package com.android.whalebuy.kernel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.android.whalebuy.R;
import com.android.whalebuy.data.DataModel;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;


import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class KernelManager
{
	private static final String TAG = "KernelManager";
	private static KernelManager sKernelManager = null;
	private DatabaseHelper mDatabaseHelper = null;
	private Context mContext = null;
	private String mVersionName; // 版本信息
	private String mPackName; // 包信息
	private int mVersionCode;
	private Random mRandom;

	private KernelManager()
	{
		mRandom = new Random(System.currentTimeMillis());
	}

	public static KernelManager _GetObject()
	{
		if (null == sKernelManager)
		{
			sKernelManager = new KernelManager();
		}

		return sKernelManager;
	}

	/**
	 * 打印trace信息
	 * 
	 * @param e
	 */
	public static void showTrace(Exception e)
	{
		StackTraceElement[] trace = e.getStackTrace();
		for (int nIndex = 0; nIndex < trace.length; nIndex++)
		{
			Log.e("bmw_trace", "TRACE:" + trace[nIndex].toString());
		}
	}
	
	/**
	 * 获取2个数字之间的随机数
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandom(int min, int max)
	{
		if(max <= min)
		{
			max = min + 1;
		}
		return min + _GetObject().mRandom.nextInt(max - min);
	}

	/**
	 * 获取本月的最后一天
	 * @return
	 */
	public static String getMonthLastDay() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);

		return dateFormat.format(calendar.getTime());
	}

	public static boolean isStringEmpty(String string)
	{
		if(null == string || 0 == string.length())
		{
			return true;
		}
		return false;
	}
	
	public static int string2Int(String szVale, int defaultValue)
	{
		int nRet = defaultValue;
		try
		{
			nRet = Integer.parseInt(szVale);
		}
		catch (Exception e)
		{
		}

		return nRet;
	}

	public static long string2Long(String szValue, long defaultValue)
	{
		long lRet = defaultValue;
		try
		{
			lRet = Long.parseLong(szValue);
		}
		catch (Exception e)
		{
		}
		return lRet;
	}

	public static boolean string2Bool(String value, boolean defaultValue)
	{
		boolean ret = defaultValue;
		try
		{
			ret = Boolean.parseBoolean(value);
		}
		catch (Exception e)
		{
		}
		return ret;
	}

	public static String getStringMD5(String strings)
	{
		StringBuilder szMD5Buf = new StringBuilder();

		try
		{
			MessageDigest digester = MessageDigest.getInstance("MD5");
			digester.update(strings.getBytes());

			byte[] md5Value = digester.digest();
			for (byte b : md5Value)
			{
				if ((b & 0xff) < 0x10)
				{
					szMD5Buf.append("0");
				}
				szMD5Buf.append(Long.toString(b & 0xff, 16));
			}
		}
		catch (Exception e)
		{
		}

		return szMD5Buf.toString();
	}
	
	/**
	 * 获取会员的级别
	 * @param memberType
	 * @return
	 */
	public static String getMemberType(int memberType)
	{
		String result = "免费会员";
		if(DataModel.UserInfo.MEM_DIAMOND == memberType)
		{
			result = "钻石会员";
		}
		else if(DataModel.UserInfo.MEM_GOLD == memberType)
		{
			result = "黄金会员";
		}
		else if(DataModel.UserInfo.MEM_PLATINUM == memberType)
		{
			result = "白金会员";
		}
		
		return result;
	}
	
	public static String getStringSHA1(String str)
	{
		if (str == null || str.length() == 0) 
		{
			return null;
		}
		
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		
		try 
		{
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes());
			
			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++)
			{
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e)
		{
			return null;
		}
	}

	public void init(Context context)
	{
		try
		{
			mContext = context;
			PackageManager packMng = context.getPackageManager();
			PackageInfo packInfo = packMng.getPackageInfo(
			        context.getPackageName(), 0);
			mPackName = packInfo.packageName;
			mVersionCode = packInfo.versionCode;
			mVersionName = packInfo.versionName;

			// imageLoader初始设置
			ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
			        context).threadPriority(Thread.NORM_PRIORITY - 2)
			        .denyCacheImageMultipleSizesInMemory()
			        .diskCacheFileNameGenerator(new Md5FileNameGenerator())
			        .tasksProcessingOrder(QueueProcessingType.LIFO)
			        .diskCacheFileCount(200)
			        // .writeDebugLogs() // Remove for release app
			        .build();
			// Initialize ImageLoader with configuration.
			ImageLoader.getInstance().init(config);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public Context getContext()
	{
		return mContext;
	}
	
	/**
	 * 发送短信
	 * @param message
	 */
	public void sendSMSMessage(String message, Activity activity)
	{
		try
		{
			Intent sendIntent = new Intent(Intent.ACTION_VIEW);
		    sendIntent.putExtra("sms_body", message);
		    sendIntent.setType("vnd.android-dir/mms-sms");
		    activity.startActivity(sendIntent);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String getIMEI()
	{
		TelephonyManager telphoneManager = (TelephonyManager)mContext
		        .getSystemService(Context.TELEPHONY_SERVICE);
		return telphoneManager.getDeviceId();
	}
	
	/**
	 * 判断网络是否打开
	 * 
	 * @return
	 */
	public boolean isNetworkOpen()
	{
		ConnectivityManager connManager = (ConnectivityManager) mContext
		        .getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo.State state = connManager.getNetworkInfo(
		        ConnectivityManager.TYPE_WIFI).getState();
		if (NetworkInfo.State.CONNECTED == state)
		{
			return true;
		}

		state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
		        .getState();
		if (NetworkInfo.State.CONNECTED == state)
		{
			return true;
		}

		return false;
	}
	
	//错误码
	public static String getErrorMsg(Context context, int err_code, String errMessage)
	{
		if(null != errMessage && errMessage.length() > 0)
		{
			return errMessage;
		}
		String message = String.format("%s%d",
		        context.getString(R.string.unknown_error), err_code);
		final int string_id = context.getResources().getIdentifier(
		        "error_" + err_code, "string", _GetObject().getMyPackName());
		if (0 != string_id)
		{
			message = context.getString(string_id);
		}
		return message;
	}
	
	/**
	 * 根据字符串的id获取字符串值
	 * @param resourceId
	 * @return
	 */
	public static String getStringByResourceId(String resourceId)
	{
		String result = "";
		final int string_id = _GetObject().getContext().getResources().getIdentifier(
		        resourceId, "string", _GetObject().getMyPackName());
		if (0 != string_id)
		{
			result = _GetObject().getContext().getString(string_id);
		}
		
		return result;
	}
	
	/**
	 * 获取sd卡目录的名称
	 * @return
	 */
	public static String getSdcardDir()
	{  
        if (Environment.getExternalStorageState().equalsIgnoreCase(
                Environment.MEDIA_MOUNTED))
        {  
            return Environment.getExternalStorageDirectory().toString();
        }  
        return null;  
    }     
	
	/**
	 * 获取本机mac地址
	 * @return
	 */
	public static String getLocalMacAddress()
	{  
		String mac = "null_mac";
		try
		{
	        WifiManager wifi = (WifiManager)sKernelManager.mContext.getSystemService(Context.WIFI_SERVICE);
	        WifiInfo info = wifi.getConnectionInfo();
	        mac = info.getMacAddress();
		}
		catch(Exception e)
		{}
        return mac;  
    }

	/**
	 * 获取application的metadata值
	 * 
	 * @param key
	 * @return
	 */
	public static String getAppMetaDataValue(String key, String defaultValue)
	{
		String result = defaultValue;
		try
		{
			ApplicationInfo appInfo = sKernelManager.mContext
			        .getPackageManager().getApplicationInfo(
			                sKernelManager.mPackName,
			                PackageManager.GET_META_DATA);
			result = appInfo.metaData.getString(key);
		}
		catch (Exception e)
		{
		}

		return result;
	}

	/**
	 * 获取application的metadata值
	 * 
	 * @param key
	 * @return
	 */
	public static boolean getAppMetaDataValue(String key, boolean defaultValue)
	{
		boolean result = defaultValue;
		try
		{
			ApplicationInfo appInfo = sKernelManager.mContext
			        .getPackageManager().getApplicationInfo(
			                sKernelManager.mPackName,
			                PackageManager.GET_META_DATA);
			result = appInfo.metaData.getBoolean(key);
		}
		catch (Exception e)
		{
		}

		return result;
	}

	/**
	 * 检查字符串是否为邮箱
	 * 
	 * @param strings
	 * @return
	 */
	public static boolean isEmail(String strings)
	{
		Pattern pattern = Pattern
		        .compile("^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$");
		Matcher matcher = pattern.matcher(strings);
		return matcher.matches();
	}

	/**
	 * 检查手机号是否合法
	 * 
	 * @param strings
	 * @return
	 */
	public static boolean isPhoneNum(String strings)
	{
		Pattern pattern = Pattern.compile("^1(3|4|5|8)\\d{9}$");
		Matcher matcher = pattern.matcher(strings);
		return matcher.matches();
	}
	
	/**
	 * 获取货物的重量
	 * @param weight
	 * @return
	 */
	public static String getWeight(int weight)
	{
	    String result = "30kg以上";
	    if(weight <= 5)
	    {
	    	result = "0~5kg（含5kg）";
	    }
	    else if(weight > 5 && weight <= 10)
	    {
	    	result = "6kg~10kg（含10kg）";
	    }
	    else if(weight > 10 && weight <= 15)
	    {
	    	result = "11kg~15kg（含15kg）";
	    }
	    else if(weight > 15 && weight <= 20)
	    {
	    	result = "16kg~20kg（含20kg）";
	    }
	    else if(weight > 20 && weight <= 25)
	    {
	    	result = "21kg~25kg（含25kg）";
	    }
	    else if(weight > 26 && weight <= 30)
	    {
	    	result = "26kg~30kg（含30kg）";
	    }
	    
	    return result;
	}

	/**
	 * 退出程序
	 */
	public void exitApp()
	{
		try
		{
			// 关闭网络及业务处理器
			if (null != mDatabaseHelper)
			{
				OpenHelperManager.releaseHelper();
				mDatabaseHelper = null;
			}
		}
		catch (Exception e)
		{
		}
		
		// 退出后台线程
		Process.killProcess(Process.myPid());
	}
	
	// 多线程安全
	public final DatabaseHelper getDatabaseHelper()
	{
		if (null == mDatabaseHelper)
		{
			mDatabaseHelper = OpenHelperManager.getHelper(mContext,
			        DatabaseHelper.class);
		}

		return mDatabaseHelper;
	}

	public String getMyPackName()
	{
		return mPackName;
	}

	public String getMyVersionName()
	{
		return mVersionName;
	}

	public int getMyVersionCode()
	{
		return mVersionCode;
	}

}