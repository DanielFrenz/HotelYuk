package com.example.hotelyuk.ui.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hotelyuk.MainActivity;
import com.example.hotelyuk.R;
import com.example.hotelyuk.api.ApiClient;
import com.example.hotelyuk.api.ApiInterface;
import com.example.hotelyuk.apiresponse.UserResponse;
import com.example.hotelyuk.entity.LoginData;
import com.example.hotelyuk.preferences.UserPreferences;
import com.example.hotelyuk.entity.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText etEmail, etPassword;
    private MaterialButton btnLogin, btnRegister;
    private UserPreferences userPreferences;
    private User user;
    private ApiInterface apiService;
    private LinearLayout layoutLoading;
    private String access_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");

        apiService = ApiClient.getClient().create(ApiInterface.class);
        userPreferences = new UserPreferences(LoginActivity.this);

        user = new User();

        layoutLoading = findViewById(R.id.layout_loading);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        /* Apps will check the login first from shared preferences */
        checkLogin();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateForm()){
                    loginUser();
                }
            }
        });
    }

    private void loginUser() {
        setLoading(true);
        LoginData loginData = new LoginData(etEmail.getText().toString(), etPassword.getText().toString());
        Call<UserResponse> call = apiService.loginUser(loginData);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call,
                                   Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this,
                            response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    access_token = response.body().getAccessToken();
                    user = response.body().getUser();

                    userPreferences.setUser(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(),
                            user.getNo_telp(), user.getImg_url(), access_token);
                    checkLogin();
                } else {
                    try {
                        JSONObject jObjError = new
                                JSONObject(response.errorBody().string());
                        Toast.makeText(LoginActivity.this,
                                jObjError.getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this,
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                setLoading(false);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
                setLoading(false);
            }
        });
    }

    private boolean validateForm(){
        /* Check username & password is empty or not */
        if(etEmail.getText().toString().trim().isEmpty() || etPassword.getText().toString().trim().isEmpty()){
            Toast.makeText(LoginActivity.this,"Email Atau Password Kosong",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void checkLogin(){
        if(userPreferences.checkLogin()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    private void setLoading(boolean isLoading) {
        if (isLoading) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.VISIBLE);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.INVISIBLE);
        }
    }
}