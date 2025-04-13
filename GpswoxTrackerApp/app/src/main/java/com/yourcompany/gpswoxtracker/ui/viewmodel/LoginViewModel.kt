// LoginViewModel.kt
package com.yourcompany.gpswoxtracker.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yourcompany.gpswoxtracker.data.remote.ApiService
import com.yourcompany.gpswoxtracker.data.model.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val apiService: ApiService
) : ViewModel() {

    private val _loginStatus = MutableLiveData<String>()
    val loginStatus: LiveData<String> get() = _loginStatus

    // Función para realizar el login
    suspend fun login(apiToken: String) {
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.login(apiToken)
                if (response.isSuccessful) {
                    _loginStatus.postValue("Login exitoso")
                } else {
                    _loginStatus.postValue("Error en el login")
                }
            } catch (e: Exception) {
                _loginStatus.postValue("Error: ${e.message}")
            }
        }
    }
}
