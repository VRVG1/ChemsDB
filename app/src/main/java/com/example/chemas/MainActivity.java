package com.example.chemas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void openBook(View view){
        Intent intento = new Intent(this, Activity2.class);
        startActivity(intento);
    }

    public void openAutor(View view){
        Intent intent = new Intent(this, ActivityAutor.class);
        startActivity(intent);
    }

    public void setBtAbout(View view) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Programadores");
        dialogo.setMessage("Ventus\n" +
                "Ordep\n" +
                "VRVG");
        dialogo.setPositiveButton("Aceptar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        dialogo.show();
    }
}