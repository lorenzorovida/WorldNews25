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

        fillGridLayout();
    }

    void fillGridLayout() {
        GridLayout gridLayout = findViewById(R.id.grid_layout);

        LayoutInflater inflater = LayoutInflater.from(this);

        String[] titoli = {
                "Elemento 1",
                "Elemento 2",
                "Elemento 3",
                "Elemento 4",
                "Elemento 5",
                "Elemento 6",
                "Elemento 6",
                "Elemento 6",
                "Elemento 6",
                "Elemento 6",
                "Elemento 6",
                "Elemento 6"
        };

        Drawable[] disegni = {
                AppCompatResources.getDrawable(this, R.drawable.country_italy),
                AppCompatResources.getDrawable(this, R.drawable.login_main)
        };

        for (int i = 0; i < titoli.length; i++) {
            View view = inflater.inflate(R.layout.card_category, gridLayout, false);

            TextView titleView = view.findViewById(R.id.title);
            titleView.setText(titoli[i]);

            ImageView imageView = view.findViewById(R.id.image_view);
            imageView.setImageDrawable(disegni[i % 2]);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();

            params.rowSpec = GridLayout.spec(i / 2, 1);
            params.columnSpec = GridLayout.spec(i % 2, 1, 1f);
            params.width = 0;

            int margin = (int) getResources().getDimension(R.dimen.margin_medium);
            params.setMargins(margin, margin, margin, margin);

            view.setLayoutParams(params);
            view.setOnClickListener(v ->
                    ((MaterialCardView)view).setChecked(!((MaterialCardView) view).isChecked())
            );

            gridLayout.addView(view);
        }
    }
}