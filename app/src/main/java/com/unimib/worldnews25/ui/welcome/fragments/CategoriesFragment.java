package com.unimib.worldnews25.ui.welcome.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.unimib.worldnews25.R;
import com.unimib.worldnews25.utils.Constants;
import com.unimib.worldnews25.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.Set;

public class CategoriesFragment extends Fragment {

    private ArrayList<String> selectedCategories = new ArrayList<>();

    private ExtendedFloatingActionButton floatingActionButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        floatingActionButton = view.findViewById(R.id.floating_action_button);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Set<String> categoriesCodes = new ArraySet<>();

                for (int i = 0; i < selectedCategories.size(); i++) {
                    categoriesCodes.add(selectedCategories.get(i));
                }

                SharedPreferencesUtils sharedPreferencesUtils = new SharedPreferencesUtils(getContext());

                sharedPreferencesUtils.writeStringSetData(Constants.SHARED_PREFERENCES_FILENAME,
                        Constants.SHARED_PREFERENCES_CATEGORIES_OF_INTEREST,
                        categoriesCodes);

                Navigation.findNavController(view).navigate(R.id.action_categoriesFragment_to_countryFragment);
            }
        });

        fillGrid(view);
    }

    void fillGrid(View view) {
        GridLayout gridLayout = view.findViewById(R.id.grid_layout);
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        for (int i = 0; i < Constants.LIST_CATEGORIES.length; i++) {
            MaterialCardView card = (MaterialCardView) layoutInflater.inflate(R.layout.card_category, gridLayout, false);

            TextView textView = card.findViewById(R.id.title);
            textView.setText(Constants.getListCategoriesNames(getContext())[i]);

            ImageView imageView = card.findViewById(R.id.image_view);
            imageView.setImageDrawable(Constants.getListCategoriesDrawables(getContext())[i]);

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