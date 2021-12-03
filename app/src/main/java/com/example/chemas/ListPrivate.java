package com.example.chemas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chemas.database.LibreriaContract;
import com.example.chemas.database.LibreriaDbHelper;

import java.util.List;

public class ListPrivate extends RecyclerView.Adapter<ListPrivate.ViewHolder> {
    private List<Libro> listLibro;
    private LayoutInflater layoutInflater;
    private Context context;

    public ListPrivate(List<Libro> itemList, Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.listLibro = itemList;
    }

    @Override
    public int getItemCount() { return listLibro.size(); }

    @Override
    public ListPrivate.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_book, parent, false);
        return new ListPrivate.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListPrivate.ViewHolder holder, final int position) {
        holder.bindData(listLibro.get(position));
    }

    public void setItems(List<Libro> items) { listLibro = items; }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private int id, idAutor, anioPub, noPags;
        private String titulo, editorial, genero;

        ImageView imBorrar;
        TextView tvLibroName, tvLibroEditorial, tvLibroFecha, tvLibroPaginas, tvLibroGeneros, tvLibroAutor;

        ViewHolder(View itemView) {
            super(itemView);
            tvLibroAutor = itemView.findViewById(R.id.tvLibroAutor);
            tvLibroEditorial = itemView.findViewById(R.id.tvLibroEditorial);
            tvLibroFecha = itemView.findViewById(R.id.tvLibroFecha);
            tvLibroGeneros = itemView.findViewById(R.id.tvLibroGeneros);
            tvLibroName = itemView.findViewById(R.id.tvLibroName);
            tvLibroPaginas = itemView.findViewById(R.id.tvLibroPaginas);
            imBorrar = (ImageView) itemView.findViewById(R.id.imBorrar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Activity2 activity = (Activity2) context;
                    activity.initUpdateLibroActivity(id, idAutor, titulo, editorial,
                            genero, anioPub, noPags);
                }
            });

            imBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Activity2 activity = (Activity2) context;
                    activity.deleteLibro(id);
                }
            });
        }

        @SuppressLint("Range")
        void bindData(final Libro item) {
            String autorName = "";
            id = item.getId_libro();
            idAutor = item.getAutor_id();
            titulo = item.getTitulo();
            editorial = item.getEditorial();
            genero = item.getGenero();
            anioPub = item.getAnio_pub();
            noPags = item.getNo_pags();

            LibreriaDbHelper mDbHelper = new LibreriaDbHelper(context);
            Cursor c = mDbHelper.getAutorById(idAutor);
            if (c.moveToFirst()) {
                autorName = c.getString(c.getColumnIndex(
                        LibreriaContract.AutoresEntry.COLUMN_NAME_NOMBRE));
            }

            tvLibroName.setText(titulo);
            tvLibroPaginas.setText(String.valueOf(noPags));
            tvLibroAutor.setText(autorName);//Pedro o victor del futuro, aqui se tiene que cambiar el autor id por su nombre completo
            tvLibroGeneros.setText(genero);
            tvLibroFecha.setText(String.valueOf(anioPub));
            tvLibroEditorial.setText(editorial);
        }
    }
}
