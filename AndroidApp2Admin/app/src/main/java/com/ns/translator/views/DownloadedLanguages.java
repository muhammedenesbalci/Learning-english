package com.ns.translator.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ns.translator.R;
import com.ns.translator.adapters.RecyclerViewAdapters.RecyclerViewAdapterLanguages;
import com.ns.translator.viewModels.Translate.EnglishToTurkish;
import com.ns.translator.models.Languages;

import java.util.Arrays;
import java.util.List;

public class DownloadedLanguages extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView firsToSecondTextView;

    Languages languages;

    ConstraintLayout errorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloaded_languages);

        recyclerView = findViewById(R.id.recyclerView2);
        firsToSecondTextView = findViewById(R.id.firstToSecondTextView);
        errorLayout = findViewById(R.id.errorLayout);

        languages = Languages.getInstance();

        String downloadedLanguages = languages.getDownloadedLanguages(DownloadedLanguages.this);

        if (downloadedLanguages.matches("")){
            errorLayout.setVisibility(View.VISIBLE);
        } else {
            errorLayout.setVisibility(View.INVISIBLE);

            String[] list = downloadedLanguages.split(",");
            List<String> arrayList = Arrays.asList(list);

            recyclerView.setLayoutManager(new LinearLayoutManager(DownloadedLanguages.this));
            RecyclerViewAdapterLanguages recyclerViewAdapterLanguages = new RecyclerViewAdapterLanguages(arrayList, new RecyclerViewAdapterLanguages.ItemClickListener() {
                @Override
                public void onItemClick(String string) {
                    firsToSecondTextView.setText(string);

                    String[] s = string.split("-");
                    languages.setSelectedFirstLanguage(s[0]);
                    languages.setSelectedSecondLanguage(s[1]);
                    select();
                }
            });
            recyclerView.setAdapter(recyclerViewAdapterLanguages);
        }
    }

    public void select() {
        SharedPreferences sharedPreferences = DownloadedLanguages.this.getSharedPreferences("DATA", MODE_PRIVATE);
        sharedPreferences.edit().putString("lastLanguage", languages.getSelectedFirstLanguage() + "," + languages.getSelectedSecondLanguage()).apply();

        EnglishToTurkish englishToTurkish = new ViewModelProvider(DownloadedLanguages.this).get(EnglishToTurkish.class);
        englishToTurkish.getStatus().observe(DownloadedLanguages.this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Toast.makeText(DownloadedLanguages.this,languages.getSelectedFirstLanguage()+" >" + languages.getSelectedSecondLanguage(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(DownloadedLanguages.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}