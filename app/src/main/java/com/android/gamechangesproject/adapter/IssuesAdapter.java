package com.android.gamechangesproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.android.gamechangesproject.adapter.ItemEventListener;
import com.android.gamechangesproject.R;
import com.android.gamechangesproject.databinding.IssueListItemBinding;
import com.android.gamechangesproject.model.IssuesResponse;
import com.android.gamechangesproject.view.DetailActivity;
import com.android.gamechangesproject.view.MainActivity;
import java.util.ArrayList;
import java.util.List;

public class IssuesAdapter extends RecyclerView.Adapter<IssuesAdapter.IssueViewHolder> implements ItemEventListener {

    private static final String TAG = "IssuesAdapter";
    private List<IssuesResponse> issuesResponse;
    private Context context;

    public IssuesAdapter(List<IssuesResponse> issuesResponses,MainActivity context) {
       this.issuesResponse=issuesResponses;
       this.context=context;
    }

    @NonNull
    @Override
    public IssueViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        IssueListItemBinding issuesResponseListItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.issue_list_item, viewGroup, false);
        return new IssueViewHolder(issuesResponseListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull IssueViewHolder issuesViewHolder, int i) {
        IssuesResponse issuesRes = issuesResponse.get(i);
        issuesViewHolder.issuesResponseListItemBinding.setIssueResponse(issuesRes);
        issuesViewHolder.issuesResponseListItemBinding.setItemClickListener(this);

    }

    @Override
    public int getItemCount() {
        if (issuesResponse != null) {
            return issuesResponse.size();
        } else {
            return 0;
        }
    }

    @Override
    public void onClickItem(IssuesResponse i) {
        Log.e(TAG,"On Click Item:"+i);
        Intent intent=new Intent(context, DetailActivity.class);
        intent.putExtra("number",i.getNumber());
        context.startActivity(intent);
    }

    class IssueViewHolder extends RecyclerView.ViewHolder {
        private IssueListItemBinding issuesResponseListItemBinding;

        public IssueViewHolder(@NonNull IssueListItemBinding issuesResponseListItemBinding) {
            super(issuesResponseListItemBinding.getRoot());
            this.issuesResponseListItemBinding = issuesResponseListItemBinding;
        }
    }
}