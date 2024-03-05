package com.example.examen_segundo_bimestre.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.example.examen_segundo_bimestre.Controller.AlbumFirestore
import com.example.examen_segundo_bimestre.Model.Album
import com.example.examen_segundo_bimestre.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class EditarAlbum : AppCompatActivity() {

    // Variable para almacenar el ID del álbum que se está editando
    private var albumId: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_album)

        // Funcionalidad Botones

        val botonRegresarHome = findViewById<Button>(R.id.btn_Regresar_Editar_Album)
        botonRegresarHome.setOnClickListener{
            irActividad(MainActivity::class.java)
        }

        val botonActualizar = findViewById<Button>(R.id.btn_Actualizar_Album)
        botonActualizar.setOnClickListener {
            actualizarAlbum()
        }



        // Obtener ID del álbum recibido del Intent
        albumId = intent.getStringExtra("ALBUM_ID") ?: ""

        // Verificar si se recibió un ID válido
        if (albumId.isNotEmpty()) {
            // Cargar datos del álbum y llenar la interfaz
            cargarDatosDelAlbum(albumId)
        } else {
            // Manejar el caso en el que no se proporcionó un ID válido
            Toast.makeText(this, "No se proporcionó un ID de álbum válido", Toast.LENGTH_SHORT).show()
            finish()
        }

    }

    // Funciones

    private fun cargarDatosDelAlbum(albumId: String) {
        AlbumFirestore().obtenerUnAlbum(albumId)
            .addOnSuccessListener { documentSnapshot ->
                val album = if (documentSnapshot.exists()) {
                    AlbumFirestore.crearAlbumFromDocument(documentSnapshot)
                } else {
                    null
                }

                // Llenar la interfaz con los datos actuales del álbum
                album?.let {
                    findViewById<EditText>(R.id.input_Editar_Nombre_Album).setText(it.nombre)
                    findViewById<EditText>(R.id.input_Editar_Artista_Album).setText(it.artista)
                    findViewById<EditText>(R.id.input_Editar_Anio_Album).setText(it.anioLanzamiento.toString())
                    findViewById<EditText>(R.id.input_Editar_Precio_Album).setText(it.precio.toString())
                    findViewById<EditText>(R.id.input_Editar_Genero_Album).setText(it.genero)
                    findViewById<Switch>(R.id.sw_Explicito_Album2).isChecked = it.esExplicito
                }
            }
            .addOnFailureListener { e ->
                // Manejar el fallo, por ejemplo, mostrar un mensaje al usuario
                Toast.makeText(this, "Error al cargar datos del álbum", Toast.LENGTH_SHORT).show()
                finish()
            }
    }

    private fun actualizarAlbum() {
        // Obtener nuevos valores de la interfaz de usuario
        val nuevoNombre = findViewById<TextInputEditText>(R.id.input_Editar_Nombre_Album).text.toString()
        val nuevoArtista = findViewById<TextInputEditText>(R.id.input_Editar_Artista_Album).text.toString()
        val nuevoAnio = findViewById<TextInputEditText>(R.id.input_Editar_Anio_Album).text.toString().toIntOrNull() ?: 0
        val nuevoPrecio = findViewById<TextInputEditText>(R.id.input_Editar_Precio_Album).text.toString().toDoubleOrNull() ?: 0.0
        val nuevoGenero = findViewById<TextInputEditText>(R.id.input_Editar_Genero_Album).text.toString()
        val nuevoEsExplicito = findViewById<Switch>(R.id.sw_Explicito_Album2).isChecked


        // Validar que no haya campos vacíos
        if (nuevoNombre.isBlank() || nuevoArtista.isBlank() || nuevoGenero.isBlank()) {
            // Mostrar mensaje de error en un Snackbar
            mostrarSnackbarError("Completa todos los campos.")
        } else {
            // Crear un objeto Album con los nuevos valores
            val albumActualizado = Album(
                id = albumId,
                nombre = nuevoNombre,
                artista = nuevoArtista,
                anioLanzamiento = nuevoAnio,
                precio = nuevoPrecio,
                genero = nuevoGenero,
                esExplicito = nuevoEsExplicito
            )

            // Actualizar el álbum utilizando AlbumCRUD
            AlbumFirestore().updateAlbum(albumId, albumActualizado)

            //Regresar a Main Activity
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("ALBUM_ACTUALIZADO", "Álbum actualizado: $nuevoNombre")
            startActivity(intent)

        }
    }

    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    // SnackBar
    fun mostrarSnackbarError(mensaje: String) {
        val rootView: View = findViewById(android.R.id.content)
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_SHORT).show()
    }
}

