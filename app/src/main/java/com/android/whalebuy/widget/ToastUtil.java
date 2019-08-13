package com.android.whalebuy.widget;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
	public static void showInfo(Context context, String message, boolean time) {
		if (time) {
			Toast.makeText(context, message, Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
		}
	}
}
