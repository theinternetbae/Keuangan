package com.example.kaligaswag.keuangan.Database;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kaligaswag.keuangan.R;

/**
 * Created by Kaligaswag on 08/12/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private Cursor cursor;

    public MyAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_pemasukkan, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (!cursor.moveToPosition(position)){
            return;
        }

        int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        String nama = cursor.getString(cursor.getColumnIndexOrThrow("judul"));
        int harga = cursor.getInt(cursor.getColumnIndexOrThrow("harga"));

        holder.no.setText(String.valueOf(id));
        holder.judul.setText(nama);
        holder.harga.setText(String.valueOf(harga));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if (cursor != null) {
            cursor.close();
        }

        cursor = newCursor;
        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView no, judul, harga;

        public MyViewHolder(View itemView) {
            super(itemView);
            no = itemView.findViewById(R.id.textViewNoPemasukkan);
            judul = itemView.findViewById(R.id.textViewJudulPemasukkan);
            harga = itemView.findViewById(R.id.textViewHargaPemasukkan);
        }
    }

}
