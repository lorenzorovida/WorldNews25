package com.unimib.worldnews25.utils;

import com.unimib.worldnews25.model.Article;

import java.util.List;

public interface ResponseCallback {
    void onSuccess(List<Article> articlesList, long lastUpdate);
    void onFailure(String errorMessage);
}
