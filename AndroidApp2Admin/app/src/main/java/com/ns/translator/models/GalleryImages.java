package com.ns.translator.models;

import android.graphics.Bitmap;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class GalleryImages {
    private static GalleryImages galleryImages;

    private GalleryImages() {
        //Singelton class have to have a constreuctor
    }

    public static GalleryImages getInstance() {
        if(galleryImages == null) {
            galleryImages = new GalleryImages();
        }

        return galleryImages;
    }

    //IMAGE INFOS-----------------------------------------------------------------------------------
    private Bitmap imageBitmap;
    private Uri uri;

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }


    //TEXT INFOS------------------------------------------------------------------------------------
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

    //WORD INFOS------------------------------------------------------------------------------------
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
