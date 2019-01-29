package com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.vista;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ludl.proyecto_final_1h_g11.R;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.controlador.ReservaControlador;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.Reserva;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.vista.adapter.AdapterReserva;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;


public class VistaReserva extends AppCompatActivity {

    private AdapterReserva adapter;
    private Reserva seleccionado;
    ArrayList<Reserva> arrayOfUsers;

    Button eliminar;
    Button editar;

    public ReservaControlador getReservaControlador() {

        return new ReservaControlador();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservas_inicio);
        eliminar = (Button) findViewById(R.id.re_insertar);
        editar = (Button) findViewById(R.id.re_editar);

    }

    public void Editar(View view) {
        Intent newform = new Intent(VistaReserva.this, InsertarReserva.class);
        newform.putExtra("vehiculo", seleccionado);
        finish();
        startActivity(newform);
    }

    public void Insertar(View view) {
        Intent newform = new Intent(VistaReserva.this, InsertarReserva.class);
        finish();
        startActivity(newform);
    }

    public void Eliminar(View view) {
        Toast.makeText(getApplicationContext(), "Eliminando...", Toast.LENGTH_SHORT).show();
        System.out.println(" vista eliminar " + seleccionado.getId());
        String aux = getReservaControlador().eliminar(seleccionado);
        if (aux != null) {
            arrayOfUsers.remove(seleccionado);
            adapter.notifyDataSetChanged();
        }
    }

    public void listar(View view) {
        //  File file = new File(GlobalApplication.getAppContext().getFilesDir(), "vehiculo.json");
        // if (!file.exists()) {
        //    Toast.makeText(getApplicationContext(),"No exsite vehiculos reistrados",Toast.LENGTH_SHORT).show();
        // }else{
        //recupera vehiculos
        //  arrayOfUsers = new ArrayList<Vehiculo>(getVehiculoControlador().getList());
        arrayOfUsers = (ArrayList<Reserva>) getReservaControlador().getList();
        System.out.println("numero de rservas " + arrayOfUsers.size());
        //formato de lista para mosyrar vehiculos
        adapter = new AdapterReserva(this, arrayOfUsers);
        final ListView listView = (ListView) findViewById(R.id.listaReservas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                seleccionado = adapter.getItem(position);
                System.out.println(position);
                System.out.println(id);

                for (int a = 0; a < parent.getChildCount(); a++) {
                    parent.getChildAt(a).setBackgroundColor(Color.TRANSPARENT);
                }
                view.setBackgroundResource(R.color.yourColor);
            }
        });
        //  }

        editar.setEnabled(true);
        eliminar.setEnabled(true);

    }

    public void Desconectar(View view) {
        Intent newform = new Intent(VistaReserva.this, VistaMenu.class);
        finish();
        startActivity(newform);
    }

}