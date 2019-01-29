package com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.Usuario;

import java.util.List;

public class RepoUsuario extends AbtractOperations<Usuario> {

    public RepoUsuario() {
        super(Usuario.class);
    }

    private SQLiteDatabase db;

    public boolean login(String nom, String pass) {
        db = DBHelper.getDBWrite();
        System.out.print(nom+"  "+ pass);

        Cursor c = db.rawQuery("select * from usuario where nombre =? and contrasenia=?", new String[]{nom, pass});

        int aux = 0;
        if (c.moveToFirst()) {
            do {

                aux++;
                System.out.print("entrada "+ aux);
            } while (c.moveToNext());
        }

        // close db connection
        c.close();
        db.close();
        return aux == 1;

    }
}
