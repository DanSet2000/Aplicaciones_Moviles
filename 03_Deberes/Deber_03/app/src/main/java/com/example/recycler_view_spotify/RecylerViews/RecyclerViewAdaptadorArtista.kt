package com.example.recycler_view_spotify.RecylerViews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recycler_view_spotify.Model.Artista
import com.example.recycler_view_spotify.R

class RecyclerViewAdaptadorArtista(
    private val contexto: Context, // Corregido: Cambiado a Context
    private val lista: ArrayList<Artista>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<RecyclerViewAdaptadorArtista.MyViewHolder>() {

    // Inicializar los componentes visuales de la Interfaz para el Adaptador Personalizado
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreArtistaTextView: TextView
        val imgArtista: ImageView

        init {
            nombreArtistaTextView = view.findViewById(R.id.tv_Nombre_Artista_Reciente)
            imgArtista = view.findViewById(R.id.img_Artista_Reciente)
        }
    }

    // Setear el layout que vamos a utilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_artistas_recientes, parent, false)
        return MyViewHolder(itemView)
    }

    // Función para devolver el núnero de elementos que hay dentro del arreglo
    override fun getItemCount(): Int {
        return lista.size
    }

    // Setear los datos para la iteracion
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val artistaActual = lista[position]
        holder.nombreArtistaTextView.text = artistaActual.nombreArtista
        // Cargar imagen en imgArtista
        val resourceId = artistaActual.imagenArtista
        holder.imgArtista.setImageResource(resourceId)

    }
}