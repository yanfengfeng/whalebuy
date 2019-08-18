package com.android.whalebuy.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.whalebuy.R;
import com.android.whalebuy.adapter.recyclerview.BaseRcvAdapter;
import com.android.whalebuy.adapter.recyclerview.RcvViewHold;
import com.android.whalebuy.common.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/8/17.
 */

public class GridFragment extends BaseFragment {

    List<String> list = new ArrayList<>();

    RecyclerView rcv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recyclerview, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rcv = getView().findViewById(R.id.recyclerview);
        rcv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rcv.setAdapter(new BaseRcvAdapter(getActivity(), list) {
            @Override
            public int getViewId() {
                return R.layout.frg_grid_tab;
            }

            @Override
            public void bindView(RcvViewHold holder, Object bean, int index) {
                holder.setTextViewValue(R.id.tv_name, bean.toString());
            }


        });
        initData();
    }

    void initData() {
        for (int i = 0; i < 20; i++) {
            list.add("===" + i);
        }
        rcv.getAdapter().notifyDataSetChanged();
    }
}
