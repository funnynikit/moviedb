package com.android.gamechangesproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.android.gamechangesproject.model.CommentResponse;
import com.android.gamechangesproject.repository.CommentRepository;

import java.util.List;

public class CommentViewModel extends AndroidViewModel {

    private CommentRepository commentRepository;

    public CommentViewModel(@NonNull Application application) {
        super(application);

        commentRepository=new CommentRepository(application);
    }

    public LiveData<List<CommentResponse>> getAllComments(Integer number)
    {
        return commentRepository.getMutableCommentsLiveData(number);
    }
}
