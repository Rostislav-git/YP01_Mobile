package com.example.yp01mobile.boottomNav.SelectedItem;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yp01mobile.R;

public class SelectedItemViewHolder extends RecyclerView.ViewHolder{
    TextView name;
    TextView price;
    ImageView img;
    public SelectedItemViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.Name);
        price = itemView.findViewById(R.id.price);
        img = itemView.findViewById(R.id.imge);
    }
}
