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

public class LihatPengeluaran extends AppCompatActivity {

    DBHelper helper;
    MyAdapter adapter;
    SQLiteDatabase db;
    TextView sumPemasukkan, avgPemasukkan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_pengeluaran);

        setTitle("Pemasukkanku");

        sumPemasukkan = findViewById(R.id.textViewSUMPemasukkan);
        avgPemasukkan = findViewById(R.id.textViewAVGPemasukkan);

        helper = new DBHelper(this);
        db = helper.getWritableDatabase();

        Cursor avg = helper.getAVGPemasukkan();
        Cursor sum = helper.getSUMPemasukkan();

        String result = "";
        String Sumresult = "";

        if (avg.moveToNext()) {
            result = String.valueOf((int) avg.getDouble(avg.getColumnIndex("myAVG")));
        }

        if (sum.moveToNext()){
            Sumresult = String.valueOf((int) sum.getDouble(sum.getColumnIndex("mySUM")));
        }

        avgPemasukkan.setText("Rp. " + result);
        sumPemasukkan.setText("Rp. " + Sumresult);

        RecyclerView recyclerView = findViewById(R.id.recyclerviewPemasukkan);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this, helper.getAllPemasukkanView());
        recyclerView.setAdapter(adapter);



    }
}
