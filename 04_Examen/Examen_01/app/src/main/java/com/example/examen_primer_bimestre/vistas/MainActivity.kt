package com.example.examen_primer_bimestre.vistas

import com.example.examen_primer_bimestre.base_datos_y_adapter.AlbumAdapter
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import androidx.activity.ComponentActivity
import com.example.examen_primer_bimestre.base_datos_y_adapter.BaseDeDatos
import com.example.examen_primer_bimestre.R
import com.example.examen_primer_bimestre.operacionesCRUD.AlbumCRUD
import com.google.android.material.snackbar.Snackbar

class MainActivity : ComponentActivity() {

    var posAlbumSeleccionado = 0
    var idAlbumSeleccionado = 0
    val listadoDeAlbumes = AlbumCRUD().getAllAlbums()
    lateinit var adaptador: AlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Funcionalidad Botones

        val botonAgregarAlbum = findViewById<Button>(R.id.btn_Agregar_Album)
        botonAgregarAlbum.setOnClickListener{
            irActividad(AgregarAlbum::class.java)
        }

        // Visualizar ListView

        val listViewAlbumes = findViewById<ListView>(R.id.lv_albumes_almacenados)

        // Implementar Menú de Opciones en el ListView
        registerForContextMenu(listViewAlbumes)
        listViewAlbumes.setOnItemClickListener { _, _, position, _ -> val albumSeleccionado = BaseDeDatos.bibliotecaAlbumes[position]
            openContextMenu(listViewAlbumes)
        }

        // Crear el Adapter

        adaptador = AlbumAdapter(this, listadoDeAlbumes)
        listViewAlbumes.adapter = adaptador

        // Verificar si hay un extra en el Intent
        if (intent.hasExtra("NOMBRE_ALBUM_AGREGADO")) {
            val nombreAlbumAgregado = intent.getStringExtra("NOMBRE_ALBUM_AGREGADO")
            // Muestra el Snackbar
            mostrarSnackbar(nombreAlbumAgregado)
        }

        // Implementar Menú de Opciones en el ListView
        registerForContextMenu(listViewAlbumes)
        listViewAlbumes.setOnItemClickListener { _, _, position, _ ->
            // Obtener el álbum directamente del adaptador
            val albumSeleccionado = adaptador.getItem(position)

        }

        // Verificar si hay un extra en el Intent
        if (intent.hasExtra("ALBUM_ACTUALIZADO")) {
            val nombreAlbumActualizado = intent.getStringExtra("ALBUM_ACTUALIZADO")
            // Muestra el Snackbar
            mostrarSnackbarAlbumActualizado(nombreAlbumActualizado)
        }


    }

    // Crear Menú de Opciones
    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        // Completar Opciones del Menú
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_album, menu)
        // Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position
        posAlbumSeleccionado = position
        // Acceder al objeto Album en la posición seleccionada
        val albumSeleccionado = adaptador.getItem(position)
        // Obtener el id del Album seleccionado
        idAlbumSeleccionado = albumSeleccionado?.id ?: -1
    }

    // Visualizar Menú de Opciones
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position

    // Obtener el álbum seleccionado desde el adaptador
        val albumSeleccionado = adaptador.getItem(position)

    // Almacena el ID del álbum seleccionado
        idAlbumSeleccionado = albumSeleccionado?.id ?: -1

        when (item.itemId) {
            R.id.mi_VerCanciones -> {
                val intent = Intent(this, VerCanciones::class.java)
                intent.putExtra("ALBUM_ID", idAlbumSeleccionado)
                startActivity(intent)
                return true
            }
            R.id.mi_Editar_Album -> {
                val intent = Intent(this, EditarAlbum::class.java)
                intent.putExtra("ALBUM_ID", idAlbumSeleccionado)
                startActivity(intent)
                return true
            }
            R.id.mi_Eliminar_Album -> {
                abrirDialogoEliminar()
                return true
            }
            else -> return super.onContextItemSelected(item)
        }
    }


    // Funciones

    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    // Dialogo

    private fun abrirDialogoEliminar() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea Eliminar el Álbum?")
        builder.setPositiveButton("Eliminar") { dialog, which ->
            if (idAlbumSeleccionado != -1) {
                // Llamada a la función para eliminar el álbum por ID
                AlbumCRUD().eliminarAlbumById(idAlbumSeleccionado)
                // Notificar al Adapter que los datos han cambiado
                adaptador.notifyDataSetChanged()
                // Muestra el Snackbar
                mostrarSnackbarEliminado("Álbum Eliminado Exitosamente")
            }
        }
        builder.setNegativeButton("Cancelar", null)
        val dialogo = builder.create()
        dialogo.show()
    }


    // SnackBar
    private fun mostrarSnackbar(nombreAlbum: String?) {
        val rootView: View = findViewById(android.R.id.content)
        Snackbar.make(rootView, "Álbum agregado: $nombreAlbum", Snackbar.LENGTH_SHORT).show()
    }

    private fun mostrarSnackbarEliminado(mensaje: String) {
        val rootView: View = findViewById(android.R.id.content)
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_SHORT).show()
    }

    fun mostrarSnackbarAlbumActualizado(nombreAlbum: String?) {
        val rootView: View = findViewById(android.R.id.content)
        Snackbar.make(findViewById(android.R.id.content), " $nombreAlbum", Snackbar.LENGTH_SHORT).show()
    }

}

