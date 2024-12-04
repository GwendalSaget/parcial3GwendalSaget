package com.example.parcial3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.json.JSONArray

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Charge la carte Google Maps
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Charger les données JSON et ajouter les marqueurs
        val jsonData = loadJsonData() // Charge les données JSON à partir d'un fichier local ou d'une ressource
        if (jsonData != null) {
            addMarkersFromJson(jsonData)
        }

        // Définir une position initiale sur la carte (si nécessaire)
        val initialLocation = LatLng(41.39, 0.52) // Exemple : Saragosse
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialLocation, 10f))
    }

    private fun loadJsonData(): JSONArray? {
        return try {
            // Charger le JSON à partir du fichier dans /assets/locations.json
            val inputStream = assets.open("locations.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val jsonString = String(buffer, Charsets.UTF_8)
            JSONArray(jsonString) // Retourne le tableau JSON
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun addMarkersFromJson(jsonArray: JSONArray) {
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val name = jsonObject.getString("name")
            val latitude = jsonObject.getDouble("latitude")
            val longitude = jsonObject.getDouble("longitude")
            val address = jsonObject.optString("address", "No address provided")

            // Ajouter un marqueur sur la carte pour chaque lieu
            val location = LatLng(latitude, longitude)
            mMap.addMarker(
                MarkerOptions()
                    .position(location)
                    .title(name)
                    .snippet(address) // Afficher l'adresse comme description
            )
        }
    }
}

data class Farmacia(
    val name: String,
    val address: String,
    val phone: String,
    val latitude: Double,
    val longitude: Double
)


