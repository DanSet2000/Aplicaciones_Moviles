package com.example.servicios_firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class IFirestore : AppCompatActivity() {

    // Variables
    var query: Query? = null
    val arreglo: ArrayList<ICities> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ifirestore)

        // Configurando el List View
        val listView = findViewById<ListView>(R.id.lv_firestore)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1,arreglo)
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        // Botones
        // Crear Datos de Prueba
        val botonDatosPrueba = findViewById<Button>(R.id.btn_fs_datos_prueba)
        botonDatosPrueba.setOnClickListener { crearDatosPrueba() }

        // Order By
        val botonOrderBy = findViewById<Button>(R.id.btn_fs_order_by)
        botonOrderBy.setOnClickListener { consultarConOrderBy(adaptador) }

        // Obtener Documento
        val botonObtenerDocumento = findViewById<Button>(R.id.btn_fs_odoc)
        botonObtenerDocumento.setOnClickListener { consultarDocumento(adaptador) }

        // Crear Datos
        val botonCrear = findViewById<Button>(R.id.btn_fs_crear)
        botonCrear.setOnClickListener { crearEjemplo() }

        // Eliminar Datos
        val botonFirebaseEliminar = findViewById<Button>(R.id.btn_fs_eliminar)
        botonFirebaseEliminar.setOnClickListener { eliminarRegistro() }

        // Empezar a Paginar
        val botonFirebaseEmpezarPaginar = findViewById<Button>(R.id.btn_fs_epaginar)
        botonFirebaseEmpezarPaginar.setOnClickListener {
            query = null; consultarCiudades(adaptador);
        }

        // Paginar
        val botonFirebasePaginar = findViewById<Button>(R.id.btn_fs_paginar)
        botonFirebasePaginar.setOnClickListener { consultarCiudades(adaptador) }

        // Consultar Indice Compuesto
        val botonIndiceCompuesto = findViewById<Button>(R.id.btn_fs_ind_comp)
        botonIndiceCompuesto.setOnClickListener { consultarIndiceCompuesto(adaptador) }

    }

    fun consultarCiudades(adaptador: ArrayAdapter<ICities>){
        val db = Firebase.firestore
        val citiesRef = db.collection("cities").orderBy("population").limit(1)
        var tarea: Task<QuerySnapshot>? = null

        if (query == null){
            tarea = citiesRef.get() // 1ra Vez
            limpiarArreglo()
            adaptador.notifyDataSetChanged()
        }else{
            // Consulta de la Consulta Anterior empezando en el nuevo documento
            tarea = query!!.get()
        }
        if (tarea != null){
            tarea.addOnSuccessListener { documentSnapshots ->
                guardarQuery(documentSnapshots, citiesRef)
                for (ciudad in documentSnapshots){
                    anadirAArregloCiudad(ciudad)
                }
                adaptador.notifyDataSetChanged()
            }
                .addOnFailureListener {
                    // Si llegan a haber fallos
                }
        }
        // [4,5,6,1,2,3,7,8,9,10,11]
        // [1,2,3] (limit = 3)
        // [4,5,6] (limit = 3) (cursor =3)
        // [7,8,9] (limit = 3) (cursor =6)
        // [10,11] (limit = 3) (cursor =9)
        // [] (limit = 3) (cursor =11)
    }

    fun crearDatosPrueba(){
        val db = Firebase.firestore

        // Copiar Ejemplo Web
        val cities = db.collection("cities")

        val data1 = hashMapOf(
            "name" to "San Francisco",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 860000,
            "regions" to listOf("west_coast", "norcal"),
        )
        cities.document("SF").set(data1)

        val data2 = hashMapOf(
            "name" to "Los Angeles",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 3900000,
            "regions" to listOf("west_coast", "socal"),
        )
        cities.document("LA").set(data2)

        val data3 = hashMapOf(
            "name" to "Washington D.C.",
            "state" to null,
            "country" to "USA",
            "capital" to true,
            "population" to 680000,
            "regions" to listOf("east_coast"),
        )
        cities.document("DC").set(data3)

        val data4 = hashMapOf(
            "name" to "Tokyo",
            "state" to null,
            "country" to "Japan",
            "capital" to true,
            "population" to 9000000,
            "regions" to listOf("kanto", "honshu"),
        )
        cities.document("TOK").set(data4)

        val data5 = hashMapOf(
            "name" to "Beijing",
            "state" to null,
            "country" to "China",
            "capital" to true,
            "population" to 21500000,
            "regions" to listOf("jingjinji", "hebei"),
        )
        cities.document("BJ").set(data5)
    }

    fun limpiarArreglo() {arreglo.clear()}

    fun anadirAArregloCiudad(ciudad: QueryDocumentSnapshot){
        // Ciudad ID
        val nuevaCiudad = ICities(
            ciudad.data.get("name") as String?,
            ciudad.data.get("state") as String?,
            ciudad.data.get("country") as String?,
            ciudad.data.get("capital") as Boolean?,
            ciudad.data.get("population") as Long?,
            ciudad.data.get("regions") as ArrayList<String>?
        )
        arreglo.add(nuevaCiudad)
    }

    fun consultarConOrderBy(adaptador: ArrayAdapter<ICities>){
        val db = Firebase.firestore
        val citiesRefUnico = db.collection("cities")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()
        citiesRefUnico.orderBy("population", Query.Direction.ASCENDING).get()
            .addOnSuccessListener {
                // It => eso (lo que llegue)
                for (ciudad in it){
                    ciudad.id
                    anadirAArregloCiudad(ciudad)
                }
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener{
                // Errores
            }
    }

    fun consultarDocumento(adaptador: ArrayAdapter<ICities>){
        val db = Firebase.firestore
        val citiesRefUnico = db.collection("cities")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()

        citiesRefUnico
            .document("BJ")
            .get() // obtener 1 DOCUMENTO
            .addOnSuccessListener {
                // it=> ES UN OBJETO!
                arreglo
                    .add(
                        ICities(
                            it.data?.get("name") as String?,
                            it.data?.get("state") as String?,
                            it.data?.get("country") as String?,
                            it.data?.get("capital") as Boolean?,
                            it.data?.get("population") as Long?,
                            it.data?.get("regions") as
                                    ArrayList<String>?,
                        )
                    )
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener {
                // Algo salio Mal
            }

    }

    fun crearEjemplo(){
        val db = Firebase.firestore
        val referencialEjemploEstudiante = db.collection("ejemplo")

        val datosEstudiante = hashMapOf(
            "nombre" to "Daniel",
            "graduado" to false,
            "promedio" to 16.00,
            "direccion" to hashMapOf(
                "direccion" to "Solanda",
                "numeroCalle" to 1234
            ),
            "materias" to listOf("web", "moviles")
        )

        // Identificador Quemado (crear / actualizar)
        referencialEjemploEstudiante
            .document("12345678")
            .set(datosEstudiante)
            .addOnSuccessListener {  }
            .addOnFailureListener {  }

        // Identificador Quemado pero autogenerado con Date
        val identificador = Date().time
        referencialEjemploEstudiante // (crear / actualizar)
            .document(identificador.toString())
            .set(datosEstudiante)
            .addOnSuccessListener {  }
            .addOnFailureListener {  }

        // SIN Identificador (crear)
        referencialEjemploEstudiante
            .add(datosEstudiante)
            .addOnCompleteListener {  }
            .addOnFailureListener {  }
    }

    fun eliminarRegistro(){
        val db = Firebase.firestore
        val referenciaEjemploEstudiante = db.collection("ejemplo")

        referenciaEjemploEstudiante
            .document("12345678")
            .delete()
            .addOnCompleteListener { /* Si Todo salio bien */ }
            .addOnFailureListener { /* Si Algo salió mal */ }
    }

    fun guardarQuery(documentSnapshots: QuerySnapshot, refCities: Query){
        if (documentSnapshots.size() > 0){
            val ultimoDocumento = documentSnapshots.documents[documentSnapshots.size() - 1]
            // Start After nos ayuda a paginar
            query = refCities.startAfter(ultimoDocumento)
        }
    }

    fun consultarIndiceCompuesto(adaptador: ArrayAdapter<ICities>){
        val db = Firebase.firestore
        val citiesRefUnico = db.collection("cities")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()
        citiesRefUnico.whereEqualTo("capital", false)
                      .whereLessThanOrEqualTo("population", 4000000)
                      .orderBy("population", Query.Direction.DESCENDING)
                      .get()
                      .addOnSuccessListener {
                          for (ciudad in it){
                              anadirAArregloCiudad(ciudad)
                          }
                          adaptador.notifyDataSetChanged()
                      }
            .addOnFailureListener {  }
    }
}