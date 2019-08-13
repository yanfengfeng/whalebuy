package com.android.whalebuy.fragment.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.android.whalebuy.IConstants;
import com.android.whalebuy.R;
import com.android.whalebuy.common.BaseFragment;
import com.android.whalebuy.common.ObjectAdapterList;
import com.android.whalebuy.data.DataModel;
import com.android.whalebuy.kernel.DataParse;
import com.android.whalebuy.kernel.KernelException;
import com.android.whalebuy.listener.IAdapterObjectList;
import com.android.whalebuy.listener.IFragmentListener;
import com.android.whalebuy.views.XListView;
import com.android.whalebuy.widget.ImageRollView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 9元
 */
public class NineYuanFragment extends BaseFragment implements View.OnClickListener, IFragmentListener, IAdapterObjectList {

    private  View mRootView;
    private TextView tvNow;
    private TextView tvSoon;
    private TextView tvTomorrow;

    private Button btNews;
    private Button btSearch;

    private ImageView imgleft,imgright;

    private ProgressDialog mProgressDag;
    private AsyncHttpClient mHttpClient;
    private DisplayImageOptions mImageOptions;
    private XListView listView;

    public static NineYuanFragment create() {
        return new NineYuanFragment();
    }

    @Override
    public void fragmentWillShow() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_nine_list, container, false);


        imgleft = (ImageView) mRootView.findViewById(R.id.img_left); //返回
        imgleft.setOnClickListener(this);

        imgright = (ImageView) mRootView.findViewById(R.id.img_right); //消息
        imgright.setOnClickListener(this);



        mProgressDag = new ProgressDialog(getActivity());
        mProgressDag.setMessage(getString(R.string.loading));
        //mProgressDag.setOnDismissListener(getActivity());
        mProgressDag.setCancelable(true);



        mImageOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.loading)
                .showImageForEmptyUri(R.drawable.no_image)
                .showImageOnFail(R.drawable.no_image).cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true).build();



//        listView = (XListView) (mRootView.findViewById(R.id.ID_LIST_VIEW));
        ObjectAdapterList adapter = new ObjectAdapterList(this, listView);

//        listView.setAdapter(adapter);
//        listView.setXListViewListener(this);
        mHttpClient = new AsyncHttpClient();



        return mRootView;
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_left:
                Toast.makeText(getActivity(), "返回", Toast.LENGTH_SHORT).show();

//                tvQuanDuiHuan.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//加粗
//                tvQuanFan.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//常规


            break;

            case R.id.img_right:
                Toast.makeText(getActivity(), "消息", Toast.LENGTH_SHORT).show();
//                tvQuanDuiHuan.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
//                tvQuanFan.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//常规
                break;


        }

    }






    /**
     //     * 获取首页数据
     //     */
    private void getNineData() {
        mProgressDag.show();
//        mHttpClient.get(getActivity(), IConstants.URL_HOME, new JsonHttpResponseHandler() {
//            @Override
////            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
////                super.onSuccess(statusCode, headers, response);
////                mProgressDag.dismiss();
////                //     mRootView.findViewById(R.id.ID_IMG_RELOAD).setVisibility(View.GONE);
////
////                try {
////
////                    HeaderViewListAdapter headerAdapter = (HeaderViewListAdapter) (listView.getAdapter());
////                    ObjectAdapterList adapter = (ObjectAdapterList) (headerAdapter.getWrappedAdapter());
////
////                    adapter.removeAll();
////                //    DataParse.parseHomeInfo(response ,adapter.getDataList());
//////
////
////                  //  page=1;
////
////                    DataModel.Home hom = new   DataModel.Home();
////                    String ct= hom.content;
////                    //     getHomeTuiJian();
////                    //   adapter.notifyDataSetChanged();
////
////
////                } catch (KernelException e) {
////                    // TODO Auto-generated catch block
////                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
////                    mRootView.findViewById(R.id.ID_IMG_RELOAD).setVisibility(View.VISIBLE);
////                } catch (JSONException e) {
////                    // TODO Auto-generated catch block
////                    Toast.makeText(getActivity(), R.string.error_server, Toast.LENGTH_SHORT).show();
////                    //   mRootView.findViewById(R.id.ID_IMG_RELOAD).setVisibility(View.VISIBLE);
////                }
////            }
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                super.onFailure(statusCode, headers, throwable, errorResponse);
//
//                mProgressDag.dismiss();
//                Toast.makeText(getActivity(), R.string.error_network, Toast.LENGTH_SHORT).show();
//            }
//        });
    }


    @Override
    public View onItemChanged(int position, View convertView, ViewGroup parent, ObjectAdapterList adapter) {
        return null;
    }

    @Override
    public int onAdapterItemViewType(int position, ObjectAdapterList adapter) {
        return 0;
    }
}




