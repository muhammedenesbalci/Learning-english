package com.ns.translator.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.ImageProxy;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.ns.translator.R;
import com.ns.translator.models.CameraXImages;
import com.ns.translator.models.CameraXImagesOperations;
import com.ns.translator.models.GalleryImages;
import com.canhub.cropper.CropImageView;

public class CropImageActivity extends AppCompatActivity {
    CropImageView cropImageView;

    GalleryImages galleryImages;
    CameraXImages cameraXImages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_image);

        cropImageView = findViewById(R.id.cropImageView);
        galleryImages = GalleryImages.getInstance();
        cameraXImages = CameraXImages.getInstance();

        setImageView();

    }
    public void setImageView() {
        CameraXImages cameraXImages = CameraXImages.getInstance();
        ImageProxy imageProxy = cameraXImages.getImageProxy();

        CameraXImagesOperations cameraXImagesOperations = new ViewModelProvider(CropImageActivity.this).get(CameraXImagesOperations.class);
        cameraXImagesOperations.setImageProxy(imageProxy);

        cameraXImagesOperations.getBitmapImage().observe(CropImageActivity.this, new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                cropImageView.setImageBitmap(bitmap);
            }
        });
    }

    public void cropImage(View view) {
        Bitmap bm = cropImageView.getCroppedImage();
        cameraXImages.setBitmapImage(bm);
        intentToResult();
    }

    public void rotateImage(View view) {
        cropImageView.rotateImage(90);
    }

    public void intentToResult() {
        Intent intent = new Intent(CropImageActivity.this, ResultActivity.class);
        startActivity(intent);
        finish();
    }
}