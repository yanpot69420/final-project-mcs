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
        String create_users = "CREATE TABLE \"users\" (\n" +
                "\t\"UserID\" integer primary key autoincrement,\n" +
                "\t\"UserEmailAddress\" varchar not null\t,\n" +
                "\t\"UserUsername\" varchar not null , \n" +
                "\t\"UserPassword\" varchar not null\n" +
                ");";
        String create_products = "CREATE TABLE \"product\" (\n" +
                "\t\"ProductName\" varchar primary key,\n" +
                "\t\"ProductRating\" integer not null,\n" +
                "\t\"ProductPrice\" integer not null,\n" +
                "\t\"ProductImage\" varchar not null,\n" +
                "\t\"ProductDescription\" varchar not null\n" +
                ");";
        String create_transaction = "CREATE TABLE \"transaction\" (\n" +
                "\t\"TransactionID\"\tinteger primary key autoincrement,\n" +
                "\t\"UserID\" varchar not null,\n" +
                "\t\"ProductName\" varchar not null\t,\n" +
                "\t\"TransactionDate\" date not null,\n" +
                "\t\"Quantity\" integer\tnot null,\n" +
                "\tforeign key (UserID)\n" +
                "\treferences users(UserID)\n" +
                "\ton update cascade\n" +
                "\ton delete cascade\n" +
                "\ton delete cascade,\n" +
                "\tforeign key (ProductName)\n" +
                "\treferences product(ProductName)\n" +
                "\ton update cascade\n" +
                "\ton delete cascade\n" +
                ");";
        sqLiteDatabase.execSQL(create_users);
        sqLiteDatabase.execSQL(create_products);
        sqLiteDatabase.execSQL(create_transaction);
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
