package com.android.insuris.model;

import com.google.gson.annotations.SerializedName;

public class RatingData {

    @SerializedName("value")
    Float value;

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
