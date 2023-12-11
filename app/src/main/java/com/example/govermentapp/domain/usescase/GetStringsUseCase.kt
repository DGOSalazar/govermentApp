package com.example.govermentapp.domain.usescase

import com.example.govermentapp.domain.GovernmentRepository
import javax.inject.Inject

class GetStringsUseCase@Inject constructor(
    private val repositoryImplementation: GovernmentRepository
) {
    suspend operator fun invoke() : List<String> = repositoryImplementation.getRepo()
}