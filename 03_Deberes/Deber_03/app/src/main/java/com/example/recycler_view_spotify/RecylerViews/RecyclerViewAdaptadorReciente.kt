package com.example.recycler_view_spotify.RecylerViews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ui.graphics.toArgb
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.recycler_view_spotify.Model.EscuchadoReciente
import com.example.recycler_view_spotify.R

class RecyclerViewAdaptadorReciente (
    private val contexto: Context,
    private val lista: ArrayList<EscuchadoReciente>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<RecyclerViewAdaptadorReciente.MyViewHolder>(){

    // Inicializar los componentes visuales de la Interfaz para el Adaptador Personalizado
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val nombreRecienteTextView: TextView = itemView.findViewById(R.id.tv_Reciente_Home1)
        val imgReciente: ImageView = itemView.findViewById(R.id.img_Reciente_Home1)
        val itemLayoutColor: ConstraintLayout = itemView.findViewById(R.id.cl_home1)
    }

    // Setear el layout que vamos a utilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdaptadorReciente.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_home, parent, false)
        return MyViewHolder(itemView)
    }

    // Función para devolver el núnero de elementos que hay dentro del arreglo
    override fun getItemCount(): Int {
        return lista.size
    }

    // Setear los datos para la iteracion
    override fun onBindViewHolder(holder: RecyclerViewAdaptadorReciente.MyViewHolder, position: Int) {
        val recienteActual = lista[position]
        holder.nombreRecienteTextView.text = recienteActual.nombreEscuchado
        // Cargar imagen en imgArtista
        val resourceId = recienteActual.imgEscuchado
        holder.imgReciente.setImageResource(resourceId)
        // Color del Background
        holder.itemLayoutColor.setBackgroundColor(recienteActual.colorFondo.toArgb()) // Convierte el Color a Int
    }
}