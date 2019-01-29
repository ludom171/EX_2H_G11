package com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.Reserva;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.Usuario;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.Vehiculo;

public class DBHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    public static SQLiteOpenHelper dbHelper;

    // Database Name
    private static final String DATABASE_NAME = "reserva_db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        dbHelper = this;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Usuario.CREATE_TABLE);
        System.out.println("Base de datos creada usuario");
        db.execSQL(Vehiculo.CREATE_TABLE);
        System.out.println("Base de datos creada vehiculo");
        db.execSQL(Reserva.CREATE_TABLE);
        System.out.println("Base de datos creada reserva");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Usuario.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Vehiculo.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Reserva.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }


    public static SQLiteDatabase getDBWrite() {
        return dbHelper.getWritableDatabase();
    }

    public static SQLiteDatabase getDBRead() {
        return dbHelper.getReadableDatabase();
    }
}
