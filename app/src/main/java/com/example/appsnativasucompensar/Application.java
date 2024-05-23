package com.example.appsnativasucompensar;

import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.appsnativasucompensar.database.DbHelper;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the database
        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null) {
            Toast.makeText(this, "Database initialized", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to initialize database", Toast.LENGTH_SHORT).show();
        }
    }
}
