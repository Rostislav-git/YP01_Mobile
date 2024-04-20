package com.example.yp01mobile.boottomNav.Menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yp01mobile.R;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder>{
    private ArrayList<MenuClass> menus = new ArrayList<>();
    private OnMenoClickListener listener;
    public MenuAdapter(ArrayList<MenuClass>menus, OnMenoClickListener listener) {
        this.menus = menus;
        this.listener = listener;
    }
    public interface OnMenoClickListener {
        void onMenuClick(MenuClass menu);
    }
    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foods_item,parent,false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuClass menu = menus.get(position);
        holder.name.setText(menu.name);
        holder.price.setText(menu.price);
        Glide.with(holder.itemView.getContext()).load(menu.img).into(holder.img);
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onMenuClick(menu);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }
}
