package com.example.proyecto_segundo_bimestre.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.example.proyecto_segundo_bimestre.R

class AgregarJuego : AppCompatActivity() {

    // Declarar las variables a nivel de clase para que sean accesibles en toda la clase
    private var nombreJuego: String? = null
    private var plataforma: String? = null
    private var fechaLanzamiento: String? = null
    private var genero: String? = null
    private var desarrolladoraJuego: String? = null
    private var distribuidorJuego: String? = null
    private var portadaJuego: Int = 0
    private var rating: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_juego)

        // Obtener datos del Intent
        nombreJuego = intent.getStringExtra("nombreJuego")
        plataforma = intent.getStringExtra("plataforma")
        fechaLanzamiento = intent.getStringExtra("fechadeLanzamiento")
        genero = intent.getStringExtra("genero")
        desarrolladoraJuego = intent.getStringExtra("desarrolladoraJuego")
        distribuidorJuego = intent.getStringExtra("distribuidorJuego")
        portadaJuego = intent.getIntExtra("portadaJuego", 0)
        rating = intent.getDoubleExtra("rating", 0.0)

        // Funcionalidad Botones
        val btnVolverInicio = findViewById<Button>(R.id.btn_Regresar_Agregar)
        btnVolverInicio.setOnClickListener {
            irActividad(DashboardInicio::class.java)
        }

        val btnAgregarJuego = findViewById<Button>(R.id.btn_Agregar_Juego)
        btnAgregarJuego.setOnClickListener {
            val intent = Intent(this, MiColeccion::class.java)
            // Pasa los datos del juego a través del Intent
            intent.putExtra("nombreJuego", nombreJuego)
            intent.putExtra("plataforma", plataforma)
            intent.putExtra("fechadeLanzamiento", fechaLanzamiento)
            intent.putExtra("genero", genero)
            intent.putExtra("desarrolladoraJuego", desarrolladoraJuego)
            intent.putExtra("distribuidorJuego", distribuidorJuego)
            intent.putExtra("portadaJuego", portadaJuego)
            intent.putExtra("rating", rating)
            startActivity(intent)
        }


        // Obtener datos del Intent
        val nombreJuego = intent.getStringExtra("nombreJuego")
        val plataforma = intent.getStringExtra("plataforma")
        val fechaLanzamiento = intent.getStringExtra("fechadeLanzamiento")
        val genero = intent.getStringExtra("genero")
        val desarrolladoraJuego = intent.getStringExtra("desarrolladoraJuego")
        val distribuidorJuego = intent.getStringExtra("distribuidorJuego")
        val portadaJuego = intent.getIntExtra("portadaJuego", 0)
        val ratingBar = findViewById<RatingBar>(R.id.rb_calificacion_Juego)
        val notaJuego = intent.getDoubleExtra("rating", 0.0)

        // Asignar datos a los TextView
        findViewById<TextView>(R.id.txt_Juego_Seleccionado_Nombre).text = nombreJuego
        findViewById<TextView>(R.id.txt_Recibir_Plataforma).text = plataforma
        findViewById<TextView>(R.id.txt_Recibir_Fecha).text = fechaLanzamiento
        findViewById<TextView>(R.id.txt_Recibir_Genero).text = genero
        findViewById<TextView>(R.id.txt_Recibir_Desarrollador).text = desarrolladoraJuego
        findViewById<TextView>(R.id.txt_Recibir_Distribuidor).text = distribuidorJuego
        findViewById<TextView>(R.id.txt_Recibit_Nota).text = notaJuego.toString()

        // Obtener el ID del recurso drawable del intent
        val idDrawable = intent.getIntExtra("portadaJuego", 0)

        // Obtener el valor de calificación del intent
        val rating = intent.getDoubleExtra("rating", 0.0)

        // Configurar la imagen con el recurso drawable
        val imgJuego = findViewById<ImageView>(R.id.img_Juego_Seleccionado)
        imgJuego.setImageResource(idDrawable)

        // Configurar el RatingBar con la calificación obtenida
        ratingBar.rating = rating.toFloat()
    }

    // Funciones
    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }


}