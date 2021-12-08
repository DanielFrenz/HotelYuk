package com.example.hotelyuk.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelyuk.databinding.RvItemReviewBinding;
import com.example.hotelyuk.entity.Review;

import java.util.ArrayList;
import java.util.List;

public class RVReviewAdapter extends RecyclerView.Adapter<RVReviewAdapter.viewHolder>{
    private List<Review> listReview;

    public RVReviewAdapter(List<Review> listReview){
        this.listReview = listReview;
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        RvItemReviewBinding rvItemReviewBinding;

        public viewHolder(@NonNull RvItemReviewBinding rvItemReviewBinding) {
            super(rvItemReviewBinding.getRoot());
            this.rvItemReviewBinding = rvItemReviewBinding;
        }
    }

    @NonNull
    @Override
    public RVReviewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RvItemReviewBinding rvItemReviewBinding = RvItemReviewBinding.inflate(layoutInflater, parent, false);
        return new RVReviewAdapter.viewHolder(rvItemReviewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RVReviewAdapter.viewHolder holder, int position) {
        Review review = listReview.get(position);
        holder.rvItemReviewBinding.setRev(review);
        holder.rvItemReviewBinding.executePendingBindings();

//        holder.rvItemReviewBinding.tvNamaUser.setText("User " + String.valueOf(review.getUserId()));
    }

    @Override
    public int getItemCount() {
        //  Disini kita memberitahu jumlah dari item pada recycler view kita.
        return listReview.size();
    }

    public List<Review> getListReview() {
        return listReview;
    }

    public void setListReview(List<Review> listReview) {
        this.listReview = listReview;
    }

    public void clearList() {
        listReview.clear();
    }
}
