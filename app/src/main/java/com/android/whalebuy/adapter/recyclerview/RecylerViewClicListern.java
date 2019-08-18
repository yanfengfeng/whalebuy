package com.android.whalebuy.adapter.recyclerview;

import android.view.View;

/**
 * Created by Administrator on 2019/8/18.
 */

public interface RecylerViewClicListern<T> {

    public void clickListern(View view, T t, int postion);
}
