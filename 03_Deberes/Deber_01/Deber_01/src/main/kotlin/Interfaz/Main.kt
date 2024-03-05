package Interfaz


import Entidades.Album
import Entidades.Cancion
import Operaciones.CrudOperations
import Operaciones.FileWriter
import java.lang.NumberFormatException

// Deber 01 - Aplicaciones Moviles

// Funcion Principal para Ejecutar las operaciones CRUD
fun main(){

    // Instancia de las Clases de Operaciones
    val fileWriter = FileWriter("Albumes_Almacenados.txt")
    val crudOperations = CrudOperations(fileWriter)

    var opcion: Int

    do{
        println("\nMenu:")
        println("1. Crear Álbum")
        println("2. Mostrar Álbums")
        println("3. Actualizar Álbum")
        println("4. Eliminar Álbum")
        println("5. Agregar Canción a Álbum")
        println("6. Salir")
        print("Seleccione una opción: ")

        try{
            opcion = readLine()?.toInt() ?:0

            // Ciclo Switch en Kotlin
            when (opcion){
                1 -> {
                    println("\n------- Crear Álbum -------")
                    print("Nombre del álbum: ")
                    val nombre = readLine() ?: ""
                    print("Artista del álbum: ")
                    val artista = readLine() ?: ""
                    print("Año de lanzamiento: ")
                    val anioLanzamiento = readLine()?.toIntOrNull() ?: 0
                    print("Es explícito (Si/No): ")
                    val esExplicitoInput = readLine()?.toLowerCase()
                    val esExplicito = when (esExplicitoInput){
                        "si", "SI", "Si" -> true
                        "no" -> false
                        else -> false
                    }
                    print("Precio: ")
                    val precio = readLine()?.toDoubleOrNull() ?: 0.0
                    print("Género: ")
                    val genero = readLine() ?: ""

                    crudOperations.crearAlbum(nombre, artista, anioLanzamiento, esExplicito, precio, genero)
                }
                2 -> {
                    println("\n------- Mostrar Álbums Almacenados -------")
                    println("Lista de Álbumes Almacenados")
                    crudOperations.mostrarAlbum()
                }
                3 -> {
                    println("\n------- Actualizar Álbum -------")
                    // Mostrar ID y nombre de los álbumes almacenados en memoria
                    println("Álbumes disponibles para Actualizar:")
                    crudOperations.mostrarAlbumAct()

                    print("ID del álbum a Actualizar: ")
                    val idActualizar = readLine()?.toIntOrNull() ?: 0
                    crudOperations.actualizarAlbum(idActualizar)
                }
                4 -> {
                    println("\n------- Eliminar Álbum -------")
                    println("Álbumes disponibles para Eliminar:")
                    crudOperations.mostrarAlbumAct()

                    print("ID del álbum a Eliminar: ")
                    val idEliminar = readLine()?.toIntOrNull() ?: 0
                    // Implementa la lógica para eliminar el álbum y guarda los cambios en el archivo
                    crudOperations.eliminarAlbum(idEliminar)
                }
                5 -> {
                    println("\n------- Agregar Canción a un Álbum -------")
                    println("Lista de Álbumes Almacenados")
                    crudOperations.mostrarAlbumAct()

                    print("ID del álbum al que desea agregar la canción: ")
                    val idAlbum = readLine()?.toIntOrNull() ?: 0
                    crudOperations.agregarCancionAAlbum(idAlbum)

                }
                6 -> {
                    println("\nSaliendo de la Aplicación....")
                }
                else -> {
                    println("\nOpcion No Válida. Prueba otra vez")
                }
            }
        }catch (ex: NumberFormatException){
            println("Error: Ingresa un Número Valido")
            opcion = 0
        }

    }while(opcion != 6)

}
