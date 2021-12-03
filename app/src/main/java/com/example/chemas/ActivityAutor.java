package com.example.chemas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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

public class ActivityAutor extends AppCompatActivity {

    List<Autor> elements;
    private LibreriaDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autor);

        mDbHelper = new LibreriaDbHelper(this);
        init();
    }
    //Pedro, creo que si esta facil de entender como agregar elementos al reciclador vista
    //En caso de que no entiendas me hechas un telefonaso como dicen los Godinez de hoy
    public void init(){
        elements = new ArrayList<>();
        loadingElements();

        ListPrivateAutor listAdapter = new ListPrivateAutor(elements, this);
        RecyclerView recyclerView = findViewById(R.id.reciclador);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);

    }

    @SuppressLint("Range")
    private void loadingElements() {
        Cursor c = mDbHelper.getTodosAutores();
        int id, edad;
        String nombre, apellido, nacionalidad;
        while (c.moveToNext()) {
            id = c.getInt(c.getColumnIndex(LibreriaContract.AutoresEntry._ID));
            nombre = c.getString(
                    c.getColumnIndex(LibreriaContract.AutoresEntry.COLUMN_NAME_NOMBRE));
            apellido = c.getString(
                    c.getColumnIndex(LibreriaContract.AutoresEntry.COLUMN_NAME_APELLIDO));
            edad = c.getInt(c.getColumnIndex(LibreriaContract.AutoresEntry.COLUMN_NAME_EDAD));
            nacionalidad = c.getString(
                    c.getColumnIndex(LibreriaContract.AutoresEntry.COLUMN_NAME_NACIONALIDAD));

            elements.add(new Autor(id, edad, nombre, apellido, nacionalidad));
        }
    }
    // TODO: Hacer que el btLlame al intent de AutorCUD
    //  se debera pasar un flag que permita saber cuando es un registro de un nuevo autor
    public void registrarAutor(View view) {
        Intent intent = new Intent(this, ActivityAutorCUD.class);
        intent.putExtra("Flag", true);
        startActivity(intent);
    }

    public void initUpdateAutorActivity(int id, String nombre, String apellido,
                                        String nacionalidad, int edad) {

        Intent intent = new Intent(this, ActivityAutorCUD.class);
        intent.putExtra("Flag", false);
        intent.putExtra("ID", id);
        intent.putExtra("Nombre", nombre);
        intent.putExtra("Apellido", apellido);
        intent.putExtra("Nacionalidad", nacionalidad);
        intent.putExtra("Edad", edad);

        startActivity(intent);
    }
}