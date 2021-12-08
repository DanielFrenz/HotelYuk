package com.example.hotelyuk.ui.nav.accounts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hotelyuk.R;
import com.example.hotelyuk.api.ApiClient;
import com.example.hotelyuk.api.ApiInterface;
import com.example.hotelyuk.apiresponse.UserResponse;
import com.example.hotelyuk.preferences.UserPreferences;
import com.example.hotelyuk.entity.User;
import com.example.hotelyuk.ui.auth.LoginActivity;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountsFragment extends Fragment {
    public static final int LAUNCH_EDIT_ACTIVITY = 123;

    private User user;
    private UserPreferences userPreferences;
    private Button btnEditAkun, btnHelp, btnReview, btnLogout;
    private TextView teksNamaProfil, teksEmail, teksNoTelp;
    private ImageView fotoProfil;
    private ApiInterface apiService;
    private LinearLayout layoutLoading;

    public AccountsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accounts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiService = ApiClient.getClient().create(ApiInterface.class);
        layoutLoading = view.findViewById(R.id.layout_loading);

        userPreferences = new UserPreferences(super.getContext());
        user = userPreferences.getUserLogin();

        btnEditAkun = view.findViewById(R.id.btnEditAkun);
        btnHelp = view.findViewById(R.id.btnHelp);
        btnReview = view.findViewById(R.id.btnReview);
        btnLogout = view.findViewById(R.id.btnLogout);

        teksNamaProfil = view.findViewById(R.id.teks_nama_profil);
        teksEmail = view.findViewById(R.id.teks_email);
        teksNoTelp = view.findViewById(R.id.teks_no_telp);
        fotoProfil = view.findViewById(R.id.foto_profil);

        teksNamaProfil.setText(user.getUsername());
        teksEmail.setText(" "+user.getEmail());
        teksNoTelp.setText(" "+user.getNo_telp());
        Glide.with(AccountsFragment.this)
                .load(user.getImg_url())
                .centerCrop()
                .circleCrop()
                .placeholder(R.drawable.no_image)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(fotoProfil);

        btnEditAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), EditAccountActivity.class);
                startActivityForResult(i, LAUNCH_EDIT_ACTIVITY);
            }
        });

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), PusatBantuanActivity.class));
            }
        });

        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ReviewSayaActivity.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LAUNCH_EDIT_ACTIVITY && resultCode == Activity.RESULT_OK)
        {
            user = userPreferences.getUserLogin();
            teksNamaProfil.setText(user.getUsername());
            teksEmail.setText(" "+user.getEmail());
            teksNoTelp.setText(" "+user.getNo_telp());
            Glide.with(AccountsFragment.this)
                    .load(user.getImg_url())
                    .centerCrop()
                    .circleCrop()
                    .placeholder(R.drawable.no_image)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(fotoProfil);
        }
    }

    private void checkLogin(){
        if(!userPreferences.checkLogin()) {
            startActivity(new Intent(getContext(), LoginActivity.class));
            getActivity().finish();
        }
    }

    private void logout() {
        setLoading(true);
        Call<UserResponse> call = apiService.logout("Bearer "+ userPreferences.getAccessToken());

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call,
                                   Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(),
                            response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    userPreferences.logout();
                    checkLogin();
                } else {
                    try {
                        JSONObject jObjError = new
                                JSONObject(response.errorBody().string());
                        Toast.makeText(getActivity(),
                                jObjError.getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(),
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                setLoading(false);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(getActivity(),
                        t.getMessage(), Toast.LENGTH_SHORT).show();
                setLoading(false);
            }
        });
    }

    private void setLoading(boolean isLoading) {
        if (isLoading) {
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.VISIBLE);
        } else {
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.INVISIBLE);
        }
    }
}