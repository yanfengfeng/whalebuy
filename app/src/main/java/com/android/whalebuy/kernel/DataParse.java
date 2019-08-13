package com.android.whalebuy.kernel;

import android.util.Log;
import com.android.whalebuy.IConstants;
import com.android.whalebuy.data.DataModel;
import com.android.whalebuy.widget.ImageRollView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataParse {
	/**
	 * 检查异常信息
	 *
	 * @param json
	 * @throws KernelException
	 */
	public static void checkException(JSONObject json) throws KernelException {
		Log.i(IConstants.TAG, "DataParse.checkException:" + json.toString());

		int error = json.optInt("code", IConstants.ERROR_SERVER);
		if (200 != error) {
			throw new KernelException(error, json.optString("errormsg", "服务器异常,请稍后再试"));
		}
	}


	/**
	 * 注册成功
	 *
	 * @param json
	 * @return
	 * @throws KernelException
	 * @throws JSONException
	 */
	public static String parseRegister(JSONObject json) throws KernelException, JSONException {
		checkException(json);
		String result = json.getString("result");
		return result;
	}

	/**
	 * 忘记密码
	 *
	 * @param json
	 * @return
	 * @throws KernelException
	 * @throws JSONException
	 */
	public static String parseForget(JSONObject json) throws KernelException, JSONException {
		checkException(json);
		String result = json.getString("result");
		return result;
	}


	/**
	 * 解析用户信息
	 *
	 * @param json
	 * @throws KernelException
	 * @throws JSONException
	 */
	public static void parseUserInfo(JSONObject json) throws KernelException, JSONException {
		checkException(json);

		JSONObject result = json.getJSONObject("result");


		DataModel.UserInfo myInfo = DataModel.UserInfo.getUserInfo();

		myInfo.ycAccount = result.optString("mobile");
		myInfo.ycToken = result.optString("token");
		myInfo.ycUserId = result.optInt("id");
		myInfo.ycUserName = result.optString("nickname");
		myInfo.ycUserLevel = result.optString("level");
		myInfo.ycUserAvatar = result.optString("avatar");


		myInfo.save();
	}


	public static String parseCheckcode(JSONObject json) throws JSONException, KernelException {
		checkException(json);
		return json.getString("code");
	}


	/**
	 * 解析首页信息
	 *
	 * @param
	 * @param
	 * @return notice 字段
	 * @throws KernelException
	 * @throws JSONException
	 */
	public static void parseHomeInfo(JSONObject json, List<Object> allItemList)

			throws KernelException, JSONException {
		int index = 0;
		checkException(json);


		JSONObject jsonItem = null;
		List<DataModel.BannerItem> bannelList = new ArrayList<>();
		//List<Object> broadcastList = new ArrayList<>();
		List<Object> cateList = new ArrayList<>();
		List<Object> activityList= new ArrayList<>();


	//	DataModel.Home hom = new DataModel.Home();

		JSONObject result = json.getJSONObject("result");


		DataModel.BannerItem bannerItem = null;  // 焦点图
		JSONArray bArray = result.getJSONArray("banner");
		final int BAN_COUNT = bArray.length();
		for (index = 0; index < BAN_COUNT; index++) {
			jsonItem = bArray.getJSONObject(index);
			bannerItem = new DataModel.BannerItem();
			bannerItem.imageUrl = jsonItem.getString("src");
			//	bannerItem.id = jsonItem.getString("id");
		//	bannerItem.imageSrc = jsonItem.getString("url");
			bannerItem.id=jsonItem.optString("goods_id");
			bannerItem.type=jsonItem.optString("type");


			bannelList.add(bannerItem);
		}

		allItemList.add(bannelList);







		JSONObject brodcast = result.getJSONObject("broadcast");

		String content = brodcast.optString("content");

		allItemList.add(content);




		DataModel.HomeCate cateItem = null;  // 分类
		JSONArray cateArray = result.getJSONArray("cate");
		final int CATE_COUNT = cateArray.length();
		for (index = 0; index < CATE_COUNT; index++) {

			jsonItem = cateArray.getJSONObject(index);
			cateItem = new DataModel.HomeCate();
			cateItem.name = jsonItem.getString("name");
			cateItem.id = jsonItem.getString("id");
			cateItem.img = jsonItem.getString("img");
			cateList.add(cateItem);
		}

		allItemList.add(cateList);



		//		// 三十
		DataModel.Program SanShi = new DataModel.Program();
		JSONObject obs = result.optJSONObject("left");
		SanShi.name = obs.optString("name");
		SanShi.image = obs.optString("pic");



		activityList.add(SanShi);
		allItemList.add(activityList);


//
		DataModel.Program xs = new DataModel.Program();  // 限时
		JSONObject obsx = result.getJSONObject("top");

		xs.name = obsx.optString("name");
		xs.image = obsx.optString("pic");

		activityList.add(xs);





		DataModel.Program ht = new DataModel.Program();  // 热销
		JSONObject obht = result.getJSONObject("bottom_left");
		ht.name = obht.optString("name");
		ht.image = obht.optString("pic");

		activityList.add(ht);



		DataModel.Program nine = new DataModel.Program();  // 9.9
		JSONObject obn = result.getJSONObject("bottom_right");
		nine.name = obn.optString("name");
		nine.image = obn.optString("pic");

		activityList.add(nine);





//		DataModel.HomeBroadcast broadcastItem = null;  // 播报
//		JSONArray broArray = result.getJSONArray("broadcast");
//		final int BRO_COUNT = broArray.length(); // 播报
//		for (index = 0; index < BRO_COUNT; index++) {
//
//			jsonItem = broArray.getJSONObject(index);
//			broadcastItem = new DataModel.HomeBroadcast();
//			broadcastItem.content = jsonItem.getString("content");
//			broadcastList.add(broadcastItem);
//		}
//        allItemList.add(broadcastList);


	}




	public static void parseHomeTuiJianList(JSONObject json, List<Object> catList) throws KernelException, JSONException {
		int index = 0;
		checkException(json);

		JSONObject jsonItem = null;

		DataModel.HomeTuiJian  tjItem = null;
		JSONArray jonArray = json.getJSONArray("result");
		List<DataModel.HomeTuiJian> listTuiJian = null;

		final int VID_COUNT = jonArray.length();
		for (index = 0; index < VID_COUNT; index++) {
			jsonItem = jonArray.getJSONObject(index);
			if (null == listTuiJian) {
				listTuiJian = new ArrayList<>();
			}
			tjItem = new DataModel.HomeTuiJian();
			tjItem.pic_url= jsonItem.optString("pict_url");
			tjItem.price= jsonItem.optString("price");
			tjItem.title= jsonItem.optString("title");
			tjItem.rate= jsonItem.optString("rate");
			tjItem.item_url= jsonItem.optString("item_url");
			tjItem.rebateprice= jsonItem.optString("rebateprice");
			tjItem.volume=jsonItem.optInt("volume");
			listTuiJian.add(tjItem);
			if (2 == listTuiJian.size()) {
				catList.add(listTuiJian);
				listTuiJian = null;
			}

		}

	}





//	/**
//	 * 解析首页信息
//	 *
//	 * @param bannelList
//	 *            BannerItem
//	 * @param recommendList
//	 *            RecommendItem
//	 * @return notice 字段
//	 * @throws KernelException
//	 * @throws JSONException
//	 */
//	public static String parseHomeInfo(JSONObject json, List<ImageRollView.RollItem> bannelList
//			, List<Object> recommendList)
//			throws KernelException, JSONException {
//		int index = 0;
//		checkException(json);
//
//		JSONObject jsonItem = null;
//		DataModel.BannerItem bannerItem = null;
//
//		JSONObject jItem = null;
//		DataModel.RecommendItem reItem = null;
//
//		String notice = json.optString("notice");
//		JSONArray bArray = json.getJSONArray("bannerlist");
//		final int BAN_COUNT = bArray.length(); // 焦点图
//		for (index = 0; index < BAN_COUNT; index++) {
//
//			jsonItem = bArray.getJSONObject(index);
//			bannerItem = new DataModel.BannerItem();
//			bannerItem.imageUrl = jsonItem.getString("imageurl");
//			bannerItem.id = jsonItem.getString("openinfo");
//			bannerItem.imageSrc = jsonItem.getString("memberlevel");
//			bannelList.add(bannerItem);
//		}
//
//		JSONArray rArray = json.getJSONArray("recommendlist");
//		final int RE_COUNT = rArray.length(); // 推荐
//		for (index = 0; index < RE_COUNT; index++) {
//
//			jItem = rArray.getJSONObject(index);
//			reItem= new DataModel.RecommendItem();
////			reItem.imageUrl = jItem.getString("imageurl");
////			reItem.type = jItem.getInt("type");
////			reItem.title = jItem.getString("title");
////			reItem.desc = jItem.getString("desc");
////			reItem.openInfo = jItem.getString("openinfo");
////			reItem.memberLevel = jItem.getInt("memberlevel");
//			recommendList.add(reItem);
//
//		}
//
//		return notice;
//	}

	/**
	 * 5.2 解析视频分类列表
	 * 
	 * @param json
	 * @param catList VideoCatItem
	 * @throws KernelException
	 * @throws JSONException
	 */
	public static void parseVideoCatList(JSONObject json, List<Object> catList) throws KernelException, JSONException {
		int index = 0;
		checkException(json);
		
		JSONObject jsonItem = null;
		DataModel.VideoCatItem  catItem = null;
		JSONArray jonArray = json.getJSONArray("catlist");
		
		final int VID_COUNT = jonArray.length();
		for (index = 0; index < VID_COUNT; index++) {

			jsonItem = jonArray.getJSONObject(index);
			catItem = new DataModel.VideoCatItem();
			catItem.catId= jsonItem.getString("catid");
			catItem.catName= jsonItem.getString("catname");
			catList.add(catItem);
		}

	}

	/**
	 * 6.2 解析视频列表
	 * 
	 * @param json
	 * @param videoList VideoItem
	 * @return totalcount
	 * @throws KernelException
	 * @throws JSONException
	 */
	public static int parseVideoList(JSONObject json, List<Object> videoList) throws KernelException, JSONException {
		int index = 0;
		checkException(json);
		int totalcount = json.getInt("totalcount");
		JSONObject vJsonObject = null;
		DataModel.VideoItem  vItem= null  ;
		JSONArray vArray = json.getJSONArray("videolist");
		for (index = 0; index < vArray.length(); index++) {
			vJsonObject = vArray.getJSONObject(index);
			vItem= new DataModel.VideoItem();
			vItem.imageUrl = vJsonObject.getString("imageurl");
			vItem.videoId = vJsonObject.getString("videoid");
			vItem.title = vJsonObject.getString("title");
			vItem.desc = vJsonObject.getString("desc");
			vItem.memberLevel = vJsonObject.optInt("memberlevel");
			videoList.add(vItem);
			
		}
		
		
		
		
		return totalcount;
	}

	/**
	 * 7.2 解析视频的播放地址
	 * 
	 * @param json
	 * @return
	 * @throws KernelException
	 * @throws JSONException
	 */
	public static String parseVideoUrl(JSONObject json) throws KernelException, JSONException {
		checkException(json);
		String videoUrl = json.getString("videourl");
		return videoUrl;
	}

	/**
	 * 8.2 解析会员列表
	 * 
	 * @param json
	 * @param memList MemberTypeItem
	 * @throws KernelException
	 * @throws JSONException
	 */
	public static void parseMemberTypeList(JSONObject json, List<Object> memList) throws KernelException, JSONException {
		int index = 0;
		checkException(json);
		
		JSONObject mObject  = null ;
		DataModel.MemberTypeItem  typeItem = null;
		int memType = 0;
		JSONArray mArray = json.getJSONArray("memlist");
		 for (index = 0; index < mArray.length(); index++)
		 {
			 mObject = mArray.getJSONObject(index);
			 memType = mObject.getInt("membertype");
			 if(DataModel.UserInfo.MEM_NORMAL != memType)
			 {
				 typeItem = new DataModel.MemberTypeItem();
				 typeItem.memberType = memType;
				 typeItem.title= mObject.getString("title");
				 typeItem.desc= mObject.getString("desc");
				 typeItem.price= mObject.getInt("price");
				 
				 memList.add(typeItem);
			 }
		}
		
	}
	
	/**
	 * 9.2 解析订购申请
	 * @param json
	 * @return
	 * @throws JSONException
	 * @throws KernelException 
	 */
	public static DataModel.OrederApplyInfo parseOrderApply(JSONObject json) throws JSONException, KernelException
	{
		checkException(json);
		DataModel.OrederApplyInfo orderInfo = new DataModel.OrederApplyInfo();
		
		orderInfo.memberType = json.getInt("membertype");
		orderInfo.orderType = json.optString("ordertype");
		orderInfo.orderUrl = json.optString("orderurl");
		orderInfo.orderId = json.getString("orderid");
		
		Log.i(IConstants.TAG, "orderUrl:" + orderInfo.orderUrl);
		
		return orderInfo;
	}
	
	/**
	 * 14.2 解析版本更新信息
	 * @param json
	 * @return
	 * @throws KernelException 
	 */
	public static DataModel.UpdateInfo parseUpdateInfo(JSONObject json) throws KernelException
	{
		checkException(json);
		
		DataModel.UpdateInfo updateInfo = new DataModel.UpdateInfo();
		updateInfo.downloadUrl = json.optString("updateurl");
		updateInfo.updateInfo = json.optString("updateinfo");
		updateInfo.versionCode = json.optInt("versioncode");
		updateInfo.versionName = json.optString("versionname");
		
		Log.i(IConstants.TAG, "parseUpdateInfo:" + updateInfo );
		return updateInfo;
	}

	public static String parsePageUrl(JSONObject json) throws KernelException, JSONException {
		checkException(json);
		String videoUrl = json.getString("pageurl");
		return videoUrl;
	}
}
