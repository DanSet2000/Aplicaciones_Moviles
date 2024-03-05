package com.example.proyecto_segundo_bimestre.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_segundo_bimestre.Data.BaseDeDatos
import com.example.proyecto_segundo_bimestre.R
import com.example.proyecto_segundo_bimestre.RecyclerViews.RVAdaptJuegosPopulares
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class DashboardInicio : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_inicio)
        // Inicializar Recycler Views
        inicializarRecyclerViewJuegosPopulares()
        inicializarRecyclerViewJuegosNuevos()

        auth = FirebaseAuth.getInstance()

        // Funcionalidad Botones

        val btnLogout = findViewById<Button>(R.id.btn_Logout)
        btnLogout.setOnClickListener { cerrarSesion() }

        val btnBuscar = findViewById<Button>(R.id.btn_Buscar_Catalogo)
        btnBuscar.setOnClickListener {
            irActividad(BuscarVideojuego::class.java)
        }

        val btnJuegosFavs = findViewById<Button>(R.id.btn_Juegos_Favs)
        btnJuegosFavs.setOnClickListener {
            irActividad(MiColeccion::class.java)
        }

        val btnJuegos2023 = findViewById<Button>(R.id.btn_Juegos_2023)
        btnJuegos2023.setOnClickListener {
            irActividad(MiColeccion::class.java)
        }

        val btnMiCuenta = findViewById<Button>(R.id.btn_Mi_Cuenta)
        btnMiCuenta.setOnClickListener {
            irActividad(MiCuenta::class.java)
        }

        // Verificar si hay un mensaje en el intent
        val mensaje = intent.getStringExtra("mensaje")
        if (mensaje != null) {
            // Mostrar Snackbar con el mensaje
            val rootView: View = findViewById(android.R.id.content)
            Snackbar.make(rootView, mensaje, Snackbar.LENGTH_SHORT).show()
        }
    }

    // Funciones
    private fun cerrarSesion() {
        auth.signOut()
        // Redirigir a MainActivity después de cerrar sesión
        irActividadConMensaje(MainActivity::class.java, "Sesión cerrada exitosamente")
    }

    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun irActividadConMensaje(clase: Class<*>, mensaje: String) {
        val intent = Intent(this, clase)
        intent.putExtra("mensaje", mensaje)
        startActivity(intent)
    }

    fun inicializarRecyclerViewJuegosPopulares(){
        val recyclerView = findViewById<RecyclerView>(R.id.rv_Juegos_Populares)

        // Configura el LinearLayoutManager con orientación horizontal
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val adaptador = RVAdaptJuegosPopulares(
            this,
            BaseDeDatos.arregloJuegosPopulares,
            recyclerView
        )

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = DefaultItemAnimator()
        adaptador.notifyDataSetChanged()
    }

    fun inicializarRecyclerViewJuegosNuevos(){
        val recyclerView = findViewById<RecyclerView>(R.id.rv_juegos_nuevos)

        // Configura el LinearLayoutManager con orientación horizontal
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val adaptador = RVAdaptJuegosPopulares(
            this,
            BaseDeDatos.arregloJuegosNuevos,
            recyclerView
        )

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = DefaultItemAnimator()
        adaptador.notifyDataSetChanged()
    }
}