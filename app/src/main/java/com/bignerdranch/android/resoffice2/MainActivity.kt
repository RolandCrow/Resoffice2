package com.bignerdranch.android.resoffice2


import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*

class MainActivity : AppCompatActivity() {
    // иницируем элементы макета
    private lateinit var getLocationButton: Button
    private lateinit var getRegionButton: Button
    private lateinit var getOffices: Button
    private lateinit var coordinateLL: TextView
    private lateinit var yourRegionIs: TextView

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    val PERMISSION_ID = 1010

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // присоединяем все к макету
        getLocationButton = findViewById(R.id.get_location)
        getRegionButton = findViewById(R.id.get_region)
        getOffices = findViewById(R.id.get_offices)
        coordinateLL = findViewById(R.id.coordinateLL)
        yourRegionIs = findViewById(R.id.yourRegionIs)

        // кнопка для получения местоположения
        getLocationButton.setOnClickListener {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
            Log.d("Debug:", checkPermission().toString())
            Log.d("Debug:", isLocationEnabled().toString())
            requestPermission()
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location: Location? ->
                coordinateLL.text = location?.latitude.toString().plus("/").plus(location?.longitude.toString())
            }
            getLastLocation() // обвновляем местоположение
            Toast.makeText(this, "Широта и долгота: $coordinateLL", Toast.LENGTH_SHORT).show() // для проверки.show()
        }

        // иницируем кнопку для получения номера региона
        getRegionButton.setOnClickListener {
            val location: String = coordinateLL.text.toString() // полученные координаты передаем в другую активность для формирования запроса
            val intentToRegion = Intent(this@MainActivity, RegionActivity::class.java)
            intentToRegion.putExtra("coordinate1", location)
            startActivity(intentToRegion)
            Toast.makeText(this, "$location ", Toast.LENGTH_SHORT).show()
        }

        yourRegionIs.text = intent.getStringExtra("saveData") // ставим номер региона в EditText
        Toast.makeText(this, "$yourRegionIs ", Toast.LENGTH_SHORT).show()

        // иницируем кнопку для перехода к списку офисов
        getOffices.setOnClickListener {

            val numberOfReg = yourRegionIs.text.toString() // получаем номер региона и отправляем в другую активность для формирования запроса
            val intentToOffice = Intent(this@MainActivity, OfficeActivity::class.java)
            intentToOffice.putExtra("numbReg", numberOfReg)
            startActivity(intentToOffice)
        }
    }

    private fun checkPermission(): Boolean { // проверяем разрешения на получения местоположения
        if (
            ActivityCompat.checkSelfPermission
                (this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission
                (this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermission() { // запрашиваем разрешения
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSION_ID
        )
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    fun getLastLocation(){ // получение информации о последнем местоположении
        if(checkPermission()){
            if(isLocationEnabled()){
                fusedLocationProviderClient.lastLocation.addOnCompleteListener {task->
                    val location:Location? = task.result // если есть результат то сохранить, если нет то сделать новый запрос
                    if(location == null){
                        newLocationData()
                    }else{
                        Log.d("Debug:" ,"Your Location:"+ location.longitude)

                    }
                }
            }else{
                Toast.makeText(this,"Пожалуйста включите местоположение устройства",Toast.LENGTH_SHORT).show()
            }
        }else{
            requestPermission()
        }
    }

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    private fun newLocationData(){
        val locationRequest =  LocationRequest()
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,locationCallback,Looper.myLooper()
        )
    }

    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(locationResult: LocationResult) {
            var lastLocation: Location = locationResult.lastLocation
            Log.d("Debug:","Последнее местоположение: "+ lastLocation.longitude.toString())
        }
    }
}