package com.news.tinkoff.tinkoff.presenters;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.news.tinkoff.tinkoff.models.NewsInfo;
import com.news.tinkoff.tinkoff.models.NewsResponce;
import com.news.tinkoff.tinkoff.models.RepoHolder;
import com.news.tinkoff.tinkoff.models.Repository;
import com.news.tinkoff.tinkoff.views.NewsView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

@InjectViewState
public class NewsPresenter extends MvpPresenter<NewsView> {

    private Repository mRepo = RepoHolder.instance().repo();
    private Realm mRealm;

    public NewsPresenter(Context context) {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(context)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        mRealm = Realm.getDefaultInstance();
    }

    public void fetchNews() {
        RealmResults<NewsInfo> realmResults = mRealm.allObjects(NewsInfo.class);
        getViewState().newsReady(mRealm.copyFromRealm(realmResults));
    }

    public void getNews() {
        mRepo.getNews()
                .subscribeOn(Schedulers.io())
                .map(NewsResponce::getPayload)
                .flatMapIterable(items -> items)
                .subscribeOn(Schedulers.computation())
                .toSortedList((newsItem, t1) -> {
                    Long lhs = newsItem.getPublicationDate().getMilliseconds();
                    Long rhs = t1.getPublicationDate().getMilliseconds();
                    return rhs.compareTo(lhs);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<NewsInfo>>() {
                    @Override
                    public void onSuccess(List<NewsInfo> newsItems) {
                        mRealm.beginTransaction();
                        for (NewsInfo ni : newsItems)
                            mRealm.copyToRealmOrUpdate(ni);
                        mRealm.commitTransaction();

                        getViewState().newsReady(newsItems);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().failed();
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}
