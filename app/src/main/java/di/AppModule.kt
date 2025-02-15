package com.example.appifoc.di

import android.content.Context
import androidx.room.Room
import com.example.appifoc.data.AppDatabase
import com.example.appifoc.dao.NeedDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Fornisce l'istanza del database AppDatabase
    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration() // Evita crash in caso di modifica del DB
            .build()
    }

    // Fornisce il DAO per la gestione dei bisogni
    @Provides
    fun provideNeedDao(database: AppDatabase): NeedDao {
        return database.needDao()
    }
}
