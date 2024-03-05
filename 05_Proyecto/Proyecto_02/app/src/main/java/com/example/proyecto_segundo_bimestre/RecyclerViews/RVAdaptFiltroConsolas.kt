package com.example.proyecto_segundo_bimestre.RecyclerViews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_segundo_bimestre.Model.Consola
import com.example.proyecto_segundo_bimestre.R

class RVAdaptFiltroConsolas(
    private val contexto: Context,
    private val lista: ArrayList<Consola>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<RVAdaptFiltroConsolas.MyViewHolder>() {

    private var onItemClick: ((Consola) -> Unit)? = null

    fun setOnItemClickListener(listener: (Consola) -> Unit) {
        onItemClick = listener
    }

    // Inicializar los componentes visuales de la Interfaz para el Adaptador Personalizado
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val btnTexto: Button = itemView.findViewById(R.id.btn_Buscar_Consola)

        init {
            btnTexto.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick?.invoke(lista[position])
                }
            }
        }
    }

    // Setear el layour que vamos a utilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_buscar_consola, parent, false)
        return MyViewHolder(itemView)
    }

    // Función para devolver el núnero de elementos que hay dentro del arreglo
    override fun getItemCount(): Int {
        return lista.size
    }

    // Setear los datos para la iteracion
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val consolaActual = lista[position]
        holder.btnTexto.text = consolaActual.nombreConsola
    }
}






