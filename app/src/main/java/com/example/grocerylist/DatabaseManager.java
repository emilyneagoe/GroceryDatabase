package com.example.grocerylist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.TreeSet;

public class DatabaseManager extends SQLiteOpenHelper {

    public DatabaseManager(Context context) { super(context, "GroceryDB", null, 1); }

    public void onCreate(SQLiteDatabase db) {
        String sql = "create table GroceryTable (id integer primary key autoincrement, product text, category text, quantity text)";
        db.execSQL(sql);
    }


    public void insert(String productName, String categoryName, String quantityName) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "insert into GroceryTable values(null, '"+productName+"', '"+categoryName+"', '"+quantityName+"')";
        db.execSQL(sql);
        db.close();
    }

    public TreeSet<String> getGrocery() {
        TreeSet<String> list = new TreeSet<String>();
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from GroceryTable";

        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()) {
            String category = cursor.getString(2);
            list.add(category);
        }
        db.close();
        return list;

    }

    public ArrayList<String> getCategory(String categoryName) {
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from GroceryTable where category = '"+categoryName+"'";

        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()) {
            String product = cursor.getString(1);
            list.add(product);
        }
        db.close();
        return list;

    }

    public String[] view(String productName) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from GroceryTable where product = '"+productName+"'";
        Cursor cursor = db.rawQuery(sql,null);
        String[] entry = new String[3];
        if(cursor.moveToFirst()) {
            String product = cursor.getString(1);
            String category = cursor.getString(2);
            String quantity = cursor.getString(3);

            entry[0] = product;
            entry[1] = category;
            entry[2] = quantity;

        }
        db.close();
        return entry;
    }


    public String deleteProduct(String product) {

        SQLiteDatabase db = getWritableDatabase();
        String sql = "delete from GroceryTable where product = '"+product+"'";
        db.execSQL(sql);
        return product;
    }

    public String deleteCategory(String category) {

        SQLiteDatabase db = getWritableDatabase();
        String sql = "delete from GroceryTable where category = '"+category+"'";
        db.execSQL(sql);
        return category;
    }



    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
