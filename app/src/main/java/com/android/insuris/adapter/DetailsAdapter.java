package com.android.insuris.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.android.insuris.R;
import com.android.insuris.databinding.GenericListItemBinding;
import com.android.insuris.model.GenresItem;
import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder> {

    private List<GenresItem> detailsResponse;

    public DetailsAdapter(List<GenresItem> detailsResponse) {
        this.detailsResponse=detailsResponse;
    }

    @NonNull
    @Override
    public DetailsAdapter.DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        GenericListItemBinding genericResponseListItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.generic_list_item, viewGroup, false);
        return new DetailsViewHolder(genericResponseListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsAdapter.DetailsViewHolder issuesViewHolder, int i) {
        GenresItem genericResponse = detailsResponse.get(i);
        issuesViewHolder.genericResponseListItemBinding.setGenericResponse(genericResponse);
    }

    @Override
    public int getItemCount() {
        if (detailsResponse != null) {
            return detailsResponse.size();
        } else {
            return 0;
        }
    }

    static class DetailsViewHolder extends RecyclerView.ViewHolder {
        private GenericListItemBinding genericResponseListItemBinding;

        public DetailsViewHolder(@NonNull GenericListItemBinding genericResponseListItemBinding) {
            super(genericResponseListItemBinding.getRoot());
            this.genericResponseListItemBinding = genericResponseListItemBinding;
        }
    }
}