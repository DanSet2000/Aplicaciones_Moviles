package com.example.proyecto_segundo_bimestre.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_segundo_bimestre.Model.Videojuego
import com.example.proyecto_segundo_bimestre.R
import com.example.proyecto_segundo_bimestre.RecyclerViews.RVAdaptMisJuegos

class MiColeccion : AppCompatActivity() {

    private var misJuegos = ArrayList<Videojuego>()
    private var adaptadorMisJuegos: RVAdaptMisJuegos? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mi_coleccion)
        // Inicializar Recycler Views
        inicializarRecyclerViewMisJuegos()

        // Verificar si hay datos en el Intent
        if (intent.hasExtra("nombreJuego")) {
            // Llamar a agregarJuegoAColeccion con los datos del Intent
            agregarJuegoAColeccion(
                intent.getStringExtra("nombreJuego"),
                intent.getStringExtra("plataforma"),
                intent.getStringExtra("fechadeLanzamiento"),
                intent.getStringExtra("genero"),
                intent.getStringExtra("desarrolladoraJuego"),
                intent.getStringExtra("distribuidorJuego"),
                intent.getIntExtra("portadaJuego", 0),
                intent.getDoubleExtra("rating", 0.0)
            )
        }

        // Funcionalidad Botones
        val btnVolverInicio = findViewById<Button>(R.id.btn_Regresar_Mi_Coleccion)
        btnVolverInicio.setOnClickListener {
            irActividad(DashboardInicio::class.java)
        }
    }


    // Funciones

    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }


    private fun inicializarRecyclerViewMisJuegos() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_Mis_Juegos)

        // Configura el LinearLayoutManager con orientación horizontal
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Crear un nuevo adaptador y asignarlo a tu RecyclerView
        adaptadorMisJuegos = RVAdaptMisJuegos(
            this,
            ArrayList(),  // Crear una nueva instancia de ArrayList
            recyclerView,
            object : RVAdaptMisJuegos.OnItemClickListener {
                override fun onItemClick(videojuego: Videojuego) {
                    // Lógica para manejar el clic en un juego
                }
            }
        )

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adaptadorMisJuegos
        recyclerView.itemAnimator = DefaultItemAnimator()
        adaptadorMisJuegos?.notifyDataSetChanged()
    }

    private fun agregarJuegoAColeccion(
        nombreJuego: String?,
        plataforma: String?,
        fechaLanzamiento: String?,
        genero: String?,
        desarrolladoraJuego: String?,
        distribuidorJuego: String?,
        portadaJuego: Int,
        rating: Double
    ) {
        // Crear un nuevo objeto Videojuego con los datos obtenidos
        val nuevoJuego = Videojuego(
            nombreJuego ?: "",
            plataforma ?: "",
            fechaLanzamiento ?: "",
            genero ?: "",
            desarrolladoraJuego ?: "",
            distribuidorJuego ?: "",
            rating,
            portadaJuego
        )

        // Agregar el nuevo juego a la lista
        misJuegos.add(nuevoJuego)

        // Notificar al adaptador que los datos han cambiado
        adaptadorMisJuegos?.updateList(misJuegos)
    }
}