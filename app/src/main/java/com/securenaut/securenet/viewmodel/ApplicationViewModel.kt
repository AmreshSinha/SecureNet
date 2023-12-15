package com.securenaut.securenet.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.securenaut.securenet.data.models.scannedApps.RecentScannedAppsDetails
import kotlinx.coroutines.launch
import com.securenaut.securenet.data.RetrofitInstance
import com.securenaut.securenet.data.models.scorecard.ScoreCard
import com.securenaut.securenet.data.models.scorecard.ScoreCardRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.HttpException
import java.io.IOException

sealed class ScannedAppsState {
    object Loading : ScannedAppsState()
    data class Success(val recentScannedAppsDetails: RecentScannedAppsDetails) : ScannedAppsState()
    data class Error(val message: String) : ScannedAppsState()
}
sealed class ScorecardState {
    object Loading : ScorecardState()
    data class Success(val scorecardResponse: ScoreCard) : ScorecardState()
    data class Error(val message: String) : ScorecardState()
}
class ApplicationViewModel:ViewModel() {
    private val apiService = RetrofitInstance.api
    // Use mutableStateOf to represent the state
    private val _scannedAppsState = MutableStateFlow<ScannedAppsState>(ScannedAppsState.Loading)
    val scannedAppsState: StateFlow<ScannedAppsState> = _scannedAppsState
    private val _scorecardState = MutableStateFlow<ScorecardState>(ScorecardState.Loading)
    val scorecardState: StateFlow<ScorecardState> = _scorecardState

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
        fun getScorecard(hash: String) {
            viewModelScope.launch {
                try {
                    _scorecardState.value = ScorecardState.Loading
                    val scorecardRequest = ScoreCardRequest(hash = hash)
                    val response = apiService.getScorecard(scorecardRequest)
                    _scorecardState.value = ScorecardState.Success(response)
                    Log.d("lostofapp", "getScorecard: API call successful")
                } catch (e: IOException) {
                    _scorecardState.value = ScorecardState.Error("Network error")
                    Log.e("lostofapp", "getScorecard: Network error", e)
                } catch (e: HttpException) {
                    _scorecardState.value = ScorecardState.Error("HTTP error")
                    Log.e("lostofapp", "getScorecard: HTTP error", e)
                } catch (e: Exception) {
                    _scorecardState.value = ScorecardState.Error("Unexpected error")
                    Log.e("lostofapp", "getScorecard: Unexpected error", e)
                }
            }
        }
    }
