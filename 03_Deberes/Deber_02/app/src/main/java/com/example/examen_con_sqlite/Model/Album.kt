package com.example.examen_con_sqlite.Model

data class Album(
    var id: Int = 0,
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
        id = 0,
        nombre = "",
        artista = "",
        anioLanzamiento = 0,
        esExplicito = false,
        precio = 0.0,
        genero = ""
    )


    // Constructor para crear un álbum sin proporcionar un id
    constructor(
        nombre: String,
        artista: String,
        genero: String,
        precio: Double,
        anioLanzamiento: Int,
        esExplicito: Boolean
    ) : this(
        id = 0,
        nombre = nombre,
        artista = artista,
        anioLanzamiento = anioLanzamiento,
        esExplicito = esExplicito,
        precio = precio,
        genero = genero
    )

}