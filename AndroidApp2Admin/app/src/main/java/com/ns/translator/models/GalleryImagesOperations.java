package com.ns.translator.models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalleryImagesOperations extends ViewModel {

    //IMAGE OPERATOINS------------------------------------------------------------------------------
    public Bitmap scaleImage(Bitmap bitmap) {
        int height = Resources.getSystem().getDisplayMetrics().heightPixels/2;
        int widtht = Resources.getSystem().getDisplayMetrics().widthPixels;

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, widtht, height, true);


        return scaledBitmap;
    }

    //WORD OPERATIONS-------------------------------------------------------------------------------
    private MutableLiveData<String[]> parsedWords = new MutableLiveData<>();

    private String text;

    public LiveData<String[]> getParsedWords() {
        parseWords(text);
        return parsedWords;
    }

    private void parseWords(String text) {
        text = text.replace("\n"," ");
        text = text.replace(".","");
        text = text.replace("?","");
        text = text.replace("!","");
        text = text.replace(",","");

        String[] stringArray = text.split(" ");
        parsedWords.setValue(stringArray);
    }

    public void setText(String text) {
        this.text = text;
    }
}
