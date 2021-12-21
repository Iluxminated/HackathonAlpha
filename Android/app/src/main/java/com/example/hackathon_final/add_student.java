package com.example.hackathon_final;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class add_student extends AppCompatActivity {
    private static final String CERO = "0";
    private static final String BARRA = "/";

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        Spinner spin = findViewById(R.id.ciclos_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.grados, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        EditText et = findViewById(R.id.fecha);
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final int mesActual = month + 1;
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                et.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
            }
        },anio, mes, dia);

        // recogerFecha.getDatePicker().setMaxDate(c.get(c.YEAR));

        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recogerFecha.show();
            }
        });

    }

    public void add_student(View view) {
        final database_class db = new database_class(getApplicationContext());
        EditText nombre, DNI, fecha, profesor;
        Spinner ciclo;

        nombre = findViewById(R.id.nombre);
        DNI = findViewById(R.id.DNI);
        fecha = findViewById(R.id.fecha);
        ciclo = findViewById(R.id.ciclos_spinner);
        profesor = findViewById(R.id.profesor);

        try{
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(fecha.getText().toString());
        } catch (Exception e) {
            showSnackbar(view, "La fecha no es valida. Porfavor vuelvelo a intentar.");
            fecha.setError("Fecha no valida.");
        }

        if(valid_dni(DNI.getText().toString())){
                db.agregarStudent(new String[]{nombre.getText().toString(), DNI.getText().toString(), fecha.getText().toString(), ciclo.getSelectedItem().toString(), profesor.getText().toString()});
                showSnackbar(view, "Añadido ["+nombre.getText().toString()+"] correctamente.");

                // return to main activity:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
        } else {
            DNI.setError("DNI no valido.");
        }
    }

    public boolean valid_dni(String dni){
        char[] dni_digits = dni.toCharArray();
        String[] asignacionLetra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
        String letter = String.valueOf(dni_digits[dni_digits.length-1]).toUpperCase();

        String output = dni.substring(0, dni.length()-1);
        int dni_ = Integer.parseInt(output);
        int result = dni_%23;

        if(letter.equals(asignacionLetra[result])){
            final database_class db = new database_class(getApplicationContext());
            Cursor acv_cursor = db.readAllData();

            while (acv_cursor.moveToNext()) {
                String dni_check = acv_cursor.getString(2);

                if(dni_check.equals(dni)){
                    View view = findViewById(android.R.id.content).getRootView();
                    showSnackbar(view, "El DNI que has introducido ya esta en la base de datos. Porfavor vuelvelo a intentar.");
                    return false;
                }
            }

            return true;
        }

        View view = findViewById(android.R.id.content).getRootView();
        showSnackbar(view, "El DNI no es valido. Porfavor vuelvelo a intentar.");
        return false;
    }

    public void showSnackbar(View v, String message){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Snackbar acv_sb = Snackbar.make(v, message, Snackbar.LENGTH_LONG);
                View acv_mView = acv_sb.getView();
                TextView acv_mTextView = (TextView) acv_mView.findViewById(com.google.android.material.R.id.snackbar_text);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                    acv_mTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                else
                    acv_mTextView.setGravity(Gravity.CENTER_HORIZONTAL);
                acv_sb.show();
            }
        });
    }
}