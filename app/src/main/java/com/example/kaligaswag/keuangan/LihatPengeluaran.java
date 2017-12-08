package com.example.kaligaswag.keuangan;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kaligaswag.keuangan.Database.DBHelper;
import com.example.kaligaswag.keuangan.Database.MyAdapter;

public class LihatPengeluaran extends AppCompatActivity {

    DBHelper helper;
    MyAdapter adapter;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_pengeluaran);

        helper = new DBHelper(this);
        db = helper.getWritableDatabase();

        RecyclerView recyclerView = findViewById(R.id.recyclerviewPemasukkan);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this, helper.getAllPemasukkanView());
        recyclerView.setAdapter(adapter);



    }
}
