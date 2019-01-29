package com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServicioOperaciones {

    private  String fileUser = "usuario.json";
    private  String fileVehiculo = "vehiculo.json";
    private String fileReserva = "reservas.json";
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();


    /*###########usuario##############*/
    public void insertUsuario(Usuario usuario) {
        comprobar(fileUser);
        String content = leerArchivo(fileUser);
        List<Usuario> list = json2ListUsuario(content);
        if (list == null)
            list = new ArrayList<>();
        list.add(usuario);

        escribirArchivo(fileUser, gson.toJson(list));
    }

    /*###########reservas##############*/
    public void insertReserva(Reserva reserva) {
        comprobar(fileReserva);
        String content = leerArchivo(fileReserva);
        List<Reserva> list = json2ListReserva(content);
        if (list == null)
            list = new ArrayList<>();
        list.add(reserva);

        escribirArchivo(fileUser, gson.toJson(list));
    }


    public List<Vehiculo> leerVehiculos() {
        String content = leerArchivo(fileVehiculo);
        return json2ListVehiculo(content);
    }

    public List<Reserva> leerReserva() {
        String content = leerArchivo(fileReserva);
        return json2ListReserva(content);
    }


    /*###########vehiculo##############*/
    public void guardarVehiculo(Vehiculo v) {
        System.out.println("guardar");
        int auxSate = 1;
        //1 nuevo
        //2 actualizar
        comprobar(fileVehiculo);
        String content = leerArchivo(fileVehiculo);
        List<Vehiculo> list = json2ListVehiculo(content);
        if (list == null)
            list = new ArrayList<>();
        for (Vehiculo ve : list) {
            if (ve.getId().equals(v.getId())) {
                //actualizar el mismo vehiculo
             //   ve.setVehiculo(v.getVehiculo(), v.getPlaca(), v.getMarca(), v.getFechaFabricacion(), v.getCosto(), v.isMatriculado(), v.getColor());
                auxSate = 2;
            }
        }
        if (auxSate == 1)
            list.add(v);
        escribirArchivo(fileVehiculo, gson.toJson(list));
    }

    /*###########reserva##############*/
    public void guardarreserva(Reserva r) {
        System.out.println("guardar");
        int auxSate = 1;
        //1 nuevo
        //2 actualizar
        comprobar(fileReserva);
        String content = leerArchivo(fileReserva);
        List<Reserva> list = json2ListReserva(content);
        if (list == null)
            list = new ArrayList<>();
        for (Reserva re : list) {
            if (re.getId().equals(r.getId())) {
                //actualizar el mismo vehiculo
           //     re.setReserva(re.getId(), re.getVehiculo_id(), re.getEmail(), re.getCelular(), re.getFecReserva(), re.getFecEntrega(), re.getValor());
                auxSate = 2;
            }
        }
        if (auxSate == 1)
            list.add(r);
        escribirArchivo(fileVehiculo, gson.toJson(list));
    }


    public boolean eliminarVehiculo(Vehiculo vehiculo) {

        comprobar(fileVehiculo);
        String content = leerArchivo(fileVehiculo);
        List<Vehiculo> list = json2ListVehiculo(content);

        for (Vehiculo v : list
        ) {
            if (v.getId().equals(vehiculo.getId())) {
                list.remove(v);
                escribirArchivo(fileVehiculo, gson.toJson(list).toString());
                return true;
            }
        }
        return false;
    }

    public void actualizar(Vehiculo vehiculo) {
        comprobar(fileVehiculo);
        String content = leerArchivo(fileVehiculo);
        List<Vehiculo> list = json2ListVehiculo(content);
        //buscar por la placa y setar los datos del objeto con los datos
        escribirArchivo(fileVehiculo, gson.toJson(list).toString());
    }

    //lee un archivo cualquiera
    private String leerArchivo(String filename) {
        try {
            File file = new File(GlobalApplication.getAppContext().getFilesDir(), filename);
            if (file.length() > 0) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                StringBuilder stringBuilder = new StringBuilder();
                String line = null;
                String ls = System.getProperty("line.separator");
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append(ls);
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                reader.close();
                return stringBuilder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    //escribe
    private void escribirArchivo(String filename, String content) {
        FileOutputStream outputStream;
        try {
            outputStream = GlobalApplication.getAppContext().openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Usuario
    private List<Usuario> json2ListUsuario(String content) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Usuario> res = gson.fromJson(content, new TypeToken<List<Usuario>>() {
        }.getType());
        return res;
    }

    //Vehiculo
    private List<Vehiculo> json2ListVehiculo(String content) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Vehiculo> res = gson.fromJson(content, new TypeToken<List<Vehiculo>>() {
        }.getType());
        return res;
    }

    //Reserva
    private List<Reserva> json2ListReserva(String content) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Reserva> res = gson.fromJson(content, new TypeToken<List<Reserva>>() {
        }.getType());
        return res;
    }


    private void comprobar(String filename) {
        File file = new File(GlobalApplication.getAppContext().getFilesDir(), filename);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean login(String user, String pass) {
        //cargar datos del json
        boolean resp = false;
        String content = leerArchivo("usuario.json");
        List<Usuario> list = json2ListUsuario(content);
        //buscar usuario en la lista
        for (int i = 0; i < list.size(); i++) {

            Usuario aux = list.get(i);
            //System.out.println(aux.getApellidos());
            //  if (aux.getCedula().equals(user) && aux.getContrasena().equals(pass))
            //      resp = true;
            //  }
            //responder peticion

        }return true;
    }
}