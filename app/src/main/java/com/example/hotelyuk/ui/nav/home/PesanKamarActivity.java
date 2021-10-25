package com.example.hotelyuk.ui.nav.home;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.example.hotelyuk.adapter.RVKamarAdapter;
import com.example.hotelyuk.databinding.ActivityPesanKamarBinding;
import com.example.hotelyuk.entity.Hotel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PesanKamarActivity extends AppCompatActivity {
    ActivityPesanKamarBinding binding;
    private Hotel hotel;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPesanKamarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtaining data from intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            hotel = (Hotel)getIntent().getSerializableExtra("hotel");
        }

        // Setup RV for Kamar
        RVKamarAdapter rvKamarAdapter = new RVKamarAdapter(hotel.getListKamar());
        binding.rvItemKamar.setAdapter(rvKamarAdapter);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        binding.etTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar newCalendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(PesanKamarActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);

                        binding.etTgl.setText(dateFormatter.format(newDate.getTime()));
                    }
                },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });
    }


}