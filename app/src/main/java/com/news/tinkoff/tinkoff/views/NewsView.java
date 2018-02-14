package com.news.tinkoff.tinkoff.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.news.tinkoff.tinkoff.models.NewsInfo;

import java.util.List;

public interface NewsView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void failed();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void newsReady(List<NewsInfo> news);
}
