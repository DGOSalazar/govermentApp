package com.example.govermentapp.domain

interface GovernmentRepository {
    suspend fun getRepo() : List<String>
}