package com.unimib.worldnews25.ui.welcome.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.unimib.worldnews25.R;
import com.unimib.worldnews25.adapter.CountryAdapter;
import com.unimib.worldnews25.utils.Constants;


public class CountryFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CountryAdapter adapter = new CountryAdapter(getContext(),
                R.layout.card_country,
                Constants.generateCountryList(getContext())
        );

        GridView gridView = view.findViewById(R.id.grid_view);
        gridView.setAdapter(adapter);
    }
}