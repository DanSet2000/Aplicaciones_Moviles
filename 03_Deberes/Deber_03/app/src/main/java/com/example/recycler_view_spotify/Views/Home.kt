package com.example.recycler_view_spotify.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recycler_view_spotify.Data.BaseDeDatos
import com.example.recycler_view_spotify.R
import com.example.recycler_view_spotify.RecylerViews.RecyclerViewAdaptadorArtista
import com.example.recycler_view_spotify.RecylerViews.RecyclerViewAdaptadorReciente
import com.example.recycler_view_spotify.RecylerViews.RecyclerViewAdaptadorSugerencias

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        inicializarRecyclerView()
        inicializarRecyclerViewSugerencia()
        inicializarRecyclerViewReciente1()
        inicializarRecyclerViewReciente2()

        // Oculta la Action Bar
        if (getSupportActionBar() != null) {
            getSupportActionBar()?.hide();
        }

        // Funcionalidad Botones

        val botonHome = findViewById<ImageButton>(R.id.img_btn_Home_Inicio)
        botonHome.setOnClickListener{irActividad(Home::class.java)}

        val botonTuBiblioteca = findViewById<ImageButton>(R.id.img_btn_Biblioteca_Home)
        botonTuBiblioteca.setOnClickListener{irActividad(Biblioteca::class.java)}

    }



    // Funciones

    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun inicializarRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.Recycler_Artistas_Recientes)

        // Configura el LinearLayoutManager con orientación horizontal
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val adaptador = RecyclerViewAdaptadorArtista(
            this, // Contexto
            BaseDeDatos.arregloArtistas, // Arreglo datos
            recyclerView // Recycler view
        )

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = DefaultItemAnimator()
        adaptador.notifyDataSetChanged()
    }

    fun inicializarRecyclerViewSugerencia() {
        val recyclerViewSugerencia = findViewById<RecyclerView>(R.id.Recycler_Sugerencia_del_Dia)

        // Configura el LinearLayoutManager con orientación horizontal
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val adaptadorSugerencia = RecyclerViewAdaptadorSugerencias(
            this, // Contexto
            BaseDeDatos.arregloSugerencias, // Arreglo datos
            recyclerViewSugerencia // Recycler view
        )

        recyclerViewSugerencia.layoutManager = layoutManager
        recyclerViewSugerencia.adapter = adaptadorSugerencia
        recyclerViewSugerencia.itemAnimator = DefaultItemAnimator()
        adaptadorSugerencia.notifyDataSetChanged()
    }

    fun inicializarRecyclerViewReciente1(){
        val recyclerViewReciente1 = findViewById<RecyclerView>(R.id.Recycler_Inicio_1)

        val adaptadorReciente = RecyclerViewAdaptadorReciente(
            this, // Contexto
            BaseDeDatos.arregloReciente1, // Arreglo datos
            recyclerViewReciente1 // Recycler view
        )

        recyclerViewReciente1.adapter = adaptadorReciente
        recyclerViewReciente1.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerViewReciente1.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptadorReciente.notifyDataSetChanged()
    }

    fun inicializarRecyclerViewReciente2(){
        val recyclerViewReciente2 = findViewById<RecyclerView>(R.id.Recycler_Inicio_2)

        val adaptadorReciente = RecyclerViewAdaptadorReciente(
            this, // Contexto
            BaseDeDatos.arregloReciente2, // Arreglo datos
            recyclerViewReciente2 // Recycler view
        )

        recyclerViewReciente2.adapter = adaptadorReciente
        recyclerViewReciente2.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerViewReciente2.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptadorReciente.notifyDataSetChanged()
    }
}