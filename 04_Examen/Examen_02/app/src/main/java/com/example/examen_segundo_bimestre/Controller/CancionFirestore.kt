package com.example.examen_segundo_bimestre.Controller

import com.example.examen_segundo_bimestre.Model.Cancion
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class CancionFirestore {

    private val db = FirebaseFirestore.getInstance()

    //Función estática en la clase `Cancion` que crea y devuelve una instancia de `Cancion` a partir de un `DocumentSnapshot`.
    //Se utiliza para convertir datos recuperados de Firestore en objetos `Cancion` en la aplicación Kotlin
    companion object {
        fun createCancionFromDocument(document: DocumentSnapshot): Cancion {
            val id = document.id
            val albumId = document.data?.get("albumId") as String?
            val nombre = document.data?.get("nombre") as String?
            val duracion = document.data?.get("duracion") as Double?
            val artistaColaborador = document.data?.get("artistaColaborador") as String?
            val letra = document.data?.get("letra") as Boolean?
            val escritor = document.data?.get("escritor") as String?
            val productor = document.data?.get("productor") as String?

            if (id == null || albumId == null || nombre == null || duracion == null || artistaColaborador == null || letra == null || escritor == null || productor == null) {
                return Cancion()
            }

            return Cancion(id, albumId, nombre, duracion, artistaColaborador, letra, escritor, productor)
        }
    }

    fun obtenerTodasCanciones(): Task<QuerySnapshot> {
        return db.collection("canciones").get()
    }

    fun obtenerUnaCancion(id: String): Task<DocumentSnapshot> {
        return db.collection("canciones").document(id).get()
    }

    fun crearCancion(cancion: Cancion) {
        val cancionData = hashMapOf(
            "albumId" to cancion.albumId,
            "nombre" to cancion.nombre,
            "duracion" to cancion.duracion,
            "artistaColaborador" to cancion.artistaColaborador,
            "letra" to cancion.letra,
            "escritor" to cancion.escritor,
            "productor" to cancion.productor
        )

        db.collection("canciones").add(cancionData)
            .addOnSuccessListener { documentReference ->
                // Asignar el ID asignado por Firestore a la canción
                cancion.id = documentReference.id
                // Puedes hacer cualquier otra lógica aquí después de crear la canción en Firestore
            }
    }

    fun updateCancion(id: String, cancion: Cancion) {
        val cancionData = hashMapOf(
            "albumId" to cancion.albumId,
            "nombre" to cancion.nombre,
            "duracion" to cancion.duracion,
            "artistaColaborador" to cancion.artistaColaborador,
            "letra" to cancion.letra,
            "escritor" to cancion.escritor,
            "productor" to cancion.productor
        )

        db.collection("canciones").document(id).set(cancionData)
    }

    fun removeCancion(id: String) {
        db.collection("canciones").document(id).delete()
    }

    fun obtenerCancionesPorAlbumId(albumId: String): Task<QuerySnapshot> {
        return db.collection("canciones")
            .whereEqualTo("albumId", albumId)
            .get()
    }
}