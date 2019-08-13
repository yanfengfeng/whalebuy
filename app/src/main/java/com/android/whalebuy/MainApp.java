package com.android.whalebuy;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.android.whalebuy.kernel.KernelManager;
import com.android.whalebuy.widget.ToastUtil;
import com.chuanglan.shanyan_sdk.OneKeyLoginManager;
import com.chuanglan.shanyan_sdk.listener.InitListener;
import com.unicom.shield.UnicomApplicationWrapper;

public class MainApp extends Application {
    private final static String TAG = "MainApp";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        final String packageName = base.getPackageName();

        Log.i(TAG, "attachBaseContext.packgeName:" + packageName);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(getClass() + ".onCreate()", "==================START==================");
        KernelManager._GetObject().init(getApplicationContext());

        Log.i(getClass() + ".onCreate()", String.format("version_code:%d version_name:%s debug:%B"
                , KernelManager._GetObject().getMyVersionCode(), KernelManager._GetObject().getMyVersionName()
                , IConstants.DEBUG));


        OneKeyLoginManager.getInstance().init(getApplicationContext(), "CK5AVlEv", "paI5Ne7x", new InitListener() {
            @Override
            public void getInitStatus(int i, String s) {
                if(i != 1022){
                    ToastUtil.showInfo(getApplicationContext(),"初始化失败", true);
                }
            }
        });


    }

}
