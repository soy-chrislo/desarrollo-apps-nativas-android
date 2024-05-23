package com.example.appsnativasucompensar.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.appsnativasucompensar.entities.User;

import java.util.ArrayList;

public class DbUsuarios extends DbHelper {
    Context context;
    public DbUsuarios(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertUser(String name, String password) {
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("password", password);

            id = db.insert("users", null, values);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    @SuppressLint("Recycle")
    public long getUser(String name, String password) {
        long id = -1;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String[] columns = {"id"};
            String selection = "name = ? AND password = ?";
            String[] selectionArgs = {name, password};

            Cursor cursor = db.query(
                    "users",
                    columns,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            if (cursor.moveToFirst()) {
                id = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
            }
            cursor.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    @SuppressLint("Range")
    public ArrayList<User> getUsers() {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ArrayList<User> users = new ArrayList<>();

        try {
            User user = null;
            Cursor cursor = null;
            cursor = db.rawQuery("SELECT * FROM users", null);

            if (cursor.moveToFirst()) {
                do {
                    user = new User();
                    user.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    user.setName(cursor.getString(cursor.getColumnIndex("name")));
                    user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                    users.add(user);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
}
