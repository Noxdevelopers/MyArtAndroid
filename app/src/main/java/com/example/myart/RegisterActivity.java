package com.example.myart;

import androidx.appcompat.app.AppCompatActivity;
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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email, phone, fullName;
    AppCompatTextView register, registered;

    int phoneInt = 1;


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

            app.T("ایمیل خود را چک کنید", ToastType.ERROR);
            app.animateError(email);
            return;

        }else if (phone.getText().toString().length() != 11){

            app.T("شماره خود را چک کنید", ToastType.ERROR);
            app.animateError(phone);
            return;

        }else if (fullName.getText().toString().length() < 4){

            app.T("نام خود را چک کنید", ToastType.ERROR);
            app.animateError(fullName);
            return;

        }

        //http://localhost/myArt/index.php?ROUTE=USERS&ACTION=REGISTER&EMAIL=hasan7@gmail.com&PHONE=09365955580&FULLNAME=hassan7
        RequestParams params = app.getRequestParams();
        params.put(ROUTER.ROUTE, ROUTER.ROUTE_USERS);
        params.put(ROUTER.ACTION, ROUTER.ACTION_REGISTER);
        params.put(ROUTER.INPUT_EMAIL, email.getText().toString());
        params.put(ROUTER.INPUT_PHONE, phone.getText().toString());
        params.put(ROUTER.INPUT_FULLNAME, fullName.getText().toString());

        app.L(params+" isjihirjsj----------------");

        ArtClient.post(params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                app.L(response+"");
                //{"session":"38b9421b8a1f119e86bf5b4bdd9dc42a93f0d587","id":"20","state":"SUCCESS"}

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.has("error")){
                        app.T(jsonObject.getString("error"),ToastType.ERROR);
                    }else {
                        LoginObject loginObject = new Gson().fromJson(response, LoginObject.class);
                        if (loginObject.getState().equals(ROUTER.SUCCESS)){
                            String fullName1 = fullName.getText().toString();
                            app.T("خوش آمدید " + fullName1, ToastType.SUCCESS);

                            spref.getOurInstance().edit()
                                    .putString(ROUTER.SESSION , loginObject.getSession())
                                    .putString(ROUTER.INPUT_EMAIL, email.getText().toString())
                                    .putInt(ROUTER.USER_ID, loginObject.getId())
                                    .putString(ROUTER.INPUT_FULLNAME, fullName.getText().toString())
                                    .putInt(ROUTER.INPUT_PHONE, phoneInt)
                                    .apply();

                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }else {
                            app.T("مشکلی پیش آمد", ToastType.ERROR);
                        }
                    }
                } catch (JSONException e) {
                    app.T("مشکلی پیش آمد", ToastType.ERROR);
                }
                app.L("Response : " + response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                app.L("fail");
                app.L(statusCode+"--statusCode--- "+headers + "---headers---"+responseBody+ "---responseBody---"+error+"---error----");
            }
        });

    }
}