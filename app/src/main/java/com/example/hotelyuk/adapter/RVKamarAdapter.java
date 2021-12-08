package com.example.hotelyuk.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelyuk.databinding.RvItemKamarBinding;
import com.example.hotelyuk.entity.Kamar;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RVKamarAdapter extends RecyclerView.Adapter<RVKamarAdapter.viewHolder> {
    private List<Kamar> listKamar;

    public RVKamarAdapter(List<Kamar> listKamar){
        this.listKamar = listKamar;
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        RvItemKamarBinding rvItemKamarBinding;

        public viewHolder(@NonNull RvItemKamarBinding rvItemKamarBinding) {
            super(rvItemKamarBinding.getRoot());
            this.rvItemKamarBinding = rvItemKamarBinding;
        }
    }

    @NonNull
    @Override
    public RVKamarAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RvItemKamarBinding rvItemKamarBinding = RvItemKamarBinding.inflate(layoutInflater, parent, false);
        return new RVKamarAdapter.viewHolder(rvItemKamarBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RVKamarAdapter.viewHolder holder, int position) {
        Kamar kamar = listKamar.get(position);
        holder.setIsRecyclable(false);
        holder.rvItemKamarBinding.setKmr(kamar);
        holder.rvItemKamarBinding.executePendingBindings();

        DecimalFormat rupiahFormat = (DecimalFormat) DecimalFormat
                .getCurrencyInstance(new Locale("in", "ID"));
        holder.rvItemKamarBinding.tvHargaKamar.setText(rupiahFormat.format(kamar.getHarga()));
    }

    @Override
    public int getItemCount() {
        //  Disini kita memberitahu jumlah dari item pada recycler view kita.
        return listKamar.size();
    }

    public List<Kamar> getListKamar() {
        return listKamar;
    }

    public void setListKamar(List<Kamar> listKamar) {
        this.listKamar = listKamar;
    }

    public void clearList() {
        listKamar.clear();
    }
}
