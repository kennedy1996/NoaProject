package com.noaproject.repository

import com.noaproject.api.service.NoaWebService
import com.noaproject.entity.NoaDtos

class NoaRepository  {

    private val webClient = NoaWebService()

    suspend fun convertMoney(): List<NoaDtos.TablesNew> {
        return webClient.getTable()
    }
}