package com.example.govermentapp.domain

import com.example.govermentapp.data.models.entities.FirebaseState
import com.google.firebase.auth.FirebaseUser

interface GovernmentRepository {
    //firebase
    suspend fun setRegisterUser(email: String, pass: String): FirebaseState<FirebaseUser>
    suspend fun getLoginUser(email: String, pass: String) : FirebaseState<FirebaseUser>
    //network
    suspend fun getRepo() : List<String>
}