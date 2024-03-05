package Entidades

// Entidad de Canciones que contiene un Álbum Musical con sus Atributos

data class Cancion(
    val id: Int = generarId(),
    var nombre: String,
    var duracion: Double,
    var artistaColaborador: String,
    var letra: Boolean,
    var escritor: String,
    var productor: String
){
    companion object{
        private var contadorId: Int = 1

        private fun generarId(): Int{
            return contadorId++
        }
    }
}
