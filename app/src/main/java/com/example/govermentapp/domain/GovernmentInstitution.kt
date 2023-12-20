package com.example.govermentapp.domain

import com.example.govermentapp.data.models.GovernmentInstitutionModel
import com.example.govermentapp.data.models.entities.GobEntity

data class GovernmentInstitution(
    val organization: String,
    val fact: String,
    val url: String,
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var operations: String = "",
    var dataset : String = "",
    var created_at : String = ""
)

fun GovernmentInstitutionModel.toDomain() = GovernmentInstitution(
    organization = organization,
    fact= fact,
    url = url,
    operations = operations,
    dataset = dataset ,
    created_at = created_at
)

fun GobEntity.toDomain() = GovernmentInstitution(
    organization = organization,
    fact= fact,
    url = url,
    operations = operations,
    dataset = dataset ,
    created_at = created_at
)