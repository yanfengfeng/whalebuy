//package com.android.whalebuy.fragment.home;
//
//import android.app.AlertDialog;
//import android.app.ProgressDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//import com.android.whalebuy.IConstants;
//import com.android.whalebuy.R;
//import com.android.whalebuy.common.BaseFragment;
//import com.android.whalebuy.common.ObjectAdapterList;
//import com.android.whalebuy.data.DataModel;
//import com.android.whalebuy.fragment.my.FragmentLogin;
//import com.android.whalebuy.fragment.my.FragmentOrderType;
//import com.android.whalebuy.fragment.publics.FragmentWebView;
//import com.android.whalebuy.kernel.DataPackage;
//import com.android.whalebuy.kernel.KernelManager;
//import com.android.whalebuy.listener.IAdapterObjectList;
//import com.android.whalebuy.listener.IFragmentListener;
//import com.android.whalebuy.widget.ImageRollView;
//import com.loopj.android.http.AsyncHttpClient;
//import com.loopj.android.http.JsonHttpResponseHandler;
//import com.nostra13.universalimageloader.core.DisplayImageOptions;
//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.unicom.dcLoader.Utils.UnipayPayResultListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class FragmentHomePage extends BaseFragment implements IFragmentListener, OnClickListener, FragmentLogin.ILoginSuccess,
//        IAdapterObjectList, FragmentOrderType.IOrderTypeSelected, UnipayPayResultListener {
//    private final int ITEM_BANNER = 0; //轮播图
//    private final int ITEM_NOTICE = 1; //公告
//    private final int ITEM_RECOMMEND = 2; //推荐
//    private final int TYPE_URL = 0; //普通的url链接
//    private final int TYPE_VIDEO = 1; //视频节点
//    private final int TYPE_OUT_URL = 2; //外部链接
//    private View mRootView;
//    private ProgressDialog mProgressDag;
//    private AsyncHttpClient mHttpClient;
//    private String mNotice; //广告信息
//    private List<ImageRollView.RollItem> mBannerList; //轮播图列表
//
//
//    private List<Object> mRecommedList; //推荐列表
//    private DisplayImageOptions mImageOptions;
//    private String mVideoId; //视频id
//    private int mVideoMemType; //视频的会员等级
//    private DataModel.OrederApplyInfo mOrderInfo;
//
//    public static FragmentHomePage create() {
//        return new FragmentHomePage();
//    }
//
//    @Override
//    public void fragmentWillShow() {
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        mRootView = inflater.inflate(R.layout.fragment_homepage, container, false);
//
//        mProgressDag = new ProgressDialog(getActivity());
//        mProgressDag.setMessage(getString(R.string.loading));
//        //mProgressDag.setOnDismissListener(getActivity());
//        mProgressDag.setCancelable(true);
//
//        ListView listView = (ListView) (mRootView.findViewById(R.id.ID_LIST_VIEW));
//        ObjectAdapterList adapter = new ObjectAdapterList(this, listView);
//        listView.setAdapter(adapter);
//
//        mRootView.findViewById(R.id.ID_BTN_MY).setOnClickListener(this);
//        mRootView.findViewById(R.id.ID_IMG_RELOAD).setOnClickListener(this);
//
//        mBannerList = new ArrayList<ImageRollView.RollItem>();
//        mRecommedList = new ArrayList<Object>();
//        mHttpClient = new AsyncHttpClient(); // 请求类
//
//        mImageOptions = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.loading)
//                .showImageForEmptyUri(R.drawable.no_image)
//                .showImageOnFail(R.drawable.no_image).cacheInMemory(true)
//                .cacheOnDisk(true).considerExifParams(true).build();
//
////        getHomeData();
//
//        return mRootView;
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//    }
//
//    @Override
//    public void onClick(View arg0) {
//        if (arg0.getId() == R.id.ID_BTN_MY) {
//            //进入用户中心
//            goUserCenter();
//        } else if (R.id.ID_IMG_RELOAD == arg0.getId()) {
//            getHomeData();
//        } else {
//            Object tag = arg0.getTag();
//            if (null != tag) {
//                if (tag instanceof DataModel.RecommendItem) {
//                    clickItem((DataModel.RecommendItem) tag);
//                }
//            }
//        }
//    }
//
//    private void clickItem(DataModel.RecommendItem item) {
//        if (TYPE_URL == item.type) {
//            //打开一个网页
//            getFragmentManager().beginTransaction().add(R.id.container
//                    , FragmentWebView.create(item.openInfo))
//                    .addToBackStack(IConstants.FRAGMENT_MAIN_THREAD).commit();
//        } else if (TYPE_VIDEO == item.type) {
//            //播放视频
//            DataModel.UserInfo myInfo = DataModel.UserInfo.getUserInfo();
//            if (DataModel.UserInfo.MEM_NORMAL == item.memberLevel) {
//                //免费视频，可以直接观看
//                playVideo(item.openInfo);
//            } else if (myInfo.ycUserId <= 0) {
//                //用户还未登录
//                getFragmentManager().beginTransaction().add(R.id.container
//                        , FragmentLogin.create(this))
//                        .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();
//            }
//            /**
//             else if(item.memberLevel > myInfo.ycMemberType)
//             {
//             //会员的级别不够
//             tellMemberLevel(item.memberLevel, myInfo.ycMemberType);
//             }
//             **/
//            else {
//                mVideoId = item.openInfo;
//                mVideoMemType = item.memberLevel;
//                playVideo(item.openInfo);
//            }
//        }
//    }
//
//    /**
//     * 提示用户会员级别不够
//     *
//     * @param memberLevel
//     */
//    private void tellMemberLevel(int memberLevel, int myLevel) {
//        String message = "";
//        if (DataModel.UserInfo.MEM_NORMAL == myLevel) {
//            message = "该视频只有" + KernelManager.getMemberType(memberLevel) + "才能使用收看哦！是否现在成为会员？";
//        } else {
//            message = "当前视频需要" + KernelManager.getMemberType(memberLevel) + "才能进行观看，您的等级不够哦，是否升级会员？";
//        }
//
//        AlertDialog.Builder alertBuild = new AlertDialog.Builder(getActivity());
//        alertBuild.setMessage(message);
//        alertBuild.setTitle(R.string.app_name);
//        alertBuild.setIcon(R.drawable.ic_launcher);
//        alertBuild.setPositiveButton("确定",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // TODO Auto-generated method stub
////                        startActivity(new Intent(getActivity(), ActivityMy.class));
//                        /**
//                         getFragmentManager().beginTransaction().add(R.id.container
//                         , ActivityMy.create())
//                         .addToBackStack(IConstants.FRAGMENT_MAIN_THREAD).commit();
//                         **/
//                    }
//                });
//        alertBuild.setNegativeButton("取消", null);
//        alertBuild.create().show();
//    }
//
//    /**
//     * 播放视频
//     *
//     * @param videoId
//     */
//    private void playVideo(String videoId) {
//        mProgressDag.show();
//        mHttpClient.post(getActivity(), IConstants.REQUEST_SERVER_URL, DataPackage.playVideo(videoId),
//                new JsonHttpResponseHandler() {
////                    @Override
////                    public void onSuccess(int statusCode, PreferenceActivity.Header[] headers,
////                                          JSONObject response) {
////                        mProgressDag.dismiss();
////
////                        try {
////                            String playUrl = DataParse.parseVideoUrl(response);
////                            Intent it = new Intent(Intent.ACTION_VIEW);
////                            Uri uri = Uri.parse(playUrl);
////                            it.setData(uri);
////                            it.setDataAndType(uri, "video/*");
////                            getActivity().startActivity(it);
////                        } catch (KernelException e) {
////                            // TODO Auto-generated catch block
////                            if (200 == e.getErrCode()) {
////                                //该视频需要订购才能播放
////                                getFragmentManager().beginTransaction().add(R.id.container
////                                        , FragmentOrderType.create(FragmentHomePage.this, mVideoMemType))
////                                        .addToBackStack(IConstants.FRAGMENT_MAIN_THREAD).commit();
////                            } else {
////                                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
////                            }
////                        } catch (JSONException e) {
////                            // TODO Auto-generated catch block
////                            Toast.makeText(getActivity(), R.string.error_server, Toast.LENGTH_SHORT).show();
////                        }
////                    }
////
////                    @Override
////                    public void onFailure(int statusCode, PreferenceActivity.Header[] headers,
////                                          Throwable throwable, JSONObject errorResponse) {
////                        mProgressDag.dismiss();
////                        Toast.makeText(getActivity(), R.string.error_network, Toast.LENGTH_SHORT).show();
////                        Log.i(IConstants.TAG, "playVideo.onFailure.statusCode:" + statusCode + " errorResponse:" + errorResponse);
////                    }
////
////                    @Override
////                    public void onFailure(int statusCode, PreferenceActivity.Header[] headers,
////                                          String responseString, Throwable throwable) {
////                        mProgressDag.dismiss();
////                        Toast.makeText(getActivity(), R.string.error_network, Toast.LENGTH_SHORT).show();
////                        Log.i(IConstants.TAG, "playVideo.onFailure.statusCode:" + statusCode + " responseString:" + responseString);
////                    }
//                });
//    }
//
//    private void goUserCenter() {
//        DataModel.UserInfo myInfo = DataModel.UserInfo.getUserInfo();
//        if (myInfo.ycUserId <= 0) {
//            //用户还未登录
//            getFragmentManager().beginTransaction().add(R.id.container
//                    , FragmentLogin.create(this))
//                    .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();
//        } else {
//            //用户已登录
////            startActivity(new Intent(getActivity(), ActivityMy.class));
//            /**
//             getFragmentManager().beginTransaction().add(R.id.container
//             , ActivityMy.create())
//             .addToBackStack(IConstants.FRAGMENT_MAIN_THREAD).commit();
//             **/
//        }
//    }
//
//    /**
//     * 获取首页数据
//     */
//    private void getHomeData() {
//        mProgressDag.show();
//        mHttpClient.post(getActivity(), IConstants.REQUEST_SERVER_URL, DataPackage.getCommonMessage("homeinfo"),
//                new JsonHttpResponseHandler() {
////                    @Override
////                    public void onSuccess(int statusCode, PreferenceActivity.Header[] headers,
////                                          JSONObject response) {
////                        mProgressDag.dismiss();
////                        mRootView.findViewById(R.id.ID_IMG_RELOAD).setVisibility(View.GONE);
////
////                        try {
////                            mNotice = DataParse.parseHomeInfo(response, mBannerList, mRecommedList);
////
////                            ListView listView = (ListView) (mRootView.findViewById(R.id.ID_LIST_VIEW));
////                            ObjectAdapterList adapter = (ObjectAdapterList) (listView.getAdapter());
////                            adapter.removeAll();
////                            adapter.addItem(new DataModel.ListItem(ITEM_BANNER, 0));
////                            if (false == KernelManager.isStringEmpty(mNotice)) {
////                                adapter.addItem(new DataModel.ListItem(ITEM_NOTICE, 0));
////                            }
////                            adapter.addList(mRecommedList);
////
////                            adapter.notifyDataSetChanged();
////                        } catch (KernelException e) {
////                            // TODO Auto-generated catch block
////                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
////                            mRootView.findViewById(R.id.ID_IMG_RELOAD).setVisibility(View.VISIBLE);
////                        } catch (JSONException e) {
////                            // TODO Auto-generated catch block
////                            Toast.makeText(getActivity(), R.string.error_server, Toast.LENGTH_SHORT).show();
////                            mRootView.findViewById(R.id.ID_IMG_RELOAD).setVisibility(View.VISIBLE);
////                        }
////                    }
////
////                    @Override
////                    public void onFailure(int statusCode, PreferenceActivity.Header[] headers,
////                                          Throwable throwable, JSONObject errorResponse) {
////                        mProgressDag.dismiss();
////                        Toast.makeText(getActivity(), R.string.error_network, Toast.LENGTH_SHORT).show();
////                        mRootView.findViewById(R.id.ID_IMG_RELOAD).setVisibility(View.VISIBLE);
////                        Log.i(IConstants.TAG, "guessGoodsPrice.onFailure.statusCode:" + statusCode + " errorResponse:" + errorResponse);
////                    }
////
////                    @Override
////                    public void onFailure(int statusCode, PreferenceActivity.Header[] headers,
////                                          String responseString, Throwable throwable) {
////                        mProgressDag.dismiss();
////                        Toast.makeText(getActivity(), R.string.error_network, Toast.LENGTH_SHORT).show();
////                        mRootView.findViewById(R.id.ID_IMG_RELOAD).setVisibility(View.VISIBLE);
////                        Log.i(IConstants.TAG, "guessGoodsPrice.onFailure.statusCode:" + statusCode + " responseString:" + responseString);
////                    }
//                });
//    }
//
//    @Override
//    public void onLoginSuccess() {
//        mRootView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//            }
//        }, 200);
//    }
//
//    /**
//     * 设置首页轮播图
//     *
//     * @param convertView
//     */
//    private void setRollviewItem(View convertView) {
//        ImageRollView imageRollView = (ImageRollView) (convertView.findViewById(R.id.ID_ROLLVIEW));
//        imageRollView.initImageRollView(mBannerList, true);
//        imageRollView.setOnImageRollViewClickListener(new ImageRollView.OnImageRollViewClickListener() {
//            @Override
//            public void onClick(View view, int position, ImageRollView.RollItem rollitem) {
//                // TODO Auto-generated method stub
//                Log.i(IConstants.TAG, "setOnImageRollViewClickListener.position:" + position);
//                DataModel.BannerItem item = (DataModel.BannerItem) (rollitem);
//                if (TYPE_URL == item.type) {
//                    //打开一个网页
//                    getFragmentManager().beginTransaction().add(R.id.container
//                            , FragmentWebView.create(item.openInfo))
//                            .addToBackStack(IConstants.FRAGMENT_MAIN_THREAD).commit();
//                } else if (TYPE_OUT_URL == item.type) {
//                    //外部链接
//                    Log.i(IConstants.TAG, "outUrl:" + item.openInfo);
//                    Uri uri = Uri.parse(item.openInfo);
//                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                    startActivity(intent);
//                } else if (TYPE_VIDEO == item.type) {
//                    //播放视频
//                    DataModel.UserInfo myInfo = DataModel.UserInfo.getUserInfo();
//                    if (DataModel.UserInfo.MEM_NORMAL == item.memberLevel) {
//                        //免费视频，可以直接观看
//                        playVideo(item.openInfo);
//                    } else if (myInfo.ycUserId <= 0) {
//                        //用户还未登录
//                        getFragmentManager().beginTransaction().add(R.id.container
//                                , FragmentLogin.create(FragmentHomePage.this))
//                                .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();
//                    }
//                    /**
//                     else if(item.memberLevel > myInfo.ycMemberType)
//                     {
//                     //会员的级别不够
//                     tellMemberLevel(item.memberLevel, myInfo.ycMemberType);
//                     }
//                     **/
//                    else {
//                        mVideoId = item.openInfo;
//                        mVideoMemType = item.memberLevel;
//                        playVideo(item.openInfo);
//                    }
//                }
//            }
//        });
//    }
//
//    /**
//     * 设置首页公告
//     *
//     * @param convertView
//     */
//    private void setNoticeItem(View convertView) {
//        TextView notice = (TextView) (convertView.findViewById(R.id.ID_TXT_TITLE));
//        notice.setText(mNotice);
//    }
//
//    private void setRecommendItem(View convertView, DataModel.RecommendItem item) {
//        ImageView imageView = (ImageView) (convertView.findViewById(R.id.ID_IMAGE));
//        ImageLoader.getInstance().displayImage(item.imageUrl, imageView, mImageOptions);
//
//        TextView txtView = (TextView) (convertView.findViewById(R.id.ID_TXT_TITLE));
//        txtView.setText(item.title);
//
//        txtView = (TextView) (convertView.findViewById(R.id.ID_TXT_DESC));
//        txtView.setText(item.desc);
//
//        txtView = (TextView) (convertView.findViewById(R.id.ID_TXT_MEMBER));
//        txtView.setVisibility(View.GONE);
//
//        if (TYPE_VIDEO == item.type) {
//            if (DataModel.UserInfo.MEM_DIAMOND == item.memberLevel) {
//                txtView.setVisibility(View.VISIBLE);
//                txtView.setText("钻石会员");
//            } else if (DataModel.UserInfo.MEM_GOLD == item.memberLevel) {
//                txtView.setVisibility(View.VISIBLE);
//                txtView.setText("钻石会员");
//            } else if (DataModel.UserInfo.MEM_PLATINUM == item.memberLevel) {
//                txtView.setVisibility(View.VISIBLE);
//                txtView.setText("钻石会员");
//            } else {
//                txtView.setVisibility(View.VISIBLE);
//                txtView.setText("免费视频");
//            }
//        }
//
//        convertView.setTag(item);
//        convertView.setOnClickListener(this);
//    }
//
//    /**
//     * 用户即将订购
//     *
//     * @param
//     */
//    private void orderBefor() {
//        mProgressDag.show();
//        mHttpClient.post(getActivity(), IConstants.REQUEST_SERVER_URL
//                , DataPackage.orderApply(DataModel.UserInfo.MEM_TRY_DIAMOND, false, "")
//                , new JsonHttpResponseHandler() {
////                    @Override
////                    public void onSuccess(int statusCode, PreferenceActivity.Header[] headers, JSONObject response) {
////                        try {
////                            mProgressDag.dismiss();
////                            Log.i(IConstants.TAG, "首页链接:" + IConstants.REQUEST_SERVER_URL);
////                            mOrderInfo = DataParse.parseOrderApply(response);
////
////                            com.unicom.dcLoader.Utils.getInstances()
////                                    .payOnline(getActivity(), "009", "0", mOrderInfo.orderId, FragmentHomePage.this);
////
////                        } catch (KernelException e) {
////                            mProgressDag.dismiss();
////                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
////                        } catch (Exception e) {
////                            mProgressDag.dismiss();
////                            Toast.makeText(getActivity(), R.string.error_server, Toast.LENGTH_SHORT).show();
////                        }
////                    }
////
////                    @Override
////                    public void onFailure(int statusCode, PreferenceActivity.Header[] headers, Throwable throwable, JSONObject errorResponse) {
////                        mProgressDag.dismiss();
////                        Log.i(IConstants.TAG, "onFailure.statusCode:" + statusCode + ", errorResponse:" + errorResponse);
////                        Toast.makeText(getActivity(), R.string.error_network, Toast.LENGTH_SHORT).show();
////                    }
////
////                    @Override
////                    public void onFailure(int statusCode, PreferenceActivity.Header[] headers, String responseString, Throwable throwable) {
////                        mProgressDag.dismiss();
////                        Log.i(IConstants.TAG, "onFailure.statusCode:" + statusCode + ", errorResponse:" + responseString);
////                        Toast.makeText(getActivity(), R.string.error_network, Toast.LENGTH_SHORT).show();
////                    }
//                });
//    }
//
//    private void getUserInfoDelay(final int delayTime) {
//        mProgressDag.show();
//        mRootView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
//                mHttpClient.post(getActivity().getApplicationContext(), IConstants.REQUEST_SERVER_URL, DataPackage.getUserInfo()
//                        , new JsonHttpResponseHandler() {
////                            @Override
////                            public void onSuccess(int statusCode, PreferenceActivity.Header[] headers, JSONObject response) {
////
////                                try {
////                                    mProgressDag.dismiss();
////                                    DataParse.parseUserInfo(response);
////
////                                    Toast.makeText(getActivity().getApplicationContext(), "节目订购成功", Toast.LENGTH_SHORT).show();
////
////                                } catch (KernelException e) {
////                                    mProgressDag.dismiss();
////                                    Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
////                                } catch (Exception e) {
////                                    mProgressDag.dismiss();
////                                    Toast.makeText(getActivity().getApplicationContext(), R.string.error_server, Toast.LENGTH_SHORT).show();
////                                }
////                            }
////
////                            @Override
////                            public void onFailure(int statusCode, PreferenceActivity.Header[] headers, Throwable throwable, JSONObject errorResponse) {
////                                mProgressDag.dismiss();
////                                Toast.makeText(getActivity().getApplicationContext(), R.string.error_network, Toast.LENGTH_SHORT).show();
////                            }
////
////                            @Override
////                            public void onFailure(int statusCode, PreferenceActivity.Header[] headers, String responseString, Throwable throwable) {
////                                mProgressDag.dismiss();
////                                Toast.makeText(getActivity().getApplicationContext(), R.string.error_network, Toast.LENGTH_SHORT).show();
////                            }
//                        });
//            }
//        }, delayTime);
//    }
//
//    @Override
//    public View onItemChanged(int position, View convertView, ViewGroup parent, ObjectAdapterList adapter) {
//        Object objItem = adapter.getItem(position);
//        if (objItem instanceof DataModel.ListItem) {
//            DataModel.ListItem item = (DataModel.ListItem) objItem;
//            if (ITEM_BANNER == item.getViewType()) {
//                //首页轮播图
//                if (null == convertView) {
//                    convertView = LayoutInflater.from(getActivity()).inflate(
//                            R.layout.item_image_rollview, null, false);
//                }
//                setRollviewItem(convertView);
//            } else {
//                //公告
//                if (null == convertView) {
//                    convertView = LayoutInflater.from(getActivity()).inflate(
//                            R.layout.item_notice, null, false);
//                }
//                setNoticeItem(convertView);
//            }
//        } else if (objItem instanceof DataModel.RecommendItem) {
//            //推荐列表
//            if (null == convertView) {
//                convertView = LayoutInflater.from(getActivity()).inflate(
//                        R.layout.item_image, null, false);
//            }
//            setRecommendItem(convertView, (DataModel.RecommendItem) objItem);
//        }
//
//        return convertView;
//    }
//
//    @Override
//    public int onAdapterItemViewType(int position, ObjectAdapterList adapter) {
//        int type = ITEM_BANNER;
//        Object objItem = adapter.getItem(position);
//        if (objItem instanceof DataModel.ListItem) {
//            DataModel.ListItem item = (DataModel.ListItem) objItem;
//            type = item.getViewType();
//        } else if (objItem instanceof DataModel.RecommendItem) {
//            type = ITEM_RECOMMEND;
//        }
//
//        return type;
//    }
//
//    @Override
//    public int onAdapterViewTypeCount(ObjectAdapterList adapter) {
//        return 3;
//    }
//
//    @Override
//    public long onAdapterItemId(int position, ObjectAdapterList adapter) {
//        return 0;
//    }
//
//    @Override
//    public void onOrderTypeSelected(int orderType) {
//        // TODO Auto-generated method stub
//        if (FragmentOrderType.ORDER_TYPE_MONTH == orderType) {
//            //包月
////            startActivity(new Intent(getActivity(), ActivityMy.class));
//        } else {
//            //按次
//            orderBefor();
//        }
//    }
//
//    @Override
//    public void PayResult(String arg0, int arg1, int arg2, String arg3) {
//        // TODO Auto-generated method stub
//        Log.i(IConstants.TAG, "PayResult.arg0:" + arg0 + ", arg1:" + arg1 + ", arg2:" + arg2
//                + ", arg3:" + arg3);
//        switch (arg1) {
//            case 1://success
//                //此处放置支付请求已提交的相关处理代码
//                //showPayResultOffLine(cpProductName + " " + "支付成功");
//                getUserInfoDelay(1000 * 5);
//                break;
//
//            case 2://fail
//                //此处放置支付请求失败的相关处理代码
//                //showPayResultOffLine(cpProductName + " " + "支付失败");
//                Toast.makeText(getActivity().getApplicationContext(), "支付失败，请稍后再试.", Toast.LENGTH_SHORT).show();
//                break;
//
//            case 3://cancel
//                //此处放置支付请求被取消的相关处理代码
//                //showPayResultOffLine(cpProductName + " " + "支付取消");
//                Toast.makeText(getActivity().getApplicationContext(), "您已取消了支付", Toast.LENGTH_SHORT).show();
//                break;
//
//            default:
//                //showPayResultOffLine(cpProductName + " " + "支付结果未知");
//                Toast.makeText(getActivity().getApplicationContext(), "未知错误，请稍后再试.", Toast.LENGTH_SHORT).show();
//                break;
//        }
//    }
//}
