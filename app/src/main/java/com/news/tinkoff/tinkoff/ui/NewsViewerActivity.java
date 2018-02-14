package com.news.tinkoff.tinkoff.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.news.tinkoff.tinkoff.R;
import com.news.tinkoff.tinkoff.models.ConcreteNewsItem;
import com.news.tinkoff.tinkoff.presenters.ConcreteNewsPresenter;
import com.news.tinkoff.tinkoff.views.ConcreteNewsView;

public class NewsViewerActivity extends MvpAppCompatActivity implements ConcreteNewsView {
    static public final String CONCRETE_NEWS_ID = "CONCRETE_NEWS_ID";

    @InjectPresenter
    ConcreteNewsPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_viewer);

        Intent intent = getIntent();
        if (intent != null && savedInstanceState == null) {
            String newsId = intent.getStringExtra(CONCRETE_NEWS_ID);
            if (newsId != null)
                mPresenter.getConcreteNews(newsId);
            else
                failed();
        }
    }

    @Override
    public void failed() {
        Toast.makeText(this, R.string.failed_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void newsReady(ConcreteNewsItem news) {
        TextView textView = findViewById(R.id.textView);
        textView.setText(Html.fromHtml(news.getContent()));
    }
}
