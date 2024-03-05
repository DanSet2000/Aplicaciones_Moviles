package com.example.recycler_view_spotify.Data

import androidx.compose.ui.graphics.Color
import com.example.recycler_view_spotify.Model.AlbumMusical
import com.example.recycler_view_spotify.Model.Artista
import com.example.recycler_view_spotify.Model.EscuchadoReciente
import com.example.recycler_view_spotify.R

class BaseDeDatos {

    companion object{
        val arregloAlbumes = arrayListOf<AlbumMusical>()
        val arregloArtistas = arrayListOf<Artista>()
        val arregloSugerencias = arrayListOf<AlbumMusical>()
        val arregloReciente1 = arrayListOf<EscuchadoReciente>()
        val arregloReciente2 = arrayListOf<EscuchadoReciente>()

        // Datos Quemados para el Recycler View de Album Musical
        init {
            arregloAlbumes.add(AlbumMusical("American Idiot", "Green Day", R.drawable.portada_american_idiot))
            arregloAlbumes.add(AlbumMusical("Appetite For Destruction","Guns N' Roses",R.drawable.portada_apettite_for_destruction))
            arregloAlbumes.add(AlbumMusical("Blood Sugar Sex Magik", "Red Hot Chili Peppers",R.drawable.portada_bssm))
            arregloAlbumes.add(AlbumMusical("Blur: The Best Of","Blur",R.drawable.portada_blur_bestof))
            arregloAlbumes.add(AlbumMusical("Californication","Red Hot Chili Peppers",R.drawable.portada_californication))
            arregloAlbumes.add(AlbumMusical("Cracker Island", "Gorillaz", R.drawable.portada_cracker_island))
            arregloAlbumes.add(AlbumMusical("Death Magnetic", "Metallica", R.drawable.portada_death_magnetic))
            arregloAlbumes.add(AlbumMusical("Future Nostalgia", "Dua Lipa", R.drawable.portada_future_nostalgia))
            arregloAlbumes.add(AlbumMusical("In Utero", "Nirvana", R.drawable.portada_in_utero))
            arregloAlbumes.add(AlbumMusical("Make Yourself", "Incubus", R.drawable.portada_make_yourself))
            arregloAlbumes.add(AlbumMusical("The New Abnormal", "The Strokes",R.drawable.portada_the_new_abnormal))
            arregloAlbumes.add(AlbumMusical("Ride The Lightning", "Metallica",R.drawable.portada_ride))
            arregloAlbumes.add(AlbumMusical("Starboy", "The Weeknd", R.drawable.portada_starboy))
        }

        // Datos Quemados para el Recycler View de Artista Reciente
        init{
            arregloArtistas.add(Artista("Justin Timberlake", R.drawable.artista_justin_timberlake))
            arregloArtistas.add(Artista("The Weeknd", R.drawable.artista_the_weeknd))
            arregloArtistas.add(Artista("Metallica", R.drawable.artista_metallica))
            arregloArtistas.add(Artista("Alice in Chains", R.drawable.artista_aic))
            arregloArtistas.add(Artista("Rihanna", R.drawable.artista_rihanna))
            arregloArtistas.add(Artista("Post Malone", R.drawable.artista_post_malone))
            arregloArtistas.add(Artista("Gorillaz", R.drawable.artista_gorillaz))
        }

        // Datos Quemados para el Recycler View de Sugerencia del Día
        init{
            arregloSugerencias.add(AlbumMusical("Toxicity","System Of A Down",R.drawable.sugerencia_toxicity))
            arregloSugerencias.add(AlbumMusical("Paranoid","Black Sabbath",R.drawable.sugerencia_paranoid))
            arregloSugerencias.add(AlbumMusical("90's Metal Classics","Lo mejor del Heavy Metal",R.drawable.sugerencia_90_classics))
            arregloSugerencias.add(AlbumMusical("Demon Days","Gorillaz",R.drawable.sugerencia_demon_days))
            arregloSugerencias.add(AlbumMusical("13","Blur",R.drawable.sugerencia_13))
            arregloSugerencias.add(AlbumMusical("Sólo Pop", "Éxitos del Pop",R.drawable.sugerencia_solo_pop))
            arregloSugerencias.add(AlbumMusical("In English: Replay", "Éxitos internacionales",R.drawable.sugerencia_in_english))
        }

        // Datos Quemados para el Recycler View de Recientemente Escuchados
        init{
            arregloReciente1.add(EscuchadoReciente("Tus me gusta", R.drawable.reciente_liked_songs, Color.hsv(282.0f,0.73f,0.79f)))
            arregloReciente1.add(EscuchadoReciente("En repetición", R.drawable.reciente_on_repeat, Color.hsv(282.0f,0.73f,0.79f)))
            arregloReciente1.add(EscuchadoReciente("Top Canciones 2020", R.drawable.reciente_top_2020, Color.hsv(175.0f,0.68f,0.56f)))
        }

        // Datos Quemados para el Recycler View de Recientemente Escuchados
        init{
            arregloReciente2.add(EscuchadoReciente("Entiende tu mente", R.drawable.reciente_entiende_mente, Color.DarkGray))
            arregloReciente2.add(EscuchadoReciente("Música de fútbol", R.drawable.reciente_futbol, Color.hsv(232.6f,0.83f,0.56f)))
            arregloReciente2.add(EscuchadoReciente("Kanye West", R.drawable.reciente_kanye_west, Color.hsv(0.0f,0.0f,0.1f)))
        }
    }
}