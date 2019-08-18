package com.android.whalebuy.fragment;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.whalebuy.R;
import com.android.whalebuy.utils.FragmentUtil;

public class TabChangeAc extends FragmentActivity {

    FragmentUtil frgUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_tab_change);
        init();
    }

    void init() {
        frgUtil = new FragmentUtil(this);
        frgUtil.setFragmentResId(R.id.fl_tab_change);
        frgUtil.addFragment(new ListFragment());
        frgUtil.addFragment(new GridFragment());
        changeFrg(0);
        findViewById(R.id.btn_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (frgUtil.getCurrentFrl() instanceof ListFragment) {
                    changeFrg(1);
                } else {
                    changeFrg(0);
                }
            }
        });

    }

    void changeFrg(int index) {
        frgUtil.showFrl(index);
    }
}
