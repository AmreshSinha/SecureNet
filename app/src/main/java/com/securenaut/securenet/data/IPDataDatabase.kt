package com.securenaut.securenet.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [IPData::class], version = 1)
abstract class IPDataDatabase: RoomDatabase() {

    abstract fun ipDataDao(): IPDataDao

    companion object {
        @Volatile
        private var instance:IPDataDatabase? = null

        fun getInstance(context: Context): IPDataDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, IPDataDatabase::class.java, "ip.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}