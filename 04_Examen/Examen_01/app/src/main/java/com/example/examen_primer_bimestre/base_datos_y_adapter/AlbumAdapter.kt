package com.example.examen_primer_bimestre.base_datos_y_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.examen_primer_bimestre.modelo.Album

class AlbumAdapter(context: Context, albums: List<Album>) : ArrayAdapter<Album>(context, 0, albums) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false)
        }

        // Obtener el álbum actual
        val currentAlbum = getItem(position)

        // Configura el texto del elemento de la lista con el nombre del álbum
        val textView = listItemView?.findViewById<TextView>(android.R.id.text1)
        textView?.text = currentAlbum?.nombre

        return listItemView!!
    }
}