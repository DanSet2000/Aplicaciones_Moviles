package com.example.examen_primer_bimestre.operacionesCRUD

import com.example.examen_primer_bimestre.base_datos_y_adapter.BaseDeDatos
import com.example.examen_primer_bimestre.modelo.Album
import com.example.examen_primer_bimestre.modelo.Cancion


class AlbumCRUD {

    // Operaciones CRUD para trabajar con Album

    // Operacion CREATE para crear un nuevo album
    fun crearAlbum(album: Album) {
        val listaAlbumes = getAllAlbums()
        album.id = listaAlbumes.lastOrNull()?.id?.plus(1) ?: 0
        listaAlbumes.add(album)
    }

    // Operacion READ para visualizar todos los albumes almacenados
    fun getAllAlbums(): ArrayList<Album> {
        return BaseDeDatos.bibliotecaAlbumes
    }

    // Operacion READ para visualizar un Album por su ID
    fun getById(id: Int): Album? {
        return getAllAlbums().find { album -> album.id == id }
    }

    // Operacion UPDATE para actualizar un Album
    fun updateAlbum(albumActualizado: Album) {
        val listaAlbumes = getAllAlbums()
        listaAlbumes.forEachIndexed { index, album ->
            if (album.id == albumActualizado.id) {
                listaAlbumes[index] = albumActualizado
                return
            }
        }
    }

    // Función auxiliar para obtener todas las canciones
    private fun getAllCanciones(): MutableList<Cancion> {
        return BaseDeDatos.bibliotecaCanciones
    }

    // Operacion DELETE para eliminar un Album por su ID
    fun eliminarAlbumById(id: Int) {
        val listaAlbumes = getAllAlbums()
        val album = getById(id)

        album?.let {
            it.canciones?.let { cancionesDelAlbum ->
                getAllCanciones().removeAll(cancionesDelAlbum)
            }
            listaAlbumes.remove(it)
        }
    }

}