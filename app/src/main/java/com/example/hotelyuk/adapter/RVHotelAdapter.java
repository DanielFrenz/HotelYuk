package com.example.hotelyuk.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelyuk.databinding.RvItemHotelBinding;
import com.example.hotelyuk.entity.Hotel;
import com.example.hotelyuk.ui.nav.home.HotelDetailActivity;

import java.util.ArrayList;

public class RVHotelAdapter extends RecyclerView.Adapter<RVHotelAdapter.viewHolder> {
    ArrayList<Hotel> listHotel;

    public RVHotelAdapter(ArrayList<Hotel> listHotel){
        this.listHotel = listHotel;
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        RvItemHotelBinding rvItemHotelBinding;

        public viewHolder(@NonNull RvItemHotelBinding rvItemHotelBinding) {
            super(rvItemHotelBinding.getRoot());
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
        Hotel hotel = listHotel.get(position);
        holder.rvItemHotelBinding.setHtl(hotel);
        holder.rvItemHotelBinding.executePendingBindings();

        holder.rvItemHotelBinding.tvJumlahReview.setText(holder.rvItemHotelBinding.tvJumlahReview.getText() + " reviews");
        holder.rvItemHotelBinding.tvStartPrice.setText("IDR. " + holder.rvItemHotelBinding.tvStartPrice.getText());
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
        return listHotel.size();
    }
}
