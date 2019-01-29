package com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.vista;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ludl.proyecto_final_1h_g11.R;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.controlador.VehiculosControlador;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.GlobalApplication;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.Vehiculo;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.vista.adapter.AdapterVehiculo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VistaVehiculos extends AppCompatActivity {

    private AdapterVehiculo adapter;
    private Vehiculo seleccionado;
    ArrayList<Vehiculo> arrayOfUsers;

    Button eliminar;

    EditText placa;
    Button editar;

    public VehiculosControlador getVehiculoControlador() {

        return new VehiculosControlador();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehiculos);
        eliminar = (Button) findViewById(R.id.btn_eliminar);
        editar = (Button) findViewById(R.id.btn_editar);
        placa = (EditText) findViewById(R.id.txtb);

    }

    public void Editar(View view) {
        Intent newform = new Intent(VistaVehiculos.this, VistaInsertar.class);
        newform.putExtra("vehiculo", seleccionado);
        finish();
        startActivity(newform);
    }

    public void Insertar(View view) {

        Intent newform = new Intent(VistaVehiculos.this, VistaInsertar.class);
        finish();
        startActivity(newform);
    }

    public void Eliminar(View view) {
        Toast.makeText(getApplicationContext(), "Eliminando...", Toast.LENGTH_SHORT).show();
        System.out.println(" vista eliminar " + seleccionado.getId());
        String aux = getVehiculoControlador().eliminar(seleccionado);
        if (aux != null) {
            arrayOfUsers.remove(seleccionado);
            adapter.notifyDataSetChanged();
        }
    }

    public void buscar(View view) {
        Toast.makeText(getApplicationContext(), "Buscando...", Toast.LENGTH_SHORT).show();

        Vehiculo v = getVehiculoControlador().buscar(getVehiculoControlador().getList(),placa.getText().toString());
        if (v != null) {
            ArrayList res = new ArrayList();
            res.add(v);
            pintarElemento(res);
        }
    }

    public void listar(View view) {
        //  File file = new File(GlobalApplication.getAppContext().getFilesDir(), "vehiculo.json");
        // if (!file.exists()) {
        //    Toast.makeText(getApplicationContext(),"No exsite vehiculos reistrados",Toast.LENGTH_SHORT).show();
        // }else{
        //recupera vehiculos
        //  arrayOfUsers = new ArrayList<Vehiculo>(getVehiculoControlador().getList());
        arrayOfUsers = (ArrayList<Vehiculo>) getVehiculoControlador().getList();
        pintarElemento(arrayOfUsers);

    }

    private void pintarElemento(ArrayList<Vehiculo> list){

        for (Vehiculo vv :
                list) {
            System.out.println("boton listar: "+vv.getId());
            System.out.println("boton listar: "+vv.getPlaca());
        }

        //formato de lista para mosyrar vehiculos
        adapter = new AdapterVehiculo(this, list);
        final ListView listView = (ListView) findViewById(R.id.listaVehiculos);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                seleccionado = adapter.getItem(position);
                System.out.println(seleccionado.getId());
                System.out.println("fecha fab:"+seleccionado.getFechaFabricacion());
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
        Intent newform = new Intent(VistaVehiculos.this, VistaMenu.class);
        finish();
        startActivity(newform);
    }
}