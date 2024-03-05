package com.example.proyecto_segundo_bimestre.Views

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.proyecto_segundo_bimestre.R
import com.google.firebase.auth.FirebaseAuth

class MiCuenta : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var txtRecibeUsuario: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mi_cuenta)

        auth = FirebaseAuth.getInstance()

        // Inicializar el TextView
        txtRecibeUsuario = findViewById(R.id.txt_Recibe_Usuario)

        // Obtener el nombre de usuario desde las preferencias compartidas
        val sharedPreferences: SharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val nombreUsuario: String? = sharedPreferences.getString("nombre", "")

        // Establecer el nombre de usuario en el TextView
        txtRecibeUsuario.text = nombreUsuario



        // Funcionalidad Botones
        val botonRegresar = findViewById<Button>(R.id.btn_Regresar_Mi_Cuenta)
        botonRegresar.setOnClickListener{
            irActividad(DashboardInicio::class.java)
        }

        val botonLogout = findViewById<Button>(R.id.btn_logout_Mi_Cuenta)
        botonLogout.setOnClickListener {
            cerrarSesion()
        }
    }

    // Funciones

    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    // Funciones
    private fun cerrarSesion() {
        auth.signOut()
        // Redirigir a MainActivity después de cerrar sesión
        irActividadConMensaje(MainActivity::class.java, "Sesión cerrada exitosamente")
        finish()
    }

    fun irActividadConMensaje(clase: Class<*>, mensaje: String) {
        val intent = Intent(this, clase)
        intent.putExtra("mensaje", mensaje)
        startActivity(intent)
    }
}