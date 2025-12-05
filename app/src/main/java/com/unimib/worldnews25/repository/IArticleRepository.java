package com.unimib.worldnews25.repository;

import com.unimib.worldnews25.model.Article;

public interface IArticleRepository {

    void fetchArticles(String country, int page, long lastUpdate);

    void updateArticles(Article article);

    void getFavoriteArticles();

    void deleteFavoriteArticles();
}