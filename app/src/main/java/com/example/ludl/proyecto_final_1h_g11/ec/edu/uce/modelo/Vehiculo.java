package com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo;

import java.io.Serializable;
import java.util.Calendar;

public class Vehiculo implements Serializable {


    public static final String TABLE_NAME = "vehiculo";
    public static final String TABLE_PK = "id";
    public static final String CREATE_TABLE = "CREATE TABLE \"vehiculo\" (\n" +
            "\"id\" INTEGER  NOT NULL ,\n" +
            "\"placa\" TEXT NOT NULL,\n" +
            "\"marca\" TEXT,\n" +
            "\"fechaFabricacion\" INTEGER,\n" +
            "\"costo\" REAL,\n" +
            "\"matriculado\" INTEGER,\n" +
            "\"color\" TEXT,\n" +
            "\"foto\" TEXT,\n" +
            "\"estado\" INTEGER,\n" +
            "\"tipo\" INTEGER,\n" +
            "PRIMARY KEY (\"id\") \n" + ");";
    private Integer id = 0;
    // private String vehiculo = "";
    private String placa = "";
    private String marca = "";
    private Integer fechaFabricacion = (int) (Calendar.getInstance().getTime().getTime() / 1000L);
    private Double costo = 0.0d;
    private Integer matriculado = 0;

    //(Blanco, Negro, Otro)
    private String color = "";
    private String foto = "";
    private Integer estado = 0;
    //(1. Automovil,
    //2. Camioneta,
    //3. Furgoneta)
    private String tipo = "";


    //private String correo = "";

    public void Vehiculo() {
    }


    public void setVehiculo(Integer id, String vehiculo, String placa, String marca, Integer fecFabricacion, Double costo, Integer matriculado, String color) {
        // this.vehiculo = vehiculo;
        this.setId(id);
        this.setPlaca(placa);
        this.setMarca(marca);
        this.setFechaFabricacion(fecFabricacion);
        this.setCosto(costo);
        this.setMatriculado(matriculado);
        this.setColor(color);

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getFechaFabricacion() {
        return fechaFabricacion;
    }

    public void setFechaFabricacion(Integer fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Integer getMatriculado() {
        return matriculado;
    }

    public void setMatriculado(Integer matriculado) {
        this.matriculado = matriculado;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }


}