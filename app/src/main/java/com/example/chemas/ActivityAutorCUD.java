package com.example.chemas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chemas.database.LibreriaDbHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActivityAutorCUD extends AppCompatActivity {

    private int id, edad;
    private boolean flag;
    private String nombre, apellidos, nacionalidad;
    EditText etApellidos, etNacionalidad, etNombre;
    LibreriaDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autor_cud);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellidos = (EditText) findViewById(R.id.etApellidos);
        etNacionalidad = (EditText) findViewById(R.id.etNacionalidad);
        // TODO: Instanciar al etEdad

        mDbHelper = new LibreriaDbHelper(this);

        Bundle bundle = getIntent().getExtras();
        flag = bundle.getBoolean("Flag");
        // flag = true : registro | flag = false : update
        if (!flag) {
            id = bundle.getInt("ID");
            nombre = bundle.getString("Nombre");
            apellidos = bundle.getString("Apellido");
            nacionalidad = bundle.getString("Nacionalidad");
            edad = bundle.getInt("Edad");

            etNombre.setText(nombre);
            etApellidos.setText(apellidos);
            etNacionalidad.setText(nacionalidad);
            // TODO: Agregar texto al etEdad en esta linea
        }
    }

    public void manejarAutor(View view) {
        nombre = etNombre.getText().toString();
        apellidos = etApellidos.getText().toString();
        nacionalidad = etNacionalidad.getText().toString();
        //TODO: Cambiar las comillas dobles por la referencia al EditText etEdad
        edad = Integer.parseInt("".toString());

        Autor autor = new Autor(0, edad, nombre,
                apellidos, nacionalidad);

        if (flag) {
            registrarAutor(autor);
        } else {
            modificarAutor(autor);
        }
    }

    private void registrarAutor(Autor autor) {
        if (mDbHelper.saveAutor(autor) != -1) {
            Toast.makeText(
                    this,
                    "Autor registrado",
                    Toast.LENGTH_SHORT
            ).show();

            etNombre.setText("");
            etApellidos.setText("");
            etNacionalidad.setText("");
        } else {
            Toast.makeText(
                    this,
                    "No se pudo registrar el autor.",
                    Toast.LENGTH_SHORT
            ).show();
        }

    }

    private void modificarAutor(Autor autor) {
        if (mDbHelper.updateAutor(autor, id) == 1) {
            Toast.makeText(
                    this,
                    "Autor modificado EXITOSAMENTE",
                    Toast.LENGTH_SHORT
            ).show();
        } else {
            Toast.makeText(
                    this,
                    "No se pudo modificar el autor.",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}