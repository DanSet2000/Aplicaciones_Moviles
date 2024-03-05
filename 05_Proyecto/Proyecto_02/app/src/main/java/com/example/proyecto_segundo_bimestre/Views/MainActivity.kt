package com.example.proyecto_segundo_bimestre.Views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import com.example.proyecto_segundo_bimestre.R
import com.google.android.material.snackbar.Snackbar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        // Funcionalidad Botones

        val botonIniciarSesion = findViewById<Button>(R.id.btn_Login_Inicio)
        botonIniciarSesion.setOnClickListener{
            irActividad(IniciarSesion::class.java)
        }

        val botonRegistrarse = findViewById<Button>(R.id.btn_Registrar)
        botonRegistrarse.setOnClickListener {
            irActividad(RegistrarUsuario::class.java)
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

    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

}
