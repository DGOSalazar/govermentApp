package com.example.govermentapp.domain.usescase

import com.example.govermentapp.domain.GovernmentInstitution
import com.example.govermentapp.domain.GovernmentRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class FilterGovernmentInstitution @Inject constructor(private val governmentRepository: GovernmentRepository){
    suspend operator fun invoke(query: String): Flow<List<GovernmentInstitution>> {
        return governmentRepository.fetchGovernmentInstitutionsFromDB().map{ listGovernmentInstitutions ->
            listGovernmentInstitutions.filter { it.organization.lowercase().contains(query.lowercase())}
        }
    }
}