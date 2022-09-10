package com.bluesoftit.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bluesoftit.news.databinding.ActivityMainBinding;
import com.bluesoftit.news.fragments.BusinessFragment;
import com.bluesoftit.news.fragments.LocalNewsFragment;
import com.bluesoftit.news.fragments.SportsFragment;
import com.bluesoftit.news.fragments.TechnologyFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ActivityMainBinding binding;
    ColorStateList def;
    TextView allNews, businessNews, financeNews, sportsNews, select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialization
        allNews = findViewById(R.id.allNews);
        businessNews = findViewById(R.id.businessNews);
        financeNews = findViewById(R.id.technologyNews);
        sportsNews = findViewById(R.id.sportsNews);
        select = findViewById(R.id.select);
        def = businessNews.getTextColors();

        allNews.setOnClickListener(this);
        businessNews.setOnClickListener(this);
        financeNews.setOnClickListener(this);
        sportsNews.setOnClickListener(this);
        setHomeFragment();

        binding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SearchActivity.class));
            }
        });

        binding.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Menu Clicked!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.allNews){
            select.animate().x(0).setDuration(100);
            allNews.setTextColor(Color.WHITE);
            businessNews.setTextColor(def);
            financeNews.setTextColor(def);
            sportsNews.setTextColor(def);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            LocalNewsFragment localNewsFragment = new LocalNewsFragment();
            transaction.replace(R.id.fragmentContainer,localNewsFragment);
            transaction.commit();
        }else if (v.getId() == R.id.businessNews){
            allNews.setTextColor(def);
            businessNews.setTextColor(Color.WHITE);
            financeNews.setTextColor(def);
            sportsNews.setTextColor(def);
            int size = businessNews.getWidth();
            select.animate().x(size).setDuration(100);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            BusinessFragment businessFragment = new BusinessFragment();
            transaction.replace(R.id.fragmentContainer,businessFragment);
            transaction.commit();
        }else if (v.getId() == R.id.technologyNews){
            allNews.setTextColor(def);
            businessNews.setTextColor(def);
            financeNews.setTextColor(Color.WHITE);
            sportsNews.setTextColor(def);
            int size = businessNews.getWidth()*2;
            select.animate().x(size).setDuration(100);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            TechnologyFragment technologyFragment = new TechnologyFragment();
            transaction.replace(R.id.fragmentContainer,technologyFragment);
            transaction.commit();
        }else if (v.getId() == R.id.sportsNews){
            allNews.setTextColor(def);
            businessNews.setTextColor(def);
            financeNews.setTextColor(def);
            sportsNews.setTextColor(Color.WHITE);
            int size = businessNews.getWidth()*3;
            select.animate().x(size).setDuration(100);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            SportsFragment sportsFragment = new SportsFragment();
            transaction.replace(R.id.fragmentContainer,sportsFragment);
            transaction.commit();
        }
    }

    private void setHomeFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        LocalNewsFragment localNewsFragment = new LocalNewsFragment();
        transaction.replace(R.id.fragmentContainer,localNewsFragment);
        transaction.commit();
    }
}