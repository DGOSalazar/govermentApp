package com.example.govermentapp.domain.usescase

import com.example.govermentapp.domain.GovernmentRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any

class FilterGovernmentInstitutionUseCaseTest{

    @RelaxedMockK
    private lateinit var governmentRepository: GovernmentRepository
    private lateinit var filterGovernmentInstitutionUseCase: FilterGovernmentInstitutionUseCase

    @Before
    fun init() {
        MockKAnnotations.init(this)
        filterGovernmentInstitutionUseCase = FilterGovernmentInstitutionUseCase(governmentRepository)
    }

    @Test
    fun `test 1`() = runBlocking {
        //GIVEN
        coEvery { governmentRepository.fetchGovernmentInstitutionsFromDB() }
        //WHEN
        val response = filterGovernmentInstitutionUseCase(any())
        //THEN
        //Assert.assertEquals()
    }

    @Test
    fun `test2`() = runBlocking {
        //GIVEN
        coEvery { governmentRepository.fetchGovernmentInstitutionsFromDB() }
        //WHEN
        val response = filterGovernmentInstitutionUseCase(any())
        //THEN
        //Assert.assertEquals(response)
    }
    @Test
    fun `test3`() = runBlocking {
        //GIVEN
        coEvery { governmentRepository.fetchGovernmentInstitutionsFromDB() }
        //WHEN
        val response = filterGovernmentInstitutionUseCase(any())
        //THEN
        //Assert.assertEquals()
    }

    @Test
    fun `test4`() = runBlocking {
        //GIVEN
        coEvery { governmentRepository.fetchGovernmentInstitutionsFromDB() }
        //WHEN
        val response = filterGovernmentInstitutionUseCase(any())
        //THEN
        //Assert.assertEquals()
    }
}