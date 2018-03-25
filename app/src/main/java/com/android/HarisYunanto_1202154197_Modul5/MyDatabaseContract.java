package com.android.HarisYunanto_1202154197_Modul5;

import android.provider.BaseColumns;


//Class yang Digunakan untuk Mendefinisikan Skema Database

public class MyDatabaseContract {

    public static final class DatabaseScheme implements BaseColumns {
        public static final String TABLE_NAME = "table_name";
        public static final String DATABASE_ID = "ID";
        public static final String NAMA_TODO ="todoname";
        public static final String DESKRIPSI = "description";
        public static final String PRIORITAS = "priority";
    }


}
