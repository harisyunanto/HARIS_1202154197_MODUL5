package com.android.HarisYunanto_1202154197_Modul5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Inisiasikan Variabel serta Nama Database dan Versinya
    private static final String DATABASE_NAME = "deka.db";
    private static final int DATABASE_VERSION = 2;
    SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        //Deklarasikan Variavel Database dan Berikan Fungsi agar Dapat Ditulis (Writeable)
        db =  getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Membuat Tabel-Tabel di dalam Database pada kelas MyDatabaseContract
        db.execSQL("create table "+ MyDatabaseContract.DatabaseScheme.TABLE_NAME + " ( " +
                MyDatabaseContract.DatabaseScheme.DATABASE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MyDatabaseContract.DatabaseScheme.NAMA_TODO + " TEXT, " +
                MyDatabaseContract.DatabaseScheme.DESKRIPSI + " TEXT, "+
                MyDatabaseContract.DatabaseScheme.PRIORITAS + " TEXT);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop Tabel Jika Sudah Ada dan Mulai Pembuatan Baru
        db.execSQL("DROP TABLE IF EXISTS " + MyDatabaseContract.DatabaseScheme.TABLE_NAME);
        onCreate(db);
    }



    public boolean insertData(String todo, String desc, String prio){
        //Memasukkan Data yang diinputkan ke Dalam Tabel
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDatabaseContract.DatabaseScheme.NAMA_TODO,todo);
        contentValues.put(MyDatabaseContract.DatabaseScheme.DESKRIPSI,desc);
        contentValues.put(MyDatabaseContract.DatabaseScheme.PRIORITAS,prio);

        //Jika Data Tidak Dimasukkan
        long result = db.insert(MyDatabaseContract.DatabaseScheme.TABLE_NAME,null,contentValues);
        return result != -1;
    }

    public Cursor getAllData(){
        db = getWritableDatabase();
        return db.rawQuery("select * from "+MyDatabaseContract.DatabaseScheme.TABLE_NAME,null);

    }

    public boolean deleteDataSwipping(String id){
        return db.delete(MyDatabaseContract.DatabaseScheme.TABLE_NAME, MyDatabaseContract.DatabaseScheme.DATABASE_ID + "=" + id, null) > 0;
    }


}
