package com.example.examen_segundo_bimestre.Controller

import com.example.examen_segundo_bimestre.Model.Album
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class AlbumFirestore {

    private val db = FirebaseFirestore.getInstance()

    //Función estática en la clase `Album` que crea y devuelve una instancia de `Album` a partir de un `DocumentSnapshot`.
    //Se utiliza para convertir datos recuperados de Firestore en objetos `Album` en la aplicación Kotlin
    companion object {
        fun crearAlbumFromDocument(document: DocumentSnapshot): Album {
            val id = document.id
            val nombre = document.data?.get("nombre") as String?
            val artista = document.data?.get("artista") as String?
            val anioLanzamiento = document.data?.get("anioLanzamiento") as Long?
            val esExplicito = document.data?.get("esExplicito") as Boolean?
            val precio = document.data?.get("precio") as Double?
            val genero = document.data?.get("genero") as String?

            if (id == null || nombre == null || artista == null || anioLanzamiento == null || esExplicito == null || precio == null || genero == null) {
                return Album()
            }

            return Album(id, nombre, artista, anioLanzamiento.toInt(), esExplicito, precio, genero)
        }
    }

    fun obtenerTodosAlbumes(): Task<QuerySnapshot> {
        return db.collection("albums").get()
    }

    fun obtenerUnAlbum(id: String): Task<DocumentSnapshot> {
        return db.collection("albums").document(id).get()
    }

    // Funcion para crear un nuevo Album dentro de Firestore
    fun crearAlbum(album: Album) {
        val albumData = hashMapOf(
            "nombre" to album.nombre,
            "artista" to album.artista,
            "anioLanzamiento" to album.anioLanzamiento,
            "esExplicito" to album.esExplicito,
            "precio" to album.precio,
            "genero" to album.genero
        )

        db.collection("albums").add(albumData)
            .addOnSuccessListener { documentReference ->
                // Asignar el ID asignado por Firestore al álbum
                album.id = documentReference.id
            }
    }

    // Funcion para actualizar el Album de Firestore con nuevos datos
    fun updateAlbum(id: String, album: Album) {
        val albumData = hashMapOf(
            "nombre" to album.nombre,
            "artista" to album.artista,
            "anioLanzamiento" to album.anioLanzamiento,
            "esExplicito" to album.esExplicito,
            "precio" to album.precio,
            "genero" to album.genero
        )

        db.collection("albums").document(id).set(albumData)
    }

    // Funcion para eliminar el Album de Firestore
    fun removeAlbum(id: String) {
        db.collection("albums").document(id).delete()
    }

    // Función para obtener todas las canciones de un álbum desde Firestore
    fun obtenerCancionesPorAlbumId(albumId: String): Task<QuerySnapshot> {
        return db.collection("canciones").whereEqualTo("albumId", albumId).get()
    }

    // Funcion para eliminar las canciones asociadas a un album por su id
    fun eliminarCancionesAsociadasAlAlbum(albumId: String) {
        // Obtener y eliminar las canciones asociadas al álbum
        obtenerCancionesPorAlbumId(albumId)
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    val cancionId = document.id
                    // Eliminar la canción
                    CancionFirestore().removeCancion(cancionId)
                }
            }
            .addOnFailureListener { exception ->
                // Manejar errores al obtener la lista de canciones
            }
    }
}