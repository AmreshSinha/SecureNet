package com.securenaut.securenet.data

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IPDataViewModel(application: Application) : AndroidViewModel(application) {
    private val ipDataDao = IPDataDatabase.getInstance(application).ipDataDao()
    var allIPData = ipDataDao.getAllData()

    fun addIPData(ipData: IPData) = viewModelScope.launch{
        viewModelScope.launch(Dispatchers.IO){
            ipDataDao.addIPData(ipData)
        }
    }
}