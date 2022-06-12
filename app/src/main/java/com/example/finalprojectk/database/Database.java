package com.example.finalprojectk.database;

import android.content.Context;

import com.example.finalprojectk.object.Transaction;
import com.example.finalprojectk.object.Users;

import java.util.ArrayList;

public abstract class Database {
    private static DatabaseHelper data;
    public static ArrayList<Users> getUserData(Context context){
        ArrayList<Users> userList;
        data = new DatabaseHelper(context);
        userList = data.getUserList();
        return userList;
    }
    public static ArrayList<Transaction> getTransactionData(Context context, Integer userID){
        ArrayList<Transaction> transactionList;
        data = new DatabaseHelper(context);
        transactionList = data.getTransactionHistory(userID);
        return transactionList;
    }
    public static Users userLog;
}
