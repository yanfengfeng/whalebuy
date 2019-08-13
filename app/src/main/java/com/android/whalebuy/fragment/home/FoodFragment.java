package com.android.whalebuy.fragment.home;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.android.whalebuy.IConstants;
import com.android.whalebuy.R;
import com.android.whalebuy.adapter.WindowListAdapter;
import com.android.whalebuy.been.PopWindow;
import com.android.whalebuy.common.BaseFragment;
import com.android.whalebuy.listener.IFragmentListener;
import com.android.whalebuy.utils.PopupWindowUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页-meishi
 */
public class FoodFragment extends BaseFragment implements View.OnClickListener, IFragmentListener, View.OnContextClickListener {

    View mRootView;
    private LinearLayout llDialog;
    private TextView  tvZongHe,tvPrice,tvXiaoLiang,tvYouQuan,tvZongHe2,tvNew,tvPing;

    private TextView  tvquan;

    private TextView  tvset;
    private View view;
    private LinearLayout layPop;
    private PopupWindow window;
    private Button cacel;
    private List<PopWindow> pops;
    private ListView listView;
    private WindowListAdapter adapter;


    public static FoodFragment create(HomeFragment homeFragment) {
        return new FoodFragment(

        );
    }

    @Override
    public void fragmentWillShow() {

}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.top_dialog, container, false);


          tvZongHe=(TextView) mRootView.findViewById(R.id.tv_zonghe);
//        tvPrice=(TextView) mRootView.findViewById(R.id.tv_price);
//        tvXiaoLiang=(TextView) mRootView.findViewById(R.id.tv_xiaoliang);
//        tvYouQuan=(TextView) mRootView.findViewById(R.id.tv_youquan);
          tvZongHe.setOnClickListener(this);tvZongHe.setText("hahahahah");
//        tvZongHe2=(TextView) mRootView.findViewById(R.id.id_tv_zonghe);



        tvquan=(TextView) mRootView.findViewById(R.id.tv_quan);
        tvquan.setOnClickListener(this);
//        tvPing=(TextView) mRootView.findViewById(R.id.id_tv_pinglun);
//        llDialog=(LinearLayout) mRootView.findViewById(R.id.ll_zonghe_dialog);
//
//        tvPing.setOnClickListener(this);
//

        return mRootView;
    }


    /**
     * pop取消
     */
    private void init() {
        // 取消按钮
        cacel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                // 销毁弹出框
                window.dismiss();
            }
        });
        layPop.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = layPop.findViewById(R.id.rl_pop_content).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        window.dismiss();
                    }
                }
                return true;
            }
        });

    }


    /**
     * 弹框
     */
    private void upTouXiang(View anchorView) {

        if (window == null) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_list1, null, false);
            layPop = (LinearLayout) view.findViewById(R.id.pop_content);
            cacel = (Button) view.findViewById(R.id.pop_cacel);
            listView = (ListView) view.findViewById(R.id.pop_list);
            window = new PopupWindow(view, ActionBar.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(getResources().getDrawable(R.color.transparent));
            window.setOutsideTouchable(true);

        }
        // 设置SelectPicPopupWindow弹出窗体可点击
        window.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        window.setAnimationStyle(R.style.popmenu_animation);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        window.setBackgroundDrawable(dw);
       // window.showAtLocation(view, Gravity.TOP, 0, 0);

        int windowPos[] = PopupWindowUtil.calculatePopWindowPos(anchorView, view);
        int xOff = 20; // 可以自己调整偏移
        windowPos[0] -= xOff;

        window.showAtLocation(anchorView, Gravity.TOP | Gravity.START, windowPos[0], windowPos[1]);

        pops = new ArrayList<PopWindow>();
        pops.add(new PopWindow("", "综合"));
        pops.add(new PopWindow("", "新品"));
        pops.add(new PopWindow("", "代言数从高到低"));
        //判断popwindow是否加载适配器
        if (adapter == null) {
            adapter = new WindowListAdapter(pops, getActivity());
            listView.setAdapter(adapter);
        }else{
            adapter.setList(pops);
            adapter.notifyDataSetChanged();
        }
        init();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                PopWindow pop = pops.get(position);
                tvZongHe.setText(pop.getPhone());

                if(position==0){

                    Toast.makeText(getActivity(), "dianjj"+pop.getPhone(), Toast.LENGTH_SHORT).show();

                }else if(position==1){
                    Toast.makeText(getActivity(), "dianjj"+pop.getPhone(), Toast.LENGTH_SHORT).show();
               //     autoObtainCameraPermission();
                }else {
                    Toast.makeText(getActivity(), "dianjj"+pop.getPhone(), Toast.LENGTH_SHORT).show();
                }


                window.dismiss();
            }
        });
    }







    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.tv_zonghe:
                upTouXiang(v);
                Toast.makeText(getActivity(), "dianjj", Toast.LENGTH_SHORT).show();
            break;

            case R.id.tv_quan:
                Toast.makeText(getActivity(), " 有券", Toast.LENGTH_SHORT).show();



                getFragmentManager().beginTransaction().add(R.id.container
                        , ShaiXuanFragment.create())
                        .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();
                break;

        }




    }


    @Override
    public boolean onContextClick(View v) {
        Toast.makeText(getActivity(), "2222", Toast.LENGTH_SHORT).show();

        return false;
    }
}
