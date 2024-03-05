package com.example.examen_primer_bimestre.base_datos_y_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.examen_primer_bimestre.modelo.Cancion

class CancionAdapter(context: Context, canciones: List<Cancion>) :
    ArrayAdapter<Cancion>(context, 0, canciones) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
        }

        // Obtener la canción actual
        val currentCancion = getItem(position)

        // Configura el texto del elemento de la lista con el nombre de la canción
        val textView = listItemView?.findViewById<TextView>(android.R.id.text1)
        textView?.text = currentCancion?.nombre

        return listItemView!!
    }
}