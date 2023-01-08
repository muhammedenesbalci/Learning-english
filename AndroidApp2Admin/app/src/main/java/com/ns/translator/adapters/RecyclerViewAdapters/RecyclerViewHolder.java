package com.ns.translator.adapters.RecyclerViewAdapters;

import androidx.recyclerview.widget.RecyclerView;
import com.ns.translator.databinding.RecyclerViewRawsBinding;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    RecyclerViewRawsBinding recyclerViewRawsBinding;

    public RecyclerViewHolder(RecyclerViewRawsBinding recyclerViewRawsBinding) {
        super(recyclerViewRawsBinding.getRoot());
        this.recyclerViewRawsBinding = recyclerViewRawsBinding;
    }
}
