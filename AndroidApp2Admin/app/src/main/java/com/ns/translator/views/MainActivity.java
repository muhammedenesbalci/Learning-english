package com.ns.translator.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;
import com.ns.translator.R;
import com.ns.translator.viewModels.Translate.EnglishToTurkish;
import com.ns.translator.models.GalleryImages;
import com.ns.translator.models.Languages;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentToLogin();
        control();
    }

    public void takeAPicture(View view) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 200);
        } else {
            intentToCameraX();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 200) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Camera permission granted", Toast.LENGTH_LONG).show();
                intentToCameraX();
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            if (data != null) {
                GalleryImages galleryImages = GalleryImages.getInstance();
                Uri uri = data.getData();
                Bitmap selectedImage;

                try {
                    if (Build.VERSION.SDK_INT >= 28) {
                        ImageDecoder.Source source = ImageDecoder.createSource(MainActivity.this.getContentResolver(), uri);
                        selectedImage = ImageDecoder.decodeBitmap(source).copy(Bitmap.Config.ARGB_8888, true);

                        galleryImages.setImageBitmap(selectedImage);

                        intentToCrop("gallery");
                    } else {
                        selectedImage = MediaStore.Images.Media.getBitmap(MainActivity.this.getContentResolver(), uri);

                        galleryImages.setImageBitmap(selectedImage);

                        intentToCrop("gallery");
                    }
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    public void intentToCameraX() {
        Intent intent = new Intent(MainActivity.this, CameraXActivity.class);
        startActivity(intent);
    }

    public void intentToCrop(String info) {
        Intent intent = new Intent(MainActivity.this, CropImageActivity.class);
        startActivity(intent);
    }

    public void intentToLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void control() {
        Languages languages = Languages.getInstance();

        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("DATA", MODE_PRIVATE);
        String lastLanguages = sharedPreferences.getString("lastLanguage","");

        if (lastLanguages.matches("")) {
            Toast.makeText(MainActivity.this, "Firstly, you have to download at least one language", Toast.LENGTH_SHORT).show();

        } else {
            String[] lastLanguagesList = lastLanguages.split(",");
            languages.setSelectedFirstLanguage(lastLanguagesList[0]);
            languages.setSelectedSecondLanguage(lastLanguagesList[1]);

            EnglishToTurkish englishToTurkish = new ViewModelProvider(MainActivity.this).get(EnglishToTurkish.class);

            englishToTurkish.getStatus().observe(MainActivity.this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    Toast.makeText(MainActivity.this, "Succes", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}