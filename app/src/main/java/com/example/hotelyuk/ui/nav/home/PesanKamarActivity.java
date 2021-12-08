package com.example.hotelyuk.ui.nav.home;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.hotelyuk.adapter.RVKamarAdapter;
import com.example.hotelyuk.adapter.RVReviewAdapter;
import com.example.hotelyuk.api.ApiClient;
import com.example.hotelyuk.api.ApiInterface;
import com.example.hotelyuk.apiresponse.KamarResponse;
import com.example.hotelyuk.apiresponse.ReviewResponse;
import com.example.hotelyuk.data.DaftarHotel;
import com.example.hotelyuk.databinding.ActivityPesanKamarBinding;
import com.example.hotelyuk.entity.Hotel;
import com.example.hotelyuk.preferences.UserPreferences;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesanKamarActivity extends AppCompatActivity {
    ActivityPesanKamarBinding binding;
    private Hotel hotel;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private RVKamarAdapter adapter;
    private ApiInterface apiService;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPesanKamarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiService = ApiClient.getClient().create(ApiInterface.class);

        userPreferences = new UserPreferences(this);

        // Obtaining data from intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            hotel = (Hotel)getIntent().getSerializableExtra("hotel");
        }

        // Set Title ke nama hotel
        setTitle(hotel.getNamaHotel());

        // Setup RV for Kamar
        adapter = new RVKamarAdapter(new ArrayList<>());
        adapter.clearList();
        binding.rvItemKamar.setAdapter(adapter);
        adapter.setListKamar(new DaftarHotel().daftarKamar);
//        getListKamar(hotel.getId());

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        binding.etTglMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar newCalendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(PesanKamarActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);

                        binding.etTglMulai.setText(dateFormatter.format(newDate.getTime()));
                    }
                },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });

        binding.etTglSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar newCalendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(PesanKamarActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);

                        binding.etTglSelesai.setText(dateFormatter.format(newDate.getTime()));
                    }
                },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });
    }

    private void getListKamar(long id) {
        Call<KamarResponse> call = apiService.getListKamar("Bearer " + userPreferences.getAccessToken(), id);

        call.enqueue(new Callback<KamarResponse>() {
            @Override
            public void onResponse(Call<KamarResponse> call, Response<KamarResponse> response) {
                if (response.isSuccessful()) {
                    adapter.setListKamar(response.body().getKamarList());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(PesanKamarActivity.this, jObjError.getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(PesanKamarActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<KamarResponse> call, Throwable t) {
                Toast.makeText(PesanKamarActivity.this, "Network error",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}