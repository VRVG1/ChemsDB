package com.example.chemas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ActivityAutor extends AppCompatActivity {

    List<Autor> elements;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autor);

        init();
    }
    //Pedro, creo que si esta facil de entender como agregar elementos al reciclador vista
    //En caso de que no entiendas me hechas un telefonaso como dicen los Godinez de hoy
    public void init(){
        elements = new ArrayList<>();
        elements.add(new Autor(1,999, "Allan Poe", "Edgar", "Estadounidense"));
        elements.add(new Autor(2,42, "Akihito", "Tsukushi", "Japones"));
        elements.add(new Autor(3,999, "Tolkien", "J.R.R", "SDi"));
        elements.add(new Autor(4,10, "dsadasd Poe", "Edgar", "ASdasda"));
        elements.add(new Autor(5,102, "Allan Poe", "asda", "asdsvsdv"));

        ListPrivateAutor listAdapter = new ListPrivateAutor(elements, this);
        RecyclerView recyclerView = findViewById(R.id.reciclador);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);

    }
}