package com.example.govermentapp.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.govermentapp.domain.models.GovernmentInstitution
import com.example.govermentapp.domain.usescase.FilterGovernmentInstitutionUseCase
import com.example.govermentapp.domain.usescase.GetGovernmentInstitutionsUseCase
import com.example.govermentapp.domain.usescase.GetLocationUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.Assert.*

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest{
    @RelaxedMockK
    private lateinit var getGovernmentInstitutionsUseCase:GetGovernmentInstitutionsUseCase
    @RelaxedMockK
    private lateinit var filterGovernmentInstitutionUseCase: FilterGovernmentInstitutionUseCase
    @RelaxedMockK
    private lateinit var getLocationUseCase: GetLocationUseCase

    private lateinit var viewModel: HomeViewModel

    @get:Rule
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun init() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `test 1`(){
        //GIVEN
        coEvery { filterGovernmentInstitutionUseCase(any()) } returns flow { emptyList<GovernmentInstitution>() }
        //WHEN
        viewModel.searchGovernmentInstitution("")
        val result = viewModel.organizationsStateFlow.value
        //THEN
        assertNotNull(result)
    }

    @Test
    fun `test2`() = runBlocking{
        //GIVEN
        coEvery { getGovernmentInstitutionsUseCase() } returns flow { emptyList<GovernmentInstitution>() }
        //coEvery { filterGovernmentInstitutionUseCase(any()) } returns flow { emptyList<GovernmentInstitution>() }
        //WHEN
        viewModel = HomeViewModel(
            getGovernmentInstitutionsUseCase,
            filterGovernmentInstitutionUseCase,
            getLocationUseCase
        )
        //THEN
        //viewModel.organizationsStateFlow.test
        //Assert.assertEquals()
    }

    @After
    fun done(){
        Dispatchers.resetMain()
    }
}