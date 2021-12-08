package com.example.hotelyuk.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelyuk.api.ApiClient;
import com.example.hotelyuk.api.ApiInterface;
import com.example.hotelyuk.apiresponse.ReviewResponse;
import com.example.hotelyuk.databinding.RvItemHotelBinding;
import com.example.hotelyuk.entity.Hotel;
import com.example.hotelyuk.entity.Review;
import com.example.hotelyuk.preferences.UserPreferences;
import com.example.hotelyuk.ui.auth.LoginActivity;
import com.example.hotelyuk.ui.nav.home.HotelDetailActivity;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RVHotelAdapter extends RecyclerView.Adapter<RVHotelAdapter.viewHolder> implements Filterable {
    private List<Hotel> listHotel, filteredListHotel;
    private Context context;
    private UserPreferences userPreferences;
    private ApiInterface apiService;
    private int jumlah_review;

    public RVHotelAdapter(List<Hotel> listHotel, Context context){
        this.listHotel = listHotel;
        filteredListHotel = new ArrayList<>(listHotel);
        this.context = context;
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        RvItemHotelBinding rvItemHotelBinding;

        public viewHolder(@NonNull RvItemHotelBinding rvItemHotelBinding) {
            super(rvItemHotelBinding.getRoot());
            apiService = ApiClient.getClient().create(ApiInterface.class);
            this.rvItemHotelBinding = rvItemHotelBinding;
        }
    }

    @NonNull
    @Override
    public RVHotelAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RvItemHotelBinding rvItemHotelBinding = RvItemHotelBinding.inflate(layoutInflater, parent, false);
        return new viewHolder(rvItemHotelBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RVHotelAdapter.viewHolder holder, int position) {
        Hotel hotel = filteredListHotel.get(position);

        userPreferences = new UserPreferences(context.getApplicationContext());

        holder.rvItemHotelBinding.setHtl(hotel);
        holder.rvItemHotelBinding.executePendingBindings();

//        setJumlahReview(hotel.getId());
//        holder.rvItemHotelBinding.tvJumlahReview.setText(String.valueOf(jumlah_review) + " reviews");
        holder.rvItemHotelBinding.tvJumlahReview.setText("5 reviews");

        DecimalFormat rupiahFormat = (DecimalFormat) DecimalFormat
                .getCurrencyInstance(new Locale("in", "ID"));
        holder.rvItemHotelBinding.tvStartPrice.setText(rupiahFormat.format(1000000));
        holder.rvItemHotelBinding.btnDetailHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getBindingAdapterPosition();
                Hotel hotelClick = listHotel.get(pos);
                Intent i = new Intent(view.getContext(), HotelDetailActivity.class);
                i.putExtra("hotel", hotelClick);
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        //  Disini kita memberitahu jumlah dari item pada recycler view kita.
        return filteredListHotel.size();
    }

    public void setListHotel(List<Hotel> listHotel) {
        this.listHotel = listHotel;
        filteredListHotel = new ArrayList<>(listHotel);
    }

    private void setJumlahReview(long id) {
        Call<ReviewResponse> call = apiService.getListReviewByHotel("Bearer " + userPreferences.getAccessToken(), id);

        call.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context.getApplicationContext(),
                            response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    List<Review> reviewList = response.body().getReviewList();
                    jumlah_review = reviewList.size();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(context, jObjError.getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {
                Toast.makeText(context, "Network error",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charSequenceString = charSequence.toString();
                List<Hotel> filtered = new ArrayList<>();

                if (charSequenceString.isEmpty()) {
                    filtered.addAll(listHotel);
                } else {
                    for (Hotel hotel : listHotel) {
                        if (hotel.getNamaHotel().toLowerCase()
                                .contains(charSequenceString.toLowerCase()))
                            filtered.add(hotel);
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filtered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredListHotel.clear();
                filteredListHotel.addAll((List<Hotel>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }
}
