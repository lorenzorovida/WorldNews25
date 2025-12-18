package com.unimib.worldnews25.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.unimib.worldnews25.model.Article;

import java.util.List;

@Dao
public interface ArticleDAO {
    @Query("SELECT * FROM Article ORDER BY publishedAt DESC")
    List<Article> getAll();

    @Query("SELECT * FROM article WHERE uid = :id")
    Article getArticle(long id);

    @Query("SELECT * FROM Article WHERE `like` = 1")
    List<Article> getLiked();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Article... articles);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Article> articles);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertArticlesList(List<Article> articlesList);

    @Delete
    void delete(Article article);

    @Query("DELETE from Article")
    void deleteAll();

    @Update
    int updateArticle(Article article);

    @Update
    int updateListFavoriteArticles(List<Article> articles);
}