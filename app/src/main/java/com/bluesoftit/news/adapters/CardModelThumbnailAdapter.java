package com.bluesoftit.news.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluesoftit.news.R;
import com.bluesoftit.news.databinding.CardModelThumbnailBinding;
import com.bluesoftit.news.models.ItemModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CardModelThumbnailAdapter extends RecyclerView.Adapter<CardModelThumbnailAdapter.ViewHolder>{


    Context context;
    ArrayList<ItemModel> thumbnailModels;

    public CardModelThumbnailAdapter(Context context, ArrayList<ItemModel> thumbnailModels) {
        this.context = context;
        this.thumbnailModels = thumbnailModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_model_thumbnail,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemModel itemModel = thumbnailModels.get(position);
        // All codes are goes there ---------->

        Glide.with(context)
                .load(itemModel.getUrlToImage())
                .placeholder(R.drawable.placeholder)
                .into(holder.binding.cardModelThumbnail);
        holder.binding.cardModelThumbnailTitle.setText(itemModel.getTitle());
        holder.binding.cardModelThumbnailDate.setText(itemModel.getDate());



    }

    @Override
    public int getItemCount() {
        return thumbnailModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardModelThumbnailBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CardModelThumbnailBinding.bind(itemView);
        }
    }
}
