package com.noaproject.api.service

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class InicializatorRetrofit {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.newsoveraudio.com/v1/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val noaApi = retrofit.create(NoaApi::class.java)
}