package com.example.yp01mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.example.yp01mobile.databinding.ActivityLaunchScreenBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LaunchScreen extends AppCompatActivity {
    private ActivityLaunchScreenBinding binding;
    DbHelper dbHelper;
    Cursor cursor;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLaunchScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dbHelper = new DbHelper(this);
        db = dbHelper.getReadableDatabase();
        if (isNetworkAvailable(getApplicationContext())) {
            binding.progressBar.setVisibility(View.VISIBLE);
            loadFoods();
            loadDrinks();
            loadSnacks();
            loadSauce();
        } else {
            binding.progressBar.setVisibility(View.GONE);
            startActivity(new Intent(LaunchScreen.this, MainScreen.class));
            finish();

        }
    }
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public void loadFoods(){
        cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='foods';", null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {

            } else {

                db = dbHelper.getWritableDatabase();
                db.execSQL("CREATE TABLE foods ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "name" + " TEXT,"
                        + "price" + " TEXT,"
                        + "img" + " TEXT"
                        + ")");
            }
            cursor.close();
        }

        FirebaseDatabase.getInstance().getReference().child("Meal").child("Foods").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // db.delete("foods", null, null);
                    long count = snapshot.getChildrenCount();
                    db = dbHelper.getReadableDatabase();
                    cursor = db.rawQuery("SELECT COUNT(*) FROM foods", null);
                    int countSQLite;
                    if (cursor != null && cursor.moveToFirst()) {
                        countSQLite = cursor.getInt(0);
                        if (count > countSQLite) {
                            db.delete("foods", null, null);
                            for (DataSnapshot idd : snapshot.getChildren()) {
                                ContentValues cv = new ContentValues();
                                cv.put("name", idd.child("Name").getValue(String.class));
                                cv.put("price", idd.child("Price").getValue(String.class));
                                cv.put("img", idd.child("img").getValue(String.class));
                                db.insert("foods", null, cv);
                            }
                            startActivity(new Intent(LaunchScreen.this, MainScreen.class));
                            finish();
                        } else {
                            startActivity(new Intent(LaunchScreen.this, MainScreen.class));
                            finish();
                        }
                    }

                    if (cursor != null) {
                        cursor.close();
                    }
                    db.close();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Обработка ошибки
            }
        });
    }
    public void loadDrinks(){
        cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='drinks';", null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {

            } else {

                db = dbHelper.getWritableDatabase();
                db.execSQL("CREATE TABLE drinks ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "name" + " TEXT,"
                        + "price" + " TEXT,"
                        + "img" + " TEXT"
                        + ")");
            }
            cursor.close();
        }

        FirebaseDatabase.getInstance().getReference().child("Meal").child("Drinks").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // db.delete("drinks", null, null);
                    long count = snapshot.getChildrenCount();
                    db = dbHelper.getReadableDatabase();
                    cursor = db.rawQuery("SELECT COUNT(*) FROM drinks", null);
                    int countSQLite;
                    if (cursor != null && cursor.moveToFirst()) {
                        countSQLite = cursor.getInt(0);
                        if (count > countSQLite) {
                            db.delete("drinks", null, null);
                            for (DataSnapshot idd : snapshot.getChildren()) {
                                ContentValues cv = new ContentValues();
                                cv.put("name", idd.child("Name").getValue(String.class));
                                cv.put("price", idd.child("Price").getValue(String.class));
                                cv.put("img", idd.child("img").getValue(String.class));
                                db.insert("drinks", null, cv);

                            }
                            startActivity(new Intent(LaunchScreen.this, MainScreen.class));
                            finish();
                        } else {
                            startActivity(new Intent(LaunchScreen.this, MainScreen.class));
                            finish();
                        }
                    }

                    if (cursor != null) {
                        cursor.close();
                    }
                    db.close();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Обработка ошибки
            }
        });
    }
    public void loadSnacks(){
        cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='snacks';", null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {

            } else {

                db = dbHelper.getWritableDatabase();
                db.execSQL("CREATE TABLE snacks ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "name" + " TEXT,"
                        + "price" + " TEXT,"
                        + "img" + " TEXT"
                        + ")");
            }
            cursor.close();
        }

        FirebaseDatabase.getInstance().getReference().child("Meal").child("Snack").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    // db.delete("foods", null, null);
                    long count = snapshot.getChildrenCount();
                    db = dbHelper.getReadableDatabase();
                    cursor = db.rawQuery("SELECT COUNT(*) FROM snacks", null);
                    int countSQLite;
                    if (cursor != null && cursor.moveToFirst()) {
                        countSQLite = cursor.getInt(0);
                        if (count > countSQLite) {
                            db.delete("snacks", null, null);
                            for (DataSnapshot idd : snapshot.getChildren()) {
                                ContentValues cv = new ContentValues();
                                cv.put("name", idd.child("Name").getValue(String.class));
                                cv.put("price", idd.child("Price").getValue(String.class));
                                cv.put("img", idd.child("img").getValue(String.class));
                                db.insert("snacks", null, cv);

                            }
                            startActivity(new Intent(LaunchScreen.this, MainScreen.class));
                            finish();
                        } else {
                            startActivity(new Intent(LaunchScreen.this, MainScreen.class));
                            finish();
                        }
                    }

                    if (cursor != null) {
                        cursor.close();
                    }
                    db.close();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.d("HELPLP", error.getMessage());
            }
        });
    }
    public void loadSauce() {
        cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='sauce';", null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {

            } else {

                db = dbHelper.getWritableDatabase();
                db.execSQL("CREATE TABLE sauce ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "name" + " TEXT,"
                        + "price" + " TEXT,"
                        + "img" + " TEXT"
                        + ")");
            }
            cursor.close();
        }

        FirebaseDatabase.getInstance().getReference().child("Meal").child("Sauce").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    // db.delete("foods", null, null);
                    long count = snapshot.getChildrenCount();
                    db = dbHelper.getReadableDatabase();
                    cursor = db.rawQuery("SELECT COUNT(*) FROM sauce", null);
                    int countSQLite;
                    if (cursor != null && cursor.moveToFirst()) {
                        countSQLite = cursor.getInt(0);
                        if (count > countSQLite) {
                            db.delete("sauce", null, null);
                            for (DataSnapshot idd : snapshot.getChildren()) {
                                ContentValues cv = new ContentValues();
                                cv.put("name", idd.child("Name").getValue(String.class));
                                cv.put("price", idd.child("Price").getValue(String.class));
                                cv.put("img", idd.child("img").getValue(String.class));
                                db.insert("sauce", null, cv);

                            }
                            startActivity(new Intent(LaunchScreen.this, MainScreen.class));
                            finish();
                        } else {
                            startActivity(new Intent(LaunchScreen.this, MainScreen.class));
                            finish();
                        }
                    }

                    if (cursor != null) {
                        cursor.close();
                    }
                    db.close();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.d("HELPLP", error.getMessage());
            }
        });
    }
}
