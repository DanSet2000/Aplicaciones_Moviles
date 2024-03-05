package com.example.proyecto_segundo_bimestre.Views

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.proyecto_segundo_bimestre.Model.Usuario
import com.example.proyecto_segundo_bimestre.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrarUsuario : AppCompatActivity() {

    // Referencia a la base de datos
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_usuario)

        // Inicializar la base de datos
        database = FirebaseDatabase.getInstance().reference

        // Funcionalidad Botones
        val botonRegresar = findViewById<Button>(R.id.btn_Regresar_Inicio_Registro)
        botonRegresar.setOnClickListener {
            irActividad(MainActivity::class.java)
        }

        val botonCrearCuenta = findViewById<Button>(R.id.btn_Crear_Cuenta)
        botonCrearCuenta.setOnClickListener {
            registrarUsuario()
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

    private fun mostrarSnackbar(mensaje: String) {
        val rootView: View = findViewById(android.R.id.content)
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_SHORT).show()
    }

    private fun registrarUsuario() {
        // Obtén los valores ingresados por el usuario en los campos de nombre, correo y contraseña
        val nombreUsuario = findViewById<TextInputEditText>(R.id.input_Crear_Usuario)?.text.toString().trim()
        val correoUsuario = findViewById<TextInputEditText>(R.id.input_Crear_Correo)?.text.toString().trim()
        val contrasenia = findViewById<EditText>(R.id.input_Crear_Contrasenia)?.text.toString().trim()

        // Validaciones básicas, puedes agregar más según tus requisitos
        if (nombreUsuario.isEmpty() || correoUsuario.isEmpty() || contrasenia.isEmpty()) {
            mostrarSnackbar("Completa todos los campos")
            return
        }

        // Crear usuario en FirebaseAuth
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(correoUsuario, contrasenia)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Registro exitoso, obtenemos el usuario actual
                    val firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser

                    // Creamos un objeto Usuario con el ID generado por FirebaseAuth
                    val nuevoUsuario = Usuario(firebaseUser?.uid ?: "", nombreUsuario, correoUsuario)

                    // Almacenamos el nuevo usuario en la base de datos
                    database.child("usuarios").child(nuevoUsuario.id).setValue(nuevoUsuario)

                    // Almacenar el nombre de usuario en preferencias compartidas
                    val sharedPreferencesEditor = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE).edit()
                    sharedPreferencesEditor.putString("nombre", nuevoUsuario.nombre)
                    sharedPreferencesEditor.apply()

                    // Mostrar Snackbar y redirigir a MainActivity con mensaje
                    irActividadConMensaje(MainActivity::class.java, "Cuenta creada: $nombreUsuario")
                } else {
                    // Si falla el registro, puedes manejarlo aquí
                    mostrarSnackbar("Error al crear la cuenta")
                }
            }
    }
}