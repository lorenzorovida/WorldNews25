package com.unimib.worldnews25;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;

public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_categories);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fillGrid();

    }

    void fillGrid() {
        GridLayout gridLayout = findViewById(R.id.grid_layout);

        String[] categories = {
                "business",
                "entertainment",
                "general",
                "health",
                "science",
                "sports",
                "technology"
        };

        LayoutInflater layoutInflater = LayoutInflater.from(this);

        for (int i = 0; i < categories.length; i++) {
            View card = layoutInflater.inflate(R.layout.card_category, gridLayout, false);

            TextView textView = card.findViewById(R.id.title);
            textView.setText(categories[i]);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();

            params.rowSpec = GridLayout.spec(i / 2, 1);
            params.columnSpec = GridLayout.spec(i % 2, 1, 1);

            int margin = (int)getResources().getDimension(R.dimen.margin_medium);
            params.setMargins(margin,margin,margin,margin);
            params.width = 0;
            params.height = GridLayout.LayoutParams.WRAP_CONTENT;

            card.setLayoutParams(params);
            gridLayout.addView(card);
        }
    }
}