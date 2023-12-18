package com.example.govermentapp.domain

import com.example.govermentapp.data.models.entities.FirebaseState
import com.google.firebase.auth.FirebaseUser
import android.location.Location
import com.example.govermentapp.data.models.entities.GobEntity
import kotlinx.coroutines.flow.Flow

interface GovernmentRepository {
    suspend fun fetchGovernmentInstitutionsFromApi() : Flow<List<GovernmentInstitution>>
    suspend fun fetchGovernmentInstitutionsFromDB() : Flow<List<GovernmentInstitution>>
    suspend fun insertAllGovernmentInstitutions(list: List<GobEntity>)
    suspend fun clearGovernmentInstitutions()
    suspend fun getUserLocation(): Location?
    //firebase
    suspend fun setRegisterUser(email: String, pass: String): FirebaseState<FirebaseUser>
    suspend fun getLoginUser(email: String, pass: String) : FirebaseState<FirebaseUser>
}