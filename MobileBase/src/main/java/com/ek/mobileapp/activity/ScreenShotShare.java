package com.ek.mobileapp.activity;

import java.io.File;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ek.mobileapp.Config;
import com.ek.mobileapp.action.ApiClient;
import com.ek.mobileapp.utils.FileUtils;
import com.ek.mobileapp.utils.ImageUtils;
import com.ek.mobileapp.utils.StringUtils;
import com.ek.mobileapp.utils.ToastUtils;
import com.ek.mobileapp.utils.UIHelper;
import com.ek.mobilebapp.R;

/**
 * 截屏分享
 *
 * @author yeguozhong@yeah.net
 *
 */
public class ScreenShotShare extends Activity {

    private ImageView ivShare;
    private EditText etContent;
    private TextView tvUrl;
    private TextView tvLeft;

    private Button btnShear;

    private int leftTextNum;
    private static final int MAX_CONTENT_SIZE = 140;

    private String mCutImagePath;
    private String mTitle;
    private String mUrl;

    private File protraitFile;
    private Bitmap protraitBitmap;
    private LoadingDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_shot_share);
        initView();
        addListeners();
    }

    private void initView() {
        Bundle b = getIntent().getExtras();
        mTitle = b.getString("title");
        mUrl = b.getString("url");
        mCutImagePath = b.getString("cut_image_tmp_path");

        ivShare = (ImageView) findViewById(R.id.iv_shared);
        etContent = (EditText) findViewById(R.id.et_content);
        tvUrl = (TextView) findViewById(R.id.tv_url);
        tvLeft = (TextView) findViewById(R.id.tv_text_left);

        etContent.setText(mTitle);
        tvUrl.setText(mUrl);
        if (mCutImagePath != null) {
            ivShare.setImageBitmap(ImageUtils.getBitmapByPath(mCutImagePath));
        }

        leftTextNum = getLeftTextNum();
        tvLeft.setText("还可以输入:" + leftTextNum + "个字");

        Button leftBtn = (Button) findViewById(com.ek.mobilebapp.R.id.custom_title_btn_left);
        leftBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        TextView titleTv = (TextView)findViewById(R.id.custom_title_label);
        titleTv.setText("分享");

        btnShear = (Button) findViewById(com.ek.mobilebapp.R.id.btn_shear);
        btnShear.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                uploadNewPhoto();
                protraitFile = new File(mCutImagePath);
            }
        });
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
                    ToastUtils.show(ScreenShotShare.this, "上传图片成功哦");
                    // 重新取后台faceurl,显示新头像
                    //face.setImageBitmap(protraitBitmap);
                    // 加载用户头像
                    //Logger.d("after update face:"+user.getFaceUrl());
                    //UIHelper.showUserFace(face, user.getFaceUrl());

                } else if (msg.what == -1 && msg.obj != null) {
                    ToastUtils.show(ScreenShotShare.this, "上传图片出错");
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
                if (!StringUtils.isEmpty(mCutImagePath) && protraitFile.exists()) {
                    protraitBitmap = ImageUtils.loadImgThumbnail(mCutImagePath, Config.IMAGE_LARGE_WIDTH,
                            Config.IMAGE_LARGE_HEIGHT);
                } else {
                    loading.setLoadText("图像不存在，上传失败·");
                    loading.hide();
                }

                if (protraitBitmap != null) {
                    Message msg = new Message();
                    try {
                        String address = "/system/upload_user_face?type=mobile&fileName=" + protraitFile;
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

    /**
     * 添加控件的事件监听
     */
    private void addListeners() {

        ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ScreenShotShare.this, ImageDialog.class);
                intent.putExtra("local_img", mCutImagePath);
                intent.putExtra("img_url", "no_image_url");
                startActivity(intent);
            }
        });
        //监听输入字数
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTitle = etContent.getText().toString();
                leftTextNum = getLeftTextNum();
                tvLeft.setText("还可以输入:" + leftTextNum + "个字");
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 删除临时图片
        FileUtils.deleteFileWithPath(mCutImagePath);
    }

    /**
     * 返回还能输入的字数
     *
     * @return
     */
    private int getLeftTextNum() {
        return MAX_CONTENT_SIZE - (mTitle + " " + mUrl).length();
    }
}
