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

    View headView,footView;

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
    public RcvViewHold onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
//        View view = LayoutInflater.from(activity).inflate(getViewId(), viewGroup, false);
        if(viewType == RecItemType.ITEM_TYPE_HEADER){
            return  new RcvViewHold(headView);
        }else if(viewType == RecItemType.ITEM_TYPE_FOOT){
            return  new RcvViewHold(footView);
        }else if(viewType == RecItemType.ITEM_TYPE_CONTENT){
            return  new RcvViewHold(LayoutInflater.from(activity).inflate(getViewId(),viewGroup,false),this,listern);
        }
        return  null;

    }

    @Override
    public void onBindViewHolder(@NonNull RcvViewHold holder, int position) {
        if(getItemViewType(position) == RecItemType.ITEM_TYPE_CONTENT){
            if(headView != null){
                position --;
                bindView(holder,listData.get(position),position);
            }else if(listData.size() > position){
                bindView(holder,listData.get(position),position);
            }
        }
    }

    public int getContentItemCount(){
        return listData == null ? 0 : listData.size();
    }


    @Override
    public int getItemViewType(int position) {

        int dataItemCount = getContentItemCount();
        if (headView != null && position < 1) {
            //头部View
            return RecItemType.ITEM_TYPE_HEADER;
        } else if (footView != null && position >= (headView == null ? dataItemCount  : dataItemCount + 1)) {
            //底部View
            return RecItemType.ITEM_TYPE_FOOT;
        } else {
            //内容View
            return RecItemType.ITEM_TYPE_CONTENT;
        }
    }

    @Override
    public int getItemCount() {
        return listData == null ?  0 : listData.size();
    }
}
