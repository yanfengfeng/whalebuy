package com.android.whalebuy.widget;


import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.android.whalebuy.R;

public class AlertDialogCommon extends Dialog {

    private View rootView;
    private Context context;
    private int rid;

    public AlertDialogCommon(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public AlertDialogCommon(Context context, int rid) {
        super(context, R.style.popwin_anim_style);
        this.context = context;
        this.rid = rid;
    }

    public void init(){
        rootView = LayoutInflater.from(context).inflate(rid,null);
        setContentView(rootView);
    }

    public void showDialog(){
        this.show();
        init();
    }

    public View getView(){
        return rootView;
    }
}
