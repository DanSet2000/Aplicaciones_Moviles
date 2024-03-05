package com.example.google_maps

import android.content.Context
import android.content.pm.PackageManager
import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.material.snackbar.Snackbar

class GGoogleMapsActivity : AppCompatActivity() {

    private lateinit var mapa: GoogleMap
    var permisos = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ggoogle_maps)
        solicitarPermisos()
        iniciarLogicaMapa()
        // Boton
        val boton = findViewById<Button>(R.id.btn_ir_carolina)
        boton.setOnClickListener{
            irCarolina()
        }
    }

    // Funciones

    fun solicitarPermisos(){
        val contexto = this.applicationContext
        val nombrePermiso = android.Manifest.permission.ACCESS_FINE_LOCATION
        val nombrePermisoCoarse = android.Manifest.permission.ACCESS_COARSE_LOCATION
        val permisosFineLocation = ContextCompat.checkSelfPermission(contexto, nombrePermiso)
        val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED

        if (tienePermisos){
            permisos = true
        }else{
            ActivityCompat.requestPermissions(
                this,                                       // Contexto
                arrayOf(nombrePermiso, nombrePermisoCoarse),       // Arreglo Permisos),
            1)        // Código de peticion de los permisos
        }
    }

    fun iniciarLogicaMapa() {
        val fragmentoMapa = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        fragmentoMapa.getMapAsync { googleMap ->
            // with(X) => if(X != null)
            with(googleMap) {
                mapa = googleMap
                establecerConfiguracionMapa()
                moverQuicentro()
                anadirPoligono()
                anadirPolilinea()
                escucharListeners()
            }
        }
    }

    fun irCarolina(){
        val carolina = LatLng(-0.1825684318486696,
            -78.48447277600916)
        val zoom = 17f
        moverCamaraConZoom(carolina, zoom)
    }

    fun anadirMarcador(latLng: LatLng, title: String):Marker{
        return mapa.addMarker(MarkerOptions().position(latLng).title(title))!!
    }

    fun moverCamaraConZoom(latLng: LatLng, zoom: Float = 10f){
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom))
    }
    fun moverQuicentro(){
        val zoom = 17f
        val quicentro = LatLng(-0.17556708490271092, -78.48014901143776)
        val titulo = "Quicentro"
        val markQuicentro = anadirMarcador(quicentro, titulo)
        markQuicentro.tag = titulo
        moverCamaraConZoom(quicentro, zoom)
    }

    fun anadirPolilinea(){
        with(mapa){
            val poliLineaUno = mapa.addPolyline(PolylineOptions().clickable(true).add(
                LatLng(-0.1759187040647396,
                    -78.48506472421384),
                LatLng(-0.17632468492901104,
                    -78.48265589308046),
                LatLng(-0.17746143130181483,
                    -78.4770533307815)
            ))
            poliLineaUno.tag = "linea-1"  // ID
        }
    }

    fun anadirPoligono(){
        with(mapa){
            val poligonoUno = mapa.addPolygon(PolygonOptions().clickable(true).add(
                LatLng(-0.1771546902239471,
                    -78.48344981495214),
                LatLng(-0.17968981486125768,
                    -78.48269198043828),
                LatLng(-0.17710958124147777,
                    -78.48142892291516)
            ))
            poligonoUno.fillColor = -0xc771c4
            poligonoUno.tag= "poligono-2"   // ID
        }
    }

    fun escucharListeners(){
        mapa.setOnPolygonClickListener {
            mostrarSnackbar("setOnPolygonClickListener ${it.tag}")
            it.tag // ID
        }

        mapa.setOnPolylineClickListener {
            mostrarSnackbar("setOnPolylineClickListener ${it.tag}")
            it.tag // ID
        }

        mapa.setOnMarkerClickListener {
            mostrarSnackbar("setOnMarkerClickListener ${it.tag}")
            it.tag // ID
            return@setOnMarkerClickListener true
        }

        mapa.setOnCameraMoveListener {
            Log.i("mapa", "setOnCameraMoveListener")
        }

        mapa.setOnCameraMoveStartedListener {
            Log.i("mapa", "setOnCameraMoveStartedListener ${it}")
        }

        mapa.setOnCameraIdleListener {
            Log.i("mapa", "setOnCameraIdleListener")
        }
    }


    fun establecerConfiguracionMapa(){
        val contexto = this.applicationContext
        with(mapa){
            val permisosFineLocation = ContextCompat.checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED

            if (tienePermisos){
                mapa.isMyLocationEnabled = true // Se tiene los permisos
                uiSettings.isMyLocationButtonEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true // Por Defecto
        }
    }


    // Snackbar
    fun mostrarSnackbar(texto:String){
        Snackbar.make(
            findViewById(R.id.cl_google_maps), // view
            texto, // texto
            Snackbar.LENGTH_LONG // tiempo
        ).show()
    }
}

