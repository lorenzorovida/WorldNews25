package com.unimib.worldnews25.source;

import static com.unimib.worldnews25.utils.Constants.API_KEY_ERROR;
import static com.unimib.worldnews25.utils.Constants.RETROFIT_ERROR;
import static com.unimib.worldnews25.utils.Constants.TOP_HEADLINES_PAGE_SIZE_VALUE;

import androidx.annotation.NonNull;

import com.unimib.worldnews25.model.ArticleAPIResponse;
import com.unimib.worldnews25.service.ArticleAPIService;
import com.unimib.worldnews25.utils.ServiceLocator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class to get news from a remote source using Retrofit.
 */
public class ArticleNewsAPIDataSource extends BaseArticleRemoteDataSource {

    private final ArticleAPIService articleAPIService;
    private final String apiKey;

    public ArticleNewsAPIDataSource(String apiKey) {
        this.apiKey = apiKey;
        this.articleAPIService = ServiceLocator.getInstance().getArticleAPIService();
    }

    @Override
    public void getArticles(String country) {
        Call<ArticleAPIResponse> newsResponseCall = articleAPIService.getArticles(country,
                TOP_HEADLINES_PAGE_SIZE_VALUE, apiKey);

        newsResponseCall.enqueue(new Callback<ArticleAPIResponse>() {
            @Override
            public void onResponse(@NonNull Call<ArticleAPIResponse> call,
                                   @NonNull Response<ArticleAPIResponse> response) {

                if (response.body() != null && response.isSuccessful() &&
                        !response.body().getStatus().equals("error")) {
                    articleCallback.onSuccessFromRemote(response.body(), System.currentTimeMillis());

                } else {
                    articleCallback.onFailureFromRemote(new Exception(API_KEY_ERROR));
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArticleAPIResponse> call, @NonNull Throwable t) {
                articleCallback.onFailureFromRemote(new Exception(RETROFIT_ERROR));
            }
        });
    }
}
