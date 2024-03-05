package com.example.examen_primer_bimestre.modelo

data class Cancion(
    var id: Int,
    var albumId: Int,
    var nombre: String,
    var duracion: Double,
    var artistaColaborador: String,
    var letra: Boolean,
    var escritor: String,
    var productor: String
) {

    // Constructor para simplificar la creación de instancias
    constructor(
        albumId: Int,
        nombre: String,
        duracion: Double,
        artistaColaborador: String,
        letra: Boolean,
        escritor: String,
        productor: String
    ) : this(0, albumId, nombre, duracion, artistaColaborador, letra, escritor, productor)

}
