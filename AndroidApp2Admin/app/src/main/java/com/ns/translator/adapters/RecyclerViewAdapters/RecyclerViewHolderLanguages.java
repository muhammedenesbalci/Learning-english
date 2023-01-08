package com.ns.translator.adapters.RecyclerViewAdapters;

import androidx.recyclerview.widget.RecyclerView;
import com.ns.translator.databinding.RecyclerLanguagesRawsBinding;

public class RecyclerViewHolderLanguages extends RecyclerView.ViewHolder {
    RecyclerLanguagesRawsBinding recyclerLanguagesRawsBinding;

    public RecyclerViewHolderLanguages(RecyclerLanguagesRawsBinding recyclerLanguagesRawsBinding) {
        super(recyclerLanguagesRawsBinding.getRoot());
        this.recyclerLanguagesRawsBinding = recyclerLanguagesRawsBinding;
    }
}
