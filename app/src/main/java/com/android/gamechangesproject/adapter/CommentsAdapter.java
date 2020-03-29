package com.android.gamechangesproject.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.android.gamechangesproject.R;
import com.android.gamechangesproject.databinding.CommentsListItemBinding;
import com.android.gamechangesproject.databinding.IssueListItemBinding;
import com.android.gamechangesproject.model.CommentResponse;
import com.android.gamechangesproject.model.IssuesResponse;
import com.android.gamechangesproject.view.MainActivity;
import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {

    private List<CommentResponse> commentsResponse;

    public CommentsAdapter(List<CommentResponse> commentsResponse) {
        this.commentsResponse=commentsResponse;
    }


    @NonNull
    @Override
    public CommentsAdapter.CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CommentsListItemBinding commentsResponseListItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.comments_list_item, viewGroup, false);
        return new CommentsAdapter.CommentsViewHolder(commentsResponseListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.CommentsViewHolder issuesViewHolder, int i) {
        CommentResponse commentResponse = commentsResponse.get(i);
        issuesViewHolder.commentsResponseListItemBinding.setCommentResponse(commentResponse);
    }

    @Override
    public int getItemCount() {
        if (commentsResponse != null) {
            return commentsResponse.size();
        } else {
            return 0;
        }
    }

    class CommentsViewHolder extends RecyclerView.ViewHolder {
        private CommentsListItemBinding commentsResponseListItemBinding;

        public CommentsViewHolder(@NonNull CommentsListItemBinding commentsResponseListItemBinding) {
            super(commentsResponseListItemBinding.getRoot());
            this.commentsResponseListItemBinding = commentsResponseListItemBinding;
        }
    }
}