package com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo;

import java.io.Serializable;

public class Usuario implements Serializable {

    public static final String TABLE_NAME = "usuario";
    public static final String TABLE_PK = "nombre";

    public static final String CREATE_TABLE = "CREATE TABLE \"usuario\" (\n" +
            "\"nombre\" TEXT NOT NULL,\n" +
            "\"contrasenia\" TEXT NOT NULL,\n" +
            "PRIMARY KEY (\"nombre\") \n" +
            ");";
    private String nombre = "";
    private String contrasenia = "";

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}