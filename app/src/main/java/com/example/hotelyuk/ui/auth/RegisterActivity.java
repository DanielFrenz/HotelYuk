package com.example.hotelyuk.ui.auth;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hotelyuk.R;
import com.example.hotelyuk.api.ApiClient;
import com.example.hotelyuk.api.ApiInterface;
import com.example.hotelyuk.apiresponse.UserResponse;
import com.example.hotelyuk.preferences.UserPreferences;
import com.example.hotelyuk.entity.User;
import com.example.hotelyuk.unittesting.ActivityUtil;
import com.example.hotelyuk.unittesting.UserPresenter;
import com.example.hotelyuk.unittesting.UserService;
import com.example.hotelyuk.unittesting.UserView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements UserView {
    private TextInputEditText etEmail, etPassword, etUsername, etNoTelp;
    private MaterialButton btnRegister, btnLogin;
    private ApiInterface apiService;
    private LinearLayout layoutLoading;

    // Deklarasi Presenter
    private UserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setTitle("Register");

        apiService = ApiClient.getClient().create(ApiInterface.class);

        layoutLoading = findViewById(R.id.layout_loading);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etUsername = findViewById(R.id.etUsername);
        etNoTelp = findViewById(R.id.etNoTelp);

        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);

        // Mahasiswa Presenter
        presenter = new UserPresenter(this, new UserService());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateForm()){
                    registerUser();
                }
            }
        });
    }

    private String bitmapToBase64(Bitmap bitmap) {
        if(bitmap == null)
            return null;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }

    private void registerUser() {
        setLoading(true);

        User user = new User(
                etUsername.getText().toString(),
                etEmail.getText().toString(),
                etPassword.getText().toString(),
                etNoTelp.getText().toString(),
                bitmapToBase64(null));

        Call<UserResponse> call = apiService.registerUser(user);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call,
                                   Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this,
                            response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    clearField();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                } else {
                    try {
                        JSONObject jObjError = new
                                JSONObject(response.errorBody().string());
                        Toast.makeText(RegisterActivity.this,
                                jObjError.getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(RegisterActivity.this,
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                // Call Presenter buat Local Test
                presenter.onRegisterClicked();

                setLoading(false);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
                setLoading(false);
            }
        });
    }

    private void clearField(){
        etEmail.setText("");
        etPassword.setText("");
        etUsername.setText("");
        etNoTelp.setText("");
    }

    private boolean validateForm(){
        /* Check data is empty or not */
        if(etEmail.getText().toString().trim().isEmpty() || etPassword.getText().toString().trim().isEmpty()
                || etUsername.getText().toString().trim().isEmpty() || etNoTelp.getText().toString().trim().isEmpty()){
            Toast.makeText(RegisterActivity.this,"Tolong isi semua field yang dibutuhkan",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
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

    @Override
    public String getEmail() {
        return etEmail.getText().toString();
    }

    @Override
    public void showEmailError(String message) {
        etEmail.setError(message);
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @Override
    public void showPasswordError(String message) {
        etPassword.setError(message);
    }

    @Override
    public String getPhoneNumber() {
        return etNoTelp.getText().toString();
    }

    @Override
    public void showPhoneNumberError(String message) {
        etNoTelp.setError(message);
    }

    @Override
    public String getAccountName() {
        return etUsername.getText().toString();
    }

    @Override
    public void showAccountNameError(String message) {
        etUsername.setError(message);
    }

    @Override
    public void startMainRegister() {
        new ActivityUtil(this).startMainRegister();
    }

    @Override
    public void showRegisterError(String message) {
        Toast.makeText(this, message, LENGTH_SHORT).show();
    }

    @Override
    public void showErrorResponse(String message) {
        Toast.makeText(this, message, LENGTH_SHORT).show();
    }
}