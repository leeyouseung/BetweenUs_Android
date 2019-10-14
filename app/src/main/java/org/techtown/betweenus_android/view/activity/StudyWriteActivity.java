package org.techtown.betweenus_android.view.activity;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.base.BaseActivity;
import org.techtown.betweenus_android.databinding.StudyWritingActivityBinding;
import org.techtown.betweenus_android.manager.ViewModelFactory;
import org.techtown.betweenus_android.model.Location;
import org.techtown.betweenus_android.network.client.StudyClient;
import org.techtown.betweenus_android.network.request.ImgUploadRequest;
import org.techtown.betweenus_android.network.request.StudyRequest;
import org.techtown.betweenus_android.viewmodel.ImgUploadViewModel;
import org.techtown.betweenus_android.viewmodel.PlaceViewModel;
import org.techtown.betweenus_android.viewmodel.StudyViewModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class StudyWriteActivity extends BaseActivity<StudyWritingActivityBinding> {

    private StudyViewModel studyViewModel;
    private ImgUploadViewModel imgUploadViewModel;
    private PlaceViewModel placeViewModel;

    private List<Location> locations = new ArrayList<>();

    private String startTerm;
    private String endTerm;
    private String startTime;
    private String endTime;

    private final Calendar cal = Calendar.getInstance();
    private DatePickerDialog startTermDialog;
    private DatePickerDialog endTermDialog;
    private TimePickerDialog startTimeDialog;
    private TimePickerDialog endTimeDialog;

    @Override
    protected int layoutId() {
        return R.layout.study_writing_activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewModel();
        initTextView();
        ArrayAdapter personnelAdapter = ArrayAdapter.createFromResource(this,R.array.peronnel,R.layout.place_item);
        binding.personnelSpinner.setAdapter(personnelAdapter);
        dialog();

        imgUploadViewModel.getData().observe(this, images -> imgUploadViewModel.images.setValue(images));

        placeViewModel.getLocation();

        placeViewModel.getData().observe(this, locations -> {
            this.locations = locations;
            List<String> places = new ArrayList<>();

            places.add("스터디 장소");

            for (Location location : locations) {
                if (location.getStatus() == 0) {
                    places.add(location.getPlace());
                }
            }

            ArrayAdapter placeAdapter = new ArrayAdapter(this,R.layout.place_item,places);

            placeAdapter.setDropDownViewResource(R.layout.place_item_dropdown);

            binding.placeSpinner.setAdapter(placeAdapter);
        });

        studyViewModel.getSuccessMessage().observe(this, message -> {
            Toast.makeText(this, "스터디를 등록했습니다", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity.class));
        });

        clickEvent();
    }

    private void initViewModel() {
        studyViewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(StudyViewModel.class);
        imgUploadViewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(ImgUploadViewModel.class);
        placeViewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(PlaceViewModel.class);
    }

    private void initTextView() {
        binding.startTermText.setSelected(true);
        binding.endTermText.setSelected(true);
        binding.startTimeText.setSelected(true);
        binding.endTimeText.setSelected(true);
    }

    private void clickEvent() {

        binding.startTermText.setOnClickListener(v -> startTermDialog.show());

        binding.endTermText.setOnClickListener(v -> endTermDialog.show());

        binding.startTimeText.setOnClickListener(v -> startTimeDialog.show());

        binding.endTimeText.setOnClickListener(v -> endTimeDialog.show());

        binding.placeImageview.setOnClickListener(v -> goToAlbum());

        binding.writeBtn.setOnClickListener(v -> {
            Integer placeIdx = 0;
            for (Location location: locations) {
                if (location.getPlace().equals(binding.placeSpinner.getSelectedItem().toString())) {
                    placeIdx = location.getIdx();
                }
            }

            studyViewModel.postCreateStudy(new StudyRequest(
                    binding.titleText.getText().toString(),
                    binding.descriptionText.getText().toString(),
                    startTerm, endTerm,
                    startTime, endTime,
                    Integer.parseInt(binding.personnelSpinner.getSelectedItem().toString()),
                    placeIdx,imgUploadViewModel.images.getValue()
            ));
        });

        binding.back.setOnClickListener(v -> finish());
    }

    private void dialog() {
        startTermDialog = new DatePickerDialog(StudyWriteActivity.this, (datePicker, year, month, date) -> {
            startTerm = String.format("%04d-%02d-%02d 00:00:00", year, month+1, date);
            binding.startTermText.setText(String.format("%04d-%02d-%02d", year, month+1, date));
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        startTermDialog.getDatePicker().setMinDate(new Date().getTime());

        endTermDialog = new DatePickerDialog(StudyWriteActivity.this, (datePicker, year, month, date) -> {
            endTerm = String.format("%04d-%02d-%02d 00:00:00", year, month+1, date);
            binding.endTermText.setText(String.format("%04d-%02d-%02d", year, month+1, date));
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        endTermDialog.getDatePicker().setMinDate(new Date().getTime());

        startTimeDialog = new TimePickerDialog(StudyWriteActivity.this, (timePicker, hour, min) -> {
            startTime = String.format("%02d:%02d:00", hour, min);
            binding.startTimeText.setText(String.format("%02d:%02d", hour, min));
        }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);

        endTimeDialog = new TimePickerDialog(StudyWriteActivity.this, (timePicker, hour, min) -> {
            endTime = String.format("%02d:%02d:00", hour, min);
            binding.endTimeText.setText(String.format("%02d:%02d", hour, min));
        }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);
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
                    MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("img", imgUploadViewModel.file.getValue().getName(), mFile);

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

        imgUploadViewModel.file.setValue(new File(Environment.getExternalStorageDirectory().toString() + "/BetweenUs/temp" + new Random().nextInt(100000000) + ".jpg"));

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
