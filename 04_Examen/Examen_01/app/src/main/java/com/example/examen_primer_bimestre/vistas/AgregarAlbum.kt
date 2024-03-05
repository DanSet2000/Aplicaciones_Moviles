package com.example.examen_primer_bimestre.vistas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import com.example.examen_primer_bimestre.R
import com.example.examen_primer_bimestre.modelo.Album
import com.example.examen_primer_bimestre.operacionesCRUD.AlbumCRUD
import com.google.android.material.snackbar.Snackbar

class AgregarAlbum : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_album)

        // Obtener referencias a los elementos de la interfaz

        val inputNombreAlbum = findViewById<EditText>(R.id.input_Nombre_Album)
        val inputArtistaAlbum = findViewById<EditText>(R.id.input_Artista_Album)
        val inputGeneroAlbum = findViewById<EditText>(R.id.input_Genero_Album)
        val inputPrecioAlbum = findViewById<EditText>(R.id.input_Precio_Album)
        val inputAnioAlbum = findViewById<EditText>(R.id.input_Anio_Album)
        val switchExplicitoAlbum = findViewById<Switch>(R.id.sw_Explicito_Album)


        // Funcionalidad Botones

        val botonRegresarHome = findViewById<Button>(R.id.btn_Regresar_Home)
        botonRegresarHome.setOnClickListener{
            irActividad(MainActivity::class.java)
        }


        val botonCrearAlbum = findViewById<Button>(R.id.btn_Crear_Album)
        botonCrearAlbum.setOnClickListener {
            // Obtener los valores ingresados por el usuario
            val nombreAlbum = inputNombreAlbum.text.toString()
            val artistaAlbum = inputArtistaAlbum.text.toString()
            val generoAlbum = inputGeneroAlbum.text.toString()
            val precioAlbum = inputPrecioAlbum.text.toString().toDoubleOrNull() ?: 0.0
            val anioAlbum = inputAnioAlbum.text.toString().toIntOrNull() ?: 0
            val esExplicito = switchExplicitoAlbum.isChecked

            // Validar que no haya campos vacíos
            if (nombreAlbum.isBlank() || artistaAlbum.isBlank() || generoAlbum.isBlank()) {
                // Mostrar mensaje de error en un Snackbar
                mostrarSnackbarError("Completa todos los campos.")
            } else {
                // Crear un nuevo objeto Album
                val nuevoAlbum = Album(
                    nombreAlbum,
                    artistaAlbum,
                    generoAlbum,
                    precioAlbum,
                    anioAlbum,
                    esExplicito
                )

            // Utilizar la operación CRUD para agregar el nuevo álbum
            AlbumCRUD().crearAlbum(nuevoAlbum)

            // Regresa a Main Activity
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("NOMBRE_ALBUM_AGREGADO", nombreAlbum)
            startActivity(intent)
            }
        }
    }


    // Funcion
    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    // SnackBar
    private fun mostrarSnackbarError(mensaje: String) {
        val rootView: View = findViewById(android.R.id.content)
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_SHORT).show()
    }
}