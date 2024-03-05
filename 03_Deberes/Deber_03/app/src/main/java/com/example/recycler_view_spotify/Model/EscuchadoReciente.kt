package com.example.recycler_view_spotify.Model

import androidx.compose.ui.graphics.Color

data class EscuchadoReciente(

    var nombreEscuchado: String,
    var imgEscuchado: Int,
    var colorFondo: Color
){
    // Constructor vacio
    constructor(): this("",0,Color.Black)
}