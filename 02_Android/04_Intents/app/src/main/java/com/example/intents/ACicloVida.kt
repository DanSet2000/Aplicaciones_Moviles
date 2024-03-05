package com.example.intents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar

class ACicloVida : AppCompatActivity() {
    var textoGlobal = ""

    // Funcion
    fun mostrarSnackbar(texto:String){
        textoGlobal += texto
        Snackbar.make(findViewById(R.id.cl_ciclo_vida),textoGlobal, Snackbar.LENGTH_LONG).setAction("Action",null).show()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aciclo_vida)
        mostrarSnackbar("Hola")
        mostrarSnackbar("onCreate")
    }

    override fun onStart() {
        super.onStart()
        mostrarSnackbar("onStart")
    }

    override fun onResume() {
        super.onResume()
        mostrarSnackbar("onResume")
    }

    override fun onRestart() {
        super.onRestart()
        mostrarSnackbar("onRestart")
    }

    override fun onPause() {
        super.onPause()
        mostrarSnackbar("onPause")
    }

    override fun onStop() {
        super.onStop()
        mostrarSnackbar("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        mostrarSnackbar("onDestroy")
    }


    // Funciones para Arreglar Bugs

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            // Guardar las variables Primitivas
            putString("textoGuardado", textoGlobal)
        }

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Recuperar las variables Primitivas
        val textoRecuperado:String? = savedInstanceState.getString("textoGuardado")

        if(textoRecuperado != null){
            mostrarSnackbar(textoRecuperado)
            textoGlobal = textoRecuperado
        }

    }
}