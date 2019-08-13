package com.android.whalebuy.fragment.endorsement;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.whalebuy.R;
import com.android.whalebuy.common.BaseFragment;
import com.android.whalebuy.listener.IFragmentListener;

/**
 * 代言
 *
 */
public class EmdorsemntActivity extends BaseFragment implements IFragmentListener {

    View mRootView;

    public static EmdorsemntActivity create()
    {
        return new EmdorsemntActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_homepage, container, false);
        return mRootView;
    }

    @Override
    public void fragmentWillShow() {

    }
}