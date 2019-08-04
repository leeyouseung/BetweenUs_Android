package org.techtown.betweenus_android.view.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.base.BaseActivity;
import org.techtown.betweenus_android.databinding.SignupActivityBinding;
import org.techtown.betweenus_android.manager.ViewModelFactory;
import org.techtown.betweenus_android.network.request.ImgUploadRequest;
import org.techtown.betweenus_android.network.request.SignupRequest;
import org.techtown.betweenus_android.viewmodel.ImgUploadViewModel;
import org.techtown.betweenus_android.viewmodel.SignupViewModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SignupActivity extends BaseActivity<SignupActivityBinding> {

    private SignupViewModel signupViewModel;
    private ImgUploadViewModel imgUploadViewModel;

    @Override
    protected int layoutId() {
        return R.layout.signup_activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewModel();
        initImg();

        imgUploadViewModel.getData().observe(this, images -> {
            imgUploadViewModel.images.setValue(images);
            Glide.with(this).load(imgUploadViewModel.uri.getValue()).into(binding.imageView);
            imgUploadViewModel.file.getValue().delete();
        });

        signupViewModel.getSuccessMessage().observe(this, message -> {
            Toast.makeText(this, (String) message, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        signupViewModel.getErrorMessage().observe(this, message -> Toast.makeText(this, (String) message, Toast.LENGTH_SHORT).show());

        clickEvent();
    }

    private void initViewModel() {
        signupViewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(SignupViewModel.class);
        imgUploadViewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(ImgUploadViewModel.class);
    }

    private void initImg() {
        binding.imageView.setBackground(new ShapeDrawable(new OvalShape()));
        binding.imageView.setClipToOutline(true);

        Glide.with(this).load(imgUploadViewModel.uri.getValue()).into(binding.imageView);
    }

    private void clickEvent() {

        binding.imageView.setOnClickListener(v -> goToAlbum());

        binding.signupBtn.setOnClickListener(v -> {

            if (binding.idText.getText().toString().isEmpty() || binding.passwordText.getText().toString().isEmpty() || binding.rePasswordText.getText().toString().isEmpty() || binding.nameText.getText().toString().isEmpty() || binding.schoolText.getText().toString().isEmpty() || binding.gradeText.getText().toString().isEmpty() || binding.classText.getText().toString().isEmpty() || binding.phoneNumberText.getText().toString().isEmpty()) {
                Toast.makeText(this, "빈칸 없이 회원가입 해 주세요.",Toast.LENGTH_SHORT).show();
                return;
            }
            if (!binding.passwordText.getText().toString().equals(binding.rePasswordText.getText().toString())) {
                Toast.makeText(this, "비밀번호가 서로 다릅니다", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                signupViewModel.signup(new SignupRequest(binding.idText.getText().toString(),
                        binding.passwordText.getText().toString(),
                        binding.nameText.getText().toString(),
                        binding.schoolText.getText().toString(),
                        imgUploadViewModel.images.getValue().get(0),
                        binding.phoneNumberText.getText().toString(),
                        Integer.parseInt(binding.gradeText.getText().toString()),
                        Integer.parseInt(binding.classText.getText().toString())));
            } catch (NullPointerException e) {
                Toast.makeText(this, "회원가입을 위한 요소가 다 포함되지 않았습니다.", Toast.LENGTH_SHORT).show();

                e.printStackTrace();
            }
        });
    }

    private final int PICK_FROM_ALBUM = 1;
    private final int REQUEST_IMAGE_CROP = 2;

    private Uri photoURI;

    private void goToAlbum() {

        tedPermission();

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case PICK_FROM_ALBUM:
                if (data == null) {
                    return;
                }

                photoURI = data.getData();

                cropImage();

                break;


            case REQUEST_IMAGE_CROP:

                if (resultCode == Activity.RESULT_OK) {

                    RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), imgUploadViewModel.file.getValue());
                    MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("image", imgUploadViewModel.file.getValue().getName(), mFile);

                    ImgUploadRequest imgUploadRequest = new ImgUploadRequest(fileToUpload);

                    imgUploadViewModel.profileImgUpload(imgUploadRequest);
                }

                break;
        }
    }

    private void cropImage() {

        File file = new File(Environment.getExternalStorageDirectory().toString() + "/BetweenUs");

        if (!file.exists())

            file.mkdirs();

        imgUploadViewModel.file.setValue(new File(Environment.getExternalStorageDirectory().toString() + "/BetweenUs/temp.jpg"));

        try {
            imgUploadViewModel.file.getValue().createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        imgUploadViewModel.uri.setValue(Uri.fromFile(imgUploadViewModel.file.getValue()));

        Intent cropIntent = new Intent("com.android.camera.action.CROP");

        cropIntent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        cropIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        cropIntent.setDataAndType(photoURI, "image/*");
        cropIntent.putExtra("aspectX",1);
        cropIntent.putExtra("aspectY",1);
        cropIntent.putExtra("scale",true);
        cropIntent.putExtra("output",imgUploadViewModel.uri.getValue());

        startActivityForResult(cropIntent,REQUEST_IMAGE_CROP);

    }

    private void tedPermission() {

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() { }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(getApplicationContext(), "접근을 허용해야 사진을 등록할 수 있습니다", Toast.LENGTH_LONG).show();
            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();

    }
}
