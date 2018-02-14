package com.news.tinkoff.tinkoff.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.news.tinkoff.tinkoff.models.ConcreteNewsItem;
import com.news.tinkoff.tinkoff.models.ConcreteNewsResponce;
import com.news.tinkoff.tinkoff.models.RepoHolder;
import com.news.tinkoff.tinkoff.models.Repository;
import com.news.tinkoff.tinkoff.views.ConcreteNewsView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class ConcreteNewsPresenter extends MvpPresenter<ConcreteNewsView> {
    private Repository mRepo = RepoHolder.instance().repo();

    public void getConcreteNews(String id) {
        mRepo.getConcreteNews(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new DisposableObserver<ConcreteNewsResponce>() {
                @Override
                public void onNext(ConcreteNewsResponce responce) {
                    if (!responce.isGood()) {
                        getViewState().failed();
                        return;
                    }
                    ConcreteNewsItem payload = responce.getPayload();
                    if (payload == null)
                        getViewState().failed();
                    else
                        getViewState().newsReady(payload);
                }

                @Override
                public void onError(Throwable e) {
                    getViewState().failed();
                }

                @Override
                public void onComplete() {
                }
            });
    }
}
