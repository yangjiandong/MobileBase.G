package com.ek.mobileapp.activity;

import java.io.File;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ek.mobileapp.AppConfig;
import com.ek.mobileapp.Config;
import com.ek.mobileapp.action.ApiClient;
import com.ek.mobileapp.model.UserDTO;
import com.ek.mobileapp.utils.FileUtils;
import com.ek.mobileapp.utils.GlobalCache;
import com.ek.mobileapp.utils.ImageUtils;
import com.ek.mobileapp.utils.StringUtils;
import com.ek.mobileapp.utils.ToastUtils;
import com.ek.mobileapp.utils.UIHelper;
import com.ek.mobilebapp.R;

public class UserInfoActivity extends Activity {
    UserDTO user;
    ImageView face;
    ImageView gender;
    Button editer;
    TextView user_info_createtime;

    //private final static int CROP = 200;
    private LoadingDialog loading;

    private Uri origUri;
    private Uri cropUri;
    private File protraitFile;
    private Bitmap protraitBitmap;
    String protraitPath;
    String cropFileName;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.user_info_layout);

        Button leftBtn = (Button) findViewById(com.ek.mobilebapp.R.id.custom_title_btn_left);
        leftBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        TextView titleTv = (TextView) findViewById(R.id.custom_title_label);
        titleTv.setText("用户信息");

        user = GlobalCache.getCache().getLoginuser();

        TextView user_info_username = (TextView) findViewById(R.id.user_info_username);
        user_info_username.setText(user.getName());

        face = (ImageView) findViewById(R.id.user_info_userface);
        // 加载用户头像
        //Logger.d("faceurl:" + user.getFaceUrl());
        UIHelper.showUserFace(face, user.getFaceUrl());

        gender = (ImageView) findViewById(R.id.user_info_gender);

        //
        user_info_createtime = (TextView) findViewById(R.id.user_info_department);
        user_info_createtime.setText(user.getDepartName());
        TextView job = (TextView) findViewById(R.id.user_info_job);
        job.setText(user.getJob());
        TextView title = (TextView) findViewById(R.id.user_info_title);
        title.setText(user.getTitle());

        editer = (Button) findViewById(R.id.user_info_editer);
        editer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence[] items = { "相册选图", "手机拍照" };
                imageChooseItem(items);
            }
        });
    }

    /**
     * 操作选择
     */
    public void imageChooseItem(CharSequence[] items) {
        AlertDialog imageDialog = new AlertDialog.Builder(this).setTitle("上传头像").setIcon(android.R.drawable.btn_star)
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // 相册选图
                        if (item == 0) {
                            startImagePick();
                        }
                        // 手机拍照
                        else if (item == 1) {
                            startActionCamera();
                        }
                    }
                }).create();

        imageDialog.show();
    }

    /**
     * 选择图片裁剪
     */
    private void startImagePick() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "选择图片"),
                com.ek.mobileapp.utils.ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP);
    }

    /**
     * 相机拍照
     */
    private void startActionCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, this.getCameraTempFile());
        startActivityForResult(intent, ImageUtils.REQUEST_CODE_GETIMAGE_BYCAMERA);
    }

    /**
     * 拍照后裁剪
     *
     * @param data
     *            原始图片
     * @param output
     *            裁剪后图片
     */
    private void startActionCrop(Uri data) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(data, "image/*");
        intent.putExtra("output", this.getUploadTempFile(data));
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);// 裁剪框比例
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", Config.IMAGE_LARGE_WIDTH);// 输出图片大小
        intent.putExtra("outputY", Config.IMAGE_LARGE_HEIGHT);
        intent.putExtra("scale", true);// 去黑边
        intent.putExtra("scaleUpIfNeeded", true);// 去黑边
        startActivityForResult(intent, ImageUtils.REQUEST_CODE_GETIMAGE_BYSDCARD);
    }

    // 裁剪头像的绝对路径
    private Uri getUploadTempFile(Uri uri) {
        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
            File savedir = new File(AppConfig.DEFAULT_SAVE_IMAGE_PATH);
            if (!savedir.exists()) {
                savedir.mkdirs();
            }
        } else {
            ToastUtils.show(UserInfoActivity.this, "无法保存上传的头像，请检查SD卡是否挂载");
            return null;
        }
        String timeStamp = ImageUtils.getTimeStampTempFileName();//new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String thePath = ImageUtils.getAbsolutePathFromNoStandardUri(uri);

        // 如果是标准Uri
        if (StringUtils.isEmpty(thePath)) {
            thePath = ImageUtils.getAbsoluteImagePath(UserInfoActivity.this, uri);
        }
        String ext = FileUtils.getFileFormat(thePath);
        ext = StringUtils.isEmpty(ext) ? "jpg" : ext;
        // 照片命名
        cropFileName = Config.IMAGE_LARGE + "user_" + user.getId() + "_" + timeStamp + "." + ext;//"ekmobile_crop_" + timeStamp + "." + ext;
        // 预先定义,没有从后台取
        user.setFaceUrl(Config.IMAGE_MED + "user_" + user.getId() + "_" + timeStamp + "." + ext);

        // 裁剪头像的绝对路径
        protraitPath = AppConfig.DEFAULT_SAVE_IMAGE_PATH + File.separator + cropFileName;
        protraitFile = new File(protraitPath);

        cropUri = Uri.fromFile(protraitFile);
        return this.cropUri;
    }

    // 拍照保存的绝对路径
    private Uri getCameraTempFile() {
        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
            File savedir = new File(AppConfig.DEFAULT_SAVE_IMAGE_PATH);
            if (!savedir.exists()) {
                savedir.mkdirs();
            }
        } else {
            ToastUtils.show(UserInfoActivity.this, "无法保存上传的头像，请检查SD卡是否挂载");
            return null;
        }
        String timeStamp = ImageUtils.getTimeStampTempFileName();//new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        // 照片命名
        String cropFileName = "ekmobile_camera_" + timeStamp + ".jpg";
        // 裁剪头像的绝对路径
        protraitPath = AppConfig.DEFAULT_SAVE_IMAGE_PATH + File.separator + cropFileName;
        protraitFile = new File(protraitPath);
        cropUri = Uri.fromFile(protraitFile);
        this.origUri = this.cropUri;
        return this.cropUri;
    }

    /**
     * 上传新照片
     */
    @SuppressLint("HandlerLeak")
    private void uploadNewPhoto() {
        final Handler showHandler = new Handler() {
            public void handleMessage(Message msg) {
                if (loading != null)
                    loading.dismiss();
                if (msg.what == 1) {
                    ToastUtils.show(UserInfoActivity.this, "上传图片成功哦");
                    // 重新取后台faceurl,显示新头像
                    //face.setImageBitmap(protraitBitmap);
                    // 加载用户头像
                    //Logger.d("after update face:"+user.getFaceUrl());
                    UIHelper.showUserFace(face, user.getFaceUrl());

                } else if (msg.what == -1 && msg.obj != null) {
                    ToastUtils.show(UserInfoActivity.this, "上传图片出错");
                }
            }
        };

        if (loading != null) {
            loading.setLoadText("正在上传头像···");
            loading.show();
        }

        new Thread() {
            public void run() {
                // 获取头像缩略图
                if (!StringUtils.isEmpty(protraitPath) && protraitFile.exists()) {
                    protraitBitmap = ImageUtils.loadImgThumbnail(protraitPath, Config.IMAGE_LARGE_WIDTH,
                            Config.IMAGE_LARGE_HEIGHT);
                } else {
                    loading.setLoadText("图像不存在，上传失败·");
                    loading.hide();
                }

                if (protraitBitmap != null) {
                    Message msg = new Message();
                    try {
                        String address = "/system/upload_user_face?type=mobile&fileName=" + cropFileName;
                        boolean success = ApiClient.uploadImage(protraitFile, address);
                        if (success) {
                            // 保存新头像到缓存
                            //String filename = FileUtils.getFileName(user.getFaceUrl());
                            //ImageUtils.saveImage(UserInfoActivity.this, filename, protraitBitmap);
                            msg.what = 1;
                            msg.obj = "success";
                        }

                    } catch (Exception e) {
                        loading.setLoadText("上传出错·");
                        loading.hide();
                        msg.what = -1;
                        msg.obj = "失败";
                    }
                    showHandler.sendMessage(msg);
                } else {
                    loading.setLoadText("图像不存在，上传失败·");
                    loading.hide();
                }
            };
        }.start();
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (resultCode != RESULT_OK)
            return;

        switch (requestCode) {
        case ImageUtils.REQUEST_CODE_GETIMAGE_BYCAMERA:
            startActionCrop(origUri);// 拍照后裁剪
            break;
        case ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP:
            startActionCrop(data.getData());// 选图后裁剪
            break;
        case ImageUtils.REQUEST_CODE_GETIMAGE_BYSDCARD:
            uploadNewPhoto();// 上传新照片
            break;
        }
    }
}
