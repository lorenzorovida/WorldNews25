package com.unimib.worldnews25.repository;

import android.app.Application;

import com.unimib.worldnews25.model.Article;
import com.unimib.worldnews25.ui.home.fragment.HeadlineFragment;
import com.unimib.worldnews25.utils.ResponseCallback;

public class ArticleAPIRepository implements IArticleRepository {
    public ArticleAPIRepository(Application application, ResponseCallback callback) {
    }

    @Override
    public void fetchArticles(String country, int page, long lastUpdate) {

    }

    @Override
    public void updateArticles(Article article) {

    }

    @Override
    public void getFavoriteArticles() {

    }

    @Override
    public void deleteFavoriteArticles() {

    }
}
