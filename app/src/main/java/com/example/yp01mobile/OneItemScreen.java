package com.example.yp01mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.yp01mobile.databinding.ActivityOneItemScreenBinding;
import com.example.yp01mobile.databinding.ActivitySingUpScreenBinding;

public class OneItemScreen extends AppCompatActivity {
    private ActivityOneItemScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOneItemScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        if (intent != null) {
            binding.Name.setText(intent.getStringExtra("name"));
            binding.Price.setText(intent.getStringExtra("price"));
            Glide.with(this).load(intent.getStringExtra("img")).into(binding.img);
        }
        binding.back.setOnClickListener(v -> {
            startActivity(new Intent(OneItemScreen.this, MainScreen.class));
            finish();
        });
    }
}