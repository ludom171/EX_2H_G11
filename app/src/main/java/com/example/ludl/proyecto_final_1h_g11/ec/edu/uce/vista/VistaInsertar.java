package com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.vista;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ludl.proyecto_final_1h_g11.R;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.controlador.VehiculosControlador;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.Vehiculo;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;


public class VistaInsertar extends AppCompatActivity {

    private static final int REQUEST_IMAGE = 100;
    private static final int PICK_IMAGE = 100;

    Uri imageUri;
    ImageView foto_gallery;
    Button galleria;

    EditText placa;
    EditText marca;
    //Date fecha;
    String date;
    CalendarView selecfecha;
    DateFormat formato;
    Date fechaDate;
    TextView auxFecha;
    EditText costo;
    Boolean matricula;
    CheckBox blanco;
    CheckBox negro;
    CheckBox otro;
    Boolean estado;
    CheckBox automovil;
    CheckBox furgoneta;
    CheckBox camioneta;
    Button btnSave;
    Button takePhoto;
    ImageView fotografia;
    //EditText correo;

    Boolean val_placa = false;

    Vehiculo auxVehiculo;
    Spinner opcionMatricula;
    Spinner opcionReserva;
    String[] opciones = {"Si", "No"};
    String[] opciones1 = {"Registrado", "No Registrado"};


    public VehiculosControlador getVehiculoControlador() {

        return new VehiculosControlador();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insertar);
        formato = new java.text.SimpleDateFormat("yyyy/MM/dd");
        fotografia = (ImageView) findViewById(R.id.image);
        takePhoto = (Button) findViewById(R.id.btn_foto);
        btnSave = (Button) findViewById(R.id.btn_insertar);
        placa = (EditText) findViewById(R.id.txt_placa);
        marca = (EditText) findViewById(R.id.txt_marca);
        costo = (EditText) findViewById(R.id.txt_costo);
        //correo = (EditText)  findViewById(R.id.txt_correo);
        blanco = (CheckBox) findViewById(R.id.ch_blanco);
        negro = (CheckBox) findViewById(R.id.ch_negro);
        otro = (CheckBox) findViewById(R.id.ch_otro);

        automovil = (CheckBox) findViewById(R.id.ch_automovil);
        furgoneta = (CheckBox) findViewById(R.id.ch_furgoneta);
        camioneta = (CheckBox) findViewById(R.id.ch_camioneta);

        selecfecha = (CalendarView) findViewById(R.id.calendario);
        selecfecha.setDate(Calendar.getInstance().getTimeInMillis());


        auxFecha = (TextView) findViewById(R.id.text_fecha);
        auxFecha.setText("" + formato.format(selecfecha.getDate()));

        opcionMatricula = (Spinner) findViewById(R.id.matricula);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        opcionMatricula.setAdapter(adapter);

        opcionReserva = (Spinner) findViewById(R.id.estado);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones1);
        opcionReserva.setAdapter(adapter1);

        foto_gallery = (ImageView) findViewById(R.id.foto_gallery);
        galleria = (Button) findViewById(R.id.galeria);


        selecfecha.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int anio, int mes, int dia) {
                mes = mes + 1;
                date = anio + "/" + mes + "/" + dia;

                try {
                    fechaDate = (Date) formato.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                auxFecha.setText(date);
            }
        });
        Intent i = getIntent();
        auxVehiculo = (Vehiculo) i.getSerializableExtra("vehiculo");
        if (auxVehiculo != null) cargarVehiculo(auxVehiculo);

        //Toast.makeText(this, date,Toast.LENGTH_SHORT).show();

    }

    public void Galeria(View vista) {
        openGallery();
    }

    public void saveVehiculo(View v) {
//va;idacioimn placa
        Pattern plc = Pattern.compile("^([A-Z]{3}-[0-9]{3,4})$");
        if (plc.matcher(placa.getText().toString()).matches() == false) {
            placa.setError("Placa Incorrecta");
            Toast.makeText(this, "Formato Incorrecto placa", Toast.LENGTH_SHORT).show();
            val_placa = false;
        } else {
            placa.setError(null);
            val_placa = true;
            try {

                String seleccion = opcionMatricula.getSelectedItem().toString();
                if (seleccion.equals("Si")) {
                    matricula = true;
                } else if (seleccion.equals("No")) {
                    matricula = false;
                }

                String seleccion1 = opcionReserva.getSelectedItem().toString();
                if (seleccion.equals("Con Reserva")) {
                    estado = true;
                } else if (seleccion.equals("Sin Reserva")) {
                    estado = false;
                }

                //EditText auxColor = (EditText) findViewById(R.id.txt_color);

                if (auxVehiculo == null) {
                    auxVehiculo = new Vehiculo();
                    auxVehiculo.setId(null);
                }

                if (automovil.isChecked() == true) {
                    auxVehiculo.setTipo("automovil");
                    furgoneta.setChecked(false);
                    camioneta.setChecked(false);
                } else if (furgoneta.isChecked() == true) {
                    auxVehiculo.setTipo("furgoneta");
                    automovil.setChecked(false);
                    camioneta.setChecked(false);
                } else if (camioneta.isChecked() == true) {
                    auxVehiculo.setTipo("camioneta");
                    furgoneta.setChecked(false);
                    automovil.setChecked(false);
                }


                if (negro.isChecked()) {
                    auxVehiculo.setColor("negro");
                    blanco.setChecked(false);
                    otro.setChecked(false);

                }
                if (blanco.isChecked() == true) {
                    auxVehiculo.setColor("blanco");
                    negro.setChecked(false);
                    otro.setChecked(false);

                }
                if (otro.isChecked()) {
                    auxVehiculo.setColor("otro");
                    blanco.setChecked(false);
                    negro.setChecked(false);

                }
                //    auxVehiculo.setVehiculo(vehiculo.getText().toString());
                auxVehiculo.setPlaca(placa.getText().toString());
                auxVehiculo.setMarca(marca.getText().toString());
                auxVehiculo.setFechaFabricacion((int) ((fechaDate.getTime() / 1000)));
                auxVehiculo.setCosto(Double.valueOf(costo.getText().toString()));

                //auxVehiculo.setMatriculado(matricula.);
//auxVehiculo.setCorreo(correo.getText().toString());


                //System.out.println(vehiculo.getText().toString());

                this.getVehiculoControlador().guardarVehiculo(auxVehiculo);

                this.mensaje("Datos Guardados");


                placa.setText("");
                marca.setText("");
                //correo.setText("");


                costo.setText("");
                //auxColor.setText("");

                Intent newform = new Intent(VistaInsertar.this, VistaVehiculos.class);
                finish();
                startActivity(newform);

            } catch (Exception ex) {
                ex.printStackTrace();
                this.mensaje("Datos No Guardados");
            }
            Toast.makeText(this, "Formato Correcto", Toast.LENGTH_SHORT).show();
        }

    }

    public void mensaje(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show();
    }

    @SuppressLint("SetTextI18n")
    private void cargarVehiculo(Vehiculo v) {
        btnSave.setText("Actualizar");
        //  this.vehiculo.setText(v.getVehiculo());
        this.placa.setText(v.getPlaca());
        this.marca.setText(v.getMarca());
        //this.correo.setText(v.getCorreo());
        this.auxFecha.setText(v.getFechaFabricacion().toString());
        Integer estadomatricula = v.getMatriculado();
        //  Toast.makeText(this, estadomatricula.toString(),Toast.LENGTH_SHORT).show();

        this.costo.setText(v.getCosto().toString());

        String auxTipAut = v.getTipo();

        if (auxTipAut.equals("automovil")) {
            automovil.setChecked(true);
        } else if (auxTipAut.equals("furgoneta")) {
            furgoneta.setChecked(true);
        } else {
            camioneta.setChecked(true);
        }


        String auxTipColor = v.getColor();

        if (auxTipColor.equals("blanco")) {
            blanco.setChecked(true);
        } else if (auxTipColor.equals("negro")) {
            negro.setChecked(true);
        } else {
            otro.setChecked(true);
        }

        selecfecha.setDate((long) v.getFechaFabricacion() * 1000);

        auxFecha.setText(formato.format((long) v.getFechaFabricacion() * 1000));


    }

    public void RegresarVehiculo(View view) {
        Intent newform = new Intent(VistaInsertar.this, VistaVehiculos.class);
        finish();
        startActivity(newform);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE && resultCode == Activity.RESULT_OK) {
            //Inicia y captura la imagen
            Bitmap userImage = (Bitmap) data.getExtras().get("data");
            fotografia.setImageBitmap(userImage);
            //foto_gallery.setImageURI(imageUri);
        }
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    public void TakeFoto(View view) {
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_IMAGE);
        } catch (ActivityNotFoundException e) {
            //Handle if no application exists
        }
    }


}