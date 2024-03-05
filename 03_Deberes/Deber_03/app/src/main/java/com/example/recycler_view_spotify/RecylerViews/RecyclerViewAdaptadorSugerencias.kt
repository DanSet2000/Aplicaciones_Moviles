package com.example.recycler_view_spotify.RecylerViews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recycler_view_spotify.Model.AlbumMusical
import com.example.recycler_view_spotify.Model.Artista
import com.example.recycler_view_spotify.R
import org.w3c.dom.Text

class RecyclerViewAdaptadorSugerencias (
    private val contexto: Context, // Corregido: Cambiado a Context
    private val lista: ArrayList<AlbumMusical>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<RecyclerViewAdaptadorSugerencias.MyViewHolder>() {

    // Inicializar los componentes visuales de la Interfaz para el Adaptador Personalizado
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreSugerenciaTextView: TextView
        val nombreInfoTextView: TextView
        val imgSugerencia: ImageView

        init {
            nombreSugerenciaTextView = view.findViewById(R.id.tv_Sugerencia_Album)
            nombreInfoTextView = view.findViewById(R.id.tv_Info_Adicional)
            imgSugerencia = view.findViewById(R.id.img_Sugerencia)
        }
    }

    // Setear el layout que vamos a utilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdaptadorSugerencias.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_sugerencia, parent, false)
        return MyViewHolder(itemView)
    }

    // Función para devolver el núnero de elementos que hay dentro del arreglo
    override fun getItemCount(): Int {
        return lista.size
    }

    // Setear los datos para la iteracion
    override fun onBindViewHolder(holder: RecyclerViewAdaptadorSugerencias.MyViewHolder, position: Int) {
        val albumActual = lista[position]
        holder.nombreSugerenciaTextView.text = albumActual.nombreAlbum
        holder.nombreInfoTextView.text = albumActual.artistaAlbum
        // Cargar imagen en imgPortadaAlbum
        val resourceId = albumActual.portadaAlbum
        holder.imgSugerencia.setImageResource(resourceId)
    }
}