package com.example.govermentapp.data

import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationManager
import com.example.govermentapp.GovernmentApp
import com.example.govermentapp.R
import com.example.govermentapp.data.datasource.database.dao.GovernmentInstitutionsDao
import com.example.govermentapp.data.datasource.network.GobApiClient
import com.example.govermentapp.data.models.entities.FirebaseState
import com.example.govermentapp.data.models.entities.GobEntity
import com.example.govermentapp.data.models.responses.GovernmentInstitutionResponse
import com.example.govermentapp.domain.GovernmentInstitution
import com.example.govermentapp.domain.GovernmentRepository
import com.example.govermentapp.domain.toDomain
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

class GovernmentRepositoryImplementation
@Inject constructor(
    private val gobApiClient: GobApiClient,
    private val governmentInstitutionsDao: GovernmentInstitutionsDao,
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val locationManager: LocationManager,
    private val firebaseAuth : FirebaseAuth

    ) : GovernmentRepository {
    override suspend fun fetchGovernmentInstitutionsFromApi(): Flow<List<GovernmentInstitution>> = flow {
        val response: GovernmentInstitutionResponse = gobApiClient.getAllGovernmentInstitutions()
        emit(response.governmentInstitutions.map { it.toDomain() })
    }.flowOn(Dispatchers.IO)

    override suspend fun fetchGovernmentInstitutionsFromDB(): Flow<List<GovernmentInstitution>> = flow {
        val response = governmentInstitutionsDao.getGovernmentInstitutions()
        emit(response.map { it.toDomain() })
    }.flowOn(Dispatchers.IO)

    override suspend fun insertAllGovernmentInstitutions(list: List<GobEntity>) {
        governmentInstitutionsDao.insertGovernmentInstitutions(list)
    }

    override suspend fun clearGovernmentInstitutions() {
        governmentInstitutionsDao.deleteGovernmentInstitutions()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @SuppressLint("MissingPermission")
    override suspend fun getUserLocation(): Location? {
        val isGPSEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if(!isGPSEnabled){
            return null
        }
        return suspendCancellableCoroutine { cont ->
            fusedLocationProviderClient.lastLocation.apply {
                if(isComplete){
                    if(isSuccessful){
                        cont.resume(result){}
                    }else{
                        cont.resume(null){}
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    cont.resume(it){}
                }
                addOnFailureListener{
                    cont.resume(null){}
                }
            }
        }
    }
    //firebase
    override suspend fun setRegisterUser(email: String, pass: String): FirebaseState<FirebaseUser> {
        var res: FirebaseUser? = null
        var errorMsg = ""
        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
            it.addOnCompleteListener { task ->
                if (task.isSuccessful) res = task.result.user
            }.addOnFailureListener { error ->
                errorMsg = error.message ?: GovernmentApp.instance.getString(R.string.error_general_title)
            }
        }
        delay(1000)
        return if(res != null) FirebaseState.success(res) else FirebaseState.error(errorMsg, null)
    }

    override suspend fun getLoginUser(email: String, pass: String): FirebaseState<FirebaseUser> {
        var res: FirebaseUser? = null
        var errorMsg = ""
        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
            it.addOnCompleteListener { task ->
                if (task.isSuccessful) res = task.result.user
            }.addOnFailureListener { error ->
                errorMsg = error.message ?: GovernmentApp.instance.getString(R.string.error_general_title)
            }
        }
        delay(1000)
        return if(errorMsg.isEmpty()) FirebaseState.success(res) else FirebaseState.error(errorMsg, null)
    }
}