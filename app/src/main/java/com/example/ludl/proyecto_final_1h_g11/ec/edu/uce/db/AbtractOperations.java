package com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.db;

import android.content.ContentValues;
import android.content.Entity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.Reserva;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.Usuario;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.Vehiculo;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public abstract class AbtractOperations<T> {


    private Class<T> entityClass;

    public AbtractOperations(Class<T> entityClass) {
        this.entityClass = entityClass;
    }


    public void delete(T entity) {

        SQLiteDatabase db = DBHelper.getDBWrite();

        try {
            Field f = entity.getClass().getDeclaredField(getTablePK(entity));
            f.setAccessible(true);

            Object o = f.get(entity);

            db.delete(getTableName(entity), getTablePK(entity) + " = ?", new String[]{String.valueOf(o.toString())});
            db.close();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }

    public Integer create(T entity) {

        ContentValues values = new ContentValues();

        Field[] f = entity.getClass().getDeclaredFields();

        for (Field ff : f) {
            if (Modifier.isPrivate(ff.getModifiers())) {
                ff.setAccessible(true);
                try {
                    Object o = ff.get(entity);
                    if (o instanceof String) {
                        values.put(ff.getName(), o.toString());
                    } else if (o instanceof Integer)
                        values.put(ff.getName(), Integer.valueOf(o.toString()));
                    else if (o instanceof Double) {
                        values.put(ff.getName(), Double.valueOf(o.toString()));
                    } else if (o instanceof Long) {
                        values.put(ff.getName(), Long.valueOf(o.toString()));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }

        }

        Object o = null;
        try {
            Field f1 = entity.getClass().getDeclaredField(getTablePK(entity));
            f1.setAccessible(true);

            o = f1.get(entity);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        SQLiteDatabase db = DBHelper.getDBWrite();
        int id = 0;
        if (o != null && entity instanceof Usuario) {
            // insert new row
            db.insert(getTableName(entity), null, values);
        } else if (o == null && entity instanceof Vehiculo) {
            db.insert(getTableName(entity), null, values);
        } else if (o == null && entity instanceof Reserva) {
            id = generateReservaId();
            values.put("id", id);
            db.insert(getTableName(entity), null, values);
        }
        //update row
        else {
            db.update(getTableName(entity), values, getTablePK(entity) + " = ?", new String[]{o.toString()});
        }

        // close db connection
        db.close();

        return id;
    }

    private int generateReservaId() {
        Random r = new Random();
        return r.nextInt((9999 - 1) + 1) + 1;
    }

    public List<T> listar() {
        List<T> resp = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + entityClass.getSimpleName().toLowerCase();

        SQLiteDatabase db = DBHelper.getDBRead();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                T obj = null;
                try {
                    obj = entityClass.newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
                Field[] f = obj.getClass().getDeclaredFields();

                for (Field ff : f) {
                    if (Modifier.isPrivate(ff.getModifiers())) {
                        ff.setAccessible(true);

                        try {
                            Object o = ff.get(obj);
                            if (o instanceof String) {
                                System.out.println("set " + ff.getName());
                                ff.set(obj, cursor.getString(cursor.getColumnIndex(ff.getName())));
                            } else if (o instanceof Integer) {
                                System.out.println("set " + ff.getName());
                                ff.set(obj, cursor.getInt(cursor.getColumnIndex(ff.getName())));
                            } else if (o instanceof Double) {
                                System.out.println("set " + ff.getName());
                                ff.set(obj, cursor.getDouble(cursor.getColumnIndex(ff.getName())));
                            } else if (o instanceof Long) {
                                System.out.println("set " + ff.getName());
                                ff.set(obj, cursor.getLong(cursor.getColumnIndex(ff.getName())));
                            } else if (o instanceof Short) {
                                System.out.println("set " + ff.getName());
                                ff.set(obj, cursor.getShort(cursor.getColumnIndex(ff.getName())));
                            }

                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }

                }
                resp.add((T) obj);

            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();


        System.out.println("repo fin " + resp.size());

        return resp;
    }

    public T buscar(Collection[] lista, Object parametro) {

        T obj = null;
        Object aux = lista[0];

        if (aux instanceof Vehiculo && parametro instanceof Integer) {

        } else if (aux instanceof Reserva && parametro instanceof Integer) {

        } else if (aux instanceof Reserva && parametro instanceof String) {
        }


        return obj;
    }

    private String getTableName(T entity) {

        String table_name = "";
        ContentValues values = new ContentValues();
        Field[] f0 = entity.getClass().getFields();
        for (Field f : f0) {
            try {
                if (f.getName().toString().equals("TABLE_NAME"))
                    table_name = (String) f.get(entity);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return table_name;
    }

    private String getTablePK(T entity) {

        String table_name = "";
        ContentValues values = new ContentValues();
        Field[] f0 = entity.getClass().getFields();
        for (Field f : f0) {
            try {
                if (f.getName().toString().equals("TABLE_PK")) table_name = (String) f.get(entity);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return table_name;
    }

}
