package com.android.whalebuy.adapter.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Administrator on 2019/8/17.
 */

public class RcvViewHold extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    private View mConvertView;

    public RcvViewHold(@NonNull View itemView) {
        super(itemView);
    }

    public RcvViewHold(final View itemView , final BaseRcvAdapter adapter, final RecylerViewClicListern listern) {
        super(itemView);
        mConvertView = itemView;
        mViews = new SparseArray<View>();

        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(listern != null){
                    if(adapter.getItemViewType(0) == RecItemType.ITEM_TYPE_HEADER){
                        listern.clickListern(itemView,adapter.getListData().get(getAdapterPosition() - 1),getAdapterPosition() - 1);
                    }else{
                        listern.clickListern(itemView,adapter.getListData().get(getAdapterPosition()),getAdapterPosition());
                    }
                }
            }
        });
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public void setTextViewValue(int viewId,String value){
        View view = getView(viewId);
        if(view instanceof TextView){
            ((TextView)view).setText(value);
        }else if(view instanceof EditText){
            ((EditText)view).setText(value);
        }else if(view instanceof Button){
            ((Button)view).setText(value);
        }
    }
}
