package com.example.examen_con_sqlite.Views

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.example.examen_con_sqlite.Controller.CancionCRUD
import com.example.examen_con_sqlite.Model.Cancion
import com.example.examen_con_sqlite.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class EditarCancion : AppCompatActivity() {

    // Variables para almacenar el ID del álbum y canción que se están editando
    private var albumId: Int = -1
    private var cancionId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_cancion)

        // Botones
        val botonRegresar = findViewById<Button>(R.id.btn_Regresar_Listado_Canciones_Editar)
        botonRegresar.setOnClickListener {
            irActividadConID(VerCanciones::class.java, albumId)
        }

        val botonActualizar = findViewById<Button>(R.id.btn_Actualizar_Cancion)
        botonActualizar.setOnClickListener {
            actualizarCancion()

        }

        // Obtener el ID del álbum desde el Intent
        albumId = intent.getIntExtra("ALBUM_ID", -1)
        // Obtener el ID de la canción desde el Intent
        cancionId = intent.getIntExtra("CANCION_ID", -1)

        // Verificar si se recibió un ID válido
        if (albumId != -1 || cancionId != -1) {
            // Cargar datos del álbum y llenar la interfaz
            cargarDatosDeLaCancion(albumId, cancionId)
        }else{
            // Manejar el caso en el que no se proporcionó un ID válido
            Toast.makeText(this, "No se proporcionó un ID de álbum y/o canción válido", Toast.LENGTH_SHORT).show()
            finish() // Cerrar la actividad si no hay ID válido
        }



    }


    // Funciones

    private fun irActividadConIDs(clase: Class<*>, idAlbum: Int, idCancion: Int) {
        val intent = Intent(this, clase)
        intent.putExtra("ALBUM_ID", idAlbum)
        intent.putExtra("CANCION_ID", idCancion)
        startActivity(intent)
    }

    private fun irActividadConID(clase: Class<*>, idCancion: Int) {
        val intent = Intent(this, clase)
        intent.putExtra("ALBUM_ID", albumId)
        intent.putExtra("CANCION_ID", idCancion)
        startActivity(intent)
    }

    private fun cargarDatosDeLaCancion(albumId: Int, cancionId: Int) {
        // Obtener la canción de la base de datos o donde se almacena las canciones
        val cancionAEditar = CancionCRUD(this).obtenerCancionPorId(cancionId)

        // Llenar la interfaz con los datos actuales de la canción
        cancionAEditar?.let {
            findViewById<EditText>(R.id.input_Editar_Nombre_Cancion).setText(it.nombre)
            findViewById<EditText>(R.id.input_Editar_Duracion_Cancion).setText(it.duracion.toString())
            findViewById<EditText>(R.id.input_Editar_Colaborador_Cancion).setText(it.artistaColaborador)
            findViewById<Switch>(R.id.sw_Editar_Letra_Cancion).isChecked = it.letra
            findViewById<EditText>(R.id.input_Editar_Escritor_Cancion).setText(it.escritor)
            findViewById<EditText>(R.id.input_Editar_Productor_Cancion).setText(it.productor)
        }
    }

    private fun actualizarCancion() {
        // Obtener nuevos valores de la interfaz de usuario
        val nuevoNombre = findViewById<TextInputEditText>(R.id.input_Editar_Nombre_Cancion).text.toString()
        val nuevaDuracion = findViewById<TextInputEditText>(R.id.input_Editar_Duracion_Cancion).text.toString().toDoubleOrNull() ?: 0.0
        val nuevoArtista = findViewById<TextInputEditText>(R.id.input_Editar_Colaborador_Cancion).text.toString()
        val nuevoLetra = findViewById<Switch>(R.id.sw_Editar_Letra_Cancion).isChecked
        val nuevoEscritor = findViewById<TextInputEditText>(R.id.input_Editar_Escritor_Cancion).text.toString()
        val nuevoProductor = findViewById<TextInputEditText>(R.id.input_Editar_Productor_Cancion).text.toString()

        // Validar que no haya campos vacíos
        if (nuevoNombre.isBlank() || nuevoArtista.isBlank()) {
            mostrarSnackbarError("Completa todos los campos.")
        }else{
            // Crear un objeto Cancion con los nuevos valores
            val cancionActualizada = Cancion(
                id = cancionId,
                albumId = albumId,
                nombre = nuevoNombre,
                duracion = nuevaDuracion,
                artistaColaborador = nuevoArtista,
                letra = nuevoLetra,
                escritor = nuevoEscritor,
                productor = nuevoProductor
            )
            // Actualizar la canción utilizando CancionCRUD
            CancionCRUD(this).actualizarCancion(cancionActualizada)

            // Regresar a VerCanciones
            irActividadConIDs(VerCanciones::class.java, albumId, cancionId)


        }
    }

    // SnackBar
    private fun mostrarSnackbarError(mensaje: String) {
        val rootView: View = findViewById(android.R.id.content)
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_SHORT).show()
    }

}