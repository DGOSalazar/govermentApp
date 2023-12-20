package com.example.govermentapp.data.models

import org.junit.Test

class GovernmentInstitutionModelTest {
    @Test
    fun `test 1`() {
        //GIVEN
        val id =""
        val organization = ""
        val fact = ""
        val url = ""
        //WHEN
        val model = GovernmentInstitutionModel(id, organization, fact, url)
        //THEN
        assert(id == model.id)
        assert(organization == model.organization)
        assert(fact == model.fact)
        assert(url == model.url)
    }
}