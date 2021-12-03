package com.example.chemas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.example.chemas.database.LibreriaContract;
import com.example.chemas.database.LibreriaDbHelper;

import java.util.ArrayList;
import java.util.List;

public class Activity2 extends AppCompatActivity {

    List<Libro> elements;
    private LibreriaDbHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        mDbHelper = new LibreriaDbHelper(this);
        init();
    }
    //Pedro, creo que si esta facil de entender como agregar elementos al reciclador vista
    //En caso de que no entiendas me hechas un telefonaso como dicen los Godinez de hoy
    public void init(){
        elements = new ArrayList<>();
        loadingElements();

        ListPrivate listAdapter = new ListPrivate(elements, this);
        RecyclerView recyclerView = findViewById(R.id.reciclador);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);

    }

    @SuppressLint("Range")
    private void loadingElements() {
        Cursor c = mDbHelper.getTodosLibros();
        int id, idAutor, anioPubli, noPags;
        String titulo, editorial, genero;

        while (c.moveToNext()) {
            id = c.getInt(c.getColumnIndex(LibreriaContract.LibrosEntry._ID));
            idAutor = c.getInt(c.getColumnIndex(
                    LibreriaContract.LibrosEntry.COLUMN_NAME_LIBRO_AUTOR_ID));
            anioPubli = c.getInt(c.getColumnIndex(
                    LibreriaContract.LibrosEntry.COLUMN_NAME_ANIO_PUB));
            noPags = c.getInt(c.getColumnIndex(LibreriaContract.LibrosEntry.COLUMN_NAME_NO_PAGS));
            titulo = c.getString(c.getColumnIndex(LibreriaContract.LibrosEntry.COLUMN_NAME_TITULO));
            editorial = c.getString(c.getColumnIndex(
                    LibreriaContract.LibrosEntry.COLUMN_NAME_EDITORIAL));
            genero = c.getString(c.getColumnIndex(LibreriaContract.LibrosEntry.COLUMN_NAME_GENERO));

            elements.add(new Libro(id, noPags, idAutor, titulo, genero, editorial, anioPubli));
        }
    }

    public void registrarLibro(View view) {
        Intent intent = new Intent(this, ActivityBookCUD.class);
        intent.putExtra("Flag", true);
        startActivity(intent);
    }

    public void initUpdateAutorActivity(int id, int idAutor, String titulo, String editorial,
                                        String genero, int anioPub, int noPags) {
        Intent intent = new Intent(this, ActivityBookCUD.class);
        intent.putExtra("Flag", false);
        intent.putExtra("ID", id);
        intent.putExtra("AutorID", idAutor);
        intent.putExtra("Nombre", titulo);
        intent.putExtra("Editorial", editorial);
        intent.putExtra("Genero", genero);
        intent.putExtra("NoPags", noPags);
        intent.putExtra("anio", anioPub);

        startActivity(intent);
    }

    public void deleteAutor(int id) {
        if (mDbHelper.delete)
    }
}