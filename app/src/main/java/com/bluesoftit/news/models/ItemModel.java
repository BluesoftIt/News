package com.bluesoftit.news.models;

public class ItemModel {
    public String title, description, urlToImage, date;

    public ItemModel(String title, String description, String urlToImage, String date) {
        this.title = title;
        this.description = description;
        this.urlToImage = urlToImage;
        this.date = date;
    }

    public ItemModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
