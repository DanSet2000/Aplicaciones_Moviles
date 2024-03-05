package Operaciones

import Entidades.Album
import Entidades.Cancion
import Operaciones.FileWriter

// Clase que contiene las Operaciones CRUD

class CrudOperations(private val fileWriter: FileWriter) {
    private val albums = mutableListOf<Album>()

    // Funcion para crear un nuevo Album (Operacion CREATE)
    fun crearAlbum(nombre: String, artista: String, anioLanzamiento: Int, esExplicito: Boolean, precio: Double, genero: String){
        val nuevoAlbum = Album(
            generarId(),
            nombre,
            artista,
            anioLanzamiento,
            esExplicito,
            precio,
            genero
        )
        albums.add(nuevoAlbum)
        println("Album Agregado: $nuevoAlbum")
        fileWriter.guardarDatosEnArchivo(albums)
    }

    // Funcion para mostrar la lista de Albumes y sus Canciones (Operacion READ)
    fun mostrarAlbum() {
        albums.forEach { album ->
            println("****  ALBUM ****")
            println("ID: ${album.id}")
            println("Nombre: ${album.nombre}")
            println("Artista: ${album.artista}")
            println("Año de Lanzamiento: ${album.anioLanzamiento}")
            println("Es Explícito: ${if (album.esExplicito) "Si" else "No"}")
            println("Precio: ${album.precio}")
            println("Genero: ${album.genero}")
            println("**** CANCIONES ****")
            album.canciones.forEach { cancion ->
                println("   - ID: ${cancion.id}")
                println("     Nombre: ${cancion.nombre}")
                println("     Duración: ${cancion.duracion}")
                println("     Colaboración: ${cancion.artistaColaborador}")
                println("     Tiene Letra: ${if (cancion.letra) "Si" else "No"}")
                println("     Escritor: ${cancion.escritor}")
                println("     Productor: ${cancion.productor}")
            }
            println()
        }
    }

    // Funcion para mostrar los Albumes disponibles para Actualizar (Operacion READ)
    fun mostrarAlbumAct(){
        albums.forEach{album ->
            println("ID: ${album.id}, Nombre: ${album.nombre}, Artista: ${album.artista}")
        }
    }

    // Funcion para actualizar la informacion del Album (Operacion UPDATE)
    fun actualizarAlbum(id: Int){
        val album = albums.find { it.id == id }
        if (album != null) {
            print("Nuevo nombre del álbum (${album.nombre}): ")
            val nuevoNombre = readLine() ?: album.nombre
            print("Nuevo nombre del artista (${album.artista}):")
            val nuevoArtista = readLine() ?: album.artista
            print("Nuevo año de lanzamiento (${album.anioLanzamiento}): ")
            val nuevoAnioLanzamiento = readLine()?.toIntOrNull() ?: album.anioLanzamiento
            print("¿Es explícito? (${album.esExplicito}): ")
            val nuevoEsExplicito = readLine()?.toBoolean() ?: album.esExplicito
            print("Nuevo precio (${album.precio}): ")
            val nuevoPrecio = readLine()?.toDoubleOrNull() ?: album.precio
            print("Nuevo género (${album.genero}): ")
            val nuevoGenero = readLine() ?: album.genero

            // Actualizar los atributos del álbum con los nuevos valores ingresados
            album.nombre = nuevoNombre
            album.artista = nuevoArtista
            album.anioLanzamiento = nuevoAnioLanzamiento
            album.esExplicito = nuevoEsExplicito
            album.precio = nuevoPrecio
            album.genero = nuevoGenero

            println("Album Actualizado: $album")
            fileWriter.guardarDatosEnArchivo(albums)
        }else{
            println("Album con el Id $id no ha sido encontrado")
        }
    }

    // Funcion para eliminar un album por su ID (Operacion DELETE)
    fun eliminarAlbum(id: Int){
        val album = albums.find { it.id == id}
        if (album != null){
            albums.remove(album)
            println("Album Eliminado: $album")
            fileWriter.guardarDatosEnArchivo(albums)
        }else{
            println("Album con Id $id no ha sido encontrado")
        }
    }

    // Funcion para agregar más canciones al album nusical (Operacion CREATE)
    fun agregarCancionAAlbum(idAlbum: Int){
        val album = albums.find { it.id == idAlbum }

        if (album != null){
            println("\n------- Agregar Canción al Álbum -------")

            val contadorIdLocal = album.canciones.size + 1

            print("Nombre de la canción: ")
            val nombreCancion = readLine() ?: ""
            print("Duración de la canción: ")
            val duracion = readLine()?.toDoubleOrNull() ?: 0.0
            print("Artista Invitado de la canción: ")
            val artistaColaborador = readLine() ?: ""
            print("¿La Canción tiene letra? (Si/No): ")
            val tieneletraInput = readLine()?.toLowerCase()
            val tieneletra = when (tieneletraInput){
                "si", "SI", "Si" -> true
                "no" -> false
                else -> false
            }
            print("Escritor de la canción: ")
            val escritor = readLine() ?: ""
            print("Productor de la canción: ")
            val productor = readLine() ?: ""

            // Atributos que tendra la Cancion Creada
            val nuevaCancion = Cancion(
                nombre = nombreCancion,
                duracion = duracion,
                artistaColaborador = artistaColaborador,
                letra = tieneletra,
                escritor = escritor,
                productor = productor
            )
            album.canciones.add(nuevaCancion)
            println("Cancion Agregada al Album $idAlbum: $nuevaCancion")
            fileWriter.guardarDatosEnArchivo(albums)
        }else{
            println("Album con el Id $idAlbum no ha sido encontrado")
        }
    }

    // Funcion para generar automaticamente un ID único
    private fun generarId(): Int{
        return if(albums.isEmpty()) 1 else albums.maxByOrNull { it.id }!!.id + 1
    }
}