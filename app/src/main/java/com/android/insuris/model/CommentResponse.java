package com.android.insuris.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentResponse
{
    @SerializedName("node_id")
    @Expose
    private String node_id;

    @SerializedName("body")
    @Expose
    private String body;

    @SerializedName("id")
    @Expose
    private Integer id;

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
