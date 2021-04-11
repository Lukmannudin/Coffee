package com.oleg.coffee.data.api

import com.oleg.coffee.data.BaseRemote
import com.oleg.coffee.data.coffeesource.remote.CoffeeRemote
import retrofit2.Response

/**
 * Crafted by Lukman on 10/04/2021.
 **/
interface ApiHelper {

    suspend fun getCoffees(): Response<BaseRemote<List<CoffeeRemote>>>

    suspend fun getCoffee(coffeeId: Int): Response<BaseRemote<CoffeeRemote>>
}