package com.bluesoftit.news.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluesoftit.news.R;
import com.bluesoftit.news.databinding.NewsItemModelBinding;
import com.bluesoftit.news.models.ItemModel;
import com.bluesoftit.news.models.NewsModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    Context context;
    ArrayList<NewsModel> models;

    public NewsAdapter (Context context, ArrayList<NewsModel> models){
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item_model,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsModel model = models.get(position);
        // All codes are goes there ------------->
        Glide.with(context)
                .load(model.getUrlToImage())
                .placeholder(R.drawable.placeholder)
                .into(holder.binding.newsThumbnail);
        holder.binding.newsTitle.setText(model.getTitle());
        holder.binding.newsContent.setText(model.getDescription());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        NewsItemModelBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = NewsItemModelBinding.bind(itemView);
        }
    }
}
