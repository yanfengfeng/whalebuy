package com.android.whalebuy.widget;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by winter on 2015/9/22.
 *
 * @author winter
 */
public class AppInfoUtils {

    /**
     * 应用程序信息-包名，版本
     */
    public static class AppInfoData {
        private String mName;
        private String mPackageName;
        private String mVersionName;
        private int mVersionCode;

        public AppInfoData() {
        }

        public void setName(String name) {
            mName = name;
        }

        public void setPackageName(String packageName) {
            mPackageName = packageName;
        }

        public void setVersionCode(int versionCode) {
            mVersionCode = versionCode;
        }

        public void setVersionName(String versionName) {
            mVersionName = versionName;
        }

        public String getName() {
            return mName;
        }

        public String getPackageName() {
            return mPackageName;
        }

        public String getVersionName() {
            return mVersionName;
        }

        public int getVersionCode() {
            return mVersionCode;
        }
    }

    private static PackageInfo sPackageInfo;

    public static void init(Context context) {
        try {
            sPackageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static PackageInfo getPackageInfo() {
        return sPackageInfo;
    }

    public static int getVersionCode() {
        if (sPackageInfo != null) {
            return sPackageInfo.versionCode;
        }
        return 0;
    }

    public static String getVersionName() {
        if (sPackageInfo != null) {
            return sPackageInfo.versionName;
        }
        return "0";
    }
}
