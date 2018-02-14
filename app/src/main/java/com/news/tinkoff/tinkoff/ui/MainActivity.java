package com.news.tinkoff.tinkoff.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.news.tinkoff.tinkoff.R;
import com.news.tinkoff.tinkoff.models.NewsInfo;
import com.news.tinkoff.tinkoff.presenters.NewsPresenter;
import com.news.tinkoff.tinkoff.views.NewsView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MvpAppCompatActivity implements NewsView {

    @InjectPresenter
    NewsPresenter mPresenter;

    private NewsItemAdapter mAdapter;
    private SwipeRefreshLayout mSwipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new NewsItemAdapter(this, new ArrayList<NewsInfo>());
        ListView newsList = findViewById(R.id.news_list_view);
        newsList.setAdapter(mAdapter);
        newsList.setOnItemClickListener((adapterView, view, position, id) -> {
            NewsInfo newsItem = mAdapter.getItem(position);
            Intent intent = new Intent(getApplicationContext(), NewsViewerActivity.class);
            intent.putExtra(NewsViewerActivity.CONCRETE_NEWS_ID, newsItem.getId());
            startActivity(intent);
        });

        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.main_layout);
        mSwipeLayout.setOnRefreshListener(() -> mPresenter.getNews());

        mPresenter.fetchNews();
    }

    @ProvidePresenter
    NewsPresenter provideNewsPresenter() {
        return new NewsPresenter(getApplicationContext());
    }

    @Override
    public void failed() {
        Toast.makeText(this, R.string.failed_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void newsReady(List<NewsInfo> news) {
        mAdapter.clear();
        mAdapter.addAll(news);
        mAdapter.notifyDataSetChanged();
        mSwipeLayout.setRefreshing(false);
    }
}
