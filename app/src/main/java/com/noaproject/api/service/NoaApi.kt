package com.noaproject.api.service

import com.noaproject.api.entity.NoaApiDtos
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface NoaApi {

    @Headers(
        "Authorization: Basic ODY2NzM4OTkzZTA4ZjA5OGVjYzQ5ZDVlOTk2YWEzM2M3OWFlMmQ0OWJlNzc4ODY2MmYxZjhlYWZmMWEyNTg4MmNkMGMxN2U1OGU2ZWEzMWU6ODY2NzM4OTkzZTA4ZjA5OGVjYzQ5ZDVlOTk2YWEzM2M3OWFlMmQ0OWJlNzc4ODY2MmYxZjhlYWZmMWEyNTg4MmNkMGMxN2U1OGU2ZWEzMWU="
    )
    @GET("feed/tabs")
    suspend fun getTables(): NoaApiDtos.TablesApi


    @Headers(
        "Authorization: Basic ODY2NzM4OTkzZTA4ZjA5OGVjYzQ5ZDVlOTk2YWEzM2M3OWFlMmQ0OWJlNzc4ODY2MmYxZjhlYWZmMWEyNTg4MmNkMGMxN2U1OGU2ZWEzMWU6ODY2NzM4OTkzZTA4ZjA5OGVjYzQ5ZDVlOTk2YWEzM2M3OWFlMmQ0OWJlNzc4ODY2MmYxZjhlYWZmMWEyNTg4MmNkMGMxN2U1OGU2ZWEzMWU="
    )
    @GET("feed/section/{url}?limit=20&offset=0")
    suspend fun getTablesDetails(@Path("url") url: String): NoaApiDtos.TablesDetailsApi

}