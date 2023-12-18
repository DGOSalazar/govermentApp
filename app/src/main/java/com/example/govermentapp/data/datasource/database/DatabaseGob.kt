package com.example.govermentapp.data.datasource.database

import androidx.room.RoomDatabase
import com.example.govermentapp.data.datasource.database.dao.GovernmentInstitutionsDao
import com.example.govermentapp.data.models.entities.GobEntity

@androidx.room.Database (entities = [GobEntity::class], version = 1)
abstract class DatabaseGob : RoomDatabase(){
    abstract fun getGobDao(): GovernmentInstitutionsDao
}