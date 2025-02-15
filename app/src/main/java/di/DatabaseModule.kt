package com.example.appifoc.di

import android.content.Context
import androidx.room.Room
import com.example.appifoc.dao.PatientDao
import com.example.appifoc.dao.NeedDao
import com.example.appifoc.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    // Fornisce l'istanza del database AppDatabase
    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"  // Nome del database
        ).build()
    }

    // Fornisce il PatientDao per interagire con la tabella dei pazienti
    @Provides
    fun providePatientDao(database: AppDatabase): PatientDao = database.patientDao()

    // Fornisce il NeedDao per interagire con la tabella dei bisogni
    @Provides
    fun provideNeedDao(database: AppDatabase): NeedDao = database.needDao()
}
