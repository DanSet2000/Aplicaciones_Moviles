package com.example.proyecto_segundo_bimestre.RecyclerViews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_segundo_bimestre.Model.Videojuego
import com.example.proyecto_segundo_bimestre.R


class RVAdaptMisJuegos(
    private val context: Context,
    private var lista: ArrayList<Videojuego>,
    private val recyclerView: RecyclerView,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<RVAdaptMisJuegos.MyViewHolder>() {

    // ViewHolder
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val imgJuego: ImageView = itemView.findViewById(R.id.img_Mis_Juegos)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onItemClickListener.onItemClick(lista[adapterPosition])
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(videojuego: Videojuego)
    }

    // Setear el layout que vamos a utilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdaptMisJuegos.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_mis_juegos, parent, false)
        return MyViewHolder(itemView)
    }

    // Función para devolver el número de elementos que hay dentro del arreglo
    override fun getItemCount(): Int {
        return lista.size
    }

    // Setear los datos para la iteración
    override fun onBindViewHolder(holder: RVAdaptMisJuegos.MyViewHolder, position: Int) {
        val recienteActual = lista[position]
        val resourceId = recienteActual.portadaJuego
        holder.imgJuego.setImageResource(resourceId)
    }

    // Método para actualizar la lista de juegos
    fun updateList(newList: ArrayList<Videojuego>) {
        lista = newList
        notifyDataSetChanged()
    }


}