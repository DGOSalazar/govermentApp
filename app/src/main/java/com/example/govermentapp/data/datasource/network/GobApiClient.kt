package com.example.govermentapp.data.datasource.network

import com.example.govermentapp.data.models.responses.GovernmentInstitutionResponse
import retrofit2.http.GET

interface GobApiClient {

    @GET("/v1/gobmx.facts")
    suspend fun getAllGovernmentInstitutions() : GovernmentInstitutionResponse
}