package com.android.whalebuy.data;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.whalebuy.kernel.KernelManager;
import com.android.whalebuy.widget.ImageRollView;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DataModel
{	
	static public class ListItem
	{
		private int mType;
		private int mSubType;

		public ListItem(int type, int subType)
		{
			mType = type;
			mSubType = subType;
		}

		public int getViewType()
		{
			// TODO Auto-generated method stub
			return mType;
		}

		public int getViewSubType()
		{
			// TODO Auto-generated method stub
			return mSubType;
		}
	}
	
	/**
	 * 上次订购的状态信息
	 * @author larry
	 */
	static public class LastOrderState
	{
		public String lastOrderId; //上次的订单id
		public int lastOrderType; //上次的订购类型
		public int lastMemberType; //上次的订购的会员类型
		public boolean isUpdate; //是否升级
	}
	
	static public class UserInfo
	{
		public static final int MEM_NORMAL = 0; //普通会员
		public static final int MEM_GOLD = 1; //黄金会员
		public static final int MEM_PLATINUM = 2; //白金会员
		public static final int MEM_DIAMOND = 3; //钻石会员
		public static final int MEM_TRY_DIAMOND = 4; //体验钻石会员
		
		public static final int PAY_TYPE_ZFB = 3; //支付宝支付
		public static final int PAY_TYPE_LT = 1; //联通支付
		public static final int PAY_TYPE_YD = 2; //移动支付
		public static final int PAY_TYPE_WOSTORE = 4; //联通wo计费包月
		public static final int PAY_TYPE_ONE_WOSTORE = 6; //wo计费单月
		
		private static UserInfo sMyInfo;
		
		private static final String USER_INFO_FILE = "user_info";

		private static final String USER_KEY = "user_key"; //用户的key
		private static final String MEMBER_TYPE = "member_type"; //用户积分

		private static final String ACCOUNT = "mobile"; //用户账号
		private static final String USER_ID = "id"; //用户Id
		private static final String USER_NAME = "nickname"; //用户名
		private static final String USER_TOKEN = "token"; //token
		private static final String USER_LEVEL = "level"; //会员等级
		private static final String USER_AVATAR = "avatar"; //头像

		public String ycKey; //用户key
		public int ycOrderType; //订购类型
		public String ycMemberTime; //会员有效期
		public boolean disorderUser; //是否退订用户
		public String lastOrderId; //上次订购的order id
		public int ycMemberType; //会员类型


		public int ycUserId; //用户id
		public String ycAccount; //用户账号
		public String ycToken; //用户token
		public String ycUserName; //用户名
		public String ycUserLevel; //用户名

		public String ycUserAvatar; //头像
		public static UserInfo getUserInfo()
		{
			if(null == sMyInfo)
			{
				sMyInfo = new UserInfo();
				SharedPreferences preference = KernelManager._GetObject().getContext()
						.getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);



				sMyInfo.ycMemberType = preference.getInt(MEMBER_TYPE, 0);
				sMyInfo.ycKey = preference.getString(USER_KEY, "");


				sMyInfo.ycUserId = preference.getInt(USER_ID, -1);
				sMyInfo.ycAccount = preference.getString(ACCOUNT, "");
				sMyInfo.ycUserName = preference.getString(USER_NAME, "");
				sMyInfo.ycUserLevel = preference.getString(USER_LEVEL, "");
				sMyInfo.ycUserAvatar = preference.getString(USER_AVATAR, "");
			}
			
			return sMyInfo;
		}
		
		public void save()
		{
			SharedPreferences.Editor edit = KernelManager._GetObject().getContext()
					.getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE).edit();

			edit.putString(USER_KEY, ycKey);
			edit.putInt(MEMBER_TYPE, ycMemberType);

			edit.putString(ACCOUNT, ycAccount);
			edit.putString(USER_NAME, ycUserName);
			edit.putString(USER_TOKEN, ycToken);
			edit.putString(USER_LEVEL, ycUserLevel);
			edit.putString(USER_AVATAR, ycUserAvatar);
			edit.putInt(USER_ID, ycUserId);


			edit.commit();
		}
		
		public void logout()
		{
			ycUserId = -1;
			ycAccount = "";
			ycToken = "";
			ycUserName = "";
			ycUserLevel = "";
			ycUserAvatar = "";

			ycKey = "";
			ycMemberType = 0;
			ycOrderType = 0;
			ycMemberTime = "";
			lastOrderId = "";
			disorderUser = false;
			
			save();
		}
	}




//
//
//	/**
//	 * 2.2首页
//	 * @author larry
//	 * @param <Program>
//	 */
//	public static class Know
//	{
//		public String title; //标题
//		public String catid; //标题catid
//
//		public final List<Kprogram> listProgram; //
//
//		public Know() {
//			listProgram = new ArrayList<Kprogram>();
//		}
//	}
//
//
//
//	public static class Kprogram
//	{
//
//		public String title; //标题
//		public String image; //图片
//		public String id; //id
//		public String catid; //id
//	}





	/**
	 * 2.2首页
	 * @author larry
	 * @param <Program>
	 */
	public static class Home
	{
		public String content; //内容

		public final List<Program> ActivityProgram; //活动

		public Home() {
			ActivityProgram = new ArrayList<Program>();
		}

	}



	public static class Program
	{

		public String name; //标题
		public String image; //图片

	}











	/**
	 * 首页轮播图信息
	 * @author larry
	 */
	  static public class BannerItem extends ImageRollView.RollItem
	{
		public String imageSrc; //图片的链接地址
		public String id; //
		public String  type;
		public String url; //
	}




	/**
	 *主页信息
	 * 主页播报
	 *
	 */

	public static class HomeBroadcast
	{
		public String content; //内容

	}






	/**
	 *主页信息
	 * 主页分类
	 *
	 */

	static	public class HomeCate
	{
		public static String name; //
		public static String id; //
		public  static  String img; //

	}



	/**
	 *主页信息
	 * 主页活动
	 *
	 */

	public static class HomeSanShi
	{
		public String name; //
		public  String img; //

	}

	public static class HomeXianShi
	{
		public  String name; //
		public   String img; //

	}
	public static class HomeHot
	{
		public  String name; //
		public  String img; //

	}
	public static class HomeNine
	{
		public  String name; //
		public String img; //

	}





//
//
//	/**
//	 *主页信息
//	 * 主页活动
//	 *
//	 */
//	public static class HomeAvtivity
//	{
//
//		public void addll( JSONObject brodcast,        JSONObject obs, JSONObject obsx, JSONObject obht, JSONObject obn) {
//		}
//
//		public static class HomeBroadcast
//		{
//			public String content; //内容
//
//		}
//
//
//		public static class HomeSanShi
//	{
//		public String name; //
//		public  String img; //
//
//	}
//
//	public static class HomeXianShi
//	{
//		public  String name; //
//		public   String img; //
//
//	}
//	public static class HomeHot
//	{
//		public  String name; //
//		public  String img; //
//
//	}
//	public static class HomeNine
//	{
//		public  String name; //
//		public String img; //
//
//	}
//
//	}
//











	/**
	 *主页信息
	 * 主页推荐
	 *
	 */

	public static class HomeTuiJian
	{
		public String title; //  标题
		public String price; //  价格
		public String pic_url; // 图片
		public String rate; //折扣类型
		public String item_url ; //跳转的url
		public int volume ; //销量
		public String rebateprice ; //佣金金额

	}




















	/**
	 * 首页推荐
	 * @author larry
	 *
	 */
	public static class RecommendItem
	{
		public String imageSrc; //图片的链接地址
		public String id; //标题
		public String url; //描述信息
	}
	
	/**
	 * 视频的分类信息
	 * @author larry
	 *
	 */
	public static class VideoCatItem
	{
		public String catId; //分类id
		public String catName; //分类的名称
	}
	
	/**
	 * 视频信息
	 * @author larry
	 *
	 */
	public static class VideoItem
	{
		public String imageUrl; //图片地址
		public String videoId; //视频id
		public String title; //标题
		public String desc; //描述信息
		public int memberLevel; //播放该视频需要的会员级别
	}
	
	/**
	 * 会员的等级信息
	 * @author larry
	 */
	public static class MemberTypeItem
	{
		public int memberType; //会员的类型
		public String title; //标题
		public String desc; //描述信息
		public int price; //价格信息
	}
	
	public static class OrederApplyInfo
	{
		public int memberType; //会员的类型
		public String orderType; //订购类型
		public String orderUrl; //订购链接
		public String orderId; //订单id
	}
	
	/**
	 * 版本更新信息
	 * @author larry
	 *
	 */
	public static class UpdateInfo
	{
		public int versionCode; //版本信息
		public String versionName; //版本信息
		public String updateInfo; //更新信息
		public String downloadUrl; //下载地址
		
		@Override
		public String toString()
		{
			return new StringBuilder().append("versionCode:").append(versionCode).append(", versionName:").append(versionName)
					.append(", updateInfo:").append(updateInfo).append("downloadUrl:").append(downloadUrl).toString();
		}
	}
}
