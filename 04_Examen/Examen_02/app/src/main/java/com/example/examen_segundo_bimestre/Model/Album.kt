package com.example.examen_segundo_bimestre.Model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName

data class Album(
    @DocumentId                         // Marca el campo como el ID del documento en Firestore
    @get:PropertyName("id")             // Indica que el getter de la propiedad "id" usa el nombre "id" en Firestore
    @set:PropertyName("id")             // Indica que el setter de la propiedad "id" usa el nombre "id" en Firestore
    var id: String,
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
        id = "",
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
        id ="",
        nombre = nombre,
        artista = artista,
        anioLanzamiento = anioLanzamiento,
        esExplicito = esExplicito,
        precio = precio,
        genero = genero
    )

}