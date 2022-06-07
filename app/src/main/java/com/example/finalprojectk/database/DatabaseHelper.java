package com.example.finalprojectk.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.example.finalprojectk.LandingActivity;
import com.example.finalprojectk.object.Users;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "insorma.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = LandingActivity.query;
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addUser(Users user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("UserEmailAddress", user.getUserEmail());
        cv.put("UserUsername", user.getUserUsername());
        cv.put("UserPhone", user.getUserPhoneNumber());
        cv.put("UserPassword", user.getUserPassword());

        long insert = db.insert("Users", null, cv);
        if(insert==-1) return false;
        else return true;
    }
}
