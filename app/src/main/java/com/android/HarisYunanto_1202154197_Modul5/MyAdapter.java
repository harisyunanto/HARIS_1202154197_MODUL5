package com.android.HarisYunanto_1202154197_Modul5;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    //Deklarasikan Variabel
    private Cursor mCursor;
    private Context mContext;
    int color;

    public MyAdapter(Cursor mCursor, int color, Context mContext) {
        //Deklarasikan Konstruktor yang Digunakan
        this.mCursor = mCursor;
        this.mContext = mContext;
        this.color = color;

    }

    //Holder untuk Recycler View
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        //Membuat View Baru
        View view = inflater.inflate(R.layout.text_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position)){
            return;
        }

        //Menentukan Nilai yang Disimpan Oleh View Holder
        String id =  mCursor.getString(mCursor.getColumnIndex(MyDatabaseContract.DatabaseScheme.DATABASE_ID));
        String todo  = mCursor.getString(mCursor.getColumnIndex(MyDatabaseContract.DatabaseScheme.NAMA_TODO));
        String desc = mCursor.getString(mCursor.getColumnIndex(MyDatabaseContract.DatabaseScheme.DESKRIPSI));
        String prio = mCursor.getString(mCursor.getColumnIndex(MyDatabaseContract.DatabaseScheme.PRIORITAS));
        holder.infoText1.setText(todo);
        holder.infoText2.setText(desc);
        holder.infoText3.setText(prio);
        holder.itemView.setTag(todo);
        holder.itemView.setTag(desc);
        holder.itemView.setTag(prio);
        holder.itemView.setTag(R.string.key,id);
        //Menentukan Warna berdasarkan Variabel Color
        holder.cv.setCardBackgroundColor(mContext.getResources().getColor(this.color));



    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    //Pertukaran Kursor untuk Setiap Perubahan pada Database
    public void swapCursor(Cursor newCursor) {
        //Selalu Menutup Kursor Sebelumnya Terlebih Dahulu
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            //Memaksa Recycler View untuk Refresh Data
            this.notifyDataSetChanged();
        }
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        //Deklarasikan Variabel
        TextView infoText1,infoText2,infoText3;
        CardView cv;

        public MyViewHolder(View itemView) {
            super(itemView);
            //Referensikan Variabel terhadap ID yang Sesuai
            infoText1 = (TextView)itemView.findViewById(R.id.recycling_text);
            infoText2 = (TextView)itemView.findViewById(R.id.recycling_text2);
            infoText3 = (TextView)itemView.findViewById(R.id.recycling_text3);
            cv = (CardView) itemView.findViewById(R.id.cardView);

        }
    }
}
