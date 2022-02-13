package com.stamford.pos22021

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.stamford.pos22021.models.Property
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://10.0.2.2/ITE343/pos_api/public/"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).build()

interface ApiService {

    @GET("orders")
    fun getAllOrder(): Call<List<Property>>

}

object Api {
val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}