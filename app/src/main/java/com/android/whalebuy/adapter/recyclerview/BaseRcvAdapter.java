package com.android.whalebuy.adapter.recyclerview;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 *
 *
 */

public abstract class BaseRcvAdapter<T>  extends RecyclerView.Adapter<RcvViewHold> {

    Activity activity;

    List<T> listData;

    RecylerViewClicListern listern;

    public abstract int getViewId();

    public abstract void bindView(RcvViewHold holder,T bean,int index);


    public BaseRcvAdapter(Activity activity, List<T> list){
        this.activity = activity;
        this.listData = list;
    }
    public BaseRcvAdapter(Activity activity, List<T> listData, RecylerViewClicListern listern){
        this.activity =activity;
        this.listData = listData;

        if(listern == null){
            if(activity instanceof RecylerViewClicListern ){
                this.listern = (RecylerViewClicListern) activity;
            }
        }else{
            this.listern = listern;
        }
    }


    public List<T> getListData(){
        return  listData;
    }

    protected Activity getActivity(){

        return  activity;
    }

    @NonNull
    @Override
    public RcvViewHold onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(activity).inflate(getViewId(), viewGroup, false);
        return new RcvViewHold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RcvViewHold viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return listData == null ?  0 : listData.size();
    }
}
