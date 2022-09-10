package com.bluesoftit.news.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluesoftit.news.R;
import com.bluesoftit.news.databinding.ThumbnailFullViewBinding;
import com.bluesoftit.news.models.ItemModel;
import com.bluesoftit.news.models.NewsModel;
import com.bumptech.glide.Glide;

import java.net.ContentHandler;
import java.util.ArrayList;

public class ThumbnailFullViewAdapter extends RecyclerView.Adapter<ThumbnailFullViewAdapter.ViewHolder>{

    Context context;
    ArrayList<ItemModel> models;

    public ThumbnailFullViewAdapter(Context context, ArrayList<ItemModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.thumbnail_full_view,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemModel model = models.get(position);
        // All codes are goes there ------------------->

        Glide.with(context)
                .load(model.getUrlToImage())
                .placeholder(R.drawable.placeholder)
                .into(holder.binding.thumbnailFullView);

        holder.binding.thumbFullViewDate.setText(model.getDate());
        holder.binding.thumbFullViewTitle.setText(model.getTitle());


    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ThumbnailFullViewBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ThumbnailFullViewBinding.bind(itemView);
        }
    }
}
