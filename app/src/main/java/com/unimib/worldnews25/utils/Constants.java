package com.unimib.worldnews25.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.unimib.worldnews25.R;
import com.unimib.worldnews25.model.Country;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {

    // Constants for NewsAPI.org
    public static final String FRANCE = "fr";
    public static final String ITALY = "it";
    public static final String GERMANY = "de";
    public static final String UNITED_KINGDOM = "gb";
    public static final String SPAIN = "es";

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

    public static final List<String> COUNTRIES = Arrays.asList(
            FRANCE, ITALY, GERMANY, SPAIN, UNITED_KINGDOM);

    public static final List<Integer> COUNTRIES_NAMES = Arrays.asList(
            R.string.countries_france, R.string.countries_italy, R.string.countries_germany,
            R.string.countries_spain, R.string.countries_unitedkingdom);

    public static final List<Integer> COUNTRIES_DRAWABLES = Arrays.asList(R.drawable.country_france,
            R.drawable.country_italy, R.drawable.country_germany, R.drawable.country_spain,
            R.drawable.country_unitedkingdom);

    public static ArrayList<Country> generateCountryList(Context context) {
        ArrayList<Country> countriesList = new ArrayList<>();
        for (int i = 0; i < COUNTRIES.size(); i++) {
            countriesList.add(new Country(
                    context.getString(COUNTRIES_NAMES.get(i)),
                    COUNTRIES.get(i),
                    context.getDrawable(COUNTRIES_DRAWABLES.get(i))
            ));
        }
        return countriesList;
    }

    public static final String SHARED_PREFERENCES_FILENAME = "com.unimib.worldnews.preferences";
    public static final String SHARED_PREFERENCES_COUNTRY_OF_INTEREST = "country_of_interest";
    public static final String SHARED_PREFERENCES_CATEGORIES_OF_INTEREST = "categories_of_interest";

    public static final String SAMPLE_JSON_API_RESPONSE = "sample_api_response.json";
    public static final String SAVED_ARTICLES_DATABASE = "db-articles";

    public static final String NEWS_API_BASE_URL = "https://newsapi.org/v2/";
    public static final String TOP_HEADLINES_ENDPOINT = "top-headlines";
    public static final String TOP_HEADLINES_COUNTRY_PARAMETER = "country";
    public static final String TOP_HEADLINES_PAGE_SIZE_PARAMETER = "pageSize";
    public static final int TOP_HEADLINES_PAGE_SIZE_VALUE = 100;

    public static final String REMOVED_ARTICLE_TITLE = "[Removed]";

    public static final int FRESH_TIMEOUT = 1000 * 60; // 1 minute in milliseconds


}
