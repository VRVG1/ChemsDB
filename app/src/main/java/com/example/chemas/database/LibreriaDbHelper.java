package com.example.chemas.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.chemas.Autor;
import com.example.chemas.Libro;

public class LibreriaDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "libreria.db";

    public LibreriaDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " %s TEXT," +
                        " %s TEXT," +
                        " %s INTEGER," +
                        " %s TEXT" +
                        ")",
                LibreriaContract.AutoresEntry.TABLE_NAME,
                LibreriaContract.AutoresEntry._ID,
                LibreriaContract.AutoresEntry.COLUMN_NAME_NOMBRE,
                LibreriaContract.AutoresEntry.COLUMN_NAME_APELLIDO,
                LibreriaContract.AutoresEntry.COLUMN_NAME_EDAD,
                LibreriaContract.AutoresEntry.COLUMN_NAME_NACIONALIDAD
        ));

        db.execSQL(String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " %s TEXT," +
                        " %s INTEGER REFERENCES %s (%s)," +
                        " %s TEXT," +
                        " %s INTEGER," +
                        " %s TEXT," +
                        " %s INTEGER" +
                        ")",
                LibreriaContract.LibrosEntry.TABLE_NAME,
                LibreriaContract.LibrosEntry._ID,
                LibreriaContract.LibrosEntry.COLUMN_NAME_TITULO,
                LibreriaContract.LibrosEntry.COLUMN_NAME_LIBRO_AUTOR_ID,
                LibreriaContract.AutoresEntry.TABLE_NAME,
                LibreriaContract.AutoresEntry._ID,
                LibreriaContract.LibrosEntry.COLUMN_NAME_EDITORIAL,
                LibreriaContract.LibrosEntry.COLUMN_NAME_ANIO_PUB,
                LibreriaContract.LibrosEntry.COLUMN_NAME_GENERO,
                LibreriaContract.LibrosEntry.COLUMN_NAME_NO_PAGS
        ));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long saveAutor(Autor autor) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert(
                LibreriaContract.AutoresEntry.TABLE_NAME,
                null,
                autor.toContentValues()
        );
    }

    public int updateAutor(Autor autor, int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.update(
                LibreriaContract.AutoresEntry.TABLE_NAME,
                autor.toContentValues(),
                LibreriaContract.AutoresEntry._ID + " LIKE ?",
                new String[]{ String.valueOf(id) }
        );
    }

    public int deleteAutor(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(
                LibreriaContract.AutoresEntry.TABLE_NAME,
                LibreriaContract.AutoresEntry._ID + " LIKE ?",
                new String[]{ String.valueOf(id) }
        );
    }

    public Cursor getTodosAutores() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor c = sqLiteDatabase.query(
                LibreriaContract.AutoresEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        return c;
    }

    public long saveLibro(Libro libro) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert(
                LibreriaContract.LibrosEntry.TABLE_NAME,
                null,
                libro.toContentValues()
        );
    }

    public int updateLibro(Libro libro, int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.update(
                LibreriaContract.LibrosEntry.TABLE_NAME,
                libro.toContentValues(),
                LibreriaContract.LibrosEntry._ID + " LIKE ?",
                new String[]{ String.valueOf(id) }
        );
    }

    public int deleteLibro(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(
                LibreriaContract.LibrosEntry.TABLE_NAME,
                LibreriaContract.LibrosEntry._ID + " LIKE ?",
                new String[]{ String.valueOf(id) }
        );
    }

    public Cursor getTodosLibros() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor c = sqLiteDatabase.query(
                LibreriaContract.LibrosEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        return c;
    }

}
