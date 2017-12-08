package com.example.kaligaswag.keuangan;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kaligaswag.keuangan.Database.DBHelper;
import com.example.kaligaswag.keuangan.Database.MyAdapter;

public class MainActivity extends AppCompatActivity {

    Spinner kategori;
    EditText judul, harga;
    Button tambah, pemasukkan, pengeluaran;
    DBHelper helper;
    MyAdapter adapter;
    SQLiteDatabase db;

    String[] pilihan = {"pilih kategori", "pemasukkan", "pengeluaran"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new DBHelper(this);
        db = helper.getWritableDatabase();


        kategori = findViewById(R.id.spinner);
        judul = findViewById(R.id.editTextJudul);
        harga = findViewById(R.id.editTextHarga);
        tambah = findViewById(R.id.buttonAdd);
        pemasukkan = findViewById(R.id.ButtonLihatPemasukkan);
        pengeluaran = findViewById(R.id.buttonPengeluaran);

        pengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LihatPemasukkan.class);
                startActivity(i);
            }
        });

        pemasukkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LihatPengeluaran.class);
                startActivity(i);
            }
        });

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, pilihan);
        kategori.setAdapter(spinnerAdapter);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jud = judul.getText().toString();
                String har = harga.getText().toString();
                if (jud.trim().length() == 0 || har.trim().length() == 0 || kategori.getSelectedItemPosition() == 0) {
                    Toast.makeText(getApplicationContext(), "silahkan lengkapi form terlebih dahulu", Toast.LENGTH_SHORT).show();
                } else {
                    String tableName = null;
                    switch (kategori.getSelectedItemPosition()) {
                        case 1:
                            tableName = "pemasukkan";
                            break;
                        case 2:
                            tableName = "pengeluaran";
                            break;
                    }
                    helper.addData(tableName, jud, Integer.parseInt(har));
                    adapter = new MyAdapter(getApplicationContext(), helper.getAllPemasukkan(tableName));
                    adapter.swapCursor(helper.getAllPemasukkan(tableName));
                    judul.getText().clear();
                    harga.getText().clear();
                    Toast.makeText(getApplicationContext(), "Data berhasil dimasukan ke tabel " + tableName, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
