package com.android.whalebuy.fragment.home;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.whalebuy.R;
import com.android.whalebuy.common.BaseFragment;
import com.android.whalebuy.listener.IFragmentListener;

/**
 * 消息
 */
public class SearchFragment extends BaseFragment implements View.OnClickListener, IFragmentListener {

    private  View mRootView;
        private TextView tvFanHui;
        private EditText edSearch;
        private TextView tvSearch;

    public static SearchFragment create() {
        return new SearchFragment();
    }

    @Override
    public void fragmentWillShow() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      //  mRootView = inflater.inflate(R.layout.home1, container, false);

                    mRootView = inflater.inflate(R.layout.search_fragment, container, false);


            tvFanHui = (TextView) mRootView.findViewById(R.id.tv_left); //返回
            tvFanHui.setOnClickListener(this);

            edSearch = (EditText) mRootView.findViewById(R.id.ed_center); //搜索框

            tvSearch = (TextView) mRootView.findViewById(R.id.tv_right); //搜索
            tvSearch.setOnClickListener(this);


        return mRootView;
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_left:
                    Toast.makeText(getActivity(), "返回", Toast.LENGTH_SHORT).show();


                break;

                case R.id.tv_right:
                    Toast.makeText(getActivity(), "搜索", Toast.LENGTH_SHORT).show();
                    break;


        }

    }


}




