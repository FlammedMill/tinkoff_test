package com.news.tinkoff.tinkoff.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class NewsInfo extends RealmObject {
    @PrimaryKey
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("text")
    private String text;

    @SerializedName("publicationDate")
    private InnerDate publicationDate;

    @SerializedName("bankInfoTypeId")
    private long bankInfoTypeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public InnerDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(InnerDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public long getBankInfoTypeId() {
        return bankInfoTypeId;
    }

    public void setBankInfoTypeId(long bankInfoTypeId) {
        this.bankInfoTypeId = bankInfoTypeId;
    }
}
