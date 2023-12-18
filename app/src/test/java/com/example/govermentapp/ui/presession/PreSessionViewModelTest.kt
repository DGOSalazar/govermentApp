package com.example.govermentapp.ui.presession

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.govermentapp.data.models.entities.FirebaseState
import com.example.govermentapp.data.utils.Status
import com.example.govermentapp.domain.usescase.OnLoginUseCase
import com.example.govermentapp.domain.usescase.OnRegisterUseCase
import com.example.govermentapp.domain.usescase.SaveAndGetUserDataUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PreSessionViewModelTest {
    @RelaxedMockK
    private  lateinit var loginUseCase: OnLoginUseCase
    @RelaxedMockK
    private  lateinit var registerUseCase: OnRegisterUseCase
    @RelaxedMockK
    private  lateinit var sharedPreference: SaveAndGetUserDataUseCase

    private lateinit var viewModel: PreSessionViewModel

    @get:Rule
    val rule:InstantTaskExecutorRule = InstantTaskExecutorRule()
    @Before
    fun init() {
        MockKAnnotations.init(this)
        viewModel = PreSessionViewModel(
            registerUseCase,
            loginUseCase,
            sharedPreference
        )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }
    @After
    fun done(){
        Dispatchers.resetMain()
    }

    @Test
    fun `register success test`() = runTest {
        //GIVEN
        coEvery { registerUseCase(any(),any()) } returns FirebaseState.success(null)
        //WHEN
        viewModel.setRegisterUser("dz122012@hotmail.com","12345678")
        val res = viewModel.registerStatus.value
        //THEN
        assert(res!!.status == Status.SUCCESS)
        assert(res.data == null)
    }
    @Test
    fun `register loading test`() = runBlocking {
        //GIVEN
        coEvery { registerUseCase(any(),any()) } returns FirebaseState.loading(null)
        //WHEN
        viewModel.setRegisterUser("dz122012@hotmail.com","12345678")
        val res = viewModel.registerStatus.value
        //THEN
        assert(res!!.status == Status.LOADING)
        assert(res.data == null)
    }
    @Test
    fun `register error test`() = runBlocking {
        //GIVEN
        val msgError = "ERROR"
        coEvery { registerUseCase(any(),any()) } returns FirebaseState.error(msgError)
        //WHEN
        viewModel.setRegisterUser("dz122012@hotmail.com","12345678")
        val res = viewModel.registerStatus.value
        //THEN
        assert(res!!.status == Status.ERROR)
        assert(res.message == msgError)
    }
    @Test
    fun `login success test`() = runBlocking {
        //GIVEN
        coEvery { loginUseCase(any(),any()) } returns FirebaseState.success(null)
        //WHEN
        viewModel.getLoginUser("dz122012@hotmail.com","12345678")
        val res = viewModel.loginStatus.value
        //THEN
        assert(res!!.status == Status.SUCCESS)
        assert(res.data == null)
    }
    @Test
    fun `login loading test`() = runBlocking {
        //GIVEN
        coEvery { loginUseCase(any(),any()) } returns FirebaseState.loading(null)
        //WHEN
        viewModel.getLoginUser("dz122012@hotmail.com","12345678")
        val res = viewModel.loginStatus.value
        //THEN
        assert(res!!.status == Status.LOADING)
        assert(res.data == null)
    }
    @Test
    fun `login error test`() = runBlocking {
        //GIVEN
        val msgError = "ERROR"
        coEvery { loginUseCase(any(),any()) } returns FirebaseState.error(msgError)
        //WHEN
        viewModel.getLoginUser("dz122012@hotmail.com","12345678")
        val res = viewModel.loginStatus.value
        //THEN
        assert(res!!.status == Status.ERROR)
        assert(res.message == msgError)
    }
}