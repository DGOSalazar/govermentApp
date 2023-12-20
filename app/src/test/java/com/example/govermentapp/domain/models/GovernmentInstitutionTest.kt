package com.example.govermentapp.domain.models

import org.junit.Test

class GovernmentInstitutionTest{
    @Test
    fun `test 1`(){
        //GIVEN
        val organization = ""
        val fact = ""
        val url = ""
        val latitude = 0.0
        val longitude = 0.0
        //WHEN
        val local = GovernmentInstitution(organization, fact, url, latitude, longitude)
        //THEN
        assert(organization == local.organization)
        assert(fact == local.fact)
        assert(url == local.url)
        assert(latitude == local.latitude)
        assert(longitude == local.longitude)
    }
}