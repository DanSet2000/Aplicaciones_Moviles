package com.example.examen_segundo_bimestre.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Switch
import com.example.examen_segundo_bimestre.Controller.CancionFirestore
import com.example.examen_segundo_bimestre.Model.Cancion
import com.example.examen_segundo_bimestre.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class AgregarCancion : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_cancion)

        // Funcionalidad Botones
        val botonRegresarListaCanciones = findViewById<Button>(R.id.btn_Regresar_Listado_Canciones)
        botonRegresarListaCanciones.setOnClickListener {
            val albumId = intent.getStringExtra("ALBUM_ID") ?: ""
            irActividad(VerCanciones::class.java, albumId)
        }

        val botonCrearCancion = findViewById<Button>(R.id.btn_Crear_Cancion)
        botonCrearCancion.setOnClickListener {
            crearNuevaCancion()
        }

    }


    // Funciones
    fun irActividad(clase: Class<*>, albumId: String) {
        val intent = Intent(this, clase)
        intent.putExtra("ALBUM_ID", albumId)
        startActivity(intent)
    }

    fun crearNuevaCancion() {
        // Obtener datos ingresados por el Usuario
        val nombreCancion = findViewById<TextInputEditText>(R.id.input_Nombre_Cancion).text.toString()
        val duracionCancionText = findViewById<TextInputEditText>(R.id.input_Duracion_Cancion).text.toString()
        val tieneLetra = findViewById<Switch>(R.id.sw_Letra_Cancion).isChecked
        val escritorCancion = findViewById<TextInputEditText>(R.id.input_Escritor_Cancion).text.toString()
        val productorCancion = findViewById<TextInputEditText>(R.id.input_Productor_Cancion).text.toString()
        val colaboradorCancion = findViewById<TextInputEditText>(R.id.input_Colaborador_Cancion).text.toString()

        // Obtener el ID del álbum desde el Intent
        val albumId = intent.getStringExtra("ALBUM_ID") ?: ""

        // Validar que no haya campos vacíos
        if (nombreCancion.isBlank() || duracionCancionText.isBlank() || escritorCancion.isBlank() || productorCancion.isBlank()) {
            // Mostrar mensaje de error en un Snackbar
            mostrarSnackbarError("Completa todos los campos.")
            return
        }

        // Convertir la duración a Double si la cadena no está vacía
        val duracionCancion = if (duracionCancionText.isNotBlank()) {
            try {
                duracionCancionText.toDouble()
            } catch (e: NumberFormatException) {
                // Manejar la excepción, por ejemplo, mostrando un mensaje al usuario
                mostrarSnackbarError("La duración debe ser un número válido.")
                return
            }
        } else {
            // Manejar el caso en que la cadena de duración es vacía
            mostrarSnackbarError("Ingresa una duración válida.")
            return
        }

        // Crear una nueva instancia de la Cancion
        val nuevaCancion = Cancion(
            id = "", // Firestore asignará automáticamente un ID
            albumId = albumId,
            nombre = nombreCancion,
            duracion = duracionCancion,
            artistaColaborador = colaboradorCancion,
            letra = tieneLetra,
            escritor = escritorCancion,
            productor = productorCancion
        )

        // Agregar la nueva canción a la base de datos
        CancionFirestore().crearCancion(nuevaCancion)

        // Regresar a la actividad de ver canciones del álbum
        val intent = Intent(this, VerCanciones::class.java)
        intent.putExtra("ALBUM_ID", albumId)
        intent.putExtra("NOMBRE_CANCION_AGREGADA", nombreCancion) // Agregar el nombre como extra
        startActivity(intent)
    }


    // SnackBar
    private fun mostrarSnackbar(mensaje: String) {
        val rootView: View = findViewById(android.R.id.content)
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_SHORT).show()
    }

    private fun mostrarSnackbarError(mensaje: String) {
        val rootView = findViewById<View>(android.R.id.content)
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_LONG).show()
    }
}
