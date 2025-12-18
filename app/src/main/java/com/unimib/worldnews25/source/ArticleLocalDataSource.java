package com.unimib.worldnews25.source;

import static com.unimib.worldnews25.utils.Constants.UNEXPECTED_ERROR;

import com.unimib.worldnews25.database.ArticleDAO;
import com.unimib.worldnews25.database.ArticleRoomDatabase;
import com.unimib.worldnews25.model.Article;
import com.unimib.worldnews25.utils.Constants;
import com.unimib.worldnews25.utils.SharedPreferencesUtils;

import java.util.List;


public class ArticleLocalDataSource extends BaseArticleLocalDataSource {

    private final ArticleDAO articleDAO;
    private final SharedPreferencesUtils sharedPreferencesUtil;

    public ArticleLocalDataSource(ArticleRoomDatabase newsRoomDatabase,
                                  SharedPreferencesUtils sharedPreferencesUtil) {
        this.articleDAO = newsRoomDatabase.articleDao();
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void getArticles() {
        ArticleRoomDatabase.databaseWriteExecutor.execute(() -> {
            articleCallback.onSuccessFromLocal(articleDAO.getAll());
        });
    }

    @Override
    public void getFavoriteArticles() {
        ArticleRoomDatabase.databaseWriteExecutor.execute(() -> {
            List<Article> favoriteNews = articleDAO.getLiked();
            articleCallback.onNewsFavoriteStatusChanged(favoriteNews);
        });
    }

    @Override
    public void updateArticle(Article article) {
        ArticleRoomDatabase.databaseWriteExecutor.execute(() -> {
            int rowUpdatedCounter = articleDAO.updateArticle(article);

            if (rowUpdatedCounter == 1) {
                Article updatedNews = articleDAO.getArticle(article.getUid());
                articleCallback.onNewsFavoriteStatusChanged(updatedNews, articleDAO.getLiked());
            } else {
                articleCallback.onFailureFromLocal(new Exception(UNEXPECTED_ERROR));
            }
        });
    }

    @Override
    public void deleteFavoriteArticles() {
        ArticleRoomDatabase.databaseWriteExecutor.execute(() -> {
            List<Article> favoriteArticles = articleDAO.getLiked();
            for (Article article : favoriteArticles) {
                article.setLike(false);
            }
            int updatedRowsNumber = articleDAO.updateListFavoriteArticles(favoriteArticles);

            // It means that the update succeeded because the number of updated rows is
            // equal to the number of the original favorite news
            if (updatedRowsNumber == favoriteArticles.size()) {
                articleCallback.onDeleteFavoriteNewsSuccess(favoriteArticles);
            } else {
                articleCallback.onFailureFromLocal(new Exception(UNEXPECTED_ERROR));
            }
        });
    }

    @Override
    public void insertArticles(List<Article> articlesList) {
        ArticleRoomDatabase.databaseWriteExecutor.execute(() -> {

            List<Article> allArticles = articleDAO.getAll();

            if (articlesList != null) {

                for (Article article : allArticles) {

                    if (articlesList.contains(article)) {
                        articlesList.set(articlesList.indexOf(article), article);
                    }
                }

                List<Long> insertedNewsIds = articleDAO.insertArticlesList(articlesList);
                for (int i = 0; i < articlesList.size(); i++) {
                    articlesList.get(i).setUid(insertedNewsIds.get(i));
                }

                sharedPreferencesUtil.writeStringData(Constants.SHARED_PREFERENCES_FILENAME,
                        Constants.SHARED_PREFERNECES_LAST_UPDATE, String.valueOf(System.currentTimeMillis()));

                articleCallback.onSuccessFromLocal(articlesList);
            }
        });
    }
}
