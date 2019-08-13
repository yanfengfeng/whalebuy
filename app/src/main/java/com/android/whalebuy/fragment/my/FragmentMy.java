//package com.android.whalebuy.fragment.my;
//
//import android.app.ProgressDialog;
//import android.content.DialogInterface;
//import android.content.DialogInterface.OnDismissListener;
//import android.os.Bundle;
//import android.preference.PreferenceActivity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//import com.android.whalebuy.IConstants;
//import com.android.whalebuy.R;
//import com.android.whalebuy.common.BaseFragment;
//import com.android.whalebuy.common.ObjectAdapterList;
//import com.android.whalebuy.data.DataModel;
//import com.android.whalebuy.kernel.DataPackage;
//import com.android.whalebuy.kernel.DataParse;
//import com.android.whalebuy.kernel.KernelException;
//import com.android.whalebuy.listener.IAdapterObjectList;
//import com.loopj.android.http.AsyncHttpClient;
//import com.loopj.android.http.JsonHttpResponseHandler;
//import org.json.JSONObject;
//
//public class FragmentMy extends BaseFragment implements OnDismissListener, IAdapterObjectList {
//
//	private View mRootView;
//	private AsyncHttpClient mHttpClient;
//	private ProgressDialog mProgressDag;
//
//	private final int ITEM_LEVEL = 0; // 等级
//	private final int ITEM_NOTICE = 1; // 会员详情
//
//	private TextView tvName;
//	private String mMemberDesc;
//
//	public static FragmentMy create() {
//		return new FragmentMy();
//	}
//
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		mRootView = inflater.inflate(R.layout.fragment_my, container, false);
//		TextView txtTitle = (TextView) (mRootView.findViewById(R.id.ID_TXT_TITLE));
//		txtTitle.setText("用户中心");
//
//		tvName = (TextView) mRootView.findViewById(R.id.tv_user_name); // 账号
//
//		mProgressDag = new ProgressDialog(getActivity());
//		mProgressDag.setMessage(getString(R.string.loading));
//		mProgressDag.setOnDismissListener(this);
//		mProgressDag.setCancelable(true);
//
//		mHttpClient = new AsyncHttpClient(); // 请求类
//
//		ListView listView = (ListView) (mRootView.findViewById(R.id.ID_LIST_VIEW));
//		ObjectAdapterList adapter = new ObjectAdapterList(this, listView);
//		listView.setAdapter(adapter);
//
//		myCenter();
//		return mRootView;
//	}
//
//	private void myCenter() {  // 用户信息
//
//		mProgressDag.show();
//		mHttpClient.post(getActivity(), IConstants.REQUEST_SERVER_URL, DataPackage.getUserInfo()
//				, new JsonHttpResponseHandler() {
//			@Override
//			public void onSuccess(int statusCode, PreferenceActivity.Header[] headers, JSONObject response) {
//
//				try {
//					DataParse.parseUserInfo(response);
//
//					DataModel.UserInfo myInfo = DataModel.UserInfo.getUserInfo();
//					tvName.setText("你好 :" + myInfo.ycAccount);
//					//tvMember.setText("会员状态 : "+KernelManager.getMemberType(myInfo.ycMemberType));
//
//					memberType();
//
//				} catch (KernelException e) {
//					mProgressDag.dismiss();
//					Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
//				} catch (Exception e) {
//					mProgressDag.dismiss();
//					Toast.makeText(getActivity(), R.string.error_server, Toast.LENGTH_SHORT).show();
//				}
//			}
//
//			@Override
//			public void onFailure(int statusCode, PreferenceActivity.Header[] headers, Throwable throwable, JSONObject errorResponse) {
//				mProgressDag.dismiss();
//				Toast.makeText(getActivity(), R.string.error_network, Toast.LENGTH_SHORT).show();
//			}
//
//			@Override
//			public void onFailure(int statusCode, PreferenceActivity.Header[] headers, String responseString, Throwable throwable) {
//				mProgressDag.dismiss();
//				Toast.makeText(getActivity(), R.string.error_network, Toast.LENGTH_SHORT).show();
//			}
//		});
//	}
//
//	private void memberType() { //  会员等级
//		mHttpClient.post(getActivity(), IConstants.REQUEST_SERVER_URL,
//				DataPackage.getCommonMessage("memberlist")
//				, new JsonHttpResponseHandler() {
//			@Override
//			public void onSuccess(int statusCode, PreferenceActivity.Header[] headers, JSONObject response) {
//				mProgressDag.dismiss();
//				try {
//					ListView listView = (ListView)(mRootView.findViewById(R.id.ID_LIST_VIEW));
//	        		ObjectAdapterList adapter = (ObjectAdapterList)(listView.getAdapter());
//	        		DataParse.parseMemberTypeList(response, adapter.getDataList());
//
//	        		DataModel.MemberTypeItem item = (DataModel.MemberTypeItem)(adapter.getItem(0));
//	        		mMemberDesc = item.desc;
//
//	        		adapter.addItem(new DataModel.ListItem(ITEM_NOTICE, 0));
//
//	        		adapter.notifyDataSetChanged();
//
//				} catch (KernelException e) {
//					Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
//				} catch (Exception e) {
//					Toast.makeText(getActivity(), R.string.error_server, Toast.LENGTH_SHORT).show();
//				}
//			}
//
//			@Override
//			public void onFailure(int statusCode, PreferenceActivity.Header[] headers, Throwable throwable, JSONObject errorResponse) {
//				mProgressDag.dismiss();
//				Toast.makeText(getActivity(), R.string.error_network, Toast.LENGTH_SHORT).show();
//			}
//
//			@Override
//			public void onFailure(int statusCode, PreferenceActivity.Header[] headers, String responseString, Throwable throwable) {
//				mProgressDag.dismiss();
//				Toast.makeText(getActivity(), R.string.error_network, Toast.LENGTH_SHORT).show();
//			}
//		});
//	}
//
//
//	// 会员类型内容
//	private void memberTypeInfo(View convertView , DataModel.MemberTypeItem item) {
//		/**
//		DataModel.UserInfo myInfo = DataModel.UserInfo.getUserInfo();
//
//		TextView tvItme = (TextView) convertView.findViewById(R.id.tv_item_type); // 会员类型
//		tvItme.setText(item.title);
//		//tvItme.setText(KernelManager.getMemberType(item.memberType));
//
//		TextView tvPrice = (TextView) convertView.findViewById(R.id.tv_item_price); // 价格
//		tvPrice.setText(item.price + "元/月");
//
//		TextView tvOrder = (TextView) convertView.findViewById(R.id.tv_order); // 订购
//		**/
//	}
//
//
//
//    //  会员描述 内容
//	private void memberInfo(View convertView)
//	{
//		TextView tvItmeConent = (TextView) convertView.findViewById(R.id.TV_CONTENT); // 会员Item 内容
//		tvItmeConent.setText(mMemberDesc);
//	}
//
//	@Override
//	public void onDismiss(DialogInterface dialog) {
//		if (mProgressDag == dialog) {
//			mHttpClient.cancelAllRequests(true);
//		}
//	}
//
//	@Override
//	public void onDestroyView() {
//		super.onDestroyView();
//		mHttpClient.cancelAllRequests(true);
//	}
//
//	@Override
//	public View onItemChanged(int position, View convertView, ViewGroup parent, ObjectAdapterList adapter) {
//
//		Object objItem = adapter.getItem(position);
//
//		if (objItem instanceof DataModel.MemberTypeItem) {
//			if (null == convertView)
//				// 会员类型
//			{
//				convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_my_center_member, null, false);
//			}
//
//			memberTypeInfo(convertView, (DataModel.MemberTypeItem) objItem);
//
//		} else{
//			//  会员具体信息
//			if (null == convertView) {
//				convertView = LayoutInflater.from(getActivity()).inflate(R.layout.my_center_details, null, false);
//			}
//			memberInfo(convertView);
//		}
//
//		return convertView;
//	}
//
//	@Override
//	public int onAdapterItemViewType(int position, ObjectAdapterList adapter) {
//
//		int type = 0;
//		Object objItem = adapter.getItem(position);
//
//
//		if (objItem instanceof DataModel.MemberTypeItem) {
//
//			type = ITEM_LEVEL;
//
//		} else {
//
//			type = ITEM_NOTICE;
//
//		}
//
//		return type;
//
//
//	}
//
//	@Override
//	public int onAdapterViewTypeCount(ObjectAdapterList adapter) {
//		return 2;
//	}
//
//	@Override
//	public long onAdapterItemId(int position, ObjectAdapterList adapter) {
//		return 0;
//	}
//}
