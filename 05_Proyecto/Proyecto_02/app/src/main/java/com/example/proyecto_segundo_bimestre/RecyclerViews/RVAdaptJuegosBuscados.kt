package com.example.proyecto_segundo_bimestre.RecyclerViews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_segundo_bimestre.Model.Videojuego
import com.example.proyecto_segundo_bimestre.R

class RVAdaptJuegosBuscados(
    private val contexto: Context, // Corregido: Cambiado a Context
    private val lista: ArrayList<Videojuego>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<RVAdaptJuegosBuscados.MyViewHolder>() {

    private var itemClickListener: OnItemClickListener? = null

    // Interfaz para manejar clics en elementos
    interface OnItemClickListener {
        fun onItemClick(videojuego: Videojuego)
    }

    // Variable para almacenar el listener
    private var listener: OnItemClickListener? = null

    // Método para establecer el listener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    // Inicializar los componentes visuales de la Interfaz para el Adaptador Personalizado
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imgBuscado: ImageView = itemView.findViewById(R.id.img_Juego_Buscado)
        val nombreBuscado: TextView = itemView.findViewById(R.id.txt_Nombre_Juego)
        val consolaBuscado: TextView = itemView.findViewById(R.id.txt_Consola_Juego)

        init {
            // Configura el OnClickListener para el elemento del RecyclerView
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener?.onItemClick(lista[position])
                }
            }
        }
    }

    // Setear el layour que vamos a utilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdaptJuegosBuscados.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_juegos_buscados, parent, false)
        return MyViewHolder(itemView)
    }

    // Función para devolver el núnero de elementos que hay dentro del arreglo
    override fun getItemCount(): Int {
        return lista.size
    }

    // Setear los datos para la iteracion
    override fun onBindViewHolder(holder: RVAdaptJuegosBuscados.MyViewHolder, position: Int) {
        val buscadoActual = lista[position]
        holder.nombreBuscado.text = buscadoActual.nombreJuego
        holder.consolaBuscado.text = buscadoActual.plataforma

        val resourceId = buscadoActual.portadaJuego
        holder.imgBuscado.setImageResource(resourceId)

        holder.itemView.setOnClickListener {
            listener?.onItemClick(lista[position])
        }
    }




}