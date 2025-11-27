package com.unimib.worldnews25.ui.home.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.unimib.worldnews25.R;
import com.unimib.worldnews25.adapter.ArticleRecyclerAdapter;
import com.unimib.worldnews25.model.Article;
import com.unimib.worldnews25.model.ArticleAPIResponse;
import com.unimib.worldnews25.utils.Constants;
import com.unimib.worldnews25.utils.JSONParserUtils;

import java.io.IOException;
import java.util.List;


public class HeadlineFragment extends Fragment {

    public HeadlineFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_headline, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        JSONParserUtils jsonParserUtils = new JSONParserUtils(getContext());
        try {
            ArticleAPIResponse response = jsonParserUtils.parseJSONFileWithGSon(Constants.SAMPLE_JSON_API_RESPONSE);
            List<Article> articleList = response.getArticles();

            ArticleRecyclerAdapter adapter = new ArticleRecyclerAdapter(articleList);

            RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.setAdapter(adapter);

        } catch (IOException e) {
            Snackbar.make(view, getString(R.string.json_loading_error), Snackbar.LENGTH_LONG);
            throw new RuntimeException(e);
        }



    }
}