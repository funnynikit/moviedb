package com.android.gamechangesproject.repository;

import com.android.gamechangesproject.model.CommentResponse;
import com.android.gamechangesproject.model.IssuesResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface IssuesDataService {

    @GET("repos/firebase/firebase-ios-sdk/issues")
    Call<List<IssuesResponse>> getIssuesList();

    @GET("repos/firebase/firebase-ios-sdk/issues/{number}/comments")
    Call<List<CommentResponse>> getCommentsList(@Path(value = "number", encoded = true) int number);

    @GET
    Call<List<CommentResponse>> getCommentsListt(@Url String url);
}
