package com.ns.translator.viewModels.Translate;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;
import com.ns.translator.models.Languages;

import java.util.ArrayList;
import java.util.List;

public class EnglishToTurkish extends ViewModel {
    private MutableLiveData<Boolean> status = new MutableLiveData<>();
    private Translator translateLanguage;

    public LiveData<Boolean> getStatus() {
        translatorDownloader();
        return status;
    }

    private void translatorConstructor() {
        Languages languages = Languages.getInstance();

        String firstLanguage = languages.getLanguageSymbolFirst();
        String secondLanguage = languages.getLanguageSymbolSecond();

        TranslatorOptions options =
                new TranslatorOptions.Builder()
                        .setSourceLanguage(firstLanguage)
                        .setTargetLanguage(secondLanguage)
                        .build();
        translateLanguage = Translation.getClient(options);
    }

    private void translatorDownloader() {
        translatorConstructor();

        DownloadConditions conditions = new DownloadConditions.Builder().build();

        translateLanguage.downloadModelIfNeeded(conditions).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                status.setValue(true);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                status.setValue(false);
            }
        });
    }

    //TEXT
    private MutableLiveData<String> turkishText = new MutableLiveData<>();
    private String englishText;

    public void setEnglishText(String englishText) {
        this.englishText = englishText;
    }

    public LiveData<String> getTurkishText() {
        translateText(englishText);
        return turkishText;
    }

    private void translateText(String englishString) {
        translatorConstructor();

        translateLanguage.translate(englishString).addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String s) {
                turkishText.setValue(s);
                System.out.println(s);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("error");

            }
        });
    }

    //WORDS
    private MutableLiveData<List<String>> turkishList = new MutableLiveData<>();
    private List<String> englishList;
    private List<String> turkishListInClass = new ArrayList<>();

    public void setEnglishList(List<String> englishList) {
        this.englishList = englishList;
    }

    public LiveData<List<String>> getTurkishList() {
        translateWords(englishList);
        return turkishList;
    }

    private void translateWords(List<String> englishList) {
        translatorConstructor();
        final int[] x = {0};

        for (String i : englishList){
            translateLanguage.translate(i).addOnSuccessListener(new OnSuccessListener<String>() {
                @Override
                public void onSuccess(String s) {
                    turkishListInClass.add(s);

                    x[0]++;

                    if (x[0] == englishList.size()) {
                        turkishList.setValue(turkishListInClass);
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    System.out.println("error");
                }
            });
        }
    }
}
