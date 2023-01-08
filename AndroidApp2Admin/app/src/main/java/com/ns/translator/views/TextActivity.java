package com.ns.translator.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.ns.translator.R;
import com.ns.translator.viewModels.Translate.EnglishToTurkish;
import com.ns.translator.models.CameraXImages;
import com.ns.translator.models.GalleryImages;

public class TextActivity extends AppCompatActivity {
    TextView englishText;
    TextView turkishText;

    String info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        englishText = findViewById(R.id.englishText);
        turkishText = findViewById(R.id.turkishText);

        translateTexts();

    }
    public void translateTexts() {
        EnglishToTurkish englishToTurkish = new ViewModelProvider(TextActivity.this).get(EnglishToTurkish.class);

        CameraXImages cameraXImages = CameraXImages.getInstance();
        String englishTextFromCameraX = cameraXImages.getTextEnglish();

        englishText.setText(englishTextFromCameraX);
        englishToTurkish.setEnglishText(englishTextFromCameraX);

        englishToTurkish.getTurkishText().observe(TextActivity.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                s = s.replace("\n", " ");
                turkishText.setText(s);
                cameraXImages.setTextTurkish(s);
            }
        });
    }
}