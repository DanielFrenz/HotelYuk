package com.example.hotelyuk.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelyuk.R;
import com.example.hotelyuk.entity.HelpData;

import java.util.List;

public class RVBantuanAdapter extends RecyclerView.Adapter<RVBantuanAdapter.viewHolder>{
    private List<HelpData> listBantuan;

    public RVBantuanAdapter(List<HelpData> listBantuan){
        this.listBantuan = listBantuan;
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        CardView cvBantuan;
        RelativeLayout expandableLayout;
        TextView tvTitle, tvDescription;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            cvBantuan = itemView.findViewById(R.id.cv_bantuan);
            expandableLayout = itemView.findViewById(R.id.expanded_menu);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);

            cvBantuan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HelpData data = listBantuan.get(getAdapterPosition());
                    data.setExpandable(!data.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }

    @NonNull
    @Override
    public RVBantuanAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_bantuan,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RVBantuanAdapter.viewHolder holder, int position) {
        HelpData data = listBantuan.get(position);
        holder.tvTitle.setText(data.getTitle());
        holder.tvDescription.setText(data.getDesc());

        boolean isExpandable = listBantuan.get(position).isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

//        holder.cvBantuan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                HelpData data = listBantuan.get(position);
//                data.setExpandable(!data.isExpandable());
//                notifyItemChanged(position);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        //  Disini kita memberitahu jumlah dari item pada recycler view kita.
        return listBantuan.size();
    }

    public void clearList() {
        listBantuan.clear();
    }
}
