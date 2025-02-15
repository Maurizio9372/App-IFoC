package com.example.appifoc.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.appifoc.dao.NeedDao
import com.example.appifoc.entities.need.NeedEntity
import com.example.appifoc.utils.Converters

@Database(entities = [NeedEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NeedDatabase : RoomDatabase() {

    abstract fun needDao(): NeedDao

    companion object {
        @Volatile
        private var INSTANCE: NeedDatabase? = null

        fun getInstance(context: Context): NeedDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NeedDatabase::class.java,
                    "need_database"
                )
                    .fallbackToDestructiveMigration()  // Reset del DB in caso di modifica della struttura
                    .build()
                INSTANCE = instance
                instance
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
