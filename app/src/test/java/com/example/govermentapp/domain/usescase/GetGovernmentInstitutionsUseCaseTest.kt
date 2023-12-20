package com.example.govermentapp.domain.usescase

import com.example.govermentapp.domain.GovernmentRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before
import org.junit.Test

class GetGovernmentInstitutionsUseCaseTest{

    @RelaxedMockK
    private lateinit var governmentRepository: GovernmentRepository
    private lateinit var getGovernmentInstitutionsUseCase: GetGovernmentInstitutionsUseCase

    @Before
    fun init() {
        MockKAnnotations.init(this)
        getGovernmentInstitutionsUseCase = GetGovernmentInstitutionsUseCase(governmentRepository)
    }

    @Test
    fun `test 1`(){
        //GIVEN

        //WHEN
        coEvery { getGovernmentInstitutionsUseCase() }
        //THEN
        //Assert.assertEquals()
    }
}