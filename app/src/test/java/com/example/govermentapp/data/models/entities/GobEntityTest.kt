package com.example.govermentapp.data.models.entities

import org.junit.Test

class GobEntityTest{
    @Test
    fun `test 1`(){
        //GIVEN
        val identity = 0
        val id = ""
        val date = ""
        val slug = ""
        val columns = ""
        val fact = ""
        val organization = ""
        val resource = ""
        val url = ""
        val operations = ""
        val dataset = ""
        val createdAt = ""
        //WHEN
        val entity = GobEntity(identity, id, date, slug, columns, fact, organization, resource, url, operations, dataset, createdAt)
        //THEN
        assert(identity == entity.identity)
        assert(id == entity.id)
        assert(date == entity.date_insert)
        assert(slug == entity.slug)
        assert(columns == entity.columns)
        assert(fact == entity.fact)
        assert(organization == entity.organization)
        assert(resource == entity.resource)
        assert(url == entity.url)
        assert(operations == entity.operations)
        assert(dataset == entity.dataset)
        assert(createdAt == entity.created_at)
    }
}