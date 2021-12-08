package com.example.hotelyuk.ui.nav.accounts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hotelyuk.R;
import com.example.hotelyuk.api.ApiClient;
import com.example.hotelyuk.api.ApiInterface;
import com.example.hotelyuk.apiresponse.UserResponse;
import com.example.hotelyuk.entity.User;
import com.example.hotelyuk.preferences.UserPreferences;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAccountActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CAMERA = 100;
    private static final int CAMERA_REQUEST = 0;
    private static final int GALLERY_PICTURE = 1;

    private ApiInterface apiService;
    private UserPreferences userPreferences;
    private User user;
    private EditText etEmail, etPassword, etNoTelp, etNamaUser;
    private Button btnCancel, btnConfirm;
    private ImageView ivImage;
    private LinearLayout layoutLoading;
    private Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        setTitle("Edit Akun");

        apiService = ApiClient.getClient().create(ApiInterface.class);
        userPreferences = new UserPreferences(EditAccountActivity.this);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etNoTelp = findViewById(R.id.et_no_telp);
        etNamaUser = findViewById(R.id.et_nama_user);
        ivImage = findViewById(R.id.iv_image);

        btnCancel = findViewById(R.id.btnCancelEdit);
        btnConfirm = findViewById(R.id.btnConfirmEdit);

        layoutLoading = findViewById(R.id.layout_loading);

        user = userPreferences.getUserLogin();

        etEmail.setText(user.getEmail());
        etPassword.setText(user.getPassword());
        etNoTelp.setText(user.getNo_telp());
        etNamaUser.setText(user.getUsername());
        Glide.with(EditAccountActivity.this)
                .load(user.getImg_url())
                .centerCrop()
                .placeholder(R.drawable.no_image)
                .into(ivImage);

        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(EditAccountActivity.this);
                View selectMediaView = layoutInflater
                        .inflate(R.layout.layout_select_media, null);

                final AlertDialog alertDialog = new AlertDialog
                        .Builder(selectMediaView.getContext()).create();

                Button btnKamera = selectMediaView.findViewById(R.id.btn_kamera);
                Button btnGaleri = selectMediaView.findViewById(R.id.btn_galeri);

                btnKamera.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (checkSelfPermission(Manifest.permission.CAMERA) ==
                                PackageManager.PERMISSION_DENIED) {
                            String[] permission = {Manifest.permission.CAMERA};
                            requestPermissions(permission, PERMISSION_REQUEST_CAMERA);
                        } else {
                            // Membuka kamera
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, CAMERA_REQUEST);
                        }

                        alertDialog.dismiss();
                    }
                });

                btnGaleri.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Membuka galeri
                        Intent intent = new Intent(Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, GALLERY_PICTURE);

                        alertDialog.dismiss();
                    }
                });

                alertDialog.setView(selectMediaView);
                alertDialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editUser();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null)
            return;

        if (resultCode == RESULT_OK && requestCode == GALLERY_PICTURE) {
            Uri selectedImage = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(selectedImage);
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (Exception e) {
                Toast.makeText(EditAccountActivity.this, e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        } else if (resultCode == RESULT_OK && requestCode == CAMERA_REQUEST) {
            bitmap = (Bitmap) data.getExtras().get("data");
        }

        bitmap = getResizedBitmap(bitmap, 512);
        ivImage.setImageBitmap(bitmap);
    }

    private Bitmap getResizedBitmap(Bitmap bitmap, int maxSize) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        float bitmapRatio = (float) width / (float) height;

        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }

        return Bitmap.createScaledBitmap(bitmap, width, height, true);
    }

    private String bitmapToBase64(Bitmap bitmap) {
        if (bitmap == null)
            return null;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }

    private void editUser() {
        setLoading(true);

        User editUser = new User(
                etNamaUser.getText().toString(),
                etEmail.getText().toString(),
                etPassword.getText().toString(),
                etNoTelp.getText().toString(),
                bitmapToBase64(bitmap));

        Call<UserResponse> call = apiService.editUser("Bearer " + userPreferences.getAccessToken(), editUser);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call,
                                   Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(EditAccountActivity.this,
                            response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    User user = response.body().getUser();
                    setUserPreferences(user.getUsername(), user.getEmail(), user.getPassword(),
                            user.getNo_telp(), user.getImg_url());
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                } else {
                    try {
                        JSONObject jObjError = new
                                JSONObject(response.errorBody().string());
                        Toast.makeText(EditAccountActivity.this,
                                jObjError.getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(EditAccountActivity.this,
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                setLoading(false);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(EditAccountActivity.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
                setLoading(false);
            }
        });
    }

    public void setUserPreferences(String username, String email, String password, String no_telp, String img_url) {
        userPreferences.setKeyUsername(username);
        userPreferences.setKeyEmail(email);
        userPreferences.setKeyPassword(password);
        userPreferences.setKeyNoTelp(no_telp);
        userPreferences.setKeyImgUrl(img_url);
    }

    // Fungsi ini digunakan menampilkan layout loading
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