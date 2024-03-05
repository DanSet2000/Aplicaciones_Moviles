package com.example.examen_primer_bimestre.operacionesCRUD

import com.example.examen_primer_bimestre.base_datos_y_adapter.BaseDeDatos
import com.example.examen_primer_bimestre.modelo.Album
import com.example.examen_primer_bimestre.modelo.Cancion

class CancionCRUD {

    // Operaciones CRUD para trabajar con Cancion

    // Operacion CREATE para crear una nueva Cancion
    fun create(cancion: Cancion) {
        val listaCanciones = getAllCanciones()
        cancion.id = obtenerNuevoId()
        listaCanciones.add(cancion)

        // También se puede considerar actualizar la lista de canciones asociadas al álbum
        val listaAlbumes = getAllAlbumes()
        val album = listaAlbumes.find { it.id == cancion.albumId }
        album?.canciones?.add(cancion)
    }

    // Función auxiliar para obtener un nuevo ID
    private fun obtenerNuevoId(): Int {
        return getAllCanciones().maxByOrNull { it.id }?.id?.plus(1) ?: 1
    }

    // Operación READ para obtener todas las canciones asociadas a un álbum por su ID
    fun getCancionesByAlbumId(albumId: Int): List<Cancion> {
        return BaseDeDatos.bibliotecaCanciones.filter { it.albumId == albumId }
    }

    // Operacion READ para visualizar todas las canciones almacenadas
    fun getAllCanciones(): ArrayList<Cancion> {
        return BaseDeDatos.bibliotecaCanciones
    }

    // Operacion READ para visualizar una Canción por su ID
    fun getById(id: Int): Cancion? {
        var cancionEncontrada: Cancion? = null
        getAllCanciones().forEach { cancion: Cancion ->
            if (cancion.id == id) cancionEncontrada = cancion
        }
        return cancionEncontrada
    }

    // Operacion UPDATE para actualizar una Canción
    fun updateCancion(cancionActualizada: Cancion) {
        val listaCanciones = getAllCanciones()
        listaCanciones.forEachIndexed { index, cancion ->
            if (cancion.id == cancionActualizada.id) {
                listaCanciones[index] = cancionActualizada
                return
            }
        }
    }

    // Función auxiliar para obtener todos los álbumes
    private fun getAllAlbumes(): MutableList<Album> {
        return BaseDeDatos.bibliotecaAlbumes
    }

    // Operacion DELETE para eliminar una Cancion por su ID
    fun eliminarCancionById(id: Int) {
        val listaCanciones = getAllCanciones()
        val cancion = getById(id)

        if (cancion != null) {
            listaCanciones.remove(cancion)

            val listaDeAlbumes = getAllAlbumes()
            listaDeAlbumes.forEach { album ->
                album.canciones.remove(cancion)
            }
        }
    }

}