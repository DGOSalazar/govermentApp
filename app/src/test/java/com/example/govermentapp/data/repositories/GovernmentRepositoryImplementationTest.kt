package com.example.govermentapp.data.repositories

import android.location.LocationManager
import com.example.govermentapp.data.datasource.database.dao.GovernmentInstitutionsDao
import com.example.govermentapp.data.datasource.network.GobApiClient
import com.example.govermentapp.data.models.entities.GobEntity
import com.example.govermentapp.domain.GovernmentRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.firebase.auth.FirebaseAuth
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class GovernmentRepositoryImplementationTest{
    @RelaxedMockK
    private lateinit var gobApiClient: GobApiClient
    @RelaxedMockK
    private lateinit var governmentInstitutionsDao: GovernmentInstitutionsDao
    @RelaxedMockK
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    @RelaxedMockK
    private lateinit var locationManager: LocationManager
    @RelaxedMockK
    private lateinit var firebaseAuth : FirebaseAuth

    private lateinit var governmentRepository: GovernmentRepository

    @Before
    fun init() {
        MockKAnnotations.init(this)
        governmentRepository = GovernmentRepositoryImplementation(
            gobApiClient,
            governmentInstitutionsDao,
            fusedLocationProviderClient,
            locationManager,
            firebaseAuth
        )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `testing`() = runBlocking{
        governmentRepository.clearGovernmentInstitutions()
    }

    @Test
    fun `test 1`() = runBlocking{
        //GIVEN
        governmentRepository.insertAllGovernmentInstitutions(listOf(GobEntity(organization = "organization", fact = "fact", url = "url")))
        val result = governmentRepository.fetchGovernmentInstitutionsFromDB().collect()
        governmentRepository.clearGovernmentInstitutions()
        //WHEN

        //THEN
        assertNotNull(result)
        //Assert.assertEquals()
    }
}