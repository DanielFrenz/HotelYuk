package com.example.hotelyuk.ui.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hotelyuk.R;
import com.example.hotelyuk.preferences.UserPreferences;
import com.example.hotelyuk.room.database.DatabaseClient;
import com.example.hotelyuk.room.model.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText etEmail, etPassword, etUsername, etNoTelp;
    private MaterialButton btnRegister, btnLogin;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userPreferences = new UserPreferences(RegisterActivity.this);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etUsername = findViewById(R.id.etUsername);
        etNoTelp = findViewById(R.id.etNoTelp);

        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);


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
                    register(etEmail.getText().toString(), etPassword.getText().toString().trim(),
                            etUsername.getText().toString().trim(), etNoTelp.getText().toString().trim());
                }
            }
        });
    }

    private void register(String email, String password, String username, String no_telp){

        class RegisterUser extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                User user = new User();
                user.setEmail(email);
                user.setPassword(password);
                user.setUsername(username);
                user.setNo_telp(no_telp);

                DatabaseClient.getInstance(RegisterActivity.this)
                        .getDatabase()
                        .userDao()
                        .registerUser(user);

                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(RegisterActivity.this, "Berhasil mendaftar", Toast.LENGTH_SHORT).show();
                clearField();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }

        }

        RegisterUser registerUser = new RegisterUser();
        registerUser.execute();
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
}