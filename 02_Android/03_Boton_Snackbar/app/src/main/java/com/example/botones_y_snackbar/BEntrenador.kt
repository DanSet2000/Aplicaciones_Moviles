package com.example.botones_y_snackbar

class BEntrenador (
    var id: Int,
    var nombre: String?,
    var descripcion: String?
){
    override fun toString(): String {
        return "${nombre} - ${descripcion}"
    }
}