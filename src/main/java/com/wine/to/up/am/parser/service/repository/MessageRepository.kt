package com.wine.to.up.am.parser.service.repository

import com.wine.to.up.am.parser.service.domain.entity.Message
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MessageRepository : CrudRepository<Message?, UUID?> {
    /**
     * Define our custom method using HQL language
     *
     * @return list of distinct message stored in DB
     */
    @Query("SELECT DISTINCT m.content FROM Message m")
    fun findDistinctContent(pageable: Pageable?): List<String?>?

    fun findDistinctContent(): List<String?>? {
        return findDistinctContent(PageRequest.of(0, 5))
    }
}