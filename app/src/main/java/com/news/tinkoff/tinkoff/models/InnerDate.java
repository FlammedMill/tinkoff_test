package com.news.tinkoff.tinkoff.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class InnerDate extends RealmObject {

    @SerializedName("milliseconds")
    private long milliseconds;

    public void setMilliseconds(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public long getMilliseconds() {
        return this.milliseconds;
    }
}
