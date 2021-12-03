package com.example.chemas;

import android.content.ContentValues;

import com.example.chemas.database.LibreriaContract;

public class Autor {
    private int autor_id, edad;
    private String nombre, apellido, nacionalidad;
    public Autor(int autor_id, int edad, String nombre, String apellido, String nacionalidad) {
        this.autor_id = autor_id;
        this.edad = edad;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
    }

    public int getAutor_id() {
        return autor_id;
    }

    public void setAutor_id(int autor_id) {
        this.autor_id = autor_id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(LibreriaContract.AutoresEntry.COLUMN_NAME_NOMBRE, nombre);
        values.put(LibreriaContract.AutoresEntry.COLUMN_NAME_APELLIDO, apellido);
        values.put(LibreriaContract.AutoresEntry.COLUMN_NAME_EDAD, edad);
        values.put(LibreriaContract.AutoresEntry.COLUMN_NAME_NACIONALIDAD, nacionalidad);

        return values;
    }
}
