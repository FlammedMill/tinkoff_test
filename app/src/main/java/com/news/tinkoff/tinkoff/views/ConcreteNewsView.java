package com.news.tinkoff.tinkoff.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.news.tinkoff.tinkoff.models.ConcreteNewsItem;

public interface ConcreteNewsView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void failed();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void newsReady(ConcreteNewsItem news);
}
