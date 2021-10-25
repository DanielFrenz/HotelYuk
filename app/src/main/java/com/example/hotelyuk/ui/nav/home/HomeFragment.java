package com.example.hotelyuk.ui.nav.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hotelyuk.adapter.RVHotelAdapter;
import com.example.hotelyuk.data.DaftarHotel;
import com.example.hotelyuk.databinding.FragmentHomeBinding;
import com.example.hotelyuk.entity.Hotel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RVHotelAdapter rvHotelAdapter = new RVHotelAdapter(getHotel());
        binding.rvItemHotel.setAdapter(rvHotelAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private ArrayList<Hotel> getHotel() {
        ArrayList<Hotel> listHotel = new DaftarHotel().daftarHotel;
        return listHotel;
    }
}