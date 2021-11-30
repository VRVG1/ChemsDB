package com.example.chemas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Activity2 extends AppCompatActivity {

    List<Libro> elements;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        init();
    }

    public void init(){
        elements = new ArrayList<>();
        elements.add(new Libro(1,69, 1, "Hoal", "Metalica", "Lala", "2021-12-10"));
        elements.add(new Libro(2,679, 2, "Hoalrrr", "Radames", "Pecsi", "2021-1-10"));
        elements.add(new Libro(3,669, 3, "Hoal4", "Dando", "Fornique", "2021-2-10"));
        elements.add(new Libro(4,659, 4, "Hoal3", "Gracias", "Ahuevo", "2021-2-14"));
        elements.add(new Libro(5,629, 5, "Hoal2", "Si", "909090", "2021-12-20"));

        ListPrivate listAdapter = new ListPrivate(elements, this);
        RecyclerView recyclerView = findViewById(R.id.reciclador);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);

    }
}