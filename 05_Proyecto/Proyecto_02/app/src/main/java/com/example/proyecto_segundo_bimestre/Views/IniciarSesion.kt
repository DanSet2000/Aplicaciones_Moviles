package com.example.proyecto_segundo_bimestre.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.proyecto_segundo_bimestre.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class IniciarSesion : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)

        // Inicializar Firebase Authentication
        auth = FirebaseAuth.getInstance()

        // Funcionalidad Botones
        val botonRegresar = findViewById<Button>(R.id.btn_Regresar_Inicio_Login)
        botonRegresar.setOnClickListener {
            irActividad(MainActivity::class.java)
        }

        val botonIniciarSesion = findViewById<Button>(R.id.btn_Login)
        botonIniciarSesion.setOnClickListener {
            // Obtener datos del formulario
            val correo = obtenerTexto(R.id.input_Login_Correo)
            val contrasenia = obtenerTexto(R.id.input_Login_Contrasenia)

            // Validar que los campos no estén vacíos
            if (correo.isNotEmpty() && contrasenia.isNotEmpty()) {
                iniciarSesion(correo, contrasenia)
            } else {
                mostrarSnackbar("Todos los campos son requeridos")
            }
        }


    }

    // Funciones

    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun irActividadConMensaje(clase: Class<*>, mensaje: String) {
        val intent = Intent(this, clase)
        intent.putExtra("mensaje", mensaje)
        startActivity(intent)
    }

    private fun obtenerTexto(id: Int): String {
        return findViewById<EditText>(id).text.toString()
    }

    private fun mostrarSnackbar(mensaje: String) {
        val rootView: View = findViewById(android.R.id.content)
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_SHORT).show()
    }

    private fun iniciarSesion(correo: String, contrasenia: String) {
        val auth = FirebaseAuth.getInstance()

        auth.signInWithEmailAndPassword(correo, contrasenia)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Inicio de sesión exitoso
                    // Mostrar Snackbar de inicio de sesión exitoso
                    irActividadConMensaje(DashboardInicio::class.java, "Inicio de sesión exitoso")

                } else {
                    // Mostrar Snackbar de error en el inicio de sesión
                    mostrarSnackbar("Error en el inicio de sesión")
                }
            }
    }


}