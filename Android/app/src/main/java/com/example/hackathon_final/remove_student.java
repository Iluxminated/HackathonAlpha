package com.example.hackathon_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class remove_student extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_student);
    }

    public void remove_student(View view) {
        EditText et = findViewById(R.id.dni_delete);
        String dni_to_delete = et.getText().toString();

        if(check_if_dni_exists(dni_to_delete)){
            alertView("Estas seguro de eliminar al estudiante con DNI: "+dni_to_delete+" ?", dni_to_delete);
        } else {
            post_alert("El dni que intentas introducir no existe en la base de datos. Vuelvelo a intentar.");
        }
    }

    public boolean check_if_dni_exists(String dni){
        final database_class db = new database_class(getApplicationContext());
        Cursor acv_cursor = db.readAllData();
        while (acv_cursor.moveToNext()) {
            String compare_dni = acv_cursor.getString(2);

            if(compare_dni.equals(dni)){
                return true;
            }
        }

        return false;
    }

    private void alertView( String message, String dni) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(remove_student.this);
        dialog.setTitle( "• Confirma tu seleccion:" )
                .setMessage(message)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialoginterface, int i) {
                     dialoginterface.cancel();
                      }})
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        final database_class db = new database_class(getApplicationContext());
                        db.deleteStudent(dni);
                        post_alert("Has borrado al estudiante correctamente.");
                    }
                }).show();
    }

    private void post_alert(String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(remove_student.this);
        dialog.setTitle( "" )
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                    }
                }).show();
    }
}

