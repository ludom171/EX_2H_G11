package com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.vista;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ludl.proyecto_final_1h_g11.R;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.controlador.UsuarioControlador;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.db.DBHelper;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.GlobalApplication;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.ImplementacioCRUD;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.InterfazCRUD;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.Usuario;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button ingresar;
    Button registro;

    EditText user;
    EditText pass;
    UsuarioControlador controlador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       //DROP nad CREATE
        DBHelper db = new DBHelper(getApplicationContext());
        SQLiteDatabase dbTest = DBHelper.getDBRead();
        boolean res = (dbTest.getVersion() > 0);
        dbTest.close();
        if(!res) {
            db.onUpgrade(db.getWritableDatabase(),1,1);
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controlador = new UsuarioControlador();

        ingresar = findViewById(R.id.ingresar);
        registro = findViewById(R.id.registrar);

        user = findViewById(R.id.usuario);
        pass = findViewById(R.id.password);


        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              //  File file = new File(GlobalApplication.getAppContext().getFilesDir(), "usuario.json");

               // if (!file.exists()) {
                //    Toast.makeText(getApplicationContext(), "No existen Usuarios Registrados", Toast.LENGTH_SHORT).show();
               // } else {
                    if (controlador.login(user.getText().toString(), pass.getText().toString())) {
                        Intent newform = new Intent(MainActivity.this, VistaMenu.class);
                        finish();
                        startActivity(newform);
                    } else {
                        Toast.makeText(getApplicationContext(), "Usuario Incorrecto", Toast.LENGTH_SHORT).show();
                    }
              //  }
            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newform = new Intent(MainActivity.this, VistaRegistro.class);
                finish();
                startActivity(newform);
            }
        });
    }

    public void onClick(View v) {

    }
}