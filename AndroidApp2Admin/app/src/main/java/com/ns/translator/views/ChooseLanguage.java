package com.ns.translator.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.ns.translator.R;
import com.ns.translator.adapters.RecyclerViewAdapters.RecyclerViewAdapterLanguages;
import com.ns.translator.viewModels.Translate.EnglishToTurkish;
import com.ns.translator.models.Languages;
import java.util.List;

public class ChooseLanguage extends AppCompatActivity {
    RecyclerView recyclerViewFirst;
    RecyclerView recyclerViewSecond;

    TextView firstText;
    TextView secondText;

    Languages languages;
    List<String> languagesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_language);

        languages = Languages.getInstance();
        languagesList = languages.getLanguageList();

        firstText = findViewById(R.id.firstTextView);
        secondText = findViewById(R.id.secondTextView);

        firstRecycler();
        secondRecycler();
    }

    public void firstRecycler() {
        recyclerViewFirst = findViewById(R.id.firstRecyclerView);
        recyclerViewFirst.setLayoutManager(new LinearLayoutManager(ChooseLanguage.this));
        RecyclerViewAdapterLanguages recyclerViewAdapterLanguages = new RecyclerViewAdapterLanguages(languagesList, new RecyclerViewAdapterLanguages.ItemClickListener() {
            @Override
            public void onItemClick(String string) {
                languages.setSelectedFirstLanguage(string);

                firstText.setText(string);
            }
        });
        recyclerViewFirst.setAdapter(recyclerViewAdapterLanguages);
    }

    public void secondRecycler() {
        recyclerViewSecond = findViewById(R.id.secondRecyclerView);
        recyclerViewSecond.setLayoutManager(new LinearLayoutManager(ChooseLanguage.this));
        RecyclerViewAdapterLanguages recyclerViewAdapterLanguages = new RecyclerViewAdapterLanguages(languagesList, new RecyclerViewAdapterLanguages.ItemClickListener() {
            @Override
            public void onItemClick(String string) {
                languages.setSelectedSecondLanguage(string);

                secondText.setText(string);
            }
        });
        recyclerViewSecond.setAdapter(recyclerViewAdapterLanguages);
    }

    public void download(View view) {
        SharedPreferences sharedPreferences = ChooseLanguage.this.getSharedPreferences("DATA", MODE_PRIVATE);
        sharedPreferences.edit().putString("lastLanguage", languages.getSelectedFirstLanguage() + "," + languages.getSelectedSecondLanguage()).apply();

        if (firstText.getText().toString().matches("") || secondText.getText().toString().matches("")) {
            Toast.makeText(ChooseLanguage.this, "Fields are empty", Toast.LENGTH_SHORT).show();
        } else {
            EnglishToTurkish englishToTurkish = new ViewModelProvider(ChooseLanguage.this).get(EnglishToTurkish.class);
            englishToTurkish.getStatus().observe(ChooseLanguage.this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    Toast.makeText(ChooseLanguage.this,firstText.getText().toString()+" >" + secondText.getText().toString(), Toast.LENGTH_LONG).show();
                    languages.setDownloadedLanguages(ChooseLanguage.this, languages.getSelectedFirstLanguage(), languages.getSelectedSecondLanguage());
                }
            });
        }
    }
}