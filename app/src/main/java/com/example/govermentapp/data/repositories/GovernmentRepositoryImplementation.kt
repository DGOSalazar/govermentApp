package com.example.govermentapp.data.repositories

import com.example.govermentapp.GovernmentApp
import com.example.govermentapp.R
import com.example.govermentapp.data.datasource.network.GobApiClient
import com.example.govermentapp.data.models.entities.FirebaseState
import com.example.govermentapp.domain.GovernmentRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.delay
import javax.inject.Inject

class GovernmentRepositoryImplementation @Inject constructor(
    private val apiService : GobApiClient,
    private val firebaseAuth : FirebaseAuth
) : GovernmentRepository {
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

    //repository
    override suspend fun getRepo(): List<String> {
        return apiService.getString()
    }
}