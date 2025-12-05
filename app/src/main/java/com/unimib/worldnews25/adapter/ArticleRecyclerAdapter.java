package com.unimib.worldnews25.adapter;


import static android.view.View.INVISIBLE;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unimib.worldnews25.R;
import com.unimib.worldnews25.database.ArticleRoomDatabase;
import com.unimib.worldnews25.model.Article;

import java.util.List;

public class ArticleRecyclerAdapter extends RecyclerView.Adapter<ArticleRecyclerAdapter.ViewHolder> {

    private final List<Article> articleList;
    private final boolean showLikes;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;
        private final TextView authorTextView;
        private final CheckBox checkboxFavorite;


        public ViewHolder(View view) {
            super(view);
            titleTextView = (TextView) view.findViewById(R.id.textViewTitle);
            authorTextView = (TextView) view.findViewById(R.id.textViewAuthor);
            checkboxFavorite = (CheckBox) view.findViewById(R.id.favoriteButton);

        }

        public TextView getAuthorTextView() {
            return authorTextView;
        }

        public TextView getTitleTextView() {
            return titleTextView;
        }

        public CheckBox getCheckboxFavorite() {
            return checkboxFavorite;
        }
    }


    public ArticleRecyclerAdapter(List<Article> articleList, boolean showLikes) {
        this.articleList = articleList;
        this.showLikes = showLikes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_article, viewGroup, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getAuthorTextView().setText(articleList.get(position).getAuthor());
        viewHolder.getTitleTextView().setText(articleList.get(position).getTitle());
        viewHolder.getCheckboxFavorite().setChecked(articleList.get(position).getLike());

        if (showLikes == false) {
            viewHolder.getCheckboxFavorite().setVisibility(INVISIBLE);
        }

        int positionSaved = position;

        viewHolder.getCheckboxFavorite().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton compoundButton, boolean b) {
                Article currentArticle = articleList.get(viewHolder.getAdapterPosition());

                currentArticle.setLike(b);

                ArticleRoomDatabase.getDatabase(viewHolder.getCheckboxFavorite().getContext()).
                        articleDao().updateArticle(currentArticle);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return articleList.size();
    }
}