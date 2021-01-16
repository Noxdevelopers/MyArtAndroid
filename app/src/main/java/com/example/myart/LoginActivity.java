package com.example.myart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import app.*;
import cz.msebera.android.httpclient.Header;
import objects.LoginObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText  phone;
    AppCompatTextView login, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {


        phone = findViewById(R.id.phone);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        login.setOnClickListener(this);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == login){

            loginFunction();

        }else if (v == register){

            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);

        }

    }

    private void loginFunction() {


        if (phone.getText().toString().length() != 11){

            app.T("شماره خود را چک کنید", ToastType.ERROR);
            app.animateError(phone);
            return;

        }

        RequestParams params = app.getRequestParams();
        params.put(ROUTER.ROUTE, ROUTER.ROUTE_USERS);
        params.put(ROUTER.ACTION, ROUTER.ACTION_LOGIN);
        params.put(ROUTER.INPUT_PHONE, phone.getText().toString());

        ArtClient.post(params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                app.L(response+"");

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.has("error")){
                        app.T(jsonObject.getString("error"), ToastType.ERROR);
                    } else {
                        LoginObject loginObject = new Gson().fromJson(response, LoginObject.class);
                        if (loginObject.getState().equals(ROUTER.SUCCESS)){

                            String fullName2 = loginObject.getUserObject().getFullname();
                            app.T("خوش آمدید " + fullName2, ToastType.SUCCESS);

                            spref.getOurInstance().edit()
                                    .putString(ROUTER.SESSION, loginObject.getUserObject().getSeesion())
                                    .putInt(ROUTER.USER_ID, loginObject.getUserObject().getId())
                                    .putString(ROUTER.INPUT_PHONE, phone.getText().toString())
                                    .putString(ROUTER.INPUT_FULLNAME, fullName2)
                                    .apply();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } else {
                            app.T("مشکلی پیش آمد", ToastType.ERROR);
                        }
                    }


                } catch (JSONException e) {
                    app.T("مشکلی پیش آمد", ToastType.ERROR);
                }
                app.L(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                app.L("fail");
            }
        });


    }
}