package com.unimib.worldnews25.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.unimib.worldnews25.R;

import java.util.List;

public class Constants {

    public static final String CATEGORY_BUSINESS = "business";
    public static final String CATEGORY_ENTERTAINMENT = "entertainment";
    public static final String CATEGORY_GENERAL = "general";
    public static final String CATEGORY_HEALTH = "health";
    public static final String CATEGORY_SCIENCE = "science";
    public static final String CATEGORY_SPORTS = "sports";
    public static final String CATEGORY_TECHNOLOGY= "technology" ;

    public static final String[] LIST_CATEGORIES = {
            CATEGORY_BUSINESS,
            CATEGORY_ENTERTAINMENT,
            CATEGORY_GENERAL,
            CATEGORY_HEALTH,
            CATEGORY_SCIENCE,
            CATEGORY_SPORTS,
            CATEGORY_TECHNOLOGY
    };

    public static String[] getListCategoriesNames(Context context) {
        return new String[]{
                context.getString(R.string.category_business),
                context.getString(R.string.category_entertainment),
                context.getString(R.string.category_general),
                context.getString(R.string.category_health),
                context.getString(R.string.category_science),
                context.getString(R.string.category_sports),
                context.getString(R.string.category_technology)
        };
    }

    public static Drawable[] getListCategoriesDrawables(Context context) {
        return new Drawable[]{
                context.getDrawable(R.drawable.category_business),
                context.getDrawable(R.drawable.category_entertainment),
                context.getDrawable(R.drawable.category_general),
                context.getDrawable(R.drawable.category_health),
                context.getDrawable(R.drawable.category_science),
                context.getDrawable(R.drawable.category_sports),
                context.getDrawable(R.drawable.category_technology)
        };
    }




}
