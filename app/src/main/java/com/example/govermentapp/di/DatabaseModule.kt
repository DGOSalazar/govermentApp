package com.example.govermentapp.di

import android.content.Context
import androidx.room.Room
import com.example.govermentapp.data.datasource.database.DatabaseGob
import com.example.govermentapp.data.datasource.database.dao.GovernmentInstitutionsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val DATABASE_NAME="database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DatabaseGob::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideQueries(database: DatabaseGob):GovernmentInstitutionsDao = database.getGobDao()
}