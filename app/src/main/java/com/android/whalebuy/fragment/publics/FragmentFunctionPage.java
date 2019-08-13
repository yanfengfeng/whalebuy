package com.android.whalebuy.fragment.publics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.whalebuy.R;
import com.android.whalebuy.common.BaseFragment;
import com.android.whalebuy.fragment.endorsement.EmdorsemntActivity;
import com.android.whalebuy.fragment.home.HomeFragment;
import com.android.whalebuy.fragment.my.MyFragment;
import com.android.whalebuy.fragment.preorder.PreorderActivity;
import com.android.whalebuy.fragment.shoppingcart.ShoppingCartActivity;
import com.android.whalebuy.listener.IFragmentListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentFunctionPage extends BaseFragment implements OnClickListener, OnPageChangeListener {

    class FunctionPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragmentList;

        public FunctionPageAdapter(FragmentManager fm) {
            super(fm);
            // TODO Auto-generated constructor stub
            mFragmentList = new ArrayList<Fragment>();
            mFragmentList.add(HomeFragment.create());
            mFragmentList.add(ShoppingCartActivity.create());
            mFragmentList.add(EmdorsemntActivity.create());
            mFragmentList.add(PreorderActivity.create());
            mFragmentList.add(MyFragment.create());
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.e(getClass() + ".destroyItem()", "object:" + object);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            // TODO Auto-generated method stub
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mFragmentList.size();
        }
    }

    private View mRootView;

    public static FragmentFunctionPage create() {
        return new FragmentFunctionPage();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        mRootView = inflater.inflate(R.layout.fragment_function,
                container, false);

        ViewPager viewPager = (ViewPager) mRootView.findViewById(R.id.ID_VIEWPAGER);
        viewPager.setAdapter(new FunctionPageAdapter(getFragmentManager()));
        viewPager.setOnPageChangeListener(this);

        mRootView.findViewById(R.id.ID_BTN_HOME).setEnabled(false);
        mRootView.findViewById(R.id.ID_BTN_HOME).setOnClickListener(this);
        mRootView.findViewById(R.id.ID_BTN_YUGOU).setOnClickListener(this);
        mRootView.findViewById(R.id.ID_BTN_DAIYAN).setOnClickListener(this);
        mRootView.findViewById(R.id.ID_BTN_GOUWU).setOnClickListener(this);
        mRootView.findViewById(R.id.id_tv_my).setOnClickListener(this);


        return mRootView;
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        ViewPager viewPager = (ViewPager) mRootView.findViewById(R.id.ID_VIEWPAGER);
        int index = -1;


        switch (arg0.getId()) {
            case R.id.ID_BTN_HOME:
                index = getFragmentIndex(HomeFragment.class);
                break;
            case R.id.ID_BTN_YUGOU:
                index = getFragmentIndex(PreorderActivity.class);
                break;
            case R.id.ID_BTN_DAIYAN:
                index = getFragmentIndex(EmdorsemntActivity.class);
                break;
            case R.id.ID_BTN_GOUWU:
                index = getFragmentIndex(ShoppingCartActivity.class);
                break;
            case R.id.id_tv_my:
                index = getFragmentIndex(MyFragment.class);
                break;
        }


        if (index >= 0) {
            viewPager.setCurrentItem(index, true);
        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onPageSelected(int arg0) {
        // TODO Auto-generated method stub
        View rootView = getView();

        TextView tvHome = (TextView) (rootView.findViewById(R.id.ID_BTN_HOME));
        TextView tvYugou = (TextView) (rootView.findViewById(R.id.ID_BTN_YUGOU));
        TextView tvDaiyan = (TextView) (rootView.findViewById(R.id.ID_BTN_DAIYAN));
        TextView tvGouWu = (TextView) (rootView.findViewById(R.id.ID_BTN_GOUWU));
        TextView tvMy = (TextView) (rootView.findViewById(R.id.id_tv_my));


        ViewPager viewPager = (ViewPager) mRootView.findViewById(R.id.ID_VIEWPAGER);
        FunctionPageAdapter adatper = (FunctionPageAdapter) (viewPager.getAdapter());

        IFragmentListener fragment = (IFragmentListener) (adatper.getItem(arg0));
        if (fragment instanceof HomeFragment) {
            tvHome.setEnabled(false);
            tvYugou.setEnabled(true);
            tvDaiyan.setEnabled(true);
            tvGouWu.setEnabled(true);
            tvMy.setEnabled(true);

        } else if (fragment instanceof MyFragment) {
            tvHome.setEnabled(true);
            tvYugou.setEnabled(true);
            tvDaiyan.setEnabled(true);
            tvGouWu.setEnabled(true);
            tvMy.setEnabled(false);
            fragment.fragmentWillShow();

        } else if (fragment instanceof PreorderActivity) {
            tvHome.setEnabled(true);
            tvYugou.setEnabled(false);
            tvDaiyan.setEnabled(true);
            tvGouWu.setEnabled(true);
            tvMy.setEnabled(true);

            fragment.fragmentWillShow();

        } else if (fragment instanceof EmdorsemntActivity) {
            tvHome.setEnabled(true);
            tvYugou.setEnabled(true);
            tvDaiyan.setEnabled(false);
            tvGouWu.setEnabled(true);
            tvMy.setEnabled(true);

            fragment.fragmentWillShow();
        } else if (fragment instanceof ShoppingCartActivity) {

            tvHome.setEnabled(true);
            tvYugou.setEnabled(true);
            tvDaiyan.setEnabled(true);
            tvGouWu.setEnabled(false);
            tvMy.setEnabled(true);
            fragment.fragmentWillShow();
        }


    }

    /**
     * 获取一个fragment在pageview 中的索引
     *
     * @param fragmentClass
     * @return
     */
    private int getFragmentIndex(Class<?> fragmentClass) {
        ViewPager viewPager = (ViewPager) mRootView.findViewById(R.id.ID_VIEWPAGER);
        FunctionPageAdapter adatper = (FunctionPageAdapter) (viewPager.getAdapter());
        for (int index = 0; index < adatper.getCount(); index++) {
            Object item = adatper.getItem(index);
            if (fragmentClass.isInstance(item)) {
                return index;
            }
        }
        return -1;
    }
}
