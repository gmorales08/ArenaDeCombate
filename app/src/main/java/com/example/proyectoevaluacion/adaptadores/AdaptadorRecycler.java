package com.example.proyectoevaluacion.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoevaluacion.R;
import com.example.proyectoevaluacion.utils.Personaje;
import com.example.proyectoevaluacion.utils.Raza;

import java.util.List;

public class AdaptadorRecycler extends RecyclerView.Adapter<AdaptadorRecycler.MiHolder> {

    private Context context;
    private List<Personaje> listaPersonajes;
    private OnPersonajeListener listener;

    public AdaptadorRecycler(Context context, List<Personaje> listaPersonajes) {
        this.context = context;
        this.listaPersonajes = listaPersonajes;

        listener = (OnPersonajeListener) context;
    }

    @NonNull
    @Override
    public MiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_fila_recycler_personajes,parent,false);

        return new MiHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MiHolder holder, int position) {
        final Personaje personajeActual = listaPersonajes.get(position);


        holder.getImagen().setImageResource(personajeActual.getImagen());
        holder.getNombre().setText(personajeActual.getNombre());
        holder.getRaza().setText(String.valueOf(personajeActual.getRaza()));
        holder.getClase().setText(String.valueOf(personajeActual.getClase()));
        holder.getAtaques().setText(String.valueOf(personajeActual.getAtaques()));
        holder.getHabilidades().setText(String.valueOf(personajeActual.getHabilidades()));
        holder.getLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnPersonajeSelected(personajeActual);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPersonajes.size();
    }

    public interface OnPersonajeListener {
        void OnPersonajeSelected(Personaje personaje);
    }

    class MiHolder extends RecyclerView.ViewHolder {

        private LinearLayout layout;
        private ImageView imagen;
        private TextView nombre, raza, clase, ataques, habilidades;

        public MiHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.fila_personaje);
            imagen = itemView.findViewById(R.id.fila_imagen_personaje);
            nombre = itemView.findViewById(R.id.fila_texto_nombre);
            raza = itemView.findViewById(R.id.fila_texto_raza);
            clase = itemView.findViewById(R.id.fila_texto_clase);
            ataques = itemView.findViewById(R.id.fila_texto_ataques);
            habilidades = itemView.findViewById(R.id.fila_texto_habilidades);
        }

        public LinearLayout getLayout() {
            return layout;
        }

        public ImageView getImagen() {
            return imagen;
        }

        public TextView getNombre() {
            return nombre;
        }

        public TextView getRaza() {
            return raza;
        }

        public TextView getClase() {
            return clase;
        }

        public TextView getAtaques() {
            return ataques;
        }

        public TextView getHabilidades() {
            return habilidades;
        }
    }

}
