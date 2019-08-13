package com.android.whalebuy.widget;//package com.android.whalebuy.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.whalebuy.R;


public class TopDialog implements OnClickListener {
    private Context context;
    private Window win;
    // private Button exitBtn0,exitBtn1;

    private TextView tv_left, tv_right; // dialog 最下面显示的 左右点击事件

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private AlertDialog alertDialog;
    private ClickListener clickListener;
    private String body;
    private String body1;
    // private ImageView imageView1;
    private View parting_line;

    public TopDialog(Context context) {
        this.context = context;
    }

    /**
     * 设置AlertDialog标题和内容
     *
     * @param
     */
    public void setTitleBody(String body) {
        this.body = body;
    }


    public void setTitleBody1(String body1) {
        this.body1 = body1;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    private ClickListener getClickListener() {
        return clickListener;
    }

    /**
     * 显示AlertDialog
     */
    public void showCustomAlertDialog() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        alertDialog.setCanceledOnTouchOutside(true);
        win = alertDialog.getWindow();
        win.setContentView(R.layout.top_dialog);
    }

//    /**
//     * 设置按钮不可见
//     */
//    public void setButtonGone() {
//        if (getClickListener() == null) {
//
//            tv_left.setVisibility(View.INVISIBLE);
//            tv_right.setVisibility(View.INVISIBLE);
//        }
//    }

    /**
     * 初始化Widget
     */
    public void initView() {

        textView1 = (TextView) win.findViewById(R.id.id_tv_zonghe);
        textView2 = (TextView) win.findViewById(R.id.id_tv_new);
        textView3 = (TextView) win.findViewById(R.id.id_tv_pinglun);



        textView1 = (TextView) win.findViewById(R.id.id_tv_zonghe);
        textView1.setOnClickListener(this);

        textView2 = (TextView) win.findViewById(R.id.id_tv_new);
        textView2.setOnClickListener(this);
        textView3 = (TextView) win.findViewById(R.id.id_tv_pinglun);
        textView3.setOnClickListener(this);
    }





    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_tv_zonghe:

                alertDialog.dismiss();
                break;

            case R.id.id_tv_new:
                if (clickListener != null) {
                    clickListener.startIntent();
                }
                alertDialog.dismiss();
                break;

            case R.id.id_tv_pinglun:
                if (clickListener != null) {
                    clickListener.startIntent();
                }
                alertDialog.dismiss();
                break;
        }
    }

    public interface ClickListener {
        void startIntent();
    }

    public void dismiss() {
        alertDialog.dismiss();
    }

}
