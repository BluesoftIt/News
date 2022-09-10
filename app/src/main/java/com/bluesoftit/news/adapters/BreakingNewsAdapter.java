package com.bluesoftit.news.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluesoftit.news.R;
import com.bluesoftit.news.databinding.CardModelBinding;
import com.bluesoftit.news.models.ItemModel;
import com.bluesoftit.news.models.NewsModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BreakingNewsAdapter extends RecyclerView.Adapter<BreakingNewsAdapter.ViewHolder>{

    Context context;
    ArrayList<NewsModel> newsModels;

    public BreakingNewsAdapter (Context context, ArrayList<NewsModel>newsModels){
        this.context = context;
        this.newsModels = newsModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_model,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsModel newsModel = newsModels.get(position);
        // All codes are goes there ---------->
        holder.binding.cardTitle.setText(newsModel.getTitle());
        holder.binding.cardDescription.setText(newsModel.getDescription());
        holder.binding.cardDate.setText(newsModel.getPublishedAt());
        Glide
                .with(context)
                .load(newsModel.getUrlToImage())
                .placeholder(R.drawable.placeholder)
                .into(holder.binding.cardThumbnail);


    }

    @Override
    public int getItemCount() {
        return newsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardModelBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CardModelBinding.bind(itemView);
        }
    }

}
