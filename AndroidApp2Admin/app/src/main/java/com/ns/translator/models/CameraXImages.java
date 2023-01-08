package com.ns.translator.models;

import android.graphics.Bitmap;

import androidx.camera.core.ImageProxy;

import java.util.ArrayList;
import java.util.List;

public class CameraXImages {
    private static CameraXImages cameraXImages;

    private CameraXImages() {}

    public static CameraXImages getInstance() {
        if (cameraXImages == null) {
            cameraXImages = new CameraXImages();
        }

        return cameraXImages;
    }

    //IMAGE INFOS-----------------------------------------------------------------------------------
    private ImageProxy imageProxy;
    private Bitmap bitmapImage;

    public Bitmap getBitmapImage() {
        return bitmapImage;
    }

    public void setBitmapImage(Bitmap bitmapImage) {
        this.bitmapImage = bitmapImage;
    }

    public ImageProxy getImageProxy() {
        return imageProxy;
    }

    public void setImageProxy(ImageProxy imageProxy) {
        this.imageProxy = imageProxy;
    }

    //TEXT INFOS
    private String textEnglish;
    private String textTurkish;

    public String getTextTurkish() {
        return textTurkish;
    }

    public void setTextTurkish(String textTurkish) {
        this.textTurkish = textTurkish;
    }

    public String getTextEnglish() {
        return textEnglish;
    }

    public void setTextEnglish(String text) {
        this.textEnglish = text;
    }

    //WORD INFOS
    private List<String> listEnglish = new ArrayList<>();
    private List<String> listTurkish = new ArrayList<>();

    public List<String> getListEnglish() {
        return listEnglish;
    }

    public void setListEnglish(List<String> listEnglish) {
        this.listEnglish = listEnglish;
    }

    public List<String> getListTurkish() {
        return listTurkish;
    }

    public void setListTurkish(List<String> listTurkish) {
        this.listTurkish = listTurkish;
    }
}
