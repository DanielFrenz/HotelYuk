package com.example.hotelyuk.ui.nav.accounts;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelyuk.R;
import com.example.hotelyuk.preferences.UserPreferences;
import com.example.hotelyuk.room.model.User;
import com.example.hotelyuk.ui.auth.LoginActivity;

public class AccountsFragment extends Fragment {
    private User user;
    private UserPreferences userPreferences;
    private Button btnLogout;
    private TextView teksNamaProfil, teksEmail, teksNoTelp;

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
        userPreferences = new UserPreferences(super.getContext());
        user = userPreferences.getUserLogin();

        btnLogout = view.findViewById(R.id.btnLogout);
        teksNamaProfil = view.findViewById(R.id.teks_nama_profil);
        teksEmail = view.findViewById(R.id.teks_email);
        teksNoTelp = view.findViewById(R.id.teks_no_telp);

        teksNamaProfil.setText(user.getUsername());
        teksEmail.setText(" "+user.getEmail());
        teksNoTelp.setText(" "+user.getNo_telp());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPreferences.logout();
                Toast.makeText(getContext(), "Anda telah logout dari akun Anda", Toast.LENGTH_SHORT).show();
                checkLogin();
            }
        });
    }

    private void checkLogin(){
        if(!userPreferences.checkLogin()) {
            startActivity(new Intent(getContext(), LoginActivity.class));
            getActivity().finish();
        }
    }
}