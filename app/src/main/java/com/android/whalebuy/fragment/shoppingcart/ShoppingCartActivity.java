package com.android.whalebuy.fragment.shoppingcart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.whalebuy.R;
import com.android.whalebuy.common.BaseFragment;
import com.android.whalebuy.listener.IFragmentListener;

/**
 *
 *
 * 购物车
 *
 *
 */
public class ShoppingCartActivity extends BaseFragment implements IFragmentListener {

    View mRootView;

    public static ShoppingCartActivity create() {
        return new ShoppingCartActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.cart_order_list, container, false);
        return mRootView;
    }

    @Override
    public void fragmentWillShow() {

    }
}
