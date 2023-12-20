package com.example.govermentapp.domain.usescase

import com.example.govermentapp.domain.GovernmentRepository
import com.example.govermentapp.domain.models.GovernmentInstitution
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FilterGovernmentInstitutionUseCase @Inject constructor(private val governmentRepository: GovernmentRepository){
    suspend operator fun invoke(query: String): Flow<List<GovernmentInstitution>> {
        return governmentRepository.fetchGovernmentInstitutionsFromDB().map{ listGovernmentInstitutions ->
            listGovernmentInstitutions.filter { it.organization.lowercase().contains(query.lowercase())}
        }
    }
}