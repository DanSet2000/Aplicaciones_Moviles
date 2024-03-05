package com.example.examen_con_sqlite.Controller

import android.content.ContentValues
import android.content.Context
import com.example.examen_con_sqlite.Database.BaseDeDatosHelper
import com.example.examen_con_sqlite.Model.Album
import com.example.examen_con_sqlite.Model.Cancion

class AlbumCRUD(context: Context) {

    private val dbHelper: BaseDeDatosHelper = BaseDeDatosHelper(context)

    // Operaciones CRUD para trabajar con Album en la Base de Datos

    // Funcion para Crear un Album con la Base de Datos

    fun crearAlbum(album: Album){

        // Obtener la base de datos en modo escritura
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put("nombre", album.nombre)
            put("artista", album.artista)
            put("anioLanzamiento", album.anioLanzamiento)
            put ("esExplicito", if (album.esExplicito) 1 else 0)
            put ("precio", album.precio)
            put ("genero", album.genero)
        }

        // Inserta el nuevo álbum y obtiene su ID
        val nuevoId = db.insert(BaseDeDatosHelper.TABLA_ALBUM, null, values)

        // Asigna el nuevo ID al álbum
        album.id = nuevoId.toInt()

        // Cerrar la conexion
        db.close()
    }

    // Funcion para Obtener Todos los Albumes

    fun obtenerTodos(): List<Album> {
        val listaAlbumes = mutableListOf<Album>()

        dbHelper.readableDatabase.use { db ->
            val query = "SELECT * FROM ${BaseDeDatosHelper.TABLA_ALBUM}"
            val cursor = db.rawQuery(query, null)

            cursor.use {
                // Iterar sobre el cursor y construir la lista de álbumes
                if (it.moveToFirst()) {
                    do {
                        val id = it.getInt(it.getColumnIndexOrThrow("id"))
                        val nombre = it.getString(it.getColumnIndexOrThrow("nombre"))
                        val artista = it.getString(it.getColumnIndexOrThrow("artista"))
                        val anioLanzamiento = it.getInt(it.getColumnIndexOrThrow("anioLanzamiento"))
                        val esExplicito = it.getInt(it.getColumnIndexOrThrow("esExplicito")) == 1
                        val precio = it.getDouble(it.getColumnIndexOrThrow("precio"))
                        val genero = it.getString(it.getColumnIndexOrThrow("genero"))

                        // Crear una instancia de Album y agregarla a la lista
                        val album = Album(id, nombre, artista, anioLanzamiento, esExplicito, precio, genero)
                        listaAlbumes.add(album)
                    } while (it.moveToNext())
                }
            }
        }

        return listaAlbumes
    }

    // Funcion para obtener un Album por su ID

    fun obtenerAlbumPorId(albumId: Int): Album? {
        var album: Album? = null

        dbHelper.readableDatabase.use { db ->
            val query = "SELECT * FROM ${BaseDeDatosHelper.TABLA_ALBUM} WHERE id = ?"
            val cursor = db.rawQuery(query, arrayOf(albumId.toString()))

            cursor.use {
                if (it.moveToFirst()) {
                    val id = it.getInt(it.getColumnIndexOrThrow("id"))
                    val nombre = it.getString(it.getColumnIndexOrThrow("nombre"))
                    val artista = it.getString(it.getColumnIndexOrThrow("artista"))
                    val anioLanzamiento = it.getInt(it.getColumnIndexOrThrow("anioLanzamiento"))
                    val esExplicito = it.getInt(it.getColumnIndexOrThrow("esExplicito")) == 1
                    val precio = it.getDouble(it.getColumnIndexOrThrow("precio"))
                    val genero = it.getString(it.getColumnIndexOrThrow("genero"))

                    album = Album(id, nombre, artista, anioLanzamiento, esExplicito, precio, genero)
                }
            }
        }

        return album
    }

    // Funcion para Actualizar un Album

    fun updateAlbum(albumActualizado: Album) {
        dbHelper.writableDatabase.use { db ->
            val values = ContentValues().apply {
                put("nombre", albumActualizado.nombre)
                put("artista", albumActualizado.artista)
                put("anioLanzamiento", albumActualizado.anioLanzamiento)
                put("esExplicito", if (albumActualizado.esExplicito) 1 else 0)
                put("precio", albumActualizado.precio)
                put("genero", albumActualizado.genero)
            }

            // Especificar la condición para la actualización (basada en el ID del álbum)
            val whereClause = "id = ?"
            val whereArgs = arrayOf(albumActualizado.id.toString())

            // Actualizar el álbum en la base de datos
            val filasActualizadas = db.update(BaseDeDatosHelper.TABLA_ALBUM, values, whereClause, whereArgs)

        }
    }

    // Funcion para Borrar un Album por su ID

    fun borrarAlbumPorId(albumId: Int) {
        val db = dbHelper.writableDatabase

        // Especificar la condición para la eliminación (basada en el ID del álbum)
        val whereClause = "id = ?"
        val whereArgs = arrayOf(albumId.toString())

        // Borrar el álbum de la base de datos
        db.delete(BaseDeDatosHelper.TABLA_ALBUM, whereClause, whereArgs)

        // Cerrar la conexión a la base de datos
        db.close()
    }

    // Funcion Auxiliar para obtener todas las Canciones del Album

    fun obtenerCancionesPorAlbumId(albumId: Int): List<Cancion> {
        val listaCanciones = mutableListOf<Cancion>()

        dbHelper.readableDatabase.use { db ->
            val query = "SELECT * FROM ${BaseDeDatosHelper.TABLA_CANCION} WHERE albumId = ?"
            val cursor = db.rawQuery(query, arrayOf(albumId.toString()))

            cursor.use {
                // Iterar sobre el cursor y construir la lista de canciones
                if (it.moveToFirst()) {
                    do {
                        val id = it.getInt(it.getColumnIndexOrThrow("id"))
                        val nombre = it.getString(it.getColumnIndexOrThrow("nombre"))
                        val duracion = it.getDouble(it.getColumnIndexOrThrow("duracion"))
                        val artistaColaborador = it.getString(it.getColumnIndexOrThrow("artistaColaborador"))
                        val letra = it.getInt(it.getColumnIndexOrThrow("letra")) == 1
                        val escritor = it.getString(it.getColumnIndexOrThrow("escritor"))
                        val productor = it.getString(it.getColumnIndexOrThrow("productor"))

                        // Crear una instancia de Cancion y agregarla a la lista
                        val cancion = Cancion(id, albumId, nombre, duracion, artistaColaborador, letra, escritor, productor)
                        listaCanciones.add(cancion)
                    } while (it.moveToNext())
                }
            }
        }

        return listaCanciones
    }

}