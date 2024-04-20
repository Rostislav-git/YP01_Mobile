package com.example.yp01mobile.boottomNav.MenuFragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.yp01mobile.DbHelper;
import com.example.yp01mobile.R;
import com.example.yp01mobile.boottomNav.Menu.MenuAdapter;
import com.example.yp01mobile.boottomNav.Menu.MenuClass;
import com.example.yp01mobile.boottomNav.SelectedItem.SelectedItemAdapter;
import com.example.yp01mobile.boottomNav.SelectedItem.SelectedItemClass;
import com.example.yp01mobile.databinding.FragmentFoodsBinding;

import java.util.ArrayList;
import java.util.List;

public class FoodsFragment extends Fragment implements MenuAdapter.OnMenoClickListener,SelectedItemAdapter.OnFoodClickListener {
    private FragmentFoodsBinding binding;
    SimpleCursorAdapter userAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFoodsBinding.inflate(inflater, container, false);
        loadItem();
        return binding.getRoot();
    }
    public void loadItem(){
        ArrayList<MenuClass> foods = new ArrayList<>();
        DbHelper dbHelper = new DbHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM foods", null);

        if (cursor.moveToFirst()) {
            do {
                int ni =cursor.getColumnIndex("name");
                int pi =cursor.getColumnIndex("price");
                int ii =cursor.getColumnIndex("img");
                String name = cursor.getString(ni);
                String price = cursor.getString(pi);
                String img = cursor.getString(ii);
                foods.add(new MenuClass(name, price, img));
            } while (cursor.moveToNext());
            cursor.close();

            // Set up the RecyclerView
            binding.foodsList.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
            binding.foodsList.setAdapter(new MenuAdapter(foods,this));
        } else {
            // Handle case when there are no rows in the cursor
        }

    }
    @Override
    public void onMenuClick(MenuClass food) {
        ArrayList<SelectedItemClass> sic = new ArrayList<>();
        sic.add(new SelectedItemClass(food.name, food.price, food.img));

        binding.foodsList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        binding.foodsList.setAdapter(new SelectedItemAdapter(sic,this));
    }

    @Override
    public void onItemClicked() {
        loadItem();
    }
}
