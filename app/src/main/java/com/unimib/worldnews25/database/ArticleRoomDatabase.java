package com.unimib.worldnews25.database;

import android.content.Context;
import com.unimib.worldnews25.utils.Constants;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.unimib.worldnews25.model.Article;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Article.class}, version = 1, exportSchema = false)
public abstract class ArticleRoomDatabase extends RoomDatabase {

    public abstract ArticleDAO articleDao();

    private static volatile ArticleRoomDatabase INSTANCE;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static ArticleRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ArticleRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ArticleRoomDatabase.class, Constants.SAVED_ARTICLES_DATABASE)
                            .allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
