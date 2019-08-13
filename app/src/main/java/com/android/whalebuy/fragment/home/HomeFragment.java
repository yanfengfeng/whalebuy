package com.android.whalebuy.fragment.home;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;
import com.android.whalebuy.IConstants;
import com.android.whalebuy.R;
import com.android.whalebuy.common.BaseFragment;
import com.android.whalebuy.common.ObjectAdapterList;
import com.android.whalebuy.data.DataModel;
import com.android.whalebuy.kernel.DataPackage;
import com.android.whalebuy.kernel.DataParse;
import com.android.whalebuy.kernel.KernelException;
import com.android.whalebuy.listener.IAdapterObjectList;
import com.android.whalebuy.listener.IFragmentListener;
import com.android.whalebuy.views.XListView;
import com.android.whalebuy.widget.ImageRollView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页
 */
public class HomeFragment extends BaseFragment implements OnClickListener, IFragmentListener, IAdapterObjectList, XListView.IXListViewListener {

    private  View mRootView;
    private TextView tvQuanFan;
    private TextView tvQuanDuiHuan;
    private View btNews;
    private View btSearch;


    private final int ITEM_BANNER = 0; //轮播图
    private final int ITEM_NOTICE = 1; //公告
    private final int ITEM_FENLEI = 2; //分类
    private final int ITEM_HUODONG = 3; //中部活动
    private final int ITEM_RECOMMEND = 4; //推荐

    private ProgressDialog mProgressDag;
    private AsyncHttpClient mHttpClient;
    private DisplayImageOptions mImageOptions;

    private TabLayout tlActivitytwoTitle;
    private String[] arrTitle=new String[]{"全返","兑换"};


    private XListView listView;
    private  ViewPager vpActivitytwoContent;
    private String mNotice; //广告信息
    private int page=1;


    public static HomeFragment create() {
        return new HomeFragment();
    }

    @Override
    public void fragmentWillShow() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_home_list, container, false);

        tvQuanFan = (TextView) mRootView.findViewById(R.id.id_tv_quanfan); //全反
        tvQuanFan.setOnClickListener(this);
        tvQuanDuiHuan = (TextView) mRootView.findViewById(R.id.id_tv_duihuan); //兑换
        tvQuanDuiHuan.setOnClickListener(this);

        btNews=(View) mRootView.findViewById(R.id.id_bt_xiaoxi); //消息
        btNews.setOnClickListener(this);
        btSearch=(View) mRootView.findViewById(R.id.id_tv_sousuo); //搜索
        btSearch.setOnClickListener(this);


        tvQuanDuiHuan.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//加粗
        tvQuanFan.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//常规

        mProgressDag = new ProgressDialog(getActivity());
        mProgressDag.setMessage(getString(R.string.loading));
        //mProgressDag.setOnDismissListener(getActivity());
        mProgressDag.setCancelable(true);


        tlActivitytwoTitle= (TabLayout)mRootView.findViewById(R.id.tl_activitytwo_title);

        mImageOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.loading)
                .showImageForEmptyUri(R.drawable.no_image)
                .showImageOnFail(R.drawable.no_image).cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true).build();



        listView = (XListView) (mRootView.findViewById(R.id.ID_LIST_VIEW));
        ObjectAdapterList adapter = new ObjectAdapterList(this, listView);

        listView.setAdapter(adapter);
        listView.setXListViewListener(this);
        mHttpClient = new AsyncHttpClient();

        getHomeData();

       // getHomeTuiJian();

        return mRootView;
    }

    public void onClick(View v) {
        switch (v.getId()){


            case R.id.id_tv_quanfan:
                Toast.makeText(getActivity(), "dianjijj", Toast.LENGTH_SHORT).show();
//                getFragmentManager().beginTransaction().add(R.id.container
//                        , HomeConvertFragment.create())
//                        .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();
                getFragmentManager().popBackStack(IConstants.FRAGMENT_LOGIN
                        , getFragmentManager().POP_BACK_STACK_INCLUSIVE);




                tvQuanDuiHuan.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//加粗
                tvQuanFan.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//常规

            break;

            case R.id.id_tv_duihuan:


                getFragmentManager().beginTransaction().add(R.id.id_frgment
                        , HomeConvertFragment.create())
                        .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();


                Toast.makeText(getActivity(), "兑换", Toast.LENGTH_SHORT).show();
                tvQuanDuiHuan.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
                tvQuanFan.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//常规




                break;
            case R.id.id_bt_xiaoxi:
                Toast.makeText(getActivity(), "消息", Toast.LENGTH_SHORT).show();


                getFragmentManager().beginTransaction().add(R.id.container
                        , MyNewsFragment.create())
                        .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();
                break;
            case R.id.id_tv_sousuo:
                Toast.makeText(getActivity(), "搜索", Toast.LENGTH_SHORT).show();

                getFragmentManager().beginTransaction().add(R.id.container
                        ,SearchFragment.create())
                        .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();
                break;

            case R.id.id_tv_fuzhuang:
                Toast.makeText(getActivity(), "点击了服装", Toast.LENGTH_SHORT).show();


                break;
            case R.id.id_tv_meizhuang:
                Toast.makeText(getActivity(), "点击了美妆", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_tv_meishi:
                Toast.makeText(getActivity(), "点击了美食", Toast.LENGTH_SHORT).show();

                getFragmentManager().beginTransaction().add(R.id.container
                        , FoodFragment.create(this))
                        .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();
                break;
            case R.id.id_tv_jujia:
                Toast.makeText(getActivity(), "点击了居家", Toast.LENGTH_SHORT).show();
                break;


            case R.id.id_ll_box1:
                Toast.makeText(getActivity(), "点击了三十元", Toast.LENGTH_SHORT).show();

                getFragmentManager().beginTransaction().add(R.id.container
                        , SanShiYanFragment.create())
                        .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();

                break;

            case R.id.id_box2:
                Toast.makeText(getActivity(), "点击了限时", Toast.LENGTH_SHORT).show();



                getFragmentManager().beginTransaction().add(R.id.container
                        , LastMinuteFragment.create())
                        .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();
                break;
            case R.id.ll_box4:
                Toast.makeText(getActivity(), "点击了热销", Toast.LENGTH_SHORT).show();

                getFragmentManager().beginTransaction().add(R.id.container
                        , SellWellFragment.create())
                        .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();
                break;
            case R.id.ll_box5:
                Toast.makeText(getActivity(), "点击了9.9", Toast.LENGTH_SHORT).show();


                getFragmentManager().beginTransaction().add(R.id.container
                        , NineYuanFragment.create())
                        .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();
                break;



        }

    }



    /**
     //     * 获取首页数据
     //     */
    private void getHomeData() {
        mProgressDag.show();
        mHttpClient.get(getActivity(), IConstants.URL_HOME, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        mProgressDag.dismiss();
                   //     mRootView.findViewById(R.id.ID_IMG_RELOAD).setVisibility(View.GONE);

                        try {

                            HeaderViewListAdapter headerAdapter = (HeaderViewListAdapter) (listView.getAdapter());
                            ObjectAdapterList adapter = (ObjectAdapterList) (headerAdapter.getWrappedAdapter());

                            adapter.removeAll();
                            DataParse.parseHomeInfo(response ,adapter.getDataList());
//

                            page=1;


                            getHomeTuiJian();
                            adapter.notifyDataSetChanged();


                        } catch (KernelException e) {
                            // TODO Auto-generated catch block
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            mRootView.findViewById(R.id.ID_IMG_RELOAD).setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            Toast.makeText(getActivity(), R.string.error_server, Toast.LENGTH_SHORT).show();
                         //   mRootView.findViewById(R.id.ID_IMG_RELOAD).setVisibility(View.VISIBLE);
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);

                        mProgressDag.dismiss();
                        Toast.makeText(getActivity(), R.string.error_network, Toast.LENGTH_SHORT).show();
                    }
                });
    }






    /**
     //     * 获取首页推荐数据
     //     */
    private void getHomeTuiJian() {
        mProgressDag.show();
        mHttpClient.get(getActivity(),IConstants.URL_HOMETUIJIAN, DataPackage.getHomeTuiJian(page),
                new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        mProgressDag.dismiss();
                   //     mRootView.findViewById(R.id.ID_IMG_RELOAD).setVisibility(View.GONE);

                        try {


                            HeaderViewListAdapter headerAdapter = (HeaderViewListAdapter) (listView.getAdapter());
                            ObjectAdapterList adapter = (ObjectAdapterList) (headerAdapter
                                    .getWrappedAdapter());
                            DataParse.parseHomeTuiJianList(response, adapter.getDataList());


                            page+=1;

                            adapter.notifyDataSetChanged();



                        } catch (KernelException e) {
                            // TODO Auto-generated catch block
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            mRootView.findViewById(R.id.ID_IMG_RELOAD).setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            Toast.makeText(getActivity(), R.string.error_server, Toast.LENGTH_SHORT).show();
                            mRootView.findViewById(R.id.ID_IMG_RELOAD).setVisibility(View.VISIBLE);
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);

                        mProgressDag.dismiss();
                        Toast.makeText(getActivity(), R.string.error_network, Toast.LENGTH_SHORT).show();
                    }
                      @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          String responseString, Throwable throwable)
                    {
                        mProgressDag.dismiss();


                        Log.i("aasssssss" ,""+statusCode);

                        Log.i("aasssssss" ,responseString);
                        Toast.makeText(getActivity(), R.string.error_network, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onRefresh() {
        getHomeData();

    }

    @Override
    public void onLoadMore() {

     getHomeTuiJian();

    }



    /**
     * 设置首页轮播图
     *
     */
    private void setRollviewItem(View convertView, List<ImageRollView.RollItem> bannelList) {
        ImageRollView imageRollView = (ImageRollView) (convertView.findViewById(R.id.ID_ROLLVIEW));
        imageRollView.initImageRollView((List<ImageRollView.RollItem>) bannelList, true);
        imageRollView.setOnImageRollViewClickListener(new ImageRollView.OnImageRollViewClickListener() {
            @Override
            public void onClick(View view, int position, ImageRollView.RollItem rollitem) {
                // TODO Auto-generated method stub
                Log.i(IConstants.TAG, "setOnImageRollViewClickListener.position:" + position);
                DataModel.BannerItem item = (DataModel.BannerItem) (rollitem);
                    //打开一个网页

                Toast.makeText(getActivity(), "点击了", Toast.LENGTH_SHORT).show();

//                    getFragmentManager().beginTransaction().add(R.id.container
//                            , FragmentWebView.create(item.url))
//                            .addToBackStack(IConstants.FRAGMENT_MAIN_THREAD).commit();

//                else if (TYPE_OUT_URL == item.type) {
//                    //外部链接
//                    Log.i(IConstants.TAG, "outUrl:" + item.openInfo);
//                    Uri uri = Uri.parse(item.url);
//                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                    startActivity(intent);
//                }


            }
        });
    }



    @Override
    public View onItemChanged(int position, View convertView, ViewGroup parent, ObjectAdapterList adapter) {

        Object objItem = adapter.getItem(position);


        if (position == 0) {
            //首页轮播图
            if (null == convertView) {
                convertView = LayoutInflater.from(getActivity()).inflate(
                        R.layout.item_image_rollview, null, false);
            }

            List<ImageRollView.RollItem> bannelList = (List<ImageRollView.RollItem>)objItem;
            setRollviewItem(convertView, bannelList);
        } else if(position==1) {
            //公告
            if (null == convertView) {
                convertView = LayoutInflater.from(getActivity()).inflate(
                        R.layout.item_notice, null, false);
            }
            String content = (String)objItem;
            setNoticeItem(convertView ,content);
        }else if(position==2) {
            //分类
            if (null == convertView) {
                convertView = LayoutInflater.from(getActivity()).inflate(

                        R.layout.item_home_fenlei, null, false);


            }
            setFenLeiItem(convertView);
        }else if(position==3) {
            //活动
            if (null == convertView) {
                convertView = LayoutInflater.from(getActivity()).inflate(
                        R.layout.item_home_activity, null, false);
            }
            List<DataModel.Program> list = (List<DataModel.Program>)objItem;
         setHuoDongItem(convertView, list);

        } else  {
            //推荐列表
            if (null == convertView) {
                convertView = LayoutInflater.from(getActivity()).inflate(
                        R.layout.item_image_two, null, false);
            }
            setTuijianItem(convertView, (List<DataModel.HomeTuiJian>)objItem);
        }

        return convertView;

    }

    @Override
    public int onAdapterItemViewType(int position, ObjectAdapterList adapter) {
        int type = ITEM_BANNER;

        if (position==0){
            type= ITEM_BANNER;


        } else if(position==1){
            type=ITEM_NOTICE;


        } else if(position==2){
            type=ITEM_FENLEI;


        }else if(position==3){
            type=ITEM_HUODONG;


        }else{
            type=ITEM_RECOMMEND;


        }


        return type;
    }


    /**
     * 设置首页公告
     *
     * @param convertView
     */
    private void setNoticeItem(View convertView, String content) {

        TextView notice = (TextView) (convertView.findViewById(R.id.ID_TXT_TITLE));
        notice.setText(content);
    }






    // 分类
    private void setFenLeiItem(View convertView ) {

//     DataModel.HomeCate cate =  new DataModel.HomeCate();

        TextView  tvfz = (TextView) (convertView.findViewById(R.id.id_tv_fuzhuang));
        tvfz.setOnClickListener(this );

        TextView  tvmz = (TextView) (convertView.findViewById(R.id.id_tv_meizhuang));
        tvmz.setOnClickListener(this );

        TextView  tvms = (TextView) (convertView.findViewById(R.id.id_tv_meishi));
        tvms.setOnClickListener(this );

        TextView  tvjj = (TextView) (convertView.findViewById(R.id.id_tv_jujia));
        tvjj.setOnClickListener(this );

    }




    // 活动
    private void setHuoDongItem(View convertView, List<DataModel.Program> list) {


        DataModel.Program sanShi=list.get(0);


        // 1部分
        TextView  tvbtone = (TextView) (convertView.findViewById(R.id.id_tv_box1_biaoti_one));
        tvbtone.setText(sanShi.name);



//        if (!StringUtil.isNullString(id)) { // 截取字符串
//            String ss[] = id.split("=");
//            if (ss != null && ss.length == 2) {
//                mId = ss[0];
//                mName = ss[1];
//
//            }
//
//        }
        TextView  tvbttwo = (TextView) (convertView.findViewById(R.id.id_tv_box1_biaoti_two));
        ImageView  imgOne=(ImageView) (convertView.findViewById(R.id.id_img_box1));
        ImageLoader.getInstance().displayImage(sanShi.image, imgOne, mImageOptions);

        LinearLayout llBox1=(LinearLayout) (convertView.findViewById(R.id.id_ll_box1));
        llBox1.setOnClickListener(this);



        DataModel.Program xs=list.get(1);


        // 2部分  限时
        TextView  tvbtbox2 = (TextView) (convertView.findViewById(R.id.id_tv_box2_biaoti));

        tvbtbox2.setText(xs.name);
        ImageView  imgBox2one=(ImageView) (convertView.findViewById(R.id.imageView3));

        ImageLoader.getInstance().displayImage(xs.image, imgBox2one, mImageOptions);
       // ImageView  imgBox2two=(ImageView) (convertView.findViewById(R.id.imageView4));

        LinearLayout llBox2=(LinearLayout) (convertView.findViewById(R.id.id_box2));
        llBox2.setOnClickListener(this);




        // 3部分
        TextView  tvbtBox4One = (TextView) (convertView.findViewById(R.id.id_tv_box4_biaoti_one));
        TextView  tvbtBox4Two = (TextView) (convertView.findViewById(R.id.id_tv_box4_biaoti_two));
        ImageView  imgBox4=(ImageView) (convertView.findViewById(R.id.id_img4));
        LinearLayout llBox4=(LinearLayout) (convertView.findViewById(R.id.ll_box4));
        llBox4.setOnClickListener(this);



        // 4部分
        TextView  tvbtBox5 = (TextView) (convertView.findViewById(R.id.id_tv_box5_biaoti));
        ImageView  imgBox5=(ImageView) (convertView.findViewById(R.id.id_img_five));
        LinearLayout llBox5=(LinearLayout) (convertView.findViewById(R.id.ll_box5));
        llBox5.setOnClickListener(this);



    }







    /**
     * 设置推荐
     *
     * @param convertView
     */
    private void setTuijianItem(View convertView  , List<DataModel.HomeTuiJian> list) {


        //  DataModel.Home home = (DataModel.Home) objItem;

//        DataModel.HomeTuiJian


if(list.size()==2) {

    DataModel.HomeTuiJian tuiian=list.get(0);
    TextView tvFanNub = (TextView) (convertView.findViewById(R.id.id_tv_nub));//返回多少


    tvFanNub.setText(tuiian.rebateprice);

    ImageView img = (ImageView) (convertView.findViewById(R.id.id_img)); //
    ImageLoader.getInstance().displayImage(tuiian.pic_url, img, mImageOptions);

    TextView tvXiaoLiang = (TextView) (convertView.findViewById(R.id.id_tv_yg_nub));//销量
    tvXiaoLiang.setText(tuiian.volume+"");
    TextView tvTitle = (TextView) (convertView.findViewById(R.id.id_tv_biaoti)); //标题

    tvTitle.setText(tuiian.title);
    TextView tvPrivce = (TextView) (convertView.findViewById(R.id.id_tv_price));//价格

    tvPrivce.setText(tuiian.price);
    TextView tvFan = (TextView) (convertView.findViewById(R.id.id_tv_fan));// 反
    tvFan.setText(tuiian.rate);


    DataModel.HomeTuiJian tuiian1=list.get(1);


    TextView tvFanNub1 = (TextView) (convertView.findViewById(R.id.id_tv_nub1));//返回多少


    tvFanNub1.setText(tuiian1.rebateprice);

    ImageView img1 = (ImageView) (convertView.findViewById(R.id.id_img1)); //
    ImageLoader.getInstance().displayImage(tuiian1.pic_url, img1, mImageOptions);

    TextView tvXiaoLiang1 = (TextView) (convertView.findViewById(R.id.id_tv_yg_nub1));//销量
    tvXiaoLiang1.setText(tuiian1.volume+"");
    TextView tvTitle1 = (TextView) (convertView.findViewById(R.id.id_tv_biaoti1)); //标题

    tvTitle1.setText(tuiian1.title);
    TextView tvPrivce1 = (TextView) (convertView.findViewById(R.id.id_tv_price1));//价格

    tvPrivce1.setText(tuiian1.price);
    TextView tvFan1 = (TextView) (convertView.findViewById(R.id.id_tv_fan1));// 反
    tvFan1.setText(tuiian1.rate);








} else {



    DataModel.HomeTuiJian tuiian=list.get(0);
    TextView tvFanNub = (TextView) (convertView.findViewById(R.id.id_tv_nub));//返回多少


    tvFanNub.setText(tuiian.rebateprice);

    ImageView img = (ImageView) (convertView.findViewById(R.id.id_img)); //
    ImageLoader.getInstance().displayImage(tuiian.pic_url, img, mImageOptions);

    TextView tvXiaoLiang = (TextView) (convertView.findViewById(R.id.id_tv_yg_nub));//销量
    tvXiaoLiang.setText(tuiian.volume);
    TextView tvTitle = (TextView) (convertView.findViewById(R.id.id_tv_biaoti)); //标题

    tvTitle.setText(tuiian.title);
    TextView tvPrivce = (TextView) (convertView.findViewById(R.id.id_tv_price));//价格

    tvPrivce.setText(tuiian.price);
    TextView tvFan = (TextView) (convertView.findViewById(R.id.id_tv_fan));// 反
    tvFan.setText(tuiian.rate);





}






    }

}




