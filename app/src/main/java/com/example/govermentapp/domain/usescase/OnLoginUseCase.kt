package com.example.govermentapp.domain.usescase

import com.example.govermentapp.domain.GovernmentRepository
import javax.inject.Inject

class OnLoginUseCase @Inject constructor(
    private val governmentAuthRepository: GovernmentRepository
) {
    suspend operator fun invoke(email: String, pass: String) = governmentAuthRepository.getLoginUser(email, pass)
}