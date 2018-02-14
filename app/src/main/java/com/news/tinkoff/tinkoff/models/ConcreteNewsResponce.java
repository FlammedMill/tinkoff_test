package com.news.tinkoff.tinkoff.models;

import com.google.gson.annotations.SerializedName;

public class ConcreteNewsResponce {
    @SerializedName("resultCode")
    private String mResultCode;

    @SerializedName("payload")
    private ConcreteNewsItem mPayload;

    @SerializedName("trackingId")
    private String mTrackingId;

    public boolean isGood() {
        return mResultCode != null && mResultCode.equalsIgnoreCase("ok");
    }

    public ConcreteNewsItem getPayload() {
        return mPayload;
    }

    public String getTrackingId() {
        return mTrackingId;
    }
}
