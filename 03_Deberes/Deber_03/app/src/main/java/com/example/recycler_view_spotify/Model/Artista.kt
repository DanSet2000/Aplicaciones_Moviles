package com.example.recycler_view_spotify.Model

data class Artista (

    var nombreArtista: String,
    var imagenArtista: Int,
){
    // Constructor vacio
    constructor(): this("",0)
}
