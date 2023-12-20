package com.example.govermentapp.domain.usescase

import com.example.govermentapp.data.models.entities.FirebaseState
import com.example.govermentapp.data.utils.Status
import com.example.govermentapp.domain.GovernmentRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class OnLoginUseCaseTest {
    @RelaxedMockK
    private  lateinit var governmentRepository : GovernmentRepository
    lateinit var onLoginUseCase: OnLoginUseCase

    @Before
    fun init() {
        MockKAnnotations.init(this)
        onLoginUseCase = OnLoginUseCase(governmentRepository)
    }

    @Test
    fun `test of login success`() = runBlocking {
        //GIVEN
        coEvery { onLoginUseCase(any(),any()) } returns FirebaseState.success(null)
        //WHEN
        val res = onLoginUseCase("dz122012@hotmail.com","12345678")
        //THEN
        coVerify(exactly = 1) { governmentRepository.getLoginUser(any(),any()) }
        Assert.assertEquals(res.status, Status.SUCCESS)
        Assert.assertEquals(res.data, null)
    }
    @Test
    fun `test of login error`() = runBlocking {
        //GIVEN
        coEvery { onLoginUseCase(any(),any()) } returns FirebaseState.error("something bad happen")
        //WHEN
        val res = onLoginUseCase("dz122012@hotmail.com","12345678")
        //THEN
        coVerify(exactly = 1) { governmentRepository.getLoginUser(any(),any()) }
        Assert.assertEquals(res.status, Status.ERROR)
        Assert.assertEquals(res.message, "something bad happen")
    }
}