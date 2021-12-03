package com.example.chemas;

import android.content.ContentValues;

import com.example.chemas.database.LibreriaContract;

public class Libro {
    private int id_libro, no_pags, autor_id, anio_pub;
    private String titulo, genero, editorial;

    public Libro(int id_libro, int no_pags, int autor_id, String titulo, String genero,
                 String editorial, int anio_pub) {
        this.id_libro = id_libro;
        this.no_pags = no_pags;
        this.autor_id = autor_id;
        this.titulo = titulo;
        this.genero = genero;
        this.editorial = editorial;
        this.anio_pub = anio_pub;
    }

    public int getId_libro() {
        return id_libro;
    }

    public int getNo_pags() {
        return no_pags;
    }

    public int getAutor_id() {
        return autor_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public String getEditorial() {
        return editorial;
    }

    public int getAnio_pub() {
        return anio_pub;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public void setNo_pags(int no_pags) {
        this.no_pags = no_pags;
    }

    public void setAutor_id(int autor_id) {
        this.autor_id = autor_id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setAnio_pub(int anio_pub) {
        this.anio_pub = anio_pub;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(LibreriaContract.LibrosEntry.COLUMN_NAME_NO_PAGS, no_pags);
        values.put(LibreriaContract.LibrosEntry.COLUMN_NAME_LIBRO_AUTOR_ID, autor_id);
        values.put(LibreriaContract.LibrosEntry.COLUMN_NAME_TITULO, titulo);
        values.put(LibreriaContract.LibrosEntry.COLUMN_NAME_GENERO, genero);
        values.put(LibreriaContract.LibrosEntry.COLUMN_NAME_EDITORIAL, editorial);
        values.put(LibreriaContract.LibrosEntry.COLUMN_NAME_ANIO_PUB, anio_pub);

        return values;
    }
}