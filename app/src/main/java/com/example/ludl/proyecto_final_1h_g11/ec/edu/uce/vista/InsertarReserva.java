package com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.vista;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ludl.proyecto_final_1h_g11.R;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.controlador.ReservaControlador;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.Reserva;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.Vehiculo;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class InsertarReserva extends AppCompatActivity {
    //Declaracion de Variables
    Button regresar;
    Button reservar;

    //Datos Insertar
    EditText num_reserva;
    EditText placa;
    EditText correo;
    EditText celular;

    String date_reserva;
    CalendarView selecfecha_reserva;
    DateFormat formato;
    Date fechaDate_reserva;
    TextView auxFecha_reserva;

    String date_entrega;
    CalendarView selecfecha_entrega;
    Date fechaDate_entrega;
    TextView auxFecha_entrega;

    CheckBox res_automovil;
    CheckBox res_furgoneta;
    CheckBox res_camioneta;

    EditText valor_reserva;
    Integer pagar;


    Vehiculo carro = new Vehiculo();

    Boolean val_placa = false;
    Boolean val_celular = false;

    Reserva auxreserva;
    ReservaControlador controladorReserva;

    private String user;
    private String pass;
    private String subject;
    private String body;
    private String recipient;

    public ReservaControlador getReservaControlador() {
        return new ReservaControlador();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        controladorReserva = getReservaControlador();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserva);

        //Inicializar Variales y captura de datos
        formato = new java.text.SimpleDateFormat("yyyy/MM/dd");

        regresar = (Button) findViewById(R.id.btn_regresar1);
        reservar = (Button) findViewById(R.id.btn_reservar);

        num_reserva = (EditText) findViewById(R.id.txt_numero_reserva);
        placa = (EditText) findViewById(R.id.txt_placa_reserva);
        correo = (EditText) findViewById(R.id.txt_email_reserva);
        celular = (EditText) findViewById(R.id.txt_celular);

        res_automovil = (CheckBox) findViewById(R.id.re_automovil);
        res_furgoneta = (CheckBox) findViewById(R.id.re_furgoneta);
        res_camioneta = (CheckBox) findViewById(R.id.re_camioneta);

        selecfecha_reserva = (CalendarView) findViewById(R.id.calendario_prestamo);
        auxFecha_reserva = (TextView) findViewById(R.id.text_fecha_prestamo);

        selecfecha_entrega = (CalendarView) findViewById(R.id.calendario_entrega);
        auxFecha_entrega = (TextView) findViewById(R.id.text_fecha_entrega);

        valor_reserva = (EditText) findViewById(R.id.txt_valor_reserva);

        valor_reserva.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                //validar que haya fecha de ingreso fecha de entrega, tipo de vehiculo
                if (hasFocus) {

                    // las fechas estan en unixtime >> nuemro entero
                    //restar fechas y sacar el numero de dias
                    //esos dias le multiplcas x el precio segun el tipo de auto

                    auxreserva.setFecReserva((int) (fechaDate_reserva.getTime() / 1000));
                    auxreserva.setFecEntrega((int) (fechaDate_entrega.getTime() / 1000));
                    int dias = (auxreserva.getFecEntrega() - auxreserva.getFecReserva()) / 86400;
                    if (res_automovil.isChecked()) {
                        pagar = (60 * dias);
                        res_furgoneta.setChecked(false);
                        res_camioneta.setChecked(false);
                    } else if (res_furgoneta.isChecked() == true) {
                        pagar = (100 * dias);
                        res_automovil.setChecked(false);
                        res_camioneta.setChecked(false);
                    } else if (res_camioneta.isChecked()) {
                        pagar = (75 * dias);
                        res_furgoneta.setChecked(false);
                        res_automovil.setChecked(false);
                    }

                    valor_reserva.setText("USD " + pagar + " por " + dias + " dias de uso");

                    Toast.makeText(getApplicationContext(), "Got the focus", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Lost the focus", Toast.LENGTH_LONG).show();
                }
                //caso contratio error falta datos


            }
        });

        //Captura y conversion de Fecha de reserva
        selecfecha_reserva.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int anio, int mes, int dia) {
                mes = mes + 1;
                date_reserva = anio + "/" + mes + "/" + dia;
                try {
                    fechaDate_reserva = (Date) formato.parse(date_reserva);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                auxFecha_reserva.setText(date_reserva);
            }
        });

        //Captura y conversion de Fecha de entrega
        selecfecha_entrega.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int anio, int mes, int dia) {
                mes = mes + 1;
                date_entrega = anio + "/" + mes + "/" + dia;

                try {
                    fechaDate_entrega = (Date) formato.parse(date_entrega);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                auxFecha_entrega.setText(date_entrega);
            }
        });

        Intent i = getIntent();

        auxreserva = (Reserva) i.getSerializableExtra("reserva");
        if (auxreserva != null) cargarReserva(auxreserva);
        if (auxreserva == null) {
            auxreserva = new Reserva();
            auxreserva.setId(null);
            //Toast.makeText(this, "No hay reservas", Toast.LENGTH_SHORT).show();
        }

        //Evento Regresar
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newform = new Intent(InsertarReserva.this, VistaReserva.class);
                finish();
                startActivity(newform);
            }
        });


    }


    public void saveReserva(View view) {
        Pattern plc = Pattern.compile("^([A-Z]{3}-[0-9]{3,4})$");
        //Pattern tlf = Pattern.compile("/^09[9|8|7][0-9]{7}$/");
        if ((plc.matcher(placa.getText().toString()).matches() == false)) {
            placa.setError("Placa Incorrecta");
            //celular.setError("Celular Incorrecto");

            Toast.makeText(this, "Datos Incorrectos", Toast.LENGTH_SHORT).show();
            val_placa = false;
            //val_celular = false;
        } else {
            placa.setError(null);
            //celular.setError(null);
            val_placa = true;
            //val_celular = true;
            try {
//nueva reserva


                //datos quemas de prueba
                //auxreserva.setPlaca(placa.getText().toString());
                //auxreserva.setEmail("Mail");
                auxreserva.setCelular(celular.getText().toString());
                auxreserva.setEmail(correo.getText().toString());


                //###################
                //##############


//guardar los campos en el objeto reserta
                //    auxVehiculo.setVehiculo(vehiculo.getText().toString());

                //  auxreserva.setId(num_reserva.getText().toString());

                String msg = controladorReserva.guadarReserva(auxreserva, placa.getText().toString());
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

                this.mensaje("Datos Guardados");
                this.mensaje("Reserva Guardada");


                placa.setText("");
                correo.setText("");
                celular.setText("");


                Intent newform = new Intent(InsertarReserva.this, VistaReserva.class);
                finish();
                startActivity(newform);


                auxreserva.setId(msg);
                auxreserva.setValor(pagar);
                sendMail2();


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

    public void Regresar(View view) {
        Intent newform = new Intent(InsertarReserva.this, VistaReserva.class);
        finish();
        startActivity(newform);
    }

    @SuppressLint("SetTextI18n")
    private void cargarReserva(Reserva v) {
        reservar.setText("Actualizar");
        //  this.vehiculo.setText(v.getVehiculo());
        this.num_reserva.setText(v.getId());
        this.placa.setText(v.getVehiculo_id());
        this.celular.setText(v.getCelular());
        this.correo.setText(v.getEmail());
        this.auxFecha_reserva.setText(v.getFecReserva().toString());
        this.auxFecha_entrega.setText(v.getFecEntrega().toString());


        //  Toast.makeText(this, estadomatricula.toString(),Toast.LENGTH_SHORT).show();


        this.valor_reserva.setText(v.getValor().toString());

        selecfecha_reserva.setDate((long) v.getFecReserva() * 1000);
        auxFecha_reserva.setText(formato.format((long) v.getFecReserva() * 1000));

        selecfecha_entrega.setDate((long) v.getFecEntrega() * 1000);
        auxFecha_entrega.setText(formato.format((long) v.getFecEntrega() * 1000));


    }


    public void sendMail2() {


        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    try {
                        System.out.println("email properties");
                        Properties props = new Properties();
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.starttls.enable", "true");
                        props.put("mail.smtp.host", "smtp.gmail.com");

                        props.put("mail.smtp.port", "587");
                        //  props.put("mail.smtp.ssl.trust", "true");

                        System.out.println("peticion auth");
                        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication("ludom2000@gmail.com", "Andreateamo2008");
                            }
                        });
                        System.out.println("escribiendo mensaje");
                        MimeMessage message = new MimeMessage(session);
                        message.setFrom(new InternetAddress("ludom2000@gmail.com"));

                        message.addRecipient(Message.RecipientType.TO, new InternetAddress(auxreserva.getEmail()));

                        message.setSubject("Reserva Número: " + auxreserva.getId());


                        message.setText("*Valor:  USD " + auxreserva.getValor() + " *Fecha Entrada: " + formato.format((long) auxreserva.getFecReserva() * 1000) + " *Fecha Salida: " + formato.format((long) auxreserva.getFecEntrega()));
                        System.out.println(" enviando");
                        Transport.send(message);
                        System.out.println("email enviado");

                    } catch (MessagingException e) {
                        e.printStackTrace();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }


}

