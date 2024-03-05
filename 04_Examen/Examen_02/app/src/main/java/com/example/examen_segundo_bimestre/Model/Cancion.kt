package com.example.examen_segundo_bimestre.Model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName

data class Cancion(
    @DocumentId
    var id: String = "", // Cambiado a String para ser compatible con DocumentId de Firestore
    @PropertyName("albumId")
    var albumId: String = "", // Cambiado a String para ser compatible con DocumentId de Firestore
    var nombre: String,
    var duracion: Double,
    var artistaColaborador: String,
    var letra: Boolean,
    var escritor: String,
    var productor: String
) {
    // Constructor vacío
    constructor() : this(
        id = "",
        albumId = "",
        nombre = "",
        duracion = 0.0,
        artistaColaborador = "",
        letra = false,
        escritor = "",
        productor = ""
    )


    // Constructor para simplificar la creación de instancias
    constructor(
        albumId: String,
        nombre: String,
        duracion: Double,
        artistaColaborador: String,
        letra: Boolean,
        escritor: String,
        productor: String
    ) : this("", albumId, nombre, duracion, artistaColaborador, letra, escritor, productor)

}