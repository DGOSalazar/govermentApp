package com.example.govermentapp.domain.usescase

import com.example.govermentapp.domain.GovernmentRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class GetLocationUseCaseTest{

    @RelaxedMockK
    private lateinit var governmentRepository: GovernmentRepository
    private lateinit var getLocationUseCase: GetLocationUseCase

    @Before
    fun init() {
        MockKAnnotations.init(this)
        getLocationUseCase = GetLocationUseCase(governmentRepository)
    }

    @Test
    fun `test 1`() = runBlocking{
        //GIVEN
        //WHEN
        val location = getLocationUseCase()
        //THEN
        assertNotNull(location)
        //Assert.assertEquals()
    }
}