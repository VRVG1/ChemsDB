package com.example.chemas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.chemas.database.LibreriaContract;
import com.example.chemas.database.LibreriaDbHelper;

import java.util.ArrayList;
import java.util.List;

public class ActivityBookCUD extends AppCompatActivity {

    private boolean flag;
    private int id, idAutor, noPags, anioPubli;
    private String nombre, editorial, genero;
    private String[] generos = {"Misterio", "Fantasía", "Ciencia Ficción", "Terror",
            "Romance"};

    EditText etNombre, etEditorial, etPaginas, etFecha;
    Spinner spAutor, spGeneros;
    LibreriaDbHelper mDbHelper;
    private List<Autor> autores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_cud);

        autores = new ArrayList<>();

        etNombre = (EditText) findViewById(R.id.etNombre);
        etEditorial = (EditText) findViewById(R.id.etEditorial);
        etPaginas = (EditText) findViewById(R.id.etPaginas);
        etFecha = (EditText) findViewById(R.id.etFecha);

        spAutor = (Spinner) findViewById(R.id.spAutor);
        spGeneros = (Spinner) findViewById(R.id.spGeneros);

        mDbHelper = new LibreriaDbHelper(this);

        initSpinnerAutores();
        fillSpinnerGeneros();

        Bundle bundle = getIntent().getExtras();
        flag = bundle.getBoolean("Flag");
        // flag = true : registro | flag = false : update
        if (!flag) {
            id = bundle.getInt("ID");
            idAutor = bundle.getInt("AutorID");
            nombre = bundle.getString("Nombre");
            editorial = bundle.getString("Editorial");
            genero = bundle.getString("Genero");
            noPags = bundle.getInt("NoPags");
            anioPubli = bundle.getInt("anio");

            etNombre.setText(nombre);
            etEditorial.setText(editorial);
            etPaginas.setText(String.valueOf(noPags));
            etFecha.setText(String.valueOf(anioPubli));
        }
    }

    private void fillSpinnerGeneros() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, generos);

        if (!flag) {
            int selectedItem = 0;
            switch (genero) {
                case "Misterio":
                    selectedItem = 0;
                    break;
                case "Fantasia":
                    selectedItem = 1;
                    break;
                case "Ciencia Ficción":
                    selectedItem = 2;
                    break;
                case "Terror":
                    selectedItem = 3;
                    break;
                case "Romance":
                    selectedItem = 4;
                    break;
            }
            spGeneros.setSelection(selectedItem);
        }

        spGeneros.setAdapter(adapter);
        spGeneros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                genero = spGeneros.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @SuppressLint("Range")
    private void initSpinnerAutores() {
        List<String> nombresAutores = new ArrayList<>();

        Cursor c = mDbHelper.getTodosLibros();
        int ceIdAutor, ceAutorEdad;
        String ceAutorNombre, ceAutorApellido, ceAutorNac;
        while (c.moveToNext()) {
            ceIdAutor = c.getInt(c.getColumnIndex(LibreriaContract.AutoresEntry._ID));
            ceAutorNombre = c.getString(
                    c.getColumnIndex(LibreriaContract.AutoresEntry.COLUMN_NAME_NOMBRE));
            ceAutorApellido = c.getString(
                    c.getColumnIndex(LibreriaContract.AutoresEntry.COLUMN_NAME_APELLIDO));
            ceAutorEdad = c.getInt(c.getColumnIndex(LibreriaContract.AutoresEntry.COLUMN_NAME_EDAD));
            ceAutorNac = c.getString(
                    c.getColumnIndex(LibreriaContract.AutoresEntry.COLUMN_NAME_NACIONALIDAD));


            nombresAutores.add(ceAutorNombre + " " + ceAutorApellido);
            autores.add(new Autor(ceIdAutor, ceAutorEdad, ceAutorNombre, ceAutorApellido, ceAutorNac));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, nombresAutores);
        spAutor.setAdapter(adapter);

        if (!flag) {
            //spAutor.setSelection();
        }
        spAutor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idAutor = autores.get(
                        spAutor.getSelectedItemPosition()).getAutor_id();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void manejarLibro(View view) {
        nombre = etNombre.getText().toString();
        editorial = etEditorial.getText().toString();
        noPags = Integer.parseInt(etPaginas.getText().toString());
        anioPubli = Integer.parseInt(etFecha.getText().toString());

        Libro libro = new Libro(0, noPags, idAutor, nombre, genero, editorial,
                anioPubli);

        if (flag) {
            registrarLibro(libro);
        } else {
            modificarLibro(libro);
        }
    }

    private void registrarLibro(Libro libro) {
        if (mDbHelper.saveLibro(libro) != -1) {
            Toast.makeText(
                    this,
                    "Libro registrado EXITOSAMENTE",
                    Toast.LENGTH_SHORT
            ).show();
        } else {
            Toast.makeText(
                    this,
                    "No se pudo registrar el libro.",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    private void modificarLibro(Libro libro) {
        if (mDbHelper.updateLibro(libro, id) == 1) {
            Toast.makeText(
                    this,
                    "Libro modificado EXITOSAMENTE",
                    Toast.LENGTH_SHORT
            ).show();
        } else {
            Toast.makeText(
                    this,
                    "No se pudo modificar el libro.",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}