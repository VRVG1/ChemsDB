package com.example.chemas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListPrivateAutor extends RecyclerView.Adapter<ListPrivateAutor.ViewHolder>{
    private List<Autor> listAutor;
    private LayoutInflater layoutInflater;
    private Context context;

    public ListPrivateAutor(List<Autor> itemList, Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.listAutor = itemList;
    }
    @Override
    public int getItemCount() { return listAutor.size(); }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_autor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListPrivateAutor.ViewHolder holder, final int position) {
        holder.bindData(listAutor.get(position));
    }

    public void setItems(List<Autor> items) { listAutor = items; }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private int id, edad;
        private String nombre, apellido, nacionalidad;

        ImageView iconImage, imBorrar;
        TextView tvAutorFullName, tvAutorAge, AutorNacionalidad;

        ViewHolder(View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.ImagenAutor);
            tvAutorFullName = itemView.findViewById(R.id.tvAutorFullName);
            tvAutorAge = itemView.findViewById(R.id.tvAutorAge);
            AutorNacionalidad = itemView.findViewById(R.id.AutorNacionalidad);
            imBorrar = (ImageView) itemView.findViewById(R.id.imBorrar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityAutor activity = (ActivityAutor) context;
                    activity.initUpdateAutorActivity(id, nombre, apellido, nacionalidad, edad);
                }
            });

            imBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityAutor activity = (ActivityAutor) context;
                    activity.deleteAutor(id);
                }
            });
        }

        void bindData(final Autor item) {
            id = item.getAutor_id();
            edad = item.getEdad();
            nombre = item.getNombre();
            apellido = item.getApellido();
            nacionalidad = item.getNacionalidad();

            //iconImage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            String nombreCompleto = nombre + " " + apellido;
            tvAutorFullName.setText(nombreCompleto);
            tvAutorAge.setText(String.valueOf(edad));
            AutorNacionalidad.setText(nacionalidad);
        }
    }
}
