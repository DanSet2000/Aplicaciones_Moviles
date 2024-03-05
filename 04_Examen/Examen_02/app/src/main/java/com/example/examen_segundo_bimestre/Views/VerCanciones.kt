package com.example.examen_segundo_bimestre.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.example.examen_segundo_bimestre.Controller.AlbumFirestore
import com.example.examen_segundo_bimestre.Controller.CancionAdapter
import com.example.examen_segundo_bimestre.Controller.CancionFirestore
import com.example.examen_segundo_bimestre.Model.Album
import com.example.examen_segundo_bimestre.Model.Cancion
import com.example.examen_segundo_bimestre.R
import com.google.android.material.snackbar.Snackbar

class VerCanciones : AppCompatActivity() {


    lateinit var album: Album
    var listaDeCanciones: List<Cancion> = emptyList()  // Inicializar como lista vacía
    lateinit var adaptadorCanciones: CancionAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_canciones)


        // Obtener el ID del álbum desde el Intent
        val albumId = intent.getStringExtra("ALBUM_ID")


        // Funcionalidad Botones
        val botonRegresarHomeCanciones = findViewById<Button>(R.id.btn_Regresar_Canciones)
        botonRegresarHomeCanciones.setOnClickListener {
            irActividad(MainActivity::class.java)
        }

        val botonAgregarCanciones = findViewById<Button>(R.id.btn_Agregar_Canciones)
        botonAgregarCanciones.setOnClickListener {
            val albumId = intent.getStringExtra("ALBUM_ID")
            irActividadConID(AgregarCancion::class.java, albumId)
        }


        // Verificar si se dio un ID Válido
        if (!albumId.isNullOrEmpty()) {
            // Obtener el álbum correspondiente desde la base de datos
            AlbumFirestore().obtenerUnAlbum(albumId).addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    album = AlbumFirestore.crearAlbumFromDocument(documentSnapshot)
                    // Mostrar el nombre del álbum
                    val nombreAlbumTextView = findViewById<TextView>(R.id.txt_Inserte_Nombre)
                    nombreAlbumTextView.text = album.nombre

                    // Configurar el Adapter para el ListView con el nuevo adaptador
                    adaptadorCanciones = CancionAdapter(this, listaDeCanciones)
                    val listViewCanciones = findViewById<ListView>(R.id.lv_Listado_Canciones)
                    listViewCanciones.adapter = adaptadorCanciones

                    // Obtener el nombre de la canción agregada de los extras del Intent
                    val nombreCancionAgregada = intent.getStringExtra("NOMBRE_CANCION_AGREGADA")

                    // Verificar si hay un nombre de canción agregada
                    if (!nombreCancionAgregada.isNullOrEmpty()) {
                        // Mostrar SnackBar con el mensaje de la canción agregada
                        mostrarSnackbar("Canción Agregada: $nombreCancionAgregada")
                    }

                    // Después de agregar la canción a Firestore
                    AlbumFirestore().obtenerCancionesPorAlbumId(album.id)
                        .addOnSuccessListener { querySnapshot ->
                            // Mapear los documentos a objetos Cancion
                            listaDeCanciones = querySnapshot.documents.map { CancionFirestore.createCancionFromDocument(it) }

                            // Crear el nuevo adaptador con la lista actualizada
                            adaptadorCanciones = CancionAdapter(this, listaDeCanciones)

                            // Obtener la referencia al ListView y establecer el nuevo adaptador
                            val listViewCanciones = findViewById<ListView>(R.id.lv_Listado_Canciones)
                            listViewCanciones.adapter = adaptadorCanciones


                        }

                    // Implementar Menú de Opciones en el ListView de Canciones
                    val listViewCancionesContextMenu = findViewById<ListView>(R.id.lv_Listado_Canciones)
                    registerForContextMenu(listViewCancionesContextMenu)

                    listViewCanciones.setOnItemClickListener { _, _, position, _ ->
                        // obtener la canción directamente del adaptador
                        val cancionSeleccionada = listaDeCanciones[position]
                        // Puedes realizar alguna acción al hacer clic en una canción
                    }
                }
            }
        }

    }


    // Crear Menú de Opciones para Canciones
    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        // Completar Opciones del Menú
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_cancion, menu)
        // Obtener el índice de la canción seleccionada
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position

        // Acceder a la canción en la posición seleccionada
        val cancionSeleccionada = listaDeCanciones[position]
        // Almacenar el ID de la canción seleccionada si es necesario
        val idCancionSeleccionada = cancionSeleccionada.id

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position

        // Obtener la canción seleccionada desde el adaptador
        val cancionSeleccionada = listaDeCanciones[position]
        // Almacenar el ID de la canción seleccionada si es necesario
        val idCancionSeleccionada = cancionSeleccionada.id
        // Almacenar el ID del álbum asociado a la canción
        val idAlbumAsociado = cancionSeleccionada.albumId

        when (item.itemId) {
            R.id.mi_EditarCancion -> {
                irActividadConIDs(EditarCancion::class.java, idAlbumAsociado, idCancionSeleccionada)
                return true
            }
            R.id.mi_EliminarCancion -> {
                // Eliminar la canción seleccionada
                CancionFirestore().removeCancion(idCancionSeleccionada)

                // Actualizar la lista de canciones
                val task = CancionFirestore().obtenerCancionesPorAlbumId(album.id)
                task.addOnSuccessListener { querySnapshot ->
                    // Mapear los documentos a objetos Cancion
                    listaDeCanciones = querySnapshot.documents.map { CancionFirestore.createCancionFromDocument(it) }

                    // Crear el nuevo adaptador con la lista actualizada
                    val adaptadorCanciones = CancionAdapter(this, listaDeCanciones)

                    // Obtener la referencia al ListView y establecer el nuevo adaptador
                    val listViewCanciones = findViewById<ListView>(R.id.lv_Listado_Canciones)
                    listViewCanciones.adapter = adaptadorCanciones

                    // Mostrar Snackbar
                    mostrarSnackbar("Canción Eliminada")
                }
                return true
            }
            else -> return super.onContextItemSelected(item)
        }
    }


    // Funcion
    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }


    fun irActividadConID(clase: Class<*>, albumId: String?) {
        val intent = Intent(this, clase)
        intent.putExtra("ALBUM_ID", albumId)
        startActivity(intent)
    }

    // Funcion para ir a la actividad con ambos IDs
    fun irActividadConIDs(clase: Class<*>, idAlbum: String?, idCancion: String?) {
        val intent = Intent(this, clase)
        intent.putExtra("ALBUM_ID", idAlbum)
        intent.putExtra("CANCION_ID", idCancion)
        startActivity(intent)
    }

    // SnackBar
    private fun mostrarSnackbar(mensaje: String) {
        val rootView = findViewById<View>(android.R.id.content)
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_SHORT).show()
    }

}