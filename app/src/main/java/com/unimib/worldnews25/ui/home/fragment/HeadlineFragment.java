package com.unimib.worldnews25.ui.home.fragment;

import static android.view.View.INVISIBLE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.loadingindicator.LoadingIndicator;
import com.google.android.material.snackbar.Snackbar;
import com.unimib.worldnews25.R;
import com.unimib.worldnews25.adapter.ArticleRecyclerAdapter;
import com.unimib.worldnews25.model.Article;
import com.unimib.worldnews25.model.ArticleAPIResponse;
import com.unimib.worldnews25.repository.ArticleAPIRepository;
import com.unimib.worldnews25.repository.ArticleMockRepository;
import com.unimib.worldnews25.repository.IArticleRepository;
import com.unimib.worldnews25.service.ServiceLocator;
import com.unimib.worldnews25.utils.Constants;
import com.unimib.worldnews25.utils.JSONParserUtils;
import com.unimib.worldnews25.utils.NetworkUtil;
import com.unimib.worldnews25.utils.ResponseCallback;
import com.unimib.worldnews25.utils.SharedPreferencesUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HeadlineFragment extends Fragment implements ResponseCallback {

    public static final String TAG = HeadlineFragment.class.getName();
    private IArticleRepository articleRepository;
    private SharedPreferencesUtils sharedPreferencesUtils;
    private List<Article> articleList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ArticleRecyclerAdapter adapter;
    private LoadingIndicator loadingIndicator;


    public HeadlineFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (requireActivity().getResources().getBoolean(R.bool.debug)) {
            articleRepository = new ArticleMockRepository(requireActivity().getApplication(), this);
        } else {
            articleRepository = new ArticleAPIRepository(requireActivity().getApplication(), this);
        }
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

        recyclerView = view.findViewById(R.id.recyclerView);
        loadingIndicator = view.findViewById(R.id.loadingIndicator);

        articleRepository.fetchArticles("us", Constants.TOP_HEADLINES_PAGE_SIZE_VALUE, 1000);

        if (!NetworkUtil.isInternetAvailable(getContext())) {
            view.findViewById(R.id.noInternetMessage).setVisibility(View.VISIBLE);
        }

        adapter = new ArticleRecyclerAdapter(articleList, true);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onSuccess(List<Article> articlesList, long lastUpdate) {
        //Riempi la recylcerview
        this.articleList.clear();

        for (int i = 0; i < articlesList.size(); i++) {
            this.articleList.add(articlesList.get(i));
        }

        Log.i(TAG, "Arrivati: " + this.articleList.size());

        requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
                recyclerView.setVisibility(View.VISIBLE);
                loadingIndicator.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onFailure(String errorMessage) {
        Snackbar.make(getView(), errorMessage, Snackbar.LENGTH_SHORT).show();
    }
}