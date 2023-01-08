package com.ns.translator.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class Languages {
    private static Languages languages;

    private Languages() {
    }

    public static Languages getInstance() {
        if (languages == null) {
            languages = new Languages();
        }

        return languages;
    }

    //LANGUAGE LIST---------------------------------------------------------------------------------
    private List<String> languageList = Arrays.asList("English", "Turkish");

    private List<String> languageListSymbols = Arrays.asList("en", "tr");

    public List<String> getLanguageList() {
        return languageList;
    }

    public List<String> getLanguageListSymbols() {
        return languageListSymbols;
    }

    //SELECTIONS------------------------------------------------------------------------------------
    private String selectedFirstLanguage;
    private String selectedSecondLanguage;

    private String languageSymbolFirst;
    private String languageSymbolSecond;

    public String getLanguageSymbolFirst() {
        return languageSymbolFirst;
    }

    public void setLanguageSymbolFirst(String languageSymbolFirst) {
        this.languageSymbolFirst = languageSymbolFirst;
    }

    public String getLanguageSymbolSecond() {
        return languageSymbolSecond;
    }

    public void setLanguageSymbolSecond(String languageSymbolSecond) {
        this.languageSymbolSecond = languageSymbolSecond;
    }

    public String getSelectedFirstLanguage() {
        return selectedFirstLanguage;
    }

    public void setSelectedFirstLanguage(String selectedFirstLanguage) {
        languages.setLanguageSymbolFirst(getSymbol(selectedFirstLanguage));
        this.selectedFirstLanguage = selectedFirstLanguage;
    }

    public String getSelectedSecondLanguage() {
        return selectedSecondLanguage;
    }

    public void setSelectedSecondLanguage(String selectedSecondLanguage) {
        languages.setLanguageSymbolSecond(getSymbol(selectedSecondLanguage));
        this.selectedSecondLanguage = selectedSecondLanguage;
    }

    //GET SYMBOL FOR TRANSLATOR---------------------------------------------------------------------
    public String getSymbol(String s) {
        int i = languageList.indexOf(s);
        String symbol =languageListSymbols.get(i);

        return symbol;
    }

    //DOWNLOADED LANGUAGES SHARED PREFERENCES
    public void setDownloadedLanguages(Context context, String first, String second) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("DATA", Context.MODE_PRIVATE);

        String downloadedLanguages = getDownloadedLanguages(context);
        String[] list = downloadedLanguages.split(",");

        if(isInclude(list, first, second)) {
            Toast.makeText(context, "This language almost downloaded",Toast.LENGTH_LONG).show();

        } else {
            downloadedLanguages = downloadedLanguages + first + "-" + second +",";
            sharedPreferences.edit().putString("data", downloadedLanguages).apply();
        }

    }

    public String getDownloadedLanguages(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("DATA", Context.MODE_PRIVATE);

        String downloadedLanguages = sharedPreferences.getString("data", "");

        return downloadedLanguages;
    }

    public boolean isInclude(String[] list, String first, String second) {
        for(String i : list) {
            if(i.matches(first + "-" + second)) {
                return true;
            }
        }

        return false;
    }
}
