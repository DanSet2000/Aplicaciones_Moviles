package com.example.recycler_view_spotify.Model

data class AlbumMusical (

    var nombreAlbum: String,
    var artistaAlbum: String,
    var portadaAlbum: Int,

){
    // Constructor vacío
    constructor() : this("", "", 0)

}