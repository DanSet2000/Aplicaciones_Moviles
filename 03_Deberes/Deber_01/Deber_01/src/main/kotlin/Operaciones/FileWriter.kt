package Operaciones

import Entidades.Album
import Entidades.Cancion
import java.io.File
import java.io.FileWriter
import java.io.FileReader
import java.io.BufferedReader

class FileWriter(private val archivo: String) {


    fun guardarDatosEnArchivo(albums: List<Album>) {
        try {
            val archivo = File(archivo)
            FileWriter(archivo).use { fw ->
                fw.write("Aplicaciones Móviles - Deber 01\n")
                fw.write("Programa Elaborado Por: Daniel Carvajal\n\n")
                fw.write("------ Álbumes Almacenados ------\n") // Título del Archivo
                albums.forEach { album ->
                    with(album) {

                        // Convertir Booleanos a "Si" o "No"
                        val esExplicitoString = if (esExplicito) "Si" else "No"


                        fw.write("Álbum Musical\n")
                        fw.write("ID: $id\n")
                        fw.write("Nombre: $nombre\n")
                        fw.write("Artista: $artista\n")
                        fw.write("Año de Lanzamiento: $anioLanzamiento\n")
                        fw.write("Es Explícito: $esExplicitoString\n")
                        fw.write("Precio: $precio\n")
                        fw.write("Género: $genero\n")

                        // Si hay canciones se escriben en el archivo
                        if (canciones.isNotEmpty()) {
                            fw.write("------ Canciones del Álbum ------\n") // Título del Archivo
                            canciones.forEach { cancion ->
                                with(cancion) {

                                    // Convertir Booleanos a "Si" o "No"
                                    val letraString = if (letra) "Si" else "No"

                                    fw.write("  - ID: $id\n")
                                    fw.write("    Nombre: $nombre\n")
                                    fw.write("    Duración: $duracion\n")
                                    fw.write("    Artista Colaboración: $artistaColaborador\n")
                                    fw.write("    Tiene Letra: $letraString\n")
                                    fw.write("    Escritor: $escritor\n")
                                    fw.write("    Productor: $productor\n" )
                                }
                            }
                        }
                        fw.write("\n")
                    }
                }
            }
            println("Datos Guardados en el Archivo.")
        } catch (ex: Exception) {
            println("Error al Guardar los Datos en el Archivo")
        }
    }

}

