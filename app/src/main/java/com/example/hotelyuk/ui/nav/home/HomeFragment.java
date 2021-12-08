package com.example.hotelyuk.ui.nav.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import android.widget.Toast;

import com.example.hotelyuk.R;
import com.example.hotelyuk.adapter.RVHotelAdapter;
import com.example.hotelyuk.api.ApiClient;
import com.example.hotelyuk.api.ApiInterface;
import com.example.hotelyuk.apiresponse.HotelResponse;
import com.example.hotelyuk.data.DaftarHotel;
import com.example.hotelyuk.databinding.FragmentHomeBinding;
import com.example.hotelyuk.entity.Hotel;
import com.example.hotelyuk.preferences.UserPreferences;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private UserPreferences userPreferences;
    private RVHotelAdapter adapter;
    private ApiInterface apiService;
    private LinearLayout layoutLoading;

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
        apiService = ApiClient.getClient().create(ApiInterface.class);
        layoutLoading = view.findViewById(R.id.layout_loading);

        userPreferences = new UserPreferences(super.getContext());

        adapter = new RVHotelAdapter(new ArrayList<>(), getContext());
        binding.rvItemHotel.setAdapter(adapter);

        binding.svHotel.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        getAllHotel();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getAllHotel() {
        setLoading(true);
        Call<HotelResponse> call = apiService.getAllHotel("Bearer " + userPreferences.getAccessToken());

        call.enqueue(new Callback<HotelResponse>() {
            @Override
            public void onResponse(Call<HotelResponse> call, Response<HotelResponse> response) {
                if (response.isSuccessful()) {
                    adapter.setListHotel(response.body().getHotelList());
                    adapter.getFilter().filter(binding.svHotel.getQuery());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getActivity(), jObjError.getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                setLoading(false);
            }

            @Override
            public void onFailure(Call<HotelResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Network error",
                        Toast.LENGTH_SHORT).show();
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
            layoutLoading.setVisibility(View.GONE);
        }
    }
}