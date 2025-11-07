package com.unimib.worldnews25.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.unimib.worldnews25.R;
import com.unimib.worldnews25.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity {

    private List<String> selectedCategories = new ArrayList<>();

    private ExtendedFloatingActionButton floatingActionButton;

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

        floatingActionButton = findViewById(R.id.floating_action_button);

        fillGrid();

    }

    void fillGrid() {
        GridLayout gridLayout = findViewById(R.id.grid_layout);


        LayoutInflater layoutInflater = LayoutInflater.from(this);

        for (int i = 0; i < Constants.LIST_CATEGORIES.length; i++) {
            MaterialCardView card = (MaterialCardView) layoutInflater.inflate(R.layout.card_category, gridLayout, false);

            TextView textView = card.findViewById(R.id.title);
            textView.setText(Constants.getListCategoriesNames(this)[i]);

            ImageView imageView = card.findViewById(R.id.image_view);
            imageView.setImageDrawable(Constants.getListCategoriesDrawables(this)[i]);

            String currentCategory = Constants.LIST_CATEGORIES[i];

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    card.setChecked(!card.isChecked());

                    if (card.isChecked()) {
                        selectedCategories.add(currentCategory);
                    } else {
                        selectedCategories.remove(currentCategory);
                    }

                    tryToEnableFloatingActionButton();
                }
            });

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

    private void tryToEnableFloatingActionButton(){
        floatingActionButton.setEnabled(!selectedCategories.isEmpty());
    }
}