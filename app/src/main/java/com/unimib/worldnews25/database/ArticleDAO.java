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
    @Query("SELECT * FROM Article")
    List<Article> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Article... articles);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Article> articles);

    @Delete
    void delete(Article article);

    @Query("DELETE from Article")
    void deleteAll();
}