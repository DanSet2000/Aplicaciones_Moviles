package com.example.proyecto_segundo_bimestre.Model

import java.io.Serializable


data class Videojuego(
    var nombreJuego: String,
    var fechaLanzamiento: String,
    var plataforma: String,
    var genero: String,
    var desarrolladoraJuego: String,
    var distribuidorJuego:String,
    var rating: Double,
    var portadaJuego: Int,
): Serializable{

}