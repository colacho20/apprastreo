package com.yourcompany.gpswoxtracker.ui.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.yourcompany.gpswoxtracker.databinding.ActivityLoginBinding
import com.yourcompany.gpswoxtracker.ui.main.VehicleListActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (validateInputs(email, password)) {
                performLogin(email, password)
            }
        }
    }

    private fun validateInputs(email: String, password: String): Boolean {
        var isValid = true

        if (email.isEmpty()) {
            binding.etEmail.error = "Email requerido"
            isValid = false
        } else {
            binding.etEmail.error = null
        }

        if (password.isEmpty()) {
            binding.etPassword.error = "Contraseña requerida"
            isValid = false
        } else {
            binding.etPassword.error = null
        }

        return isValid
    }

    private fun performLogin(email: String, password: String) {
        binding.progressBar.visibility = View.VISIBLE
        binding.btnLogin.isEnabled = false

        // Simulación de login - reemplazar con llamada real a API
        binding.root.postDelayed({
            binding.progressBar.visibility = View.GONE
            binding.btnLogin.isEnabled = true

            // Guardar datos de sesión simulados
            with(sharedPref.edit()) {
                putString("api_key", "simulated_api_key_$email")
                apply()
            }

            showSuccessMessage()
        }, 1500)
    }

    private fun showSuccessMessage() {
        Snackbar.make(binding.root, "Login exitoso", Snackbar.LENGTH_SHORT)
            .setAction("Continuar") {
                navigateToMain()
            }
            .show()
    }

    private fun navigateToMain() {
        startActivity(Intent(this, VehicleListActivity::class.java))
        finish()
    }
}