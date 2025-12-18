package com.unimib.worldnews25.source;


import com.unimib.worldnews25.model.Article;
import com.unimib.worldnews25.model.ArticleAPIResponse;

import java.util.List;

public interface ArticleCallback {
    void onSuccessFromRemote(ArticleAPIResponse articleAPIResponse, long lastUpdate);
    void onFailureFromRemote(Exception exception);
    void onSuccessFromLocal(List<Article> articlesList);
    void onFailureFromLocal(Exception exception);
    void onNewsFavoriteStatusChanged(Article news, List<Article> favoriteNews);
    void onNewsFavoriteStatusChanged(List<Article> news);
    void onDeleteFavoriteNewsSuccess(List<Article> favoriteNews);
}
