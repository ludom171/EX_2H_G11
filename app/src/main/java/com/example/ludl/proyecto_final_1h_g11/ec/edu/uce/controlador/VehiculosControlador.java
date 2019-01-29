package com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.controlador;

import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.ImplementacioCRUD;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.InterfazCRUD;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.ServicioOperaciones;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.Vehiculo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VehiculosControlador {

    InterfazCRUD crud = new ImplementacioCRUD();

   // public ServicioOperaciones op = new ServicioOperaciones();

    public void VehiculosControlador() {
    }

    public void guardarVehiculo(Vehiculo vehiculo) {
        // op.guardarVehiculo(vehiculo);
        crud.crear(vehiculo);
    }

    public VehiculosControlador() {
    }

    public List<Vehiculo> getList() {
        //return op.leerVehiculos();
       List<Vehiculo> a =new ArrayList<>(crud.listarVehiculo());
        for (Vehiculo v :
                a) {
            System.out.println("idddd: "+v.getId());
        }

        return  a;
    }

    public String eliminar(Vehiculo vehiculo) {

        return crud.borrar(vehiculo);
    }

    public Vehiculo buscar(Collection lista,String placa) {

        return (Vehiculo)crud.buscarPorParametro(lista,placa);
    }
}