package com.example.india_news;

import com.example.india_news.Modules.NewsHeadlines;

import java.util.List;

public interface onFetchDataListener<NewsApiResponse> {
    void onFetchData(List<NewsHeadlines> list, String message);
    void onError(String message);

}
