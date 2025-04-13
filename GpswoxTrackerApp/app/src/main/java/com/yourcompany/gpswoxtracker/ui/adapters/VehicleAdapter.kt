package com.yourcompany.gpswoxtracker.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yourcompany.gpswoxtracker.R
import com.yourcompany.gpswoxtracker.models.Vehicle
import java.text.SimpleDateFormat
import java.util.Locale

class VehicleAdapter : RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>() {

    private var vehicles = listOf<Vehicle>()

    inner class VehicleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivVehicle: ImageView = itemView.findViewById(R.id.ivVehicle)
        private val tvVehicleName: TextView = itemView.findViewById(R.id.tvVehicleName)
        private val tvPlateNumber: TextView = itemView.findViewById(R.id.tvPlateNumber)
        private val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
        private val tvLastLocation: TextView = itemView.findViewById(R.id.tvLastLocation)

        fun bind(vehicle: Vehicle) {
            tvVehicleName.text = vehicle.name
            tvPlateNumber.text = vehicle.plate_number

            when (vehicle.status.lowercase()) {
                "moving" -> {
                    tvStatus.text = "En movimiento"
                    tvStatus.setTextColor(Color.parseColor("#4CAF50")) // Verde
                }
                "stopped" -> {
                    tvStatus.text = "Detenido"
                    tvStatus.setTextColor(Color.parseColor("#FF9800")) // Naranja
                }
                "offline" -> {
                    tvStatus.text = "Sin conexión"
                    tvStatus.setTextColor(Color.parseColor("#F44336")) // Rojo
                }
                else -> {
                    tvStatus.text = vehicle.status
                    tvStatus.setTextColor(Color.parseColor("#000000")) // Negro
                }
            }

            vehicle.last_location?.let { location ->
                tvLastLocation.text = buildString {
                    append(location.address ?: "Ubicación desconocida")
                    append(" - ")
                    append(formatDate(location.timestamp))
                }
            } ?: run {
                tvLastLocation.text = "Sin datos de ubicación"
            }

            // Cargar imagen con Glide
            Glide.with(itemView.context)
                .load(vehicle.image_url)
                .placeholder(R.drawable.ic_vehicle_placeholder)
                .error(R.drawable.ic_vehicle_placeholder)
                .into(ivVehicle)

            itemView.setOnClickListener {
                Toast.makeText(
                    itemView.context,
                    "Vehículo: ${vehicle.name}\nPlaca: ${vehicle.plate_number}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        private fun formatDate(timestamp: String): String {
            return try {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                val outputFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                val date = inputFormat.parse(timestamp)
                outputFormat.format(date ?: timestamp)
            } catch (e: Exception) {
                timestamp
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vehicle, parent, false)
        return VehicleViewHolder(view)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        holder.bind(vehicles[position])
    }

    override fun getItemCount(): Int = vehicles.size

    fun submitList(newList: List<Vehicle>) {
        vehicles = newList
        notifyDataSetChanged()
    }
}