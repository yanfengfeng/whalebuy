package com.android.whalebuy.fragment.shoppingcart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.whalebuy.R;

/**
 * Created by Administrator on 2018/1/23.
 */

public class TabFourFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tab4,container,false);
        return view;
    }
}
