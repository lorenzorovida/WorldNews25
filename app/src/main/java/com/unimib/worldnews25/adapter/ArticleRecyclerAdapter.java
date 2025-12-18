package com.unimib.worldnews25.adapter;


import static android.view.View.INVISIBLE;

import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.unimib.worldnews25.R;
import com.unimib.worldnews25.database.ArticleRoomDatabase;
import com.unimib.worldnews25.model.Article;
import com.unimib.worldnews25.utils.Constants;

import java.util.List;

public class ArticleRecyclerAdapter extends RecyclerView.Adapter<ArticleRecyclerAdapter.ViewHolder> {

    private final List<Article> articleList;
    private final boolean showLikes;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;
        private final TextView authorTextView;
        private final CheckBox checkboxFavorite;
        private final ImageView imageView;
        private final CardView cardView;

        public ViewHolder(View view) {
            super(view);
            titleTextView = (TextView) view.findViewById(R.id.textViewTitle);
            authorTextView = (TextView) view.findViewById(R.id.textViewAuthor);
            checkboxFavorite = (CheckBox) view.findViewById(R.id.favoriteButton);
            imageView = (ImageView) view.findViewById(R.id.imageView);
            cardView = (CardView) view;

        }

        public ImageView getImageView() {
            return imageView;
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

        public CardView getCardView() {
            return cardView;
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

        Glide.with(viewHolder.getImageView().getContext())
                .load(articleList.get(position).getUrlToImage())
                .placeholder(new ColorDrawable(viewHolder.getImageView().getContext().getColor(R.color.md_theme_errorContainer)))
                .into(viewHolder.getImageView());

        if (showLikes == false) {
            viewHolder.getCheckboxFavorite().setVisibility(INVISIBLE);
        }

        int positionSaved = position;

        viewHolder.getCheckboxFavorite().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton compoundButton, boolean b) {
                Article currentArticle = articleList.get(viewHolder.getBindingAdapterPosition());

                currentArticle.setLike(b);

                ArticleRoomDatabase.getDatabase(viewHolder.getCheckboxFavorite().getContext()).
                        articleDao().updateArticle(currentArticle);
            }
        });

        viewHolder.getCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constants.BUNDLE_KEY_CURRENT_ARTICLE,
                        articleList.get(viewHolder.getBindingAdapterPosition()));

                Navigation.findNavController(view).navigate(R.id.action_headlineFragment_to_articleFragment, bundle);

            }
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return articleList.size();
    }
}