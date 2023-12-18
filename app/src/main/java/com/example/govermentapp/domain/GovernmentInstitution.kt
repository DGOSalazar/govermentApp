package com.example.govermentapp.domain

import com.example.govermentapp.data.models.GovernmentInstitutionModel
import com.example.govermentapp.data.models.entities.GobEntity

data class GovernmentInstitution(
    val organization: String,
    val fact: String,
    val url: String,
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
)

fun GovernmentInstitutionModel.toDomain() = GovernmentInstitution(organization, fact, url)

fun GobEntity.toDomain() = GovernmentInstitution(organization, fact, url)