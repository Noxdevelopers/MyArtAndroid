package com.example.myart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import app.*;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email, phone, fullName;
    AppCompatTextView register, registered;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
    }

    private void init() {

        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        fullName = findViewById(R.id.fullName);

        register = findViewById(R.id.register);
        registered = findViewById(R.id.registered);

        register.setOnClickListener(this);
        registered.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == register){

            registerFunction();

        }else if (v == registered){

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        }

    }

    private void registerFunction() {

        if (email.getText().toString().length()<5){
            app.T("لطفا ایمیل خود را چک کنید", ToastType.ERROR);
            app.animateError(email);
            return;
        }else if (phone.getText().toString().length()<11 || )

    }
}