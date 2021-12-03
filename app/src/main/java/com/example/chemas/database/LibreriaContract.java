package com.example.chemas.database;

import android.provider.BaseColumns;

public class LibreriaContract {
    private LibreriaContract() {}

    public static class AutoresEntry implements BaseColumns {
        public static final String TABLE_NAME = "autores";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_APELLIDO = "apellido";
        public static final String COLUMN_NAME_EDAD = "edad";
        public static final String COLUMN_NAME_NACIONALIDAD = "nacionalidad";
    }

    public static class LibrosEntry implements BaseColumns {
        public static final String TABLE_NAME = "libros";
        public static final String COLUMN_NAME_EDITORIAL = "editorial";
        public static final String COLUMN_NAME_LIBRO_AUTOR_ID = "autor id";
        public static final String COLUMN_NAME_GENERO = "genero";
        public static final String COLUMN_NAME_ANIO_PUB = "anio pub";
        public static final String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_NO_PAGS = "no pags";
    }
}
