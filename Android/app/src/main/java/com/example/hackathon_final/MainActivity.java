package com.example.hackathon_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final database_class acv_swapDB = new database_class(getApplicationContext());
        // test student:
        // acv_swapDB.agregarStudent(new String[]{"Alex Cassi de la Viuda", "47277519j", "10/10/2001","DAM","Ramon"});

        int counter = 0, counter_dam = 0, counter_daw = 0, counter_asix = 0;
        Cursor acv_cursor = acv_swapDB.readAllData();
        while (acv_cursor.moveToNext()) {
            System.out.println(acv_cursor.getString(1));
            System.out.println(acv_cursor.getString(2));
            System.out.println(acv_cursor.getString(3));
            System.out.println(acv_cursor.getString(4));

            String current_grado = acv_cursor.getString(4);

            switch(current_grado){
                case "DAM": counter_dam++; break;
                case "DAW": counter_daw++; break;
                case "ASIX": counter_asix++; break;
            }
            System.out.println(acv_cursor.getString(5));
            counter++;
        }

        TextView tw = findViewById(R.id.n_alumnos);
        TextView dam = findViewById(R.id.n_alumnosDAM), daw = findViewById(R.id.n_alumnosDAW), asix = findViewById(R.id.n_alumnosASIX);

        dam.setText("• Numero de alumnos de DAM: "+counter_dam);
        daw.setText("• Numero de alumnos de DAW: "+counter_daw);
        asix.setText("• Numero de alumnos de ASIX: "+counter_asix);
        tw.setText("• Numero de alumnos: "+counter);
    }

    public void add_student(View view) {
        Intent student_intent = new Intent(this, add_student.class);
        startActivity(student_intent);
    }

    public void remove_student(View view) {
        Intent remove_student = new Intent(this, remove_student.class);
        startActivity(remove_student);
    }

    public void view_all_students(View view) {
        Intent view_all_students = new Intent(this, all_students.class);
        startActivity(view_all_students);
    }
}