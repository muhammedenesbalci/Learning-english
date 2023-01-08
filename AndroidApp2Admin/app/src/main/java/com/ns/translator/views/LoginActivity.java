package com.ns.translator.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ns.translator.R;
import com.ns.translator.models.LoginUser;
import com.ns.translator.models.User;
import com.ns.translator.viewModels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    EditText emailText;
    EditText passwordText;
    TextView resultUi;
    Button loginButton;

    String emailString;
    String passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = findViewById(R.id.mail);
        passwordText = findViewById(R.id.password);
        resultUi = findViewById(R.id.resultMailAndPassword);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailString = emailText.getText().toString();
                passwordString = passwordText.getText().toString();

                if (emailString.contains("@") && emailString.contains(".com") && passwordString.length()>= 4) {
                    LoginViewModel loginViewModel1 = new ViewModelProvider(LoginActivity.this).get(LoginViewModel.class);

                    loginViewModel1.setmPassword(passwordString);
                    loginViewModel1.setmMail(emailString);

                    loginViewModel1.getData().observe(LoginActivity.this, new Observer<String>() {
                        @Override
                        public void onChanged(String strToken) {
                            if (strToken.matches("empty")) {
                                Toast.makeText(LoginActivity.this, "You are not a Admin",Toast.LENGTH_SHORT).show();
                            } else {
                                User user = User.getInstance();
                                user.setToken(strToken);
                                System.out.println("Token : " + strToken);
                                Toast.makeText(LoginActivity.this, "Success",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "E-mail or Password is incorrect",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}