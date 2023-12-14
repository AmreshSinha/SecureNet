package com.securenaut.securenet.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.securenaut.securenet.data.RecentScannedAppsDetails
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import com.securenaut.securenet.data.RetrofitInstance
import retrofit2.HttpException
import java.io.IOException

class ScannedAppsViewModel:ViewModel() {
    private val apiService = RetrofitInstance.api
    // Use mutableStateOf to represent the state
    var recentScannedAppsDetails = mutableStateOf<RecentScannedAppsDetails?>(null)
        private set

    fun getRecentScannedAppsDetails() {
        viewModelScope.launch {
            try {
                val response = apiService.getRecentScannedAppsDetails()
                recentScannedAppsDetails.value = response
                Log.d("lostofapp", "getRecentScannedAppsDetails: API call successful")
            } catch (e: IOException) {
                // Handle network-related errors (e.g., no internet connection)
                Log.e("lostofapp", "getRecentScannedAppsDetails: Network error", e)
            } catch (e: HttpException) {
                // Handle HTTP-related errors (e.g., 404 Not Found)
                Log.e("lostofapp", "getRecentScannedAppsDetails: HTTP error", e)
            } catch (e: Exception) {
                // Handle other unexpected errors
                Log.e("lostofapp", "getRecentScannedAppsDetails: Unexpected error", e)
            }
        }
    }
}