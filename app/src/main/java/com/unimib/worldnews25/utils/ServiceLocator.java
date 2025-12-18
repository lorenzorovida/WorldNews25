package com.unimib.worldnews25.utils;

import android.app.Application;

import com.unimib.worldnews25.R;
import com.unimib.worldnews25.database.ArticleRoomDatabase;
import com.unimib.worldnews25.repository.ArticleRepository;
import com.unimib.worldnews25.service.ArticleAPIService;
import com.unimib.worldnews25.source.ArticleLocalDataSource;
import com.unimib.worldnews25.source.ArticleMockDataSource;
import com.unimib.worldnews25.source.ArticleNewsAPIDataSource;
import com.unimib.worldnews25.source.BaseArticleLocalDataSource;
import com.unimib.worldnews25.source.BaseArticleRemoteDataSource;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceLocator {

    private static volatile ServiceLocator INSTANCE = null;

    private ServiceLocator() {
    }

    public static ServiceLocator getInstance() {
        if (INSTANCE == null) {
            synchronized (ServiceLocator.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ServiceLocator();
                }
            }
        }
        return INSTANCE;
    }

    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(chain -> {
                Request request = chain.request().newBuilder()
                        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                        .build();
                return chain.proceed(request);
            })
            .build();

    public ArticleAPIService getArticleAPIService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEWS_API_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ArticleAPIService.class);
    }

    public ArticleRoomDatabase getArticlesDB(Application application) {
        return ArticleRoomDatabase.getDatabase(application);
    }

    public ArticleRepository getArticlesRepository(Application application, boolean debugMode) {
        BaseArticleRemoteDataSource newsRemoteDataSource;
        BaseArticleLocalDataSource newsLocalDataSource;
        SharedPreferencesUtils sharedPreferencesUtil = new SharedPreferencesUtils(application);

        if (debugMode) {
            JSONParserUtils jsonParserUtil = new JSONParserUtils(application);
            newsRemoteDataSource =
                    new ArticleMockDataSource(jsonParserUtil);
        } else {
            newsRemoteDataSource =
                    new ArticleNewsAPIDataSource(application.getString(R.string.news_api_key));
        }

        newsLocalDataSource = new ArticleLocalDataSource(getArticlesDB(application), sharedPreferencesUtil);

        return new ArticleRepository(newsRemoteDataSource, newsLocalDataSource);
    }
}
