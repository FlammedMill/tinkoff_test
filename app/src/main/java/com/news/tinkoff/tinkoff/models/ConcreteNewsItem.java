package com.news.tinkoff.tinkoff.models;

import com.google.gson.annotations.SerializedName;

public class ConcreteNewsItem {
    @SerializedName("title")
    private NewsInfo mNewsInfo;

    @SerializedName("content")
    private String mContent;

    @SerializedName("lastModificationDate")
    private InnerDate mLastModificationDate;

    @SerializedName("creationDate")
    private InnerDate mCreationDate;

    @SerializedName("bankInfoTypeId")
    private int mBankInfoTypeId;

    @SerializedName("typeId")
    private String mTypeId;

    public NewsInfo getNewsInfo() {
        return mNewsInfo;
    }

    public String getContent() {
        return mContent;
    }

    public InnerDate getLastModificationDate() {
        return mLastModificationDate;
    }

    public InnerDate getCreationDate() {
        return mCreationDate;
    }

    public int getBankInfoTypeId() {
        return mBankInfoTypeId;
    }

    public String getTypeId() {
        return mTypeId;
    }
}
