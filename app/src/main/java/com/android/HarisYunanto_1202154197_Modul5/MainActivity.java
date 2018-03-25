package com.android.HarisYunanto_1202154197_Modul5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    RecyclerView recyclerView;
    Cursor cursor;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Deklarasikan Variabel yang Digunakan
        recyclerView = (RecyclerView)findViewById(R.id.rv);
        myDb = new DatabaseHelper(this);
        cursor  = myDb.getAllData();


        //Inisiasikan Shared Preference
        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Colourground", R.color.white);

        adapter = new MyAdapter(cursor,color,this);

        //2 Baris dibawah Ini Digunakan untuk Mengambil Data yang Telah Diinputkan
        //Kemudian Dimasukkan dengan Menggunakan Adapter
        adapter.swapCursor(myDb.getAllData());
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Menghapus Data dengan Menggunakan Swipe Ke Kiri atau Ke Kanan
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                String id = (String)viewHolder.itemView.getTag(R.string.key);
                //Memanggil Method deleteDataSwipping(id) untuk Menghapus Data
                myDb.deleteDataSwipping(id);
                //Mengambil Kembali Data yang Masih Ada/Tidak Dihapus
                adapter.swapCursor(myDb.getAllData());

            }
        }).attachToRecyclerView(recyclerView);  //Menampilkan Data pada Recycler View
    }

    //Listener Button addToDo
    public void addToDo(View view) {
        //Mengirim Intent ke Class TambahTodo
        startActivity(new Intent(MainActivity.this,TambahTodo.class));
    }

    //ketika menu pada activity di buat
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //membuat menu dengan layout menu_main
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Method yang dijalankan Ketika Item Dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Get id item yang dipilih
        int id = item.getItemId();
        //Kondisi yang dilakukan Jika Menu Settings Dipilih
        if (id== R.id.action_settings){
            //Membuat Intent Baru
            startActivity(new Intent(MainActivity.this, Setting.class));
            //Menutup Aktivitas Setelah Intent Dijalankan
            finish();
        }
        return true;
    }
}
