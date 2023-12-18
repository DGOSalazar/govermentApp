package com.example.govermentapp.domain

import android.location.Location
import com.example.govermentapp.data.models.entities.GobEntity
import kotlinx.coroutines.flow.Flow

interface GovernmentRepository {
    suspend fun fetchGovernmentInstitutionsFromApi() : Flow<List<GovernmentInstitution>>
    suspend fun fetchGovernmentInstitutionsFromDB() : Flow<List<GovernmentInstitution>>
    suspend fun insertAllGovernmentInstitutions(list: List<GobEntity>)
    suspend fun clearGovernmentInstitutions()
    suspend fun getUserLocation(): Location?
}