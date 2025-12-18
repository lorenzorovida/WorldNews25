package com.unimib.worldnews25.ui.home.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.loadingindicator.LoadingIndicator;
import com.google.android.material.snackbar.Snackbar;
import com.unimib.worldnews25.R;
import com.unimib.worldnews25.adapter.ArticleRecyclerAdapter;
import com.unimib.worldnews25.database.ArticleRoomDatabase;
import com.unimib.worldnews25.model.Article;
import com.unimib.worldnews25.model.Result;
import com.unimib.worldnews25.repository.ArticleRepository;
import com.unimib.worldnews25.source.ArticleLocalDataSource;
import com.unimib.worldnews25.source.ArticleNewsAPIDataSource;
import com.unimib.worldnews25.source.BaseArticleLocalDataSource;
import com.unimib.worldnews25.source.BaseArticleRemoteDataSource;
import com.unimib.worldnews25.ui.home.viewmodel.ArticleViewModel;
import com.unimib.worldnews25.ui.home.viewmodel.ArticleViewModelFactory;
import com.unimib.worldnews25.utils.Constants;
import com.unimib.worldnews25.utils.NetworkUtil;
import com.unimib.worldnews25.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;


public class HeadlineFragment extends Fragment {

    public static final String TAG = HeadlineFragment.class.getName();
    private ArticleRepository articleRepository;
    private SharedPreferencesUtils sharedPreferencesUtils;
    private List<Article> articleList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ArticleRecyclerAdapter adapter;
    private LoadingIndicator loadingIndicator;

    private ArticleViewModel articleViewModel;


    public HeadlineFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BaseArticleRemoteDataSource remoteDataSource;
        BaseArticleLocalDataSource localDataSource;

        sharedPreferencesUtils = new SharedPreferencesUtils(getContext());

        remoteDataSource = new ArticleNewsAPIDataSource(requireActivity().getApplication().getResources().getString(R.string.news_api_key));
        localDataSource = new ArticleLocalDataSource(ArticleRoomDatabase.getDatabase(getContext()), sharedPreferencesUtils);

        articleRepository = new ArticleRepository(remoteDataSource, localDataSource);

        articleViewModel = new ViewModelProvider(
                requireActivity(),
                new ArticleViewModelFactory(articleRepository)).get(ArticleViewModel.class);

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

        if (!NetworkUtil.isInternetAvailable(getContext())) {
            view.findViewById(R.id.noInternetMessage).setVisibility(View.VISIBLE);
        }

        adapter = new ArticleRecyclerAdapter(articleList, true);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        String lastUpdate = "0";

        MutableLiveData<Result> scatola = articleViewModel.getArticles("us", Long.parseLong(lastUpdate));

        scatola.observe(getViewLifecycleOwner(),
                result -> {
                    if (result.isSuccess()) {
                        int initialSize = this.articleList.size();
                        this.articleList.clear();
                        this.articleList.addAll(((Result.Success) result).getData().getArticles());
                        adapter.notifyItemRangeInserted(initialSize, this.articleList.size());
                        recyclerView.setVisibility(View.VISIBLE);
                        loadingIndicator.setVisibility(View.GONE);
                    } else {
                        Snackbar.make(view,
                                getString(R.string.error_retrieving_news),
                                Snackbar.LENGTH_SHORT).show();
                    }
                });

    }


}