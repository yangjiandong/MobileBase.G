package com.app.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.ek.mobilebapp.R;

/**
* ShootAndCropActivity demonstrates capturing and cropping camera images
* - user presses button to capture an image using the device camera
* - when they return with the captured image Uri, the app launches the crop action intent
* - on returning from the crop action, the app displays the cropped image
*
* Sue Smith
* Mobiletuts+ Tutorial: Capturing and Cropping an Image with the Android Camera
* July 2012
*
*/
//http://mobile.tutsplus.com/tutorials/android/capture-and-crop-an-image-with-the-device-camera/
public class ShootAndCropActivity extends Activity implements OnClickListener {

    //keep track of camera capture intent
    final int CAMERA_CAPTURE = 1;
    //keep track of cropping intent
    final int PIC_CROP = 2;
    //captured picture uri
    private Uri picUri;
    //save file
    private File picFile;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.shoot_and_crop);

        //retrieve a reference to the UI button
        Button captureBtn = (Button) findViewById(R.id.capture_btn);
        //handle button clicks
        captureBtn.setOnClickListener(this);
    }

    /**
     * Click method to handle user pressing button to launch camera
     */
    public void onClick(View v) {

        final Context dialogContext = new ContextThemeWrapper(this, android.R.style.Theme_Light);
        String cancel = "Back";
        String[] choices;
        choices = new String[2];
        choices[0] = "相机"; // 拍照
        choices[1] = "图库"; // 从相册中选择
        final ListAdapter adapter = new ArrayAdapter<String>(dialogContext, android.R.layout.simple_list_item_1,
                choices);

        final AlertDialog.Builder builder = new AlertDialog.Builder(dialogContext);
        builder.setTitle("Choose Photo");
        builder.setSingleChoiceItems(adapter, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                switch (which) {
                case 0:
                    String status = Environment.getExternalStorageState();
                    if (status.equals(Environment.MEDIA_MOUNTED)) {// 判断是否有SD卡
                        doTakePhoto();// 用户点击了从照相机获取
                    }
                    break;
                case 1:
                    doCropPhoto();// 从相册中去获取
                    break;
                }
            }
        });
        builder.setNegativeButton(cancel, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });
        builder.create().show();

        //        if (v.getId() == R.id.capture_btn) {
        //            try {
        //                //use standard intent to capture an image
        //                Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //                //we will handle the returned data in onActivityResult
        //                startActivityForResult(captureIntent, CAMERA_CAPTURE);
        //            } catch (ActivityNotFoundException anfe) {
        //                //display an error message
        //                String errorMessage = "Whoops - your device doesn't support capturing images!";
        //                Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
        //                toast.show();
        //            }
        //        }
    }

    /**
     * 拍照获取图片
     *
     */
    protected void doTakePhoto() {
        try {
            File uploadFileDir = new File(Environment.getExternalStorageDirectory(), "/upload");
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (!uploadFileDir.exists()) {
                uploadFileDir.mkdirs();
            }
            picFile = new File(uploadFileDir, "upload.jpeg");
            if (!picFile.exists()) {
                picFile.createNewFile();
            }
            picUri = Uri.fromFile(picFile);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
            startActivityForResult(cameraIntent, CAMERA_CAPTURE);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doCropPhoto() {
        try {
            File pictureFileDir = new File(Environment.getExternalStorageDirectory(), "/upload");
            if (!pictureFileDir.exists()) {
                pictureFileDir.mkdirs();
            }
            picFile = new File(pictureFileDir, "upload.jpeg");
            if (!picFile.exists()) {
                picFile.createNewFile();
            }
            picUri = Uri.fromFile(picFile);
            final Intent intent = getCropImageIntent();
            startActivityForResult(intent, PIC_CROP);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle user returning from both capturing and cropping the image
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
            case CAMERA_CAPTURE: // 拍照
                try {
                    cropImageUriByTakePhoto();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case PIC_CROP:
                try {
                    if (picUri != null) {
                        Bitmap bitmap = decodeUriAsBitmap(picUri);
                        //retrieve a reference to the ImageView
                        ImageView picView = (ImageView) findViewById(R.id.picture);
                        //display the returned cropped image
                        picView.setImageBitmap(bitmap);
                        //imageView.setImageBitmap(bitmap);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }

            //            //user is returning from capturing an image using the camera
            //            if (requestCode == CAMERA_CAPTURE) {
            //                //get the Uri for the captured image
            //                picUri = data.getData();
            //                //carry out the crop operation
            //                performCrop();
            //            }
            //            //user is returning from cropping the image
            //            else if (requestCode == PIC_CROP) {
            //                //get the returned data
            //                Bundle extras = data.getExtras();
            //                //get the cropped bitmap
            //                Bitmap thePic = extras.getParcelable("data");
            //                //retrieve a reference to the ImageView
            //                ImageView picView = (ImageView) findViewById(R.id.picture);
            //                //display the returned cropped image
            //                picView.setImageBitmap(thePic);
            //            }
        }
    }

    /**
     * Helper method to carry out crop operation
     */
    private void performCrop() {
        //take care of exceptions
        try {
            //call the standard crop action intent (the user device may not support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            //indicate image type and Uri
            cropIntent.setDataAndType(picUri, "image/*");
            //set crop properties
            cropIntent.putExtra("crop", "true");
            //indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            //indicate output X and Y
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
            cropIntent.putExtra("scale", true);//
            cropIntent.putExtra("scaleUpIfNeeded", true);
            //retrieve data on return
            cropIntent.putExtra("return-data", true);
            //start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, PIC_CROP);
        }
        //respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            //display an error message
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * Constructs an intent for image cropping. 调用图片剪辑程序
     */
    public Intent getCropImageIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //图片生成的最终尺寸
        intent.putExtra("outputX", 600);
        intent.putExtra("outputY", 600);
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        return intent;
    }

    private void cropImageUriByTakePhoto() {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(picUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 600);
        intent.putExtra("outputY", 600);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        startActivityForResult(intent, PIC_CROP);
    }

    private Bitmap decodeUriAsBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }
}
