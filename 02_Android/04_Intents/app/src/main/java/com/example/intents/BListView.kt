package com.example.intents

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

class BListView : AppCompatActivity() {
    val arreglo = BBaseDatosMemoria.arregloBEntrenador
    var posicionItemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        val listView = findViewById<ListView>(R.id.lv_list_view)

        val adaptador = ArrayAdapter(
            this,                           // Contexto
            android.R.layout.simple_list_item_1,   // Como se va a ver (XML)
            arreglo
        )

        // Se reflejan los cambios en pantalla
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonAnadirListView = findViewById<Button>(R.id.btn_anadir_list_view)
        botonAnadirListView.setOnClickListener {
            // Se escucha el click del Boton
            anadirEntrenador(adaptador)
        }
        registerForContextMenu(listView)
    }

    fun anadirEntrenador(adaptador: ArrayAdapter<BEntrenador>){
        arreglo.add(BEntrenador(1, "Daniel", "Descripcion"))

        adaptador.notifyDataSetChanged()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llamamos las opciones del Menú
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        // Obtener el ID del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_editar ->{
                mostrarSnackbar("${posicionItemSeleccionado}")
                return true
            }
            R.id.mi_eliminar ->{
                mostrarSnackbar("${posicionItemSeleccionado}")
                abrirDialogo()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    // Utilizar un Builder para poder construir un Dialogo
    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea Eliminar")
        builder.setPositiveButton(
            "Aceptar", DialogInterface.OnClickListener{
                    dialog, which -> mostrarSnackbar("Acepto ${which}")
            }
        )
        builder.setNegativeButton("Cancelar", null)

        val opciones = resources.getStringArray(R.array.string_array_opciones_dialogo)

        val seleccionPrevia = booleanArrayOf(
            true,   // Lunes Seleccionado
            false,  // Martes NO Seleccionado
            false   // Miercoles NO Seleccionado
        )

        builder.setMultiChoiceItems(
            opciones,
            seleccionPrevia,
            {dialog, which, isChecked -> mostrarSnackbar("Item: ${which}")}
        )

        val dialogo = builder.create()
        dialogo.show()
    }




    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(findViewById(R.id.lv_list_view), texto, Snackbar.LENGTH_LONG)
        snack.show()
    }
}