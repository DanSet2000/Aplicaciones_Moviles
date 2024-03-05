package com.example.intents

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar

class MainActivity : ComponentActivity() {

    // Crear Funciones para recibir la información de los Intents

    // Intent Explicito recibiendo parámetros

    val callbackContenidoIntentExplicito = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){
        result -> if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null){
                // Logica del Negocio
                val data = result.data
                mostrarSnackbar("${data?.getStringExtra("nombreModificado")}")
            }
        }
    }

    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(findViewById(R.id.id_layout_main), texto, Snackbar.LENGTH_LONG)
        snack.show()
    }

    // Intent Implicito recibiendo contactos del teléfono

    val callbackIntentPickUri = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){
        result -> if(result.resultCode == RESULT_OK){
            if(result.data!!.data != null){
                val uri: Uri = result.data!!.data!!
                val cursor = contentResolver.query(uri,null,null,null,null,null)
                cursor?.moveToFirst()
                val indiceTelefono = cursor?.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                val telefono = cursor?.getString(indiceTelefono!!)
                cursor?.close()
                mostrarSnackbar("Telefono ${telefono}")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Botones

        val botonCicloVida = findViewById<Button>(R.id.btn_ciclo_vida)
        botonCicloVida.setOnClickListener{
            irActividad(ACicloVida::class.java)
        }

        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonListView.setOnClickListener{
            irActividad(BListView::class.java)
        }

        val botonIntentImplicito = findViewById<Button>(R.id.btn_ir_intent_implicito)
        botonIntentImplicito.setOnClickListener{
            val intentConRespuesta = Intent(
                Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            )

            callbackIntentPickUri.launch(intentConRespuesta)
        }

        val botonIntentExplicito = findViewById<Button>(R.id.btn_ir_intent_explicito)
        botonIntentExplicito.setOnClickListener{
            abrirActividadConParametros(CIntentExplicitoParametros::class.java)
        }
    }

    // Aquí termina On Create

    // Funcion
    fun abrirActividadConParametros(clase: Class<*>){
        val intentExplicito = Intent(this, clase)
        // Enviar Parametros (solamente variables primitivas)
        intentExplicito.putExtra("nombre", "Daniel")
        intentExplicito.putExtra("apellido", "Carvajal")
        intentExplicito.putExtra("edad", 23)

        callbackContenidoIntentExplicito.launch(intentExplicito)
    }

    // Funcion
    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}