package com.example.govermentapp.data

import com.example.govermentapp.data.datasource.network.GobApiClient
import com.example.govermentapp.domain.GovernmentRepository

class GovernmentRepositoryImplementation(private val apiService : GobApiClient) : GovernmentRepository {
    override suspend fun getRepo(): List<String> {
        return apiService.getString()
    }
}