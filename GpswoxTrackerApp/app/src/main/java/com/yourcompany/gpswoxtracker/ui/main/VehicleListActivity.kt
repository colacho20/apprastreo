package com.yourcompany.gpswoxtracker.ui.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.yourcompany.gpswoxtracker.databinding.ActivityVehicleListBinding
import com.yourcompany.gpswoxtracker.ui.adapters.VehicleAdapter
import com.yourcompany.gpswoxtracker.ui.auth.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VehicleListActivity : AppCompatActivity() {

    // ViewBinding
    private lateinit var binding: ActivityVehicleListBinding

    // Adapter y componentes
    private lateinit var vehicleAdapter: VehicleAdapter
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configuración del binding
        binding = ActivityVehicleListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicialización de SharedPreferences
        sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

        // Verificación de sesión
        if (!isUserLoggedIn()) {
            redirectToLogin()
            return
        }

        setupUI()
        setupRecyclerView()
        loadSampleData() // Cambiado de loadVehicles() para evitar confusión
    }

    private fun isUserLoggedIn(): Boolean {
        val apiKey = sharedPref.getString("api_key", null)
        return if (apiKey.isNullOrEmpty()) {
            Toast.makeText(this, "Sesión no válida", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    private fun setupUI() {
        // Configuración del SwipeRefreshLayout
        binding.swipeRefreshLayout.setOnRefreshListener {
            loadSampleData()
        }

        // Configuración del botón de logout
        binding.fabLogout.setOnClickListener {
            performLogout()
        }
    }

    private fun setupRecyclerView() {
        vehicleAdapter = VehicleAdapter()
        with(binding.rvVehicles) {
            layoutManager = LinearLayoutManager(this@VehicleListActivity)
            adapter = vehicleAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this@VehicleListActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun loadSampleData() {
        showLoading(true)

        // Simulación de carga con corrutinas
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Simulación de delay para carga
                kotlinx.coroutines.delay(1500)

                val sampleVehicles = listOf(
                    createSampleVehicle(
                        "1",
                        "Vehículo Demo 1",
                        "ABC123",
                        "moving",
                        4.7110,
                        -74.0721,
                        "2023-05-15 14:30:00",
                        "Bogotá, Colombia"
                    ),
                    createSampleVehicle(
                        "2",
                        "Vehículo Demo 2",
                        "XYZ789",
                        "offline"
                    )
                )

                runOnUiThread {
                    vehicleAdapter.submitList(sampleVehicles)
                    showLoading(false)
                }
            } catch (e: Exception) {
                runOnUiThread {
                    showLoading(false)
                    showError("Error: ${e.localizedMessage}")
                }
            }
        }
    }

    private fun createSampleVehicle(
        id: String,
        name: String,
        plate: String,
        status: String,
        lat: Double? = null,
        lng: Double? = null,
        timestamp: String? = null,
        address: String? = null
    ): com.yourcompany.gpswoxtracker.models.Vehicle {
        return com.yourcompany.gpswoxtracker.models.Vehicle(
            vehicle_id = id,
            name = name,
            plate_number = plate,
            last_location = if (lat != null && lng != null) {
                com.yourcompany.gpswoxtracker.models.LocationData(
                    lat = lat,
                    lng = lng,
                    timestamp = timestamp ?: "",
                    address = address
                )
            } else null,
            status = status,
            image_url = null
        )
    }

    private fun performLogout() {
        sharedPref.edit().clear().apply()
        redirectToLogin()
    }

    private fun redirectToLogin() {
        startActivity(Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
                finish()
    }

    private fun showLoading(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
        binding.swipeRefreshLayout.isRefreshing = show
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}