package com.android.insuris.di;

import android.app.Application;

public class MyApplication extends Application {

    private static ApiComponent apiComponent;
    private static DetailsComponent commentComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        apiComponent = DaggerApiComponent.builder()
                .apiModule(new ApiModule())
                .build();

        commentComponent = DaggerDetailsComponent.builder()
                .apiModule(new ApiModule())
                .build();

    }

    public static ApiComponent getApiComponent() {
        return apiComponent;
    }

    public static void setApiComponent(ApiComponent apiComponent) {
        MyApplication.apiComponent = apiComponent;
    }

    public static DetailsComponent getCommentComponent() {
        return commentComponent;
    }

    public static void setCommentComponent(DetailsComponent commentComponent) {
        MyApplication.commentComponent = commentComponent;
    }

}
