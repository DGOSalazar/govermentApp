package com.example.govermentapp.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.govermentapp.data.models.entities.GobEntity

@Dao
interface GovernmentInstitutionsDao {
    @Query("SELECT * FROM government_institution_table")
    fun getGovernmentInstitutions():List<GobEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGovernmentInstitutions(governmentInstitutions: List<GobEntity>)

    @Query("DELETE FROM government_institution_table")
    fun deleteGovernmentInstitutions()
}