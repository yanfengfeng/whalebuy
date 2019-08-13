//package com.android.whalebuy.fragment.home;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.GridLayoutManager;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import com.android.whalebuy.HomeContract;
//import com.android.whalebuy.R;
//import com.android.whalebuy.adapter.HomeAdapter;
//import com.android.whalebuy.base.HomeType;
//import com.android.whalebuy.been.HomeBase;
//import com.android.whalebuy.been.HomeBottom;
//import com.android.whalebuy.been.HomeTop;
//import com.android.whalebuy.common.BaseFragment;
//import com.android.whalebuy.listener.IFragmentListener;
//import com.android.whalebuy.presenter.HomePresenter;
//import com.android.whalebuy.widget.LoadMoreRecyclerView;
//
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.List;
////import butterknife.BindView;
////import butterknife.ButterKnife;
//
//public class NavHomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, IFragmentListener,  LoadMoreRecyclerView.OnLoadMoreListener, HomeContract.View
//
//{
//
//    private final static int HOME_TOP = 1;
//    private final static int HOME_BOTTOM = 2;
////    @BindView(R.id.refresh_layout)
//    SwipeRefreshLayout refreshLayout;
////    @BindView(R.id.recycler_view)
//    LoadMoreRecyclerView recyclerView;
//    private Context context;
//    private Activity activity;
//
//
////    private List<HomeBase> list = new ArrayList<>();
//    private HomeAdapter adapter;
//    private HomePresenter presenter;
//    private int page = 1;
//    private int pageSize = 10;
//    private HomeBase footerItem = new HomeBase();
//    View mRootView;
//
//
//    public static NavHomeFragment newInstance() {
//        NavHomeFragment fragment = new NavHomeFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void fragmentWillShow() {
//
//    }
//
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        mRootView = inflater.inflate(R.layout.fragment_navigation_home, container, false);
//
////        ButterKnife.bind(this, mRootView);
//
//
//
//
////         activity = getActivity();
////        context = activity.getApplicationContext();
//
//        presenter = new HomePresenter();
//        presenter.init(this, context);
//
//        initView();
//        return   mRootView;
//
//
//
//    }
//
//
//
//
//    @Override
//    public void initView() {
////        refreshLayout.setColorSchemeResources(R.color.font_orange_color);
////        refreshLayout.setOnRefreshListener(this);
////       int spanCount = getResources().getInteger(R.integer.grid_span_count);
////        GridLayoutManager layoutManager = new GridLayoutManager(activity, spanCount);
////        recyclerView.setLayoutManager(layoutManager);
////        adapter = new HomeAdapter(context, activity, list);
////        adapter.setOnItemClickListener(this);
////        layoutManager.setSpanSizeLookup(adapter.getSpanSizeLookup());
////        recyclerView.setAdapter(adapter);
////        recyclerView.setOnLoadMoreListener(this);
////
////        footerItem.setType(HomeType.TYPE_FOOTER_LOAD);
////        footerItem.setSpanCount(spanCount);
////        presenter.start(HOME_TOP);
//    }
//
//
//
//
//
//
//
//
//
//
//    @Override
//    public void onRefresh() {
//        setRefreshLoading(false);
//     //   presenter.start(HOME_TOP);
//        page = 1;
//
//    }
//
//    @Override
//    public void onLoadMore() {
//        setRefreshLoading(true);
//        page++;
//     //   presenter.start(HOME_BOTTOM, page, pageSize);
//    }
//
//
//    @Override
//    public void show(HomeTop bean) {
//
//        adapter.setLoopList(bean.getCarousel());
//
//    }
//
//    @Override
//    public void show(HomeBottom bean) {
//
//    }
//
//    @Override
//    public void loading() {
//
//    }
//
//    @Override
//    public void networkError() {
//        setRefreshLoading(false);
//   //     ToastUtil.showShortToast(context, R.string.toast_network_error);
//
//    }
//
//    @Override
//    public void error(String msg) {
//        setRefreshLoading(false);
//     //   ToastUtil.showShortToast(context, msg);
//    }
//
//    @Override
//    public void showFailed(String msg) {
//        setRefreshLoading(false);
//      //  ToastUtil.showShortToast(context, msg);
//    }
//
//    /**
//     * 设置刷新和加载更多的状态
//     *
//     * @param isLoading 加载更多状态
//     */
//    private void setRefreshLoading(boolean isLoading) {
//        if (!isLoading) {
//            recyclerView.setLoading(false);
//            refreshLayout.setRefreshing(false);
//        }
//    }
////
//////    @Override
////    public void onItemClick(int position) {
//////        Intent intent = new Intent();
//////        intent.setClass(getActivity(), DetailActivity.class);
//////        intent.putExtra(BundleKey.PARCELABLE, list.get(position));
//////        startActivity(intent);
////    }
//}
