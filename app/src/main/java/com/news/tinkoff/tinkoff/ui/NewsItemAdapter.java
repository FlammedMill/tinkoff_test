package com.news.tinkoff.tinkoff.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.news.tinkoff.tinkoff.R;
import com.news.tinkoff.tinkoff.models.NewsInfo;

import java.util.List;

public class NewsItemAdapter extends ArrayAdapter<NewsInfo> {

    public NewsItemAdapter(Context context, List<NewsInfo> users) {
        super(context, 0, users);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_list_delegate, parent, false);

        NewsInfo newsItem = getItem(position);
        TextView text = (TextView) convertView.findViewById(R.id.text);
        text.setText(Html.fromHtml(newsItem.getText()));
        return convertView;
    }
}