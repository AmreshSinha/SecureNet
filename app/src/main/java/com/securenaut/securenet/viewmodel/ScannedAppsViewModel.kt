package com.securenaut.securenet.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.securenaut.securenet.data.RecentScannedAppsDetails
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import com.securenaut.securenet.data.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.HttpException
import java.io.IOException

sealed class ScannedAppsState {
    object Loading : ScannedAppsState()
    data class Success(val recentScannedAppsDetails: RecentScannedAppsDetails) : ScannedAppsState()
    data class Error(val message: String) : ScannedAppsState()
}
class ScannedAppsViewModel:ViewModel() {
    private val apiService = RetrofitInstance.api
    // Use mutableStateOf to represent the state
    private val _scannedAppsState = MutableStateFlow<ScannedAppsState>(ScannedAppsState.Loading)
    val scannedAppsState: StateFlow<ScannedAppsState> = _scannedAppsState

    fun getRecentScannedAppsDetails() {
        viewModelScope.launch {
            try {
                val response = apiService.getRecentScannedAppsDetails()
                _scannedAppsState.value = ScannedAppsState.Success(response)
                Log.d("lostofapp", "getRecentScannedAppsDetails: API call successful")
            } catch (e: IOException) {
                // Handle network-related errors (e.g., no internet connection)
                _scannedAppsState.value = ScannedAppsState.Error("Network error")
                Log.e("lostofapp", "getRecentScannedAppsDetails: Network error", e)
            } catch (e: HttpException) {
                // Handle HTTP-related errors (e.g., 404 Not Found)
                _scannedAppsState.value = ScannedAppsState.Error("HTTP error")
                Log.e("lostofapp", "getRecentScannedAppsDetails: HTTP error", e)
            } catch (e: Exception) {
                // Handle other unexpected errors
                _scannedAppsState.value = ScannedAppsState.Error("Unexpected error")
                Log.e("lostofapp", "getRecentScannedAppsDetails: Unexpected error", e)
            }
        }
    }
}