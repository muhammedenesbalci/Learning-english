package com.ns.translator.adapters.RecyclerViewAdapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ns.translator.databinding.RecyclerLanguagesRawsBinding;
import java.util.List;

public class RecyclerViewAdapterLanguages extends RecyclerView.Adapter<RecyclerViewHolderLanguages> {
    private List<String> languages;
    private ItemClickListener mItemListListener;


    public RecyclerViewAdapterLanguages(List<String> languages, ItemClickListener mItemListListener) {
        this.languages = languages;
        this.mItemListListener = mItemListListener;
    }

    @NonNull
    @Override
    public RecyclerViewHolderLanguages onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerLanguagesRawsBinding recyclerLanguagesRawsBinding = RecyclerLanguagesRawsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecyclerViewHolderLanguages(recyclerLanguagesRawsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolderLanguages holder, int position) {
        holder.recyclerLanguagesRawsBinding.languageRaw.setText(languages.get(position));

        holder.itemView.setOnClickListener(view ->{
            mItemListListener.onItemClick(languages.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return languages.size();
    }

    public interface ItemClickListener{
        void onItemClick(String string);
    }
}
