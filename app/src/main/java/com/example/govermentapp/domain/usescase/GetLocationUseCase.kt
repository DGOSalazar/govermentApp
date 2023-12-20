package com.example.govermentapp.domain.usescase

import android.location.Location
import com.example.govermentapp.domain.GovernmentRepository
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(private val repository: GovernmentRepository) {
    suspend operator fun invoke():Location? = repository.getUserLocation()
}