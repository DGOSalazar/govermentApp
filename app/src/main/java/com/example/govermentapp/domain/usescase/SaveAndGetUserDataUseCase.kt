package com.example.govermentapp.domain.usescase

import com.example.govermentapp.domain.SharedPreferenceRepository
import javax.inject.Inject

class SaveAndGetUserDataUseCase @Inject constructor(
    private val sharedPreferenceRepository: SharedPreferenceRepository
) {
    fun clearData() = sharedPreferenceRepository.clearAll()
    fun saveUserData(email: String, pass: String, isBiometric: Boolean) =
        sharedPreferenceRepository.saveUserData(email,pass, isBiometric)
    fun getEmail(): String = sharedPreferenceRepository.getEmail()
    fun getPass(): String = sharedPreferenceRepository.getPass()
    fun getBiometric(): Boolean = sharedPreferenceRepository.isBiometric()
}