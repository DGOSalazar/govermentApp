package com.example.govermentapp.domain.usescase

import com.example.govermentapp.data.models.entities.FirebaseState
import com.example.govermentapp.domain.GovernmentRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class OnRegisterUseCase @Inject constructor(
    private val governmentAuthRepository: GovernmentRepository
) {
    suspend operator fun invoke(email: String,pass: String): FirebaseState<FirebaseUser> =
        governmentAuthRepository.setRegisterUser(email,pass)
}