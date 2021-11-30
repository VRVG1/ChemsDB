package com.example.chemas;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

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
        View view = layoutInflater.inflate(R.layout.list_book, null);
        return new ListPrivate.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListPrivate.ViewHolder holder, final int position) {
        holder.bindData(listLibro.get(position));
    }

    public void setItems(List<Libro> items) { listLibro = items; }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView tvLibroName, tvLibroEditorial, tvLibroFecha, tvLibroPaginas, tvLibroGeneros, tvLibroAutor;

        ViewHolder(View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.imagenLibros);
            tvLibroAutor = itemView.findViewById(R.id.tvLibroAutor);
            tvLibroEditorial = itemView.findViewById(R.id.tvLibroEditorial);
            tvLibroFecha = itemView.findViewById(R.id.tvLibroFecha);
            tvLibroGeneros = itemView.findViewById(R.id.tvLibroGeneros);
            tvLibroName = itemView.findViewById(R.id.tvLibroName);
            tvLibroPaginas = itemView.findViewById(R.id.tvLibroPaginas);
        }

        void bindData(final Libro item) {
            //iconImage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            tvLibroName.setText(item.getTitulo());
            tvLibroPaginas.setText(item.getNo_pags());
            tvLibroAutor.setText(item.getAutor_id());
            tvLibroGeneros.setText(item.getGenero());
            tvLibroFecha.setText(item.getAnio_pub());
            tvLibroEditorial.setText(item.getEditorial());
        }
    }
}
