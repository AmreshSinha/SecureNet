package com.securenaut.securenet.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IPDataDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addIPData(user: IPData)

    @Query("SELECT * FROM ip_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<IPData>>

}