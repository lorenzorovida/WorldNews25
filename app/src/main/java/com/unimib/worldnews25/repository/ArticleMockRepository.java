package com.unimib.worldnews25.repository;

import android.app.Application;

import com.unimib.worldnews25.R;
import com.unimib.worldnews25.database.ArticleDAO;
import com.unimib.worldnews25.database.ArticleRoomDatabase;
import com.unimib.worldnews25.model.Article;
import com.unimib.worldnews25.model.ArticleAPIResponse;
import com.unimib.worldnews25.service.ServiceLocator;
import com.unimib.worldnews25.utils.Constants;
import com.unimib.worldnews25.utils.JSONParserUtils;
import com.unimib.worldnews25.utils.ResponseCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArticleMockRepository implements IArticleRepository {
    private final Application application;
    private final ResponseCallback responseCallback;
    private final ArticleDAO articleDao;

    public ArticleMockRepository(Application application, ResponseCallback responseCallback) {
        this.application = application;
        this.responseCallback = responseCallback;
        this.articleDao = ServiceLocator.getInstance().getArticlesDB(application).articleDao();
    }

    @Override
    public void fetchArticles(String country, int page, long lastUpdate) {
        ArticleAPIResponse articleApiResponse;

        JSONParserUtils jsonParserUtils = new JSONParserUtils(application.getApplicationContext());

        try {
            articleApiResponse = jsonParserUtils.parseJSONFileWithGSon(Constants.SAMPLE_JSON_API_RESPONSE);
            if (articleApiResponse != null) {
                saveDataInDatabase(articleApiResponse.getArticles());
            } else {
                responseCallback.onFailure(application.getString(R.string.error_retrieving_news));
            }
        } catch (IOException e) {
            responseCallback.onFailure(application.getString(R.string.error_retrieving_news));
            throw new RuntimeException(e);
        }

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

    private void saveDataInDatabase(List<Article> articleList) {
        ArticleRoomDatabase.databaseWriteExecutor.execute(() -> {
            // Reads the news from the database
            List<Article> allArticles = articleDao.getAll();

            // Checks if the news just downloaded has already been downloaded earlier
            // in order to preserve the news status (marked as favorite or not)
            for (Article article : allArticles) {
                // This check works because News and NewsSource classes have their own
                // implementation of equals(Object) and hashCode() methods

                if (articleList.contains(article)) {
                    // The primary key and the favorite status is contained only in the News objects
                    // retrieved from the database, and not in the News objects downloaded from the
                    // Web Service. If the same news was already downloaded earlier, the following
                    // line of code replaces the the News object in newsList with the corresponding
                    // News object saved in the database, so that it has the primary key and the
                    // favorite status.
                    articleList.set(articleList.indexOf(article), article);
                }
            }

            // Writes the news in the database and gets the associated primary keys
            List<Long> insertedNewsIds = articleDao.insertArticlesList(articleList);
            for (int i = 0; i < articleList.size(); i++) {
                // Adds the primary key to the corresponding object News just downloaded so that
                // if the user marks the news as favorite (and vice-versa), we can use its id
                // to know which news in the database must be marked as favorite/not favorite
                articleList.get(i).setUid(insertedNewsIds.get(i));
            }

            responseCallback.onSuccess(articleList, System.currentTimeMillis());
        });
    }

    private void readDataFromDatabase(long lastUpdate) {
        ArticleRoomDatabase.databaseWriteExecutor.execute(() -> responseCallback.onSuccess(articleDao.getAll(), lastUpdate));
    }
}
