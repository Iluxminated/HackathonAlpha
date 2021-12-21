package com.example.hackathon_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class all_students extends AppCompatActivity {
    private ArrayList<String> nombres, dnis, fechas, grados, profesores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_students);

        nombres = new ArrayList<>();
        dnis = new ArrayList<>();
        fechas = new ArrayList<>();
        grados = new ArrayList<>();
        profesores = new ArrayList<>();

        final database_class acv_swapDB = new database_class(getApplicationContext());
        // test student:
        // acv_swapDB.agregarStudent(new String[]{"Alex Cassi de la Viuda", "47277519j", "10/10/2001","DAM","Ramon"});

        Cursor acv_cursor = acv_swapDB.readAllData();
        while (acv_cursor.moveToNext()) {
            nombres.add(acv_cursor.getString(1));
            dnis.add(acv_cursor.getString(2));
            fechas.add(acv_cursor.getString(3));
            grados.add(acv_cursor.getString(4));
            profesores.add(acv_cursor.getString(5));
        }

        RecyclerView acv_recyclerView = findViewById(R.id.recycler_viewStudents);
        ClaseAlmacenarArray acv_claseAlmacenarArray = new ClaseAlmacenarArray(nombres.toArray(new String[0]), dnis.toArray(new String[0]), fechas.toArray(new String[0]), grados.toArray(new String[0]), profesores.toArray(new String[0]), this);

        acv_recyclerView.setAdapter(acv_claseAlmacenarArray);
        acv_recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}