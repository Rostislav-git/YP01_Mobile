package com.example.yp01mobile.boottomNav.SelectedItem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yp01mobile.OneItemScreen;
import com.example.yp01mobile.R;

import java.util.ArrayList;

public class SelectedItemAdapter extends RecyclerView.Adapter<SelectedItemViewHolder>{
    private ArrayList<SelectedItemClass> sItem = new ArrayList<>();
    private OnFoodClickListener listener;
    public int count;

    public interface OnFoodClickListener {
        // Define a method to be called in the activity
        void onItemClicked();
    }

    public SelectedItemAdapter(ArrayList<SelectedItemClass> sItem,OnFoodClickListener listener) {
        this.sItem = sItem;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SelectedItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_item_screen,parent,false);
        return new SelectedItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedItemViewHolder holder, int position) {
        SelectedItemClass item = sItem.get(position);
        if(count > 0)
        {
            Integer.parseInt(item.name);
            holder.name.setText(count*Integer.parseInt(item.name));
        }
        else holder.name.setText(item.name);
        holder.price.setText(item.price);
        Glide.with(holder.itemView.getContext()).load(item.img).into(holder.img);
        holder.itemView.findViewById(R.id.backItem).setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClicked();
            }
        });
        holder.itemView.findViewById(R.id.bntAddToCart).setOnClickListener(v -> {
            if (listener != null) {
                holder.itemView.findViewById(R.id.SelectedItem).setVisibility(View.GONE);
                holder.itemView.findViewById(R.id.AddToCart).setVisibility(View.VISIBLE);
            }
        });
        holder.itemView.findViewById(R.id.bntGoToCart).setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClicked();
            }
        });
        holder.itemView.findViewById(R.id.plus).setOnClickListener(v -> {
            if (listener != null) {
                count--;
            }
        });
        holder.itemView.findViewById(R.id.minus).setOnClickListener(v -> {
            if (listener != null) {
                count++;
            }
        });
        holder.itemView.findViewById(R.id.bntContinueShop).setOnClickListener(v -> {
            if (listener != null) {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, OneItemScreen.class);

                intent.putExtra("name", item.name);
                intent.putExtra("price", item.price);
                intent.putExtra("img", item.img);

                context.startActivity(intent);
            }
        });
        holder.itemView.findViewById(R.id.more).setOnClickListener(v -> {
            if (listener != null) {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, OneItemScreen.class);

                intent.putExtra("name", item.name);
                intent.putExtra("price", item.price);
                intent.putExtra("img", item.img);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
