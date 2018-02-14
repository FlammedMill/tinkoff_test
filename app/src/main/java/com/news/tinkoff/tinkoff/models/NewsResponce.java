package com.news.tinkoff.tinkoff.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponce {
    @SerializedName("resultCode")
    private String mResultCode;

    @SerializedName("payload")
    private List<NewsInfo> mPayload;

    public boolean isGood() {
        return mResultCode != null && mResultCode.equalsIgnoreCase("ok");
    }

    public List<NewsInfo> getPayload() {
        return mPayload;
    }
}
