package com.ns.translator.adapters.RecyclerViewAdapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ns.translator.databinding.RecyclerViewRawsBinding;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    List<String> english;
    List<String> turkish;
    Context context;

    public RecyclerViewAdapter(List<String> english,List<String> turkish, Context context) {
        this.english = english;
        this.turkish = turkish;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerViewRawsBinding recyclerViewRawsBinding = RecyclerViewRawsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new RecyclerViewHolder(recyclerViewRawsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.recyclerViewRawsBinding.englishTextView.setText(english.get(position));
        holder.recyclerViewRawsBinding.turkishTextView.setText(turkish.get(position));

//        System.out.println("XXXXX TUR TEXT : " + turkish.get(position));
//        System.out.println("XXXXX ENG TEXT : " + english.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.recyclerViewRawsBinding.turkishTextView.setTextColor(Color.parseColor("#123abc"));
                holder.recyclerViewRawsBinding.englishTextView.setTextColor(Color.parseColor("#123abc"));

                System.out.println("ADDING TO API : " + turkish.get(position) + " => " + english.get(position));
                Toast.makeText(context, "ADDING TO API : : " + turkish.get(position) + " => " + english.get(position), Toast.LENGTH_LONG).show();

                // BURAYA API ENTEGRE EDİLECEK
            }
        });
    }

    @Override
    public int getItemCount() {
        return english.size();
    }
}
