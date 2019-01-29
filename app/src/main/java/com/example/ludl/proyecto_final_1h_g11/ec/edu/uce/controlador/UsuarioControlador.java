package com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.controlador;

import android.content.Context;
import android.content.ContextWrapper;

import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.ImplementacioCRUD;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.InterfazCRUD;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.ServicioOperaciones;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.Usuario;

import java.util.ArrayList;

public class UsuarioControlador {

    public ServicioOperaciones op = new ServicioOperaciones();
    InterfazCRUD crud= new ImplementacioCRUD();

    public void UsuarioControlador() {
    }

    public void agregarUsuario(Usuario usuario) {
       // op.insertUsuario(usuario);
        crud.crear(usuario);
    }

    private ArrayList<Usuario> lisUsuario;

    public UsuarioControlador() {
    }

    public ArrayList<Usuario> getLisUsuario() {
        return lisUsuario;
    }

    public void setLisUsuario(ArrayList<Usuario> lisUsuario) {
        this.lisUsuario = lisUsuario;
    }

    public boolean login(String user, String pass) {
        //return op.login(user, pass);
        return crud.login(user,pass);

    }
}