package com.example.examen_primer_bimestre.modelo

data class Album(
    var id: Int,
    var nombre: String,
    var artista: String,
    var anioLanzamiento: Int,
    var esExplicito: Boolean,
    var precio: Double,
    var genero: String,
    var canciones: MutableList<Cancion> = mutableListOf()
){
    // Constructor vacío
    constructor() : this(
        id = generarNuevoId(),
        nombre = "",
        artista = "",
        anioLanzamiento = 0,
        esExplicito = false,
        precio = 0.0,
        genero = ""
    )

    // Nuevo constructor para crear un álbum sin proporcionar un id
    constructor(
        nombre: String,
        artista: String,
        genero: String,
        precio: Double,
        anioLanzamiento: Int,
        esExplicito: Boolean
    ) : this(
        id = generarNuevoId(), // Llamar a la función para generar un nuevo id
        nombre = nombre,
        artista = artista,
        anioLanzamiento = anioLanzamiento,
        esExplicito = esExplicito,
        precio = precio,
        genero = genero
    )

    companion object {
        private var contadorIds = 3 // Inicializar en 3

        // Función para generar un nuevo id incremental
        private fun generarNuevoId(): Int {
            return contadorIds++
        }
    }
}