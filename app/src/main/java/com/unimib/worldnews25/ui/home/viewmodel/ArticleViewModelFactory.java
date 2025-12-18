package com.unimib.worldnews25.ui.home.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.unimib.worldnews25.repository.ArticleRepository;

public class ArticleViewModelFactory implements ViewModelProvider.Factory {

    private final ArticleRepository articleRepository;

    public ArticleViewModelFactory(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ArticleViewModel(articleRepository);
    }
}