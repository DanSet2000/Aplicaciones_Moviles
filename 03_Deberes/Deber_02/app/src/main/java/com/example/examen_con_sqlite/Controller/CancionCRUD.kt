package com.example.examen_con_sqlite.Controller

import android.content.ContentValues
import android.content.Context
import com.example.examen_con_sqlite.Database.BaseDeDatosHelper
import com.example.examen_con_sqlite.Model.Cancion

class CancionCRUD(context: Context) {

    private val dbHelper: BaseDeDatosHelper = BaseDeDatosHelper(context)

    // Operaciones CRUD para trabajar con Cancion en la Base de Datos

    // Funcion para Crear una Cancion con la Base de Datos

    fun crearCancion(cancion: Cancion){

        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put("albumId", cancion.albumId)
            put("nombre", cancion.nombre)
            put("duracion", cancion.duracion)
            put("artistaColaborador", cancion.artistaColaborador)
            put("letra", if (cancion.letra) 1 else 0)
            put("escritor", cancion.escritor)
            put("productor", cancion.productor)
        }

        // Insertar la nueva canción y obtener su ID
        val nuevoId = db.insert(BaseDeDatosHelper.TABLA_CANCION, null, values)

        // Asignar el nuevo ID a la canción
        cancion.id = nuevoId.toInt()

        // Cerrar la conexión
        db.close()
    }

    // Funcion para obtener todas las canciones de un Album asociadas por su ID

    fun obtenerCancionesPorAlbumId(albumId: Int): List<Cancion> {
        val listaCanciones = mutableListOf<Cancion>()
        val db = dbHelper.readableDatabase

        val query = "SELECT * FROM ${BaseDeDatosHelper.TABLA_CANCION} WHERE albumId = ?"
        val selectionArgs = arrayOf(albumId.toString())

        val cursor = db.rawQuery(query, selectionArgs)

        // Verificar si el cursor tiene columnas antes de intentar acceder a ellas
        if (cursor.columnCount > 0) {
            // Iterar sobre el cursor y construir la lista de canciones
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                    val nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
                    val duracion = cursor.getDouble(cursor.getColumnIndexOrThrow("duracion"))
                    val artistaColaborador = cursor.getString(cursor.getColumnIndexOrThrow("artistaColaborador"))
                    val letra = cursor.getInt(cursor.getColumnIndexOrThrow("letra")) == 1
                    val escritor = cursor.getString(cursor.getColumnIndexOrThrow("escritor"))
                    val productor = cursor.getString(cursor.getColumnIndexOrThrow("productor"))

                    // Crear una instancia de Cancion y agregarla a la lista
                    val cancion = Cancion(id, albumId, nombre, duracion, artistaColaborador, letra, escritor, productor)
                    listaCanciones.add(cancion)
                } while (cursor.moveToNext())
            }
        }

        // Cerrar el cursor y la base de datos
        cursor.close()
        db.close()

        return listaCanciones
    }


    // Funcion para obtener todas las canciones almacenadas

    fun obtenerTodasLasCanciones(): List<Cancion> {
        val listaCanciones = mutableListOf<Cancion>()

        dbHelper.readableDatabase.use { db ->
            val query = "SELECT * FROM ${BaseDeDatosHelper.TABLA_CANCION}"
            val cursor = db.rawQuery(query, null)

            cursor.use {
                // Iterar sobre el cursor y construir la lista de canciones
                if (it.moveToFirst()) {
                    do {
                        val id = it.getInt(it.getColumnIndexOrThrow("id"))
                        val albumId = it.getInt(it.getColumnIndexOrThrow("albumId"))
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

    // Funcion para Obtener una Cancion por su ID

    fun obtenerCancionPorId(cancionId: Int): Cancion? {
        var cancion: Cancion? = null

        dbHelper.readableDatabase.use { db ->
            val query = "SELECT * FROM ${BaseDeDatosHelper.TABLA_CANCION} WHERE id = ?"
            val selectionArgs = arrayOf(cancionId.toString())
            val cursor = db.rawQuery(query, selectionArgs)

            cursor.use {
                if (it.moveToFirst()) {
                    val id = it.getInt(it.getColumnIndexOrThrow("id"))
                    val albumId = it.getInt(it.getColumnIndexOrThrow("albumId"))
                    val nombre = it.getString(it.getColumnIndexOrThrow("nombre"))
                    val duracion = it.getDouble(it.getColumnIndexOrThrow("duracion"))
                    val artistaColaborador = it.getString(it.getColumnIndexOrThrow("artistaColaborador"))
                    val letra = it.getInt(it.getColumnIndexOrThrow("letra")) == 1
                    val escritor = it.getString(it.getColumnIndexOrThrow("escritor"))
                    val productor = it.getString(it.getColumnIndexOrThrow("productor"))

                    // Crear una instancia de Cancion
                    cancion = Cancion(id, albumId, nombre, duracion, artistaColaborador, letra, escritor, productor)
                }
            }
        }

        return cancion
    }

    // Funcion para Actualizar una Cancion

    fun actualizarCancion(cancionActualizada: Cancion) {
        dbHelper.writableDatabase.use { db ->
            val values = ContentValues().apply {
                put("albumId", cancionActualizada.albumId)
                put("nombre", cancionActualizada.nombre)
                put("duracion", cancionActualizada.duracion)
                put("artistaColaborador", cancionActualizada.artistaColaborador)
                put("letra", if (cancionActualizada.letra) 1 else 0)
                put("escritor", cancionActualizada.escritor)
                put("productor", cancionActualizada.productor)
            }

            // Especificar la condición para la actualización (basada en el ID de la canción)
            val whereClause = "id = ?"
            val whereArgs = arrayOf(cancionActualizada.id.toString())

            // Actualizar la canción en la base de datos
            db.update(BaseDeDatosHelper.TABLA_CANCION, values, whereClause, whereArgs)
        }
    }

    // Funcion para Eliminar una cancion por su ID

    fun eliminarCancionPorId(idCancion: Int) {
        dbHelper.writableDatabase.use { db ->
            // Especificar la condición para la eliminación (basada en el ID de la canción)
            val whereClause = "id = ?"
            val whereArgs = arrayOf(idCancion.toString())

            // Eliminar la canción de la base de datos
            db.delete(BaseDeDatosHelper.TABLA_CANCION, whereClause, whereArgs)
        }
    }

}