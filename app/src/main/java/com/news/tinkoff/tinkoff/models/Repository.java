package com.news.tinkoff.tinkoff.models;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Repository {
    @GET("v1/news")
    Observable<NewsResponce> getNews();

    @GET("v1/news_content?")
    Observable<ConcreteNewsResponce> getConcreteNews(@Query("id") String id);
}
