package com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.vista;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.ludl.proyecto_final_1h_g11.R;


public class VistaMenu extends AppCompatActivity {

    Button vehiculo;
    Button reserva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        vehiculo=(Button)findViewById(R.id.btn_vehiculo);
        reserva=(Button)findViewById(R.id.btn_reservas);

        vehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newform = new Intent(VistaMenu.this,VistaVehiculos.class);
                finish();
                startActivity(newform);
            }
        });


        reserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newform = new Intent(VistaMenu.this,VistaReserva.class);
                finish();
                startActivity(newform);
            }
        });

    }
}