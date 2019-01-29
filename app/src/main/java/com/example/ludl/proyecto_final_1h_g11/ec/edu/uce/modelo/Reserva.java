package com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo;

import java.io.Serializable;
import java.util.Calendar;

public class Reserva implements Serializable {

    public static final String TABLE_NAME = "reserva";
    public static final String TABLE_PK = "id";
    public static final String CREATE_TABLE = "CREATE TABLE \"reserva\" (\n" +
            "\"id\" INTEGER NOT NULL,\n" +
            "\"vehiculo_id\" INTEGER,\n" +
            "\"email\" TEXT,\n" +
            "\"celular\" TEXT,\n" +
            "\"fecReserva\" INTEGER,\n" +
            "\"fecEntrega\" INTEGER,\n" +
            "\"valor\" REAL,\n" +
            "PRIMARY KEY (\"id\") ,\n" +
            "CONSTRAINT \"fk_reserva_vehiculo_1\" FOREIGN KEY (\"vehiculo_id\") REFERENCES \"vehiculo\" (\"id\")\n" +
            ");";
    private String id = "";
    private Integer vehiculo_id = -1;
    private String email = "";
    private String celular = "";
    //private String placa = "";

    private Integer fecReserva = (int)(Calendar.getInstance().getTimeInMillis()/1000);
    private Integer fecEntrega = (int)(Calendar.getInstance().getTimeInMillis()/1000);

    private Integer valor =0;


    public void setReserva(Integer num_reserva, String placa, String email, String celular, Integer fecReserva, Integer fecEntrega, Integer valor_reserva) {
        this.setId(getId());
        this.setVehiculo_id(getVehiculo_id());
        this.setEmail(email);
        this.setCelular(celular);
        this.setFecReserva(fecReserva);
        this.setFecEntrega(fecEntrega);
        this.setValor(valor_reserva);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVehiculo_id() {
        return vehiculo_id;
    }

    public void setVehiculo_id(Integer vehiculo_id) {
        this.vehiculo_id = vehiculo_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    /*public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }*/

    public Integer getFecReserva() {
        return fecReserva;
    }

    public void setFecReserva(Integer fecReserva) {
        this.fecReserva = fecReserva;
    }

    public Integer getFecEntrega() {
        return fecEntrega;
    }

    public void setFecEntrega(Integer fecEntrega) {
        this.fecEntrega = fecEntrega;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }
}
