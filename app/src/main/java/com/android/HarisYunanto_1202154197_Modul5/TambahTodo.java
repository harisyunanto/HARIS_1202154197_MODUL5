package com.android.HarisYunanto_1202154197_Modul5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TambahTodo extends AppCompatActivity {

    //Deklarasikan Variabel
    DatabaseHelper myDb;
    EditText nameET , descET , prioET;
    RecyclerView recyclerView;
    MyAdapter adapter;
    Cursor cursor;
    Toast myToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_todo);

        //Referensikan Variabel
        recyclerView = (RecyclerView)findViewById(R.id.rv);
        //Pembuatan Objek DatabaseHelper Baru
        myDb = new DatabaseHelper(this);
        //Memanggil method getAllData untuk mengambil data yang ada
        cursor  = myDb.getAllData();

        //Referensikan Variabel ke ID
        nameET = (EditText)findViewById(R.id.namaTodo);
        descET = (EditText)findViewById(R.id.descTodo);
        prioET = (EditText)findViewById(R.id.prioTodo);

        //Deklarasikan Shared Preference
        SharedPreferences sharedP = this.getApplicationContext().
                getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Colourground", R.color.white);

        //pembuatan objek MyAdapter baru
        adapter = new MyAdapter(cursor, color, this);
    }

    //Method listener untuk tombol AddTOdo
    public void addDataClicked(View view) {
        boolean isInserted;
        //Deklarasikan variabel penampung isi dari edit text name, desc, dan priority diubah ke String
        String todoname = nameET.getText().toString();
        String description = descET.getText().toString();
        String priority = prioET.getText().toString();
        if(myToast!=null){
            myToast.cancel();
        }
        //Kondisi yang Harus Dipenuhi
        if(!todoname.isEmpty()&&!description.isEmpty()&&!priority.isEmpty()) {
           isInserted =  myDb.insertData(todoname,description,priority);
            if(!isInserted){
                //Jika Gagal Input
                myToast.makeText(this,"Data tidak berhasil diinputkan",
                        Toast.LENGTH_SHORT).show();
            }else {
                //Jika Berhasil Input
                myToast.makeText(this,"Data berhasil diinputkan",
                        Toast.LENGTH_SHORT).show();
                //Memulai Intent baru menuju MainActivity kembali
                startActivity(new Intent(TambahTodo.this, MainActivity.class));
            }
        }else {
            //jika terdapat edit text yang tidak diisi
            myToast.makeText(this,"Lengkapi terlebih dahulu field yang ada!",
                    Toast.LENGTH_SHORT).show();
        }

    }

}
