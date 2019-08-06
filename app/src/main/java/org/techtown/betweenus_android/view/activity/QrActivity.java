package org.techtown.betweenus_android.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.base.BaseActivity;
import org.techtown.betweenus_android.databinding.QrActivityBinding;
import org.techtown.betweenus_android.manager.QR;
import org.techtown.betweenus_android.model.Study;
import org.techtown.betweenus_android.widget.recyclerview.viewholder.StudyListViewHolder;

public class QrActivity extends BaseActivity<QrActivityBinding> {

    private Study study;

    @Override
    protected int layoutId() {
        return R.layout.qr_activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initIntent();
        initText();
        setQR(study.getIdx());

        binding.back.setOnClickListener(v -> finish());
    }

    private void initIntent() {
        Intent intent = getIntent();
        study = (Study) intent.getSerializableExtra("study");
    }

    private void initText() {
        binding.title.setText(study.getTitle());
        binding.time.setText(study.getStartTime().split(":")[0]+":"+study.getStartTime().split(":")[1] + " ~ " + study.getEndTime().split(":")[0]+":"+study.getEndTime().split(":")[1]);
        binding.location.setText(study.getLocation());
    }

    private void setQR(Integer idx) {

        String url = new QR(this,"betweenUs.db",null,2).getResult(idx);

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try{
            BitMatrix bitMatrix = multiFormatWriter.encode(url, BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            Glide.with(this).load(bitmap).into(binding.qr);
        }catch (Exception e){}
    }
}
