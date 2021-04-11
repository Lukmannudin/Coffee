package com.oleg.coffee.data.api

import com.oleg.coffee.data.BaseRemote
import com.oleg.coffee.data.coffeesource.remote.CoffeeRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Crafted by Lukman on 09/04/2021.
 **/
interface ApiService {

    @GET("api/v1/coffes")
    suspend fun getCoffees(): Response<BaseRemote<List<CoffeeRemote>>>

    @GET("api/v1/coffe/{id}")
    suspend fun getCoffee(@Path("id") coffeId: Int) : Response<BaseRemote<CoffeeRemote>>
}