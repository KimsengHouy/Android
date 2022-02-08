package com.stamford.pos22021

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("orders")
    fun getData(): Call<List<MyOrderDataItem>>
}