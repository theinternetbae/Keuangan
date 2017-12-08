package com.example.kaligaswag.keuangan.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kaligaswag on 08/12/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "keuangan.db";
    private static String TABLE_NAME_PEMASUKKAN = "pemasukkan";
    private static String TABLE_NAME_PENGELUARAN = "pengeluaran";
    private static String COL_1 = "id";
    private static String COL_2 = "judul";
    private static String COL_3 = "harga";
    private static String SALDO = "saldo";
    private static String TIMESTAMP = "timestamp";
    private static int DATABASE_VERSION = 1;
    private static String CREATE_TB_PEMASUKKAN = "create table pemasukkan(id INTEGER PRIMARY KEY AUTOINCREMENT, judul TEXT NOT NULL, " +
            "harga INTEGER NOT NULL, timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
    private static String CREATE_TB_PENGELUARAN = "create table pengeluaran(id INTEGER PRIMARY KEY AUTOINCREMENT, judul TEXT NOT NULL, " +
            "harga INTEGER NOT NULL, timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
    private static String CREATE_TB_SALDO = "create table mysaldo(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "saldo INTEGER, timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TB_PEMASUKKAN);
        db.execSQL(CREATE_TB_PENGELUARAN);
        db.execSQL(CREATE_TB_SALDO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PEMASUKKAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PENGELUARAN);
        db.execSQL("DROP TABLE IF EXISTS mysaldo");
        onCreate(db);
    }

    public void addData(String tableName, String judul, int jumlah){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2, judul);
        cv.put(COL_3, jumlah);
        db.insert(tableName, null, cv);
    }

    public void addSaldo(int saldo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SALDO, saldo);
        db.insert("mysaldo", null, cv);
    }

    public Cursor getAllPemasukkan(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.query(tableName,
                null,
                null,
                null,
                null,
                null,
                TIMESTAMP + " ASC");
    }

    public Cursor getAllPemasukkanView(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.query(TABLE_NAME_PEMASUKKAN,
                null,
                null,
                null,
                null,
                null,
                TIMESTAMP + " ASC");
    }

    public Cursor getAllPengeluaranView(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.query(TABLE_NAME_PENGELUARAN,
                null,
                null,
                null,
                null,
                null,
                TIMESTAMP + " ASC");
    }

    public Cursor getAVGPengeluaran(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT AVG(harga) AS myAVG FROM pengeluaran";
        return db.rawQuery(query, null);
    }

    public Cursor getSUMPengeluaran(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT SUM(harga) AS mySUM FROM pengeluaran";
        return db.rawQuery(query, null);
    }

    public Cursor getAVGPemasukkan(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT AVG(harga) AS myAVG FROM pemasukkan";
        return db.rawQuery(query, null);
    }

    public Cursor getSUMPemasukkan(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT SUM(harga) AS mySUM FROM pemasukkan";
        return db.rawQuery(query, null);
    }

    public Cursor getAllSaldo(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.query("mysaldo",
                null,
                null,
                null,
                null,
                null,
                TIMESTAMP + " ASC");
    }

}
