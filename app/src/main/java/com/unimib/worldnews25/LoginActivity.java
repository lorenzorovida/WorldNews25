package com.unimib.worldnews25;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.apache.commons.validator.routines.EmailValidator;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);


        Button button = findViewById(R.id.login_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText inputEmail = findViewById(R.id.textInputEmail);
                TextInputEditText inputPassword = findViewById(R.id.textInputPassword);

                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                if (isEmailOk(email)) {
                    if (isPasswordOk(password)) {

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