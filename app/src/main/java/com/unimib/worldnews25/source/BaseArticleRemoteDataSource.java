package com.unimib.worldnews25.source;

public abstract class BaseArticleRemoteDataSource {
    protected ArticleCallback articleCallback;

    public void setArticleCallback(ArticleCallback articleCallback) {
        this.articleCallback = articleCallback;
    }

    public abstract void getArticles(String country);
}
