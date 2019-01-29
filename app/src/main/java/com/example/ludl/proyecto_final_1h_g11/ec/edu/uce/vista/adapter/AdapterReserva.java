package com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.vista.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ludl.proyecto_final_1h_g11.R;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.controlador.ReservaControlador;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.controlador.VehiculosControlador;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.Reserva;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.Vehiculo;

import java.text.DateFormat;
import java.util.ArrayList;

public class AdapterReserva extends ArrayAdapter<Reserva> {

    DateFormat formato;

    public AdapterReserva(Context context, ArrayList<Reserva> reservas) {

        super(context, 0, reservas);
       // reservas = new ArrayList<>(getReservaControlador().op.leerReserva());

    }

    public ReservaControlador getReservaControlador() {

        return new ReservaControlador();
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position

        Reserva r = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.reserva_item, parent, false);

        }

        // Ver los input para poner los datos




        // Return the completed view to render on screen
        // Ver los input para poner los datos
        formato = new java.text.SimpleDateFormat("yyyy/MM/dd");
        TextView n_reserva = (TextView) convertView.findViewById(R.id.num_reserva);
        TextView placa = (TextView) convertView.findViewById(R.id.placa);
        TextView email = (TextView) convertView.findViewById(R.id.e_mail);
        TextView celular = (TextView) convertView.findViewById(R.id.celular);
        TextView fecha_ini = (TextView) convertView.findViewById(R.id.feIngreso);
        TextView fecha_ent = (TextView) convertView.findViewById(R.id.feSalida);
        TextView valor = (TextView) convertView.findViewById(R.id.valor);

        //TextView correo = (TextView) convertView.findViewById(R.id.correo);


        // llenar con la info cada item
        //vehiculo.setText(v.getVehiculo());
        n_reserva.setText(r.getId());
      //  placa.setText(r.getPlaca());
        email.setText(r.getEmail());
        celular.setText(r.getCelular());


        fecha_ini.setText(formato.format((long)r.getFecReserva()*1000));
        fecha_ent.setText(formato.format((long)r.getFecEntrega()*1000));
        //Integer estadomatricula=v.getMatriculado();
        /*if(estadomatricula==1){
            matriculado.setText("Si");
        }else {
            matriculado.setText("No");
        }*/


        valor.setText(r.getValor().toString());

        return convertView;

    }
}
