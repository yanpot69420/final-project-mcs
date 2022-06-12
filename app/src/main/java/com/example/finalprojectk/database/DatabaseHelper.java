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
                "\t\"user_id\" integer primary key autoincrement,\n" +
                "\t\"user_email\" varchar not null\t,\n" +
                "\t\"user_name\" varchar not null , \n" +
                "\t\"user_phone\" varchar not null,\n" +
                "\t\"user_password\" varchar not null\n" +
                ");";
        String create_products = "CREATE TABLE \"product\" (\n" +
                "\t\"product_name\" varchar primary key,\n" +
                "\t\"product_rating\" integer not null,\n" +
                "\t\"product_price\" integer not null,\n" +
                "\t\"product_image\" varchar not null,\n" +
                "\t\"product_description\" varchar not null\n" +
                ");";
        String create_transaction = "CREATE TABLE \"transactiondata\" (\n" +
                "\t\"transaction_id\"\tinteger primary key autoincrement,\n" +
                "\t\"transaction_userid\" integer not null,\n" +
                "\t\"transaction_product\" varchar not null\t,\n" +
                "\t\"transaction_date\" varchar not null,\n" +
                "\t\"transaction_quantity\" integer\tnot null,\n" +
                "\tforeign key (transaction_userid)\n" +
                "\treferences users(user_id)\n" +
                "\ton update cascade\n" +
                "\ton delete cascade,\n" +
                "\tforeign key (transaction_product)\n" +
                "\treferences product(product_name)\n" +
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
        cv.put("user_email", user.getUserEmail());
        cv.put("user_name", user.getUserUsername());
        cv.put("user_phone", user.getUserPhoneNumber());
        cv.put("user_password", user.getUserPassword());
        long insert = db.insert("users", null, cv);
        if(insert == -1) return false;
        else return true;
    }

    public Boolean addTransaction(Transaction transaction) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("transaction_userid", transaction.getUserID());
        cv.put("transaction_product", transaction.getProductID());
        cv.put("transaction_date", transaction.getTransactionDate());
        cv.put("transaction_quantity", transaction.getQuantity());
        long insert  = db.insert("transactiondata", null, cv);
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

    public ArrayList<Transaction> getTransactionHistory(Integer userID){
        ArrayList<Transaction> returnList = new ArrayList<>();
        String query = "select * from transactiondata where transaction_userid = "+ userID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do {
                int cID = cursor.getInt(0);
                int cUserid = cursor.getInt(1);
                String cProduct = cursor.getString(2);
                String cDate = cursor.getString(3);
                Integer cQuantity = cursor.getInt(4);
                Transaction transaction = new Transaction(cID, cUserid, cProduct, cDate, cQuantity);
                returnList.add(transaction);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }
}
