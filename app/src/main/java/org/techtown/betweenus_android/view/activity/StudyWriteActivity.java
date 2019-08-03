package org.techtown.betweenus_android.view.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.base.BaseActivity;
import org.techtown.betweenus_android.databinding.StudyWritingActivityBinding;
import org.techtown.betweenus_android.manager.ViewModelFactory;
import org.techtown.betweenus_android.network.client.StudyClient;
import org.techtown.betweenus_android.network.request.ImgUploadRequest;
import org.techtown.betweenus_android.network.request.StudyRequest;
import org.techtown.betweenus_android.viewmodel.ImgUploadViewModel;
import org.techtown.betweenus_android.viewmodel.StudyViewModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class StudyWriteActivity extends BaseActivity<StudyWritingActivityBinding> {

    private StudyViewModel studyViewModel;
    private ImgUploadViewModel imgUploadViewModel;

    @Override
    protected int layoutId() {
        return R.layout.study_writing_activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewModel();
        initIntent();

        imgUploadViewModel.getData().observe(this, images -> {
            imgUploadViewModel.images.setValue(images);
            Glide.with(this).load(imgUploadViewModel.uri.getValue()).into(binding.placeImageview);
            imgUploadViewModel.file.getValue().delete();
        });

        clickEvent();
    }

    private void initViewModel() {
        studyViewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(StudyViewModel.class);
        imgUploadViewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(ImgUploadViewModel.class);
    }

    private void initIntent() {
        Intent intent = getIntent();
        studyViewModel.locationIdx = intent.getIntExtra("locationIdx",0);
    }

    private void clickEvent() {

        binding.placeImageview.setOnClickListener(v -> goToAlbum());

        binding.writeBtn.setOnClickListener(v -> {
            studyViewModel.postCreateStudy(new StudyRequest(
                    binding.titleText.getText().toString(),
                    binding.descriptionText.getText().toString(),
                    binding.startTermText.getText().toString(),
                    binding.endTermText.getText().toString(),
                    binding.startTimeText.getText().toString(),
                    binding.endTimeText.getText().toString(),
                    Integer.parseInt(binding.personnelText.getText().toString()),
                    studyViewModel.locationIdx,
                    imgUploadViewModel.images.getValue().getImages()
            ));
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

                    imgUploadViewModel.studyImgUpload(imgUploadRequest);
                    Glide.with(this).load(imgUploadViewModel.uri.getValue()).into(binding.placeImageview);
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
