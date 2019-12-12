package com.example.cursoradapter;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);

        DBAdapter dbAdapter = new DBAdapter(this);
        dbAdapter.insertarDatosCodigo();
        Cursor cursor = dbAdapter.consultarDatos();
        if (cursor!=null){

            String[] from = new String[]{"nombre","cate"};
            int[] to = new int[]{R.id.ciclo,R.id.cate};

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.spinner,cursor,from,to,0);
            spinner.setAdapter(adapter);
            dbAdapter.cerrarBD();

        }

    }
}
