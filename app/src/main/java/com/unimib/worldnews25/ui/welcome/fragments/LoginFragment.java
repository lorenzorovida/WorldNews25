package com.unimib.worldnews25.ui.welcome.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.unimib.worldnews25.R;

import org.apache.commons.validator.routines.EmailValidator;

public class LoginFragment extends Fragment {



    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        //Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button = view.findViewById(R.id.login_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText inputEmail = view.findViewById(R.id.textInputEmail);
                TextInputEditText inputPassword = view.findViewById(R.id.textInputPassword);

                //String email = inputEmail.getText().toString();
                //String password = inputPassword.getText().toString();

                if (true) {
                    if (true) {
                        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_categoriesFragment);
                    } else {
                        inputPassword.setError(getString(R.string.check_password));
                    }
                } else {
                    inputEmail.setError(getString(R.string.check_email));
                }
            }
        });

    }

    boolean isEmailOk(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    boolean isPasswordOk(String password) {
        return password.length() > 7;
    }
}