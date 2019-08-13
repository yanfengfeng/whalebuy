package com.android.whalebuy.utils;
import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.whalebuy.R;
import com.chuanglan.shanyan_sdk.listener.ShanYanCustomInterface;
import com.chuanglan.shanyan_sdk.tool.ShanYanUIConfig;


public class ConfigUtils {
    /**
     * 闪验三网运营商授权页配置类
     *
     * @param context
     * @return
     */
    public static ShanYanUIConfig getUiConfig(Context context) {
        /************************************************自定义控件**************************************************************/
        //其他方式登录
        TextView otherTV = new TextView(context);
        otherTV.setText("其他方式登录");
        otherTV.setTextColor(0xff3a404c);
        otherTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        RelativeLayout.LayoutParams mLayoutParams1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        mLayoutParams1.setMargins(0, AbScreenUtils.dp2px(context, 270), 0, 0);
        mLayoutParams1.addRule(RelativeLayout.CENTER_HORIZONTAL);
        otherTV.setLayoutParams(mLayoutParams1);
        //标题栏下划线
     /*   View view = new View(context);
        view.setBackgroundColor(0xffe8e8e8);
        RelativeLayout.LayoutParams mLayoutParams3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, AbScreenUtils.dp2px(context, 1));
        mLayoutParams3.setMargins(0, AbScreenUtils.dp2px(context, 0), 0, 0);
        view.setLayoutParams(mLayoutParams3);*/

        /****************************************************设置授权页*********************************************************/
        ShanYanUIConfig uiConfig = new ShanYanUIConfig.Builder()
                //授权页导航栏：
                .setNavColor(Color.parseColor("#ffffff"))  //设置导航栏颜色
                .setNavText("免密登录")  //设置导航栏标题文字
                .setNavTextColor(0xff080808) //设置标题栏文字颜色
                .setNavReturnImgPath("sy_sdk_left")  //设置导航栏返回按钮图标
                //.setAuthBGImgPath("login_bg")

                //授权页logo（logo的层级在次底层，仅次于自定义控件）
                .setLogoImgPath("preoperaicon")  //设置logo图片
                .setLogoWidth(70)   //设置logo宽度
                .setLogoHeight(70)   //设置logo高度
                .setLogoOffsetY(50)  //设置logo相对于标题栏下边缘y偏移
                .setLogoHidden(false)   //是否隐藏logo

                //授权页号码栏：
                .setNumberColor(0xff333333)  //设置手机号码字体颜色
                .setNumFieldOffsetY(140)    //设置号码栏相对于标题栏下边缘y偏移
                .setNumberSize(18)


                //授权页登录按钮：
                .setLogBtnText("本机号码一键登录")  //设置登录按钮文字
                .setLogBtnTextColor(0xffffffff)   //设置登录按钮文字颜色
                .setLogBtnImgPath("onekeybackground")   //设置登录按钮图片
                .setLogBtnOffsetY(220)   //设置登录按钮相对于标题栏下边缘y偏移
                .setLogBtnTextSize(15)
                .setLogBtnHeight(45)

                //授权页隐私栏：
                .setAppPrivacyOne("用户自定义协议条款", "https://www.253.com")  //设置开发者隐私条款1名称和URL(名称，url)
                .setAppPrivacyTwo("用户服务条款", "https://www.253.com")  //设置开发者隐私条款2名称和URL(名称，url)
                .setAppPrivacyColor(0xff666666, 0xff0085d0)   //	设置隐私条款名称颜色(基础文字颜色，协议文字颜色)
                .setPrivacyOffsetBottomY(30)//设置隐私条款相对于屏幕下边缘y偏移
                .setUncheckedImgPath("sy_uncheck")
                .setCheckedImgPath("sy_check")
                .setCheckBoxHidden(false)

                //授权页slogan：
                .setSloganTextColor(0xff999999)  //设置slogan文字颜色
                .setSloganOffsetY(195)  //设置slogan相对于标题栏下边缘y偏移

                // 添加自定义控件:
                //其他方式登录及监听，可以不写
                .addCustomView(otherTV, true, false, new ShanYanCustomInterface() {
                    @Override
                    public void onClick(Context context, View view) {
                        Toast.makeText(context, "其他方式登录", Toast.LENGTH_SHORT).show();
                    }
                })
                //标题栏下划线，可以不写
                //.addCustomView(view, true, false, null)
                .build();
        return uiConfig;

    }

    public static ShanYanUIConfig getCJSConfig(final Context context) {
        /************************************************自定义控件**************************************************************/
        //其他方式登录
        TextView otherTV = new TextView(context);
        otherTV.setText("其他方式登录");
        otherTV.setTextColor(0xff3a404c);
        otherTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        RelativeLayout.LayoutParams mLayoutParams1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        mLayoutParams1.setMargins(0,AbScreenUtils.dp2px(context, 420), 0, 0);
        mLayoutParams1.addRule(RelativeLayout.CENTER_HORIZONTAL);
        otherTV.setLayoutParams(mLayoutParams1);
        //标题栏下划线
     /*   View view = new View(context);
        view.setBackgroundColor(0xffe8e8e8);
        RelativeLayout.LayoutParams mLayoutParams3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, AbScreenUtils.dp2px(context, 1));
        mLayoutParams3.setMargins(0, AbScreenUtils.dp2px(context, 0), 0, 0);
        view.setLayoutParams(mLayoutParams3);*/

        Button close = new Button(context);
        close.setBackgroundResource(context.getResources().getIdentifier("close","drawable",context.getPackageName()));
        RelativeLayout.LayoutParams mLayoutParamsClose = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        mLayoutParamsClose.setMargins(0,AbScreenUtils.dp2px(context, 10), AbScreenUtils.dp2px(context, 10), 0);
        mLayoutParamsClose.width = AbScreenUtils.dp2px(context, 15);
        mLayoutParamsClose.height = AbScreenUtils.dp2px(context, 15);
        mLayoutParamsClose.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        close.setLayoutParams(mLayoutParamsClose);

        //其他方式登录
        TextView tip = new TextView(context);
        tip.setText("使用该手机号登录");
        tip.setTextColor(0xffffffff);
        tip.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        RelativeLayout.LayoutParams mLayoutParamsTip = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        mLayoutParamsTip.setMargins(AbScreenUtils.dp2px(context, 20), AbScreenUtils.dp2px(context, 170), 0, 0);
        mLayoutParamsTip.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        tip.setLayoutParams(mLayoutParamsTip);


        LayoutInflater inflater = LayoutInflater.from(context);
        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(R.layout.relative_item_view, null);
        RelativeLayout.LayoutParams layoutParamsOther = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsOther.setMargins(0, AbScreenUtils.dp2px(context, 410), 0, 0);
        layoutParamsOther.addRule(RelativeLayout.CENTER_HORIZONTAL);
        relativeLayout.setLayoutParams(layoutParamsOther);
        ImageView weixin = relativeLayout.findViewById(R.id.weixin);
        ImageView qq = relativeLayout.findViewById(R.id.qq);
        ImageView weibo = relativeLayout.findViewById(R.id.weibo);
        weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "微信登录", Toast.LENGTH_SHORT).show();
            }
        });
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "qq登录", Toast.LENGTH_SHORT).show();

            }
        });
        weibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "微博登录", Toast.LENGTH_SHORT).show();

            }
        });






        /****************************************************设置授权页*********************************************************/
        ShanYanUIConfig uiConfig = new ShanYanUIConfig.Builder()
                //授权页导航栏：
                .setNavColor(Color.parseColor("#ffffff"))  //设置导航栏颜色
                .setNavText("")  //设置导航栏标题文字
                .setNavTextColor(0xff080808) //设置标题栏文字颜色
                .setNavReturnImgPath("")  //设置导航栏返回按钮图标
                .setAuthNavHidden(true)
                .setAuthBGImgPath("sy_login_bg")

                //授权页logo（logo的层级在次底层，仅次于自定义控件）
                .setLogoImgPath("shanyan_logo")  //设置logo图片
                .setLogoWidth(140)   //设置logo宽度
                .setLogoHeight(70)   //设置logo高度
                .setLogoOffsetY(230)  //设置logo相对于标题栏下边缘y偏移
                .setLogoHidden(false)   //是否隐藏logo
                .setLogoOffsetX(20)

                //授权页号码栏：
                .setNumberColor(0xffffffff)  //设置手机号码字体颜色
                .setNumFieldOffsetY(200)    //设置号码栏相对于标题栏下边缘y偏移
                .setNumFieldWidth(110)
                .setNumberSize(18)
                .setNumFieldOffsetX(20)


                //授权页登录按钮：
                .setLogBtnText("本机号码一键登录")  //设置登录按钮文字
                .setLogBtnTextColor(0xff492C5A)   //设置登录按钮文字颜色
                .setLogBtnImgPath("sysdk_login_bg")   //设置登录按钮图片
                .setLogBtnOffsetY(350)   //设置登录按钮相对于标题栏下边缘y偏移
                .setLogBtnTextSize(15)
                .setLogBtnHeight(45)
                .setLogBtnWidth(AbScreenUtils.getScreenWidth(context,true)-40)

                //授权页隐私栏：
                .setAppPrivacyOne("用户自定义协议条款", "https://www.253.com")  //设置开发者隐私条款1名称和URL(名称，url)
                .setAppPrivacyTwo("用户服务条款", "https://www.253.com")  //设置开发者隐私条款2名称和URL(名称，url)
                .setAppPrivacyColor(0xffffffff, 0xff0085d0)   //	设置隐私条款名称颜色(基础文字颜色，协议文字颜色)
                .setPrivacyOffsetBottomY(30)//设置隐私条款相对于屏幕下边缘y偏
                .setUncheckedImgPath("sy_uncheck")
                .setCheckedImgPath("sy_check")
                .setCheckBoxHidden(false)

                //授权页slogan：
                .setSloganTextColor(0xff999999)  //设置slogan文字颜色
                .setSloganOffsetY(245)  //设置slogan相对于标题栏下边缘y偏移
                .setSloganHidden(true)


                // 添加自定义控件:
                //其他方式登录及监听，可以不写
//                .addCustomView(otherTV, true, false, new ShanYanCustomInterface() {
//                    @Override
//                    public void onClick(Context context, View view) {
//                        Toast.makeText(context, "其他方式登录", Toast.LENGTH_SHORT).show();
//                    }
//                })
                .addCustomView(close, true, false, new ShanYanCustomInterface() {
                    @Override
                    public void onClick(Context context, View view) {
                        Toast.makeText(context, "退出页面", Toast.LENGTH_SHORT).show();
                    }
                })
                .addCustomView(tip, false, false, new ShanYanCustomInterface() {
                    @Override
                    public void onClick(Context context, View view) {

                    }
                })
                .addCustomView(relativeLayout, false, false, null)
                //标题栏下划线，可以不写
                .build();
        return uiConfig;

    }

}
