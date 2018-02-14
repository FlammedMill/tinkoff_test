package com.news.tinkoff.tinkoff.models;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.realm.RealmObject;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepoHolder {
    private final static String BASE_URL = "https://api.tinkoff.ru/";
    private Repository mRepo;

    static private RepoHolder repoHolder;

    private RepoHolder() {
        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        mRepo = retrofit.create(Repository.class);
    }

    static public RepoHolder instance() {
        if (repoHolder == null)
            repoHolder = new RepoHolder();
        return repoHolder;
    }

    public Repository repo() {
        return mRepo;
    }
}
