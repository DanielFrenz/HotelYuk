package com.example.hotelyuk.ui.nav.accounts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.hotelyuk.R;
import com.example.hotelyuk.adapter.RVReviewAdapter;
import com.example.hotelyuk.data.DaftarHotel;
import com.example.hotelyuk.databinding.ActivityReviewSayaBinding;

import java.util.ArrayList;

public class ReviewSayaActivity extends AppCompatActivity {
    private ActivityReviewSayaBinding binding;
    private RVReviewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityReviewSayaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTitle("Review Saya");

        adapter = new RVReviewAdapter(new ArrayList<>());
        adapter.clearList();
        binding.rvItemReviewUser.setAdapter(adapter);
        adapter.setListReview(new DaftarHotel().daftarReviewUser);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}