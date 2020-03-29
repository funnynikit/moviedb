package com.android.gamechangesproject.di;

import android.app.Application;

import com.google.gson.Gson;

public class MyApplication extends Application {

    private static ApiComponent apiComponent;
    private static CommentComponent commentComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        apiComponent = DaggerApiComponent.builder()
                .apiModule(new ApiModule())
                .build();

        commentComponent = DaggerCommentComponent.builder()
                .apiModule(new ApiModule())
                .build();

    }

    public static ApiComponent getApiComponent() {
        return apiComponent;
    }

    public static void setApiComponent(ApiComponent apiComponent) {
        MyApplication.apiComponent = apiComponent;
    }

    public static CommentComponent getCommentComponent() {
        return commentComponent;
    }

    public static void setCommentComponent(CommentComponent commentComponent) {
        MyApplication.commentComponent = commentComponent;
    }

}
