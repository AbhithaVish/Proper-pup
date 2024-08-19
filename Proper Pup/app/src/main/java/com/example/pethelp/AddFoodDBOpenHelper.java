package com.example.pethelp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AddFoodDBOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "petfood.db";
    private static final int DB_VERSION = 1;

    AddFoodDBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createFoodsTable = "CREATE TABLE foods(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "foodid TEXT," +
                "s1 TEXT," +
                "s2 TEXT," +
                "s3 INTEGER)";
        db.execSQL(createFoodsTable);

        String createCartTable = "CREATE TABLE cart(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "product_id TEXT," +
                "product_name TEXT," +
                "brand_name TEXT," +
                "price TEXT)";
        db.execSQL(createCartTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrade as needed
    }

    public void insertFood(SQLiteDatabase db, String foodid, String s1, String s2, String s3) {
        ContentValues values = new ContentValues();
        values.put("foodid", foodid);
        values.put("s1", s1);
        values.put("s2", s2);
        values.put("s3", s3);
        db.insert("foods", null, values);
    }

    public boolean insertToCart(SQLiteDatabase db, String productId, String productName, String brandName, String price) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("product_id", productId);
        contentValues.put("product_name", productName);
        contentValues.put("brand_name", brandName);
        contentValues.put("price", price);

        long result = db.insert("cart", null, contentValues);
        return result != -1; // Returns true if insertion was successful, false otherwise
    }

    public void updateMark(SQLiteDatabase db, String s1, String foodid) {
        ContentValues values = new ContentValues();
        values.put("s1", s1);
        db.update("foods", values, "foodid=?", new String[]{foodid});
    }

    public void deleteMark(SQLiteDatabase db, String foodid) {
        db.delete("foods", "foodid=?", new String[]{foodid});
    }

    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("SELECT * FROM foods", null);
    }
}
