package com.example.recycler_view_spotify.RecylerViews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recycler_view_spotify.Model.AlbumMusical
import com.example.recycler_view_spotify.R

class RecyclerViewAdaptadorAlbum(
    private val contexto: Context, // Corregido: Cambiado a Context
    private val lista: ArrayList<AlbumMusical>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<RecyclerViewAdaptadorAlbum.MyViewHolder>() {

    // Inicializar los componentes visuales de la Interfaz para el Adaptador Personalizado
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreAlbumTextView: TextView
        val nombreArtistaTextView: TextView
        val imgPortadaAlbum: ImageView

        init {
            nombreAlbumTextView = view.findViewById(R.id.tv_Nombre_Album)
            nombreArtistaTextView = view.findViewById(R.id.tv_Artista_Album)
            imgPortadaAlbum = view.findViewById(R.id.img_Portada_Album)
        }
    }

    // Setear el layout que vamos a utilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_album, parent, false)
        return MyViewHolder(itemView)
    }

    // Función para devolver el núnero de elementos que hay dentro del arreglo
    override fun getItemCount(): Int {
        return lista.size
    }

    // Setear los datos para la iteracion
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val albumActual = lista[position]
        holder.nombreAlbumTextView.text = albumActual.nombreAlbum
        holder.nombreArtistaTextView.text = albumActual.artistaAlbum
        // Cargar imagen en imgPortadaAlbum
        val resourceId = albumActual.portadaAlbum
        holder.imgPortadaAlbum.setImageResource(resourceId)
    }
}