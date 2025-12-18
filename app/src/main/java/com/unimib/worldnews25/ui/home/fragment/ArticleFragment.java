package com.unimib.worldnews25.ui.home.fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.unimib.worldnews25.R;
import com.unimib.worldnews25.model.Article;
import com.unimib.worldnews25.utils.Constants;


public class ArticleFragment extends Fragment {

    private Article currentArticle;

    public ArticleFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        currentArticle = getArguments().getParcelable(Constants.BUNDLE_KEY_CURRENT_ARTICLE);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(currentArticle.getTitle());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((TextView) view.findViewById(R.id.textViewTitle)).setText(currentArticle.getTitle());
        ((TextView) view.findViewById(R.id.textViewBody)).setText(currentArticle.getContent());
        ImageView imageView = view.findViewById(R.id.imageView);

        Glide.with(getContext())
                .load(currentArticle.getUrlToImage())
                .placeholder(new ColorDrawable(getContext().getColor(R.color.md_theme_errorContainer)))
                .into(imageView);
    }
}