package com.example.proyecto_segundo_bimestre.Data

import com.example.proyecto_segundo_bimestre.Model.Consola
import com.example.proyecto_segundo_bimestre.Model.Videojuego
import com.example.proyecto_segundo_bimestre.R

class BaseDeDatos {

    companion object{
        val arregloJuegosPopulares = arrayListOf<Videojuego>()
        val arregloJuegosNuevos = arrayListOf<Videojuego>()
        val arregloConsola = arrayListOf<Consola>()

        // Datos Quemados para el Recycler View de Videojuegos Populares
        init{
            arregloJuegosPopulares.add(Videojuego("Zelda Breath Of The Wild","Mar 03,2017","Nintendo Switch", "Aventura", "Nintendo", "Nintendo", 5.0, R.drawable.zelda_botw))
            arregloJuegosPopulares.add(Videojuego("Forza Horizon 5", "Nov 01,2021","Xbox Series X", "Carreras","Microsoft Studios", "Microsoft", 3.9, R.drawable.forza_horizon_5))
            arregloJuegosPopulares.add(Videojuego("Red Dead Redemption 2","Oct 26,2018", "PlayStation 4", "Acción","Rockstar Games", "2K Interactive", 4.5, R.drawable.red_dead_redemption_2))
            arregloJuegosPopulares.add(Videojuego("Bloodborne", "Mar 24,2015", "PlayStation 4","Aventura","From Software", "Bandai Namco", 4.8,R.drawable.bloodborne))
            arregloJuegosPopulares.add(Videojuego("EA Sports FC 24", "Sep 29,2023","PlayStation 5","Deportes", "Electronic Arts", "Electronic Arts", 3.5, R.drawable.fifa_24))
            arregloJuegosPopulares.add(Videojuego("The Last Of Us", "Jun 14,2013",  "PlayStation 3", "Aventura","Naughty Dog", "Sony Interactive", 4.9, R.drawable.tlou))
            arregloJuegosPopulares.add(Videojuego("Dark Souls II", "Mar 11, 2014", "Xbox 360", "RPG", "From Software", "Bandai Namco", 4.0, R.drawable.dark_souls_2))
            arregloJuegosPopulares.add(Videojuego("Super Mario 3D World", "Nov 21,2013", "Nintendo Wii U", "Plataformas", "Nintendo", "Nintendo", 3.5, R.drawable.super_mario_3d_world))
        }

        // Datos Quemados para el Recycler View de Videojuegos Recientes
        init{
            arregloJuegosNuevos.add(Videojuego("Final Fantasy 7 Remake", "Feb 29,2024", "PlayStation 5", "RPG", "Square Enix", "Square Enix", 4.2, R.drawable.ff7_remake))
            arregloJuegosNuevos.add(Videojuego("Persona 3 Reload", "Feb 02, 2024", "Xbox Series X", "RPG","Atlus", "Sega", 4.7, R.drawable.persona_3_reload))
            arregloJuegosNuevos.add(Videojuego("Baldur's Gate 3", "Ago 03,2023", "Steam", "RPG", "Larian Studios", "Valve", 4.0, R.drawable.baldurs_gate))
            arregloJuegosNuevos.add(Videojuego("Mario vs Donkey Kong", "Feb 16,2024", "Nintendo Switch", "Plataformas","Nintendo", "Nintendo", 3.2,R.drawable.mario_vs_donkey))
            arregloJuegosNuevos.add(Videojuego("The Last Of Us Part II", "Jun 19,2020", "PlayStation 4", "Aventura","Naughty Dog", "Sony Interactive", 4.7,R.drawable.tlou_2))
            arregloJuegosNuevos.add(Videojuego("Super Mario RPG", "Nov 17,2023", "Nintendo Switch", "RPG", "Square Enix", "Nintendo", 4.2,R.drawable.super_mario))
            arregloJuegosNuevos.add(Videojuego("Tekken 8", "Ene 26,2024", "PlayStation 5", "Peleas", "Bandai Namco", "Bandai Namco", 5.0, R.drawable.tekken_8))
            arregloJuegosNuevos.add(Videojuego("Signalis", "Oct 27,2022", "Steam", "Terror", "Playism", "Humble Games", 4.5, R.drawable.signalis))

        }

        // Datos Quemados para el Recycler View de Filtrar por Consola
        init{
            arregloConsola.add(Consola("PlayStation"))
            arregloConsola.add(Consola("Xbox"))
            arregloConsola.add(Consola("Nintendo"))
            arregloConsola.add(Consola("Steam"))
        }
    }
}