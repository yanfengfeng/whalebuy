package com.android.whalebuy.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.whalebuy.R;
import com.android.whalebuy.adapter.recyclerview.BaseRcvAdapter;
import com.android.whalebuy.adapter.recyclerview.RcvViewHold;
import com.android.whalebuy.adapter.recyclerview.RecylerViewClicListern;
import com.android.whalebuy.common.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/8/17.
 */

public class ListFragment extends BaseFragment {

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
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcv.setAdapter(new BaseRcvAdapter(getActivity(), list) {
            @Override
            public int getViewId() {
                return R.layout.frg_list_tab;
            }

            @Override
            public void bindView(RcvViewHold holder, final Object bean, int index) {
                holder.setTextViewValue(R.id.tv_name, bean.toString());
                holder.getView(R.id.list_view).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        toast("click==" + bean);
                    }
                });
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
