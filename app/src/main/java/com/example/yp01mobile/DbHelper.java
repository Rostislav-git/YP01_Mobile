package com.example.yp01mobile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "database";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE tablena ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name" + " TEXT,"
                + "price" + " TEXT,"
                + "img" + " TEXT"
                + ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // В этом методе ты можешь добавить логику обновления базы данных при изменении версии
        // Например, можешь удалить старые таблицы и создать их заново
        db.execSQL("DROP TABLE IF EXISTS tablena");
        onCreate(db);
    }
    public void clearTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("tablena", null, null);
        db.close();
    }
}
