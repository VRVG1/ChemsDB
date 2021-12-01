package com.example.chemas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

    @Override
    public ListPrivateAutor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_book, null);
        return new ListPrivateAutor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListPrivateAutor.ViewHolder holder, final int position) {
        holder.bindData(listAutor.get(position));
    }

    public void setItems(List<Autor> items) { listAutor = items; }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView tvAutorFullName, tvAutorAge, AutorNacionalidad;

        ViewHolder(View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.ImagenAutor);
            tvAutorFullName = itemView.findViewById(R.id.tvAutorFullName);
            tvAutorAge = itemView.findViewById(R.id.tvAutorAge);
            AutorNacionalidad = itemView.findViewById(R.id.AutorNacionalidad);
        }

        void bindData(final Autor item) {
            //iconImage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            tvAutorFullName.setText(item.getNombre() + item.getApellido());
            tvAutorAge.setText(item.getEdad());
            AutorNacionalidad.setText(item.getNacionalidad());
        }
    }
}