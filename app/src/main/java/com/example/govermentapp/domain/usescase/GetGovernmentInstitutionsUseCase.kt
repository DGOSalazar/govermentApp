package com.example.govermentapp.domain.usescase

import com.example.govermentapp.data.models.entities.toDataBase
import com.example.govermentapp.domain.GovernmentRepository
import com.example.govermentapp.domain.models.GovernmentInstitution
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetGovernmentInstitutionsUseCase @Inject constructor(private val governmentRepository: GovernmentRepository) {
    suspend operator fun invoke() : Flow<List<GovernmentInstitution>> {
        governmentRepository.fetchGovernmentInstitutionsFromApi().collect{ governmentInstitutionsFromApi ->
           if(governmentInstitutionsFromApi.isNotEmpty()){
               withContext(Dispatchers.IO){
                   governmentRepository.clearGovernmentInstitutions()
                   governmentRepository.insertAllGovernmentInstitutions(governmentInstitutionsFromApi.map { it.toDataBase()})
               }
           }
        }
        return governmentRepository.fetchGovernmentInstitutionsFromDB()
    }
}