package com.example.hackathon_final;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database_class extends SQLiteOpenHelper {
    private Context acv_context;
    public static final int acv_DATABASE_VERSION = 1;
    public static final String acv_DATABASE_NAME = "StudentDB.db";

    public static final String acv_TABLE_NAME = "Students";
    public static final String acv_COLUMN_ID = "_id";
    public static final String acv_COLUMN_NAME = "nombre";
    public static final String acv_COLUMN_DNI = "DNI";
    public static final String acv_COLUMN_PROFESOR = "profesor";
    public static final String acv_COLUMN_FECHA = "fecha";
    public static final String acv_COLUMN_GRADO = "grado";

    public database_class(Context context){
        super(context, acv_DATABASE_NAME, null, acv_DATABASE_VERSION);
        this.acv_context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String acv_query = "CREATE TABLE " + acv_TABLE_NAME +
                " (" + acv_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                acv_COLUMN_NAME + " TEXT, " +
                acv_COLUMN_DNI + " TEXT, " +
                acv_COLUMN_FECHA + " TEXT, " +
                acv_COLUMN_GRADO + " TEXT, " +
                acv_COLUMN_PROFESOR + " TEXT);";
        db.execSQL(acv_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String acv_query = "DROP TABLE IF EXISTS " + acv_TABLE_NAME;
        db.execSQL(acv_query);
        onCreate(db);
    }

    public void agregarStudent(String[] data){
        SQLiteDatabase acv_db = this.getWritableDatabase();
        ContentValues acv_cv = new ContentValues();

        acv_cv.put(acv_COLUMN_NAME, data[0]);
        acv_cv.put(acv_COLUMN_DNI, data[1]);
        acv_cv.put(acv_COLUMN_FECHA, data[2]);
        acv_cv.put(acv_COLUMN_GRADO, data[3]);
        acv_cv.put(acv_COLUMN_PROFESOR, data[4]);

        long acv_result = acv_db.insert(acv_TABLE_NAME, null, acv_cv);
        System.out.println(acv_result);
        acv_db.close();
    }

    public Cursor readAllData(){
        String acv_query = "SELECT * FROM " + acv_TABLE_NAME;
        SQLiteDatabase acv_db = this.getReadableDatabase();

        Cursor acv_cursor = null;

        if(acv_db != null){
            acv_cursor = acv_db.rawQuery(acv_query, null);
        }

        return acv_cursor;
    }

    public void deleteStudent(String toString) {
        // String acv_query = "DELETE FROM " + acv_TABLE_NAME + " WHERE "+ acv_COLUMN_DNI + " = " + toString;
        SQLiteDatabase acv_db = this.getReadableDatabase();
        acv_db.delete(acv_TABLE_NAME,"DNI = ?",new String[]{toString});
    }
}
