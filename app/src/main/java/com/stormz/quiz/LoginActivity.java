package com.stormz.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.stormz.quiz.database.ApplicationDatabase;
import com.stormz.quiz.instance.UserInstance;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    public ArrayList<UserInstance> users = new ArrayList<UserInstance>();
    //Call
    EditText emailTxt, passTxt;
    Button btnLogin;
    CheckBox cbShow;
    TextView regisNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Import:

        // Text
        emailTxt = findViewById(R.id.emailTxt);
        passTxt = findViewById(R.id.passTxt);
        regisNow = findViewById(R.id.registerNow);

        // Button
        btnLogin = findViewById(R.id.btnLogin);

        //Checkbox
        cbShow = findViewById(R.id.showPass);


        //Show atau hide password
        cbShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbShow.isChecked()) {

                    passTxt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {

                    passTxt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


        //Intent for register now:
        regisNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        //Validation
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered();
            }
        });
    }

    //Empty text
    private boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    private void checkDataEntered() {
        boolean isChecked = true;
        //Email must be filled
        if (isEmpty(emailTxt)) {
            emailTxt.setError("Email must be filled!");
            isChecked = false;
        }

        //Password must be filled
        if (isEmpty(passTxt)) {
            passTxt.setError("Password must be filled!");
            isChecked = false;
        }

        //Email and password must be registered
        String email = emailTxt.getText().toString();
        UserInstance user = ApplicationDatabase.getInstance(LoginActivity.this).findUser(email);

        //User and email validation not registered
        if (user == null) {
            emailTxt.setError("Your account not found or incorrect email!");
            isChecked = false;
        } else {
            String password = passTxt.getText().toString();
            String registeredPassword = user.getPassword();
            if (!password.equals(registeredPassword)) {
                passTxt.setError("Wrong password, please try again!");
                isChecked = false;
            }
        }

        if (isChecked) {
            Intent home = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(home);
        }
    }


}

