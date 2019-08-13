package com.android.whalebuy.fragment.my;

import android.Manifest;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.view.*;
import android.widget.*;
import com.android.whalebuy.IConstants;
import com.android.whalebuy.R;
import com.android.whalebuy.adapter.WindowListAdapter;
import com.android.whalebuy.common.BaseFragment;
import com.android.whalebuy.been.PopWindow;
import com.android.whalebuy.listener.IFragmentListener;
import com.android.whalebuy.widget.PhotoUtils;
import com.android.whalebuy.widget.RoundImageView;
import com.android.whalebuy.widget.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 *个人资料
 *
 *
 */
public class SettingMyInformationFragment extends BaseFragment implements View.OnClickListener,IFragmentListener {
    View mRootView;

    private TextView tvQuXiao,tvWanCheng;
    private TextView tvGengHuan;
    private EditText  edName;
    private TextView  tvset;
    private TextView sex;
    private View view;
    private LinearLayout layPop;
    private PopupWindow window;
    private Button cacel;
    private List<PopWindow> pops;
    private ListView listView;
    private boolean afterShot = false;
    private ImageView imageViewUserLogoShot;
    private RoundImageView roTouXiang;
    private WindowListAdapter adapter;

    private AlertDialog.Builder builder;
    private ProgressDialog progressDialog;




    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private Uri imageUri;
    private Uri cropImageUri;
    private static final int OUTPUT_X = 480;
    private static final int OUTPUT_Y = 480;

    private File file;
    private static int REQUEST_CAMERA =1;

    public static SettingMyInformationFragment create() {
        return new SettingMyInformationFragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_information, container, false);

        imageViewUserLogoShot = (ImageView) (mRootView.findViewById(R.id.img_tx));// 头像



        tvQuXiao = (TextView) (mRootView.findViewById(R.id.tv_top_left));// 取消
        tvQuXiao.setOnClickListener(this);

        tvWanCheng = (TextView) (mRootView.findViewById(R.id.tv_top_right));// 完成
        tvWanCheng.setOnClickListener(this);

        tvGengHuan = (TextView) (mRootView.findViewById(R.id.tv_genghuan));//更换头像
        tvGengHuan.setOnClickListener(this);

        edName = (EditText) (mRootView.findViewById(R.id.ed_name));// 名字
        edName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText _e = (EditText)v;
                if(!hasFocus)  //没有焦点
                {
                    _e.setHint(_e.getTag().toString());
                }
                else  //获取到焦点
                {
                    _e.setTag(_e.getHint().toString());
                    _e.setHint("");
                }
            }
        });

        tvset = (TextView) (mRootView.findViewById(R.id.tv_sex));// 性别
        tvset.setOnClickListener(this);

        return mRootView;
    }

    @Override
    public void fragmentWillShow() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_top_left:
                Toast.makeText(getActivity(), "取消", Toast.LENGTH_SHORT).show();
//                getFragmentManager().beginTransaction().add(R.id.container
//                        ,MyActivitySetting.create())
//                        .addToBackStack(IConstants.FRAGMENT_LOGIN).commit();






                break;
            case R.id.tv_top_right:
                Toast.makeText(getActivity(), "完成", Toast.LENGTH_SHORT).show();

                break;
            case R.id.tv_genghuan:
                Toast.makeText(getActivity(), "更换头像", Toast.LENGTH_SHORT).show();
             //   ShowPickDialog();
                upTouXiang();
                // showTwo();
//                Intent intent = new Intent(Intent.ACTION_PICK, null);
//                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//                startActivityForResult(intent, 1);
                break;

            case R.id.tv_sex:
                Toast.makeText(getActivity(), "选择性别", Toast.LENGTH_SHORT).show();
                updateSex();
                break;


        }

    }





    /**
     * 性别选择
     */
    private void updateSex() {

        if (window == null) {
//            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = inflater.inflate(R.layout.pop_list, null);

            view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_list, null, false);
            layPop = (LinearLayout) view.findViewById(R.id.pop_content);
            cacel = (Button) view.findViewById(R.id.pop_cacel);
            listView = (ListView) view.findViewById(R.id.pop_list);
            window = new PopupWindow(view, ActionBar.LayoutParams.FILL_PARENT,
                    ActionBar.LayoutParams.FILL_PARENT);
        }
        // 设置SelectPicPopupWindow弹出窗体可点击
        window.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        window.setBackgroundDrawable(dw);
        window.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        pops = new ArrayList<PopWindow>();
        pops.add(new PopWindow("", "男"));
        pops.add(new PopWindow("", "女"));
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
                tvset.setText(pop.getPhone());
                window.dismiss();
            }
        });
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
     * 性别选择
     */
    private void upTouXiang() {

        if (window == null) {
//            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = inflater.inflate(R.layout.pop_list, null);

            view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_list, null, false);
            layPop = (LinearLayout) view.findViewById(R.id.pop_content);
            cacel = (Button) view.findViewById(R.id.pop_cacel);
            listView = (ListView) view.findViewById(R.id.pop_list);
            window = new PopupWindow(view, ActionBar.LayoutParams.FILL_PARENT,
                    ActionBar.LayoutParams.FILL_PARENT);
        }
        // 设置SelectPicPopupWindow弹出窗体可点击
        window.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        window.setBackgroundDrawable(dw);
        window.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        pops = new ArrayList<PopWindow>();
        pops.add(new PopWindow("", "相册获取"));
        pops.add(new PopWindow("", "拍照获取"));
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
                tvset.setText(pop.getPhone());

    if(position==0){

        Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, 1);


    }else{

        autoObtainCameraPermission();
    }


                window.dismiss();
            }
        });
    }


    /**
     * 动态申请sdcard读写权限
     */
    private void autoObtainStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSIONS_REQUEST_CODE);
            } else {
                PhotoUtils.openPic(SettingMyInformationFragment.this, CODE_GALLERY_REQUEST);
            }
        } else {
            PhotoUtils.openPic(SettingMyInformationFragment.this, CODE_GALLERY_REQUEST);
        }
    }

    /**
     * 申请访问相机权限
     */
    private void autoObtainCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {
                    ToastUtils.showShort(getActivity(), "您已经拒绝过一次");
                }
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_PERMISSIONS_REQUEST_CODE);
            } else {//有权限直接调用系统相机拍照
                if (hasSdcard()) {
                    imageUri = Uri.fromFile(fileUri);
                    //通过FileProvider创建一个content类型的Uri
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        imageUri = FileProvider.getUriForFile(getActivity(), "com.zz.fileprovider", fileUri);
                    }
                    PhotoUtils.takePicture(SettingMyInformationFragment.this, imageUri, CODE_CAMERA_REQUEST);
                } else {
                    ToastUtils.showShort(getActivity(), "设备没有SD卡！");
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       // Log.d(TAG, "onRequestPermissionsResult: ");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            //调用系统相机申请拍照权限回调
            case CAMERA_PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (hasSdcard()) {
                        imageUri = Uri.fromFile(fileUri);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            //通过FileProvider创建一个content类型的Uri
                            imageUri = FileProvider.getUriForFile(getActivity(), "com.zz.fileprovider", fileUri);
                        }
                        PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
                    } else {
                        ToastUtils.showShort(getActivity(), "设备没有SD卡！");
                    }
                } else {
                    ToastUtils.showShort(getActivity(), "请允许打开相机！！");
                }
                break;
            }
            //调用系统相册申请Sdcard权限回调
            case STORAGE_PERMISSIONS_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
                } else {
                    ToastUtils.showShort(getActivity(), "请允许打操作SDCard！！");
                }
                break;
            default:
        }
    }

    private void showImages(Bitmap bitmap) {
        imageViewUserLogoShot.setImageBitmap(bitmap);
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }



    /**
     * 裁剪图片
     *
     * @param uri
     */
    private void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    private void setPicToView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap bitmap = extras.getParcelable("data");
            if (bitmap == null) {
                return;
            }

            Drawable drawable = new BitmapDrawable(bitmap);
            imageViewUserLogoShot.setImageBitmap(bitmap);

          //  imageViewUserLogoShot(bitmap);
        }
    }


}