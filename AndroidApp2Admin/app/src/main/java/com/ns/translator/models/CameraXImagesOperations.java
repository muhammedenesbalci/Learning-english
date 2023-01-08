package com.ns.translator.models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import androidx.camera.core.ImageProxy;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.nio.ByteBuffer;

public class CameraXImagesOperations extends ViewModel {
    //IMAGE OPERATIONS------------------------------------------------------------------------------
    private MutableLiveData<Bitmap> bitmapImage = new MutableLiveData<>();

    private ImageProxy imageProxy;

    public void setImageProxy(ImageProxy imageProxy) {
        this.imageProxy = imageProxy;
    }

    public LiveData<Bitmap> getBitmapImage() {
        imageProxyToBitmap(imageProxy);
        return bitmapImage;
    }

    public Bitmap scaleBitmap(Bitmap bitmap) {
        int height = Resources.getSystem().getDisplayMetrics().heightPixels/2;
        int widtht = Resources.getSystem().getDisplayMetrics().widthPixels;

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, widtht, height, true);

        return scaledBitmap;
    }

    private void imageProxyToBitmap(ImageProxy imageProxy) {
        Image image = imageProxy.getImage();

        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        Bitmap myBitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length,null);

        bitmapImage.setValue(myBitmap);
    }

    //WORD OPERATIONS-------------------------------------------------------------------------------
    private MutableLiveData<String[]> parsedWords = new MutableLiveData<>();

    private String text;

    public LiveData<String[]> getParsedWords() {
        parseWords(text);
        return parsedWords;
    }

    private void parseWords(String text) {
        System.out.println("XXXXXXX text = " + text);

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
