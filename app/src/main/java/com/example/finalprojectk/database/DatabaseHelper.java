package com.example.finalprojectk.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.finalprojectk.object.Product;
import com.example.finalprojectk.object.Transaction;
import com.example.finalprojectk.object.Users;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "insorma.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_users = "CREATE TABLE \"users\" (\n" +
                "\t\"UserID\" integer primary key autoincrement,\n" +
                "\t\"UserEmailAddress\" varchar not null\t,\n" +
                "\t\"UserUsername\" varchar not null , \n" +
                "\t\"UserPhoneNumber\" varchar not null,\n" +
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
        db.execSQL(create_users);
        db.execSQL(create_products);
        db.execSQL(create_transaction);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String drop_table = "drop table if exists users";
        String drop_table_2 = "drop table if exists product";
        String drop_table_3 = "drop table if exists history";
        db.execSQL(drop_table);
        db.execSQL(drop_table_2);
        db.execSQL(drop_table_3);
    }

    public Boolean addUser(Users user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("useremailaddress", user.getUserEmail());
        cv.put("userusername", user.getUserUsername());
        cv.put("userphonenumber", user.getUserPhoneNumber());
        cv.put("userpassword", user.getUserPassword());
        long insert = db.insert("users", null, cv);
        if(insert == -1) return false;
        else return true;
    }

    public Boolean addTransaction(Transaction transaction) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("userid", transaction.getUserID());
        cv.put("productname", transaction.getProductID());
        cv.put("transactiondate", transaction.getTransactionDate());
        cv.put("quantity", transaction.getQuantity());
        long insert  = db.insert("transaction", null, cv);
        if(insert == -1) return false;
        else return true;
    }

    public ArrayList<Users> getUserList(){
        ArrayList<Users> returnList = new ArrayList<>();
        String query = "select * from users";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do {
                int cID = cursor.getInt(0);
                String cEmail = cursor.getString(1);
                String cUsername = cursor.getString(2);
                String cUserPhone = cursor.getString(3);
                String cPassword = cursor.getString(4);
                Users users = new Users(cID, cEmail, cUsername, cUserPhone, cPassword);
                returnList.add(users);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return  returnList;
    }
}
