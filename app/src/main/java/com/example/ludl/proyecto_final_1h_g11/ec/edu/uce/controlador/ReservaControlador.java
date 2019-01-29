package com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.controlador;

import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.ImplementacioCRUD;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.InterfazCRUD;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.Reserva;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.ServicioOperaciones;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class ReservaControlador {

    InterfazCRUD crud = new ImplementacioCRUD();

    //  public ServicioOperaciones op = new ServicioOperaciones();

    public void ReservaControlador() {
    }

    public String guadarReserva(Reserva reserva, String placa) {
        //op.guardarreserva(reserva);

        //buscar id segun la placa

        Reserva r = (Reserva) crud.buscarPorParametro(crud.listarReserva(), placa);

        if (r != null) {
            reserva.setVehiculo_id(r.getVehiculo_id());

            return  crud.crear(reserva);
        }
        else return "placa de vehiculo no encontrado";
    }

    public ReservaControlador() {
    }

    public List<Reserva> getList() {
        //return op.leerVehiculos();
        List<Reserva> a = new ArrayList<>(crud.listar());
        for (Reserva v : a) {
            System.out.println("idddd: " + v.getVehiculo_id());
        }

        return a;
    }

    public String eliminar(Reserva reserva) {
        crud.borrar(reserva);
        return "borrado";
    }
}
