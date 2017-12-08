package com.example.kaligaswag.keuangan;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.kaligaswag.keuangan.Database.DBHelper;
import com.example.kaligaswag.keuangan.Database.MyAdapter;

public class LihatPemasukkan extends AppCompatActivity {

    DBHelper helper;
    MyAdapter adapter;
    SQLiteDatabase db;
    TextView avgPengeluaran, sumPengeluaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_pemasukkan);

        setTitle("Pengeluaranku");

        avgPengeluaran = findViewById(R.id.textViewAVGPengeluaran);
        sumPengeluaran = findViewById(R.id.textViewSUMPengeluaran);

        helper = new DBHelper(this);
        db = helper.getWritableDatabase();
        Cursor avg = helper.getAVGPengeluaran();
        Cursor sum = helper.getSUMPengeluaran();

        String result = "";
        String sumResult = "";

        // get column value
        if (avg.moveToNext()) {
            result = String.valueOf((int) avg.getDouble(avg.getColumnIndex("myAVG")));
        }

        if (sum.moveToNext()){
            sumResult = String.valueOf((int) sum.getDouble(sum.getColumnIndex("mySUM")));
        }

        avgPengeluaran.setText("Rp. " + result);
        sumPengeluaran.setText("Rp. " + sumResult);

        RecyclerView recyclerView = findViewById(R.id.recyclerviewPengeluaran);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this, helper.getAllPengeluaranView());
        recyclerView.setAdapter(adapter);

    }
}
