package com.bluesoftit.news.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluesoftit.news.R;
import com.bluesoftit.news.databinding.CardModelVerticalBinding;
import com.bluesoftit.news.models.ItemModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CardModelVerticalAdapter extends RecyclerView.Adapter<CardModelVerticalAdapter.ViewHolder> {

    Context context;
    ArrayList<ItemModel> verticalModels;

    public CardModelVerticalAdapter(Context context, ArrayList<ItemModel> verticalModels) {
        this.context = context;
        this.verticalModels = verticalModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_model_vertical,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemModel itemModel = verticalModels.get(position);
        // All codes are goes there ------------>

        Glide.with(context)
                .load(itemModel.getUrlToImage())
                .placeholder(R.drawable.placeholder)
                .into(holder.binding.cardVerticalThumbnail);
        holder.binding.cardVerticalTitle.setText(itemModel.getTitle());
        holder.binding.cardVerticalDate.setText(itemModel.getDate());





    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardModelVerticalBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CardModelVerticalBinding.bind(itemView);
        }
    }
}
