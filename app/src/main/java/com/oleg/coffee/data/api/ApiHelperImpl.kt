package com.oleg.coffee.data.api

import com.oleg.coffee.data.BaseRemote
import com.oleg.coffee.data.coffeesource.remote.CoffeeRemote
import retrofit2.Response
import javax.inject.Inject

/**
 * Crafted by Lukman on 10/04/2021.
 **/
class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getCoffees(): Response<BaseRemote<List<CoffeeRemote>>> {
        return apiService.getCoffees()
    }

    override suspend fun getCoffee(coffeeId: Int): Response<BaseRemote<CoffeeRemote>> {
        return apiService.getCoffee(coffeeId)
    }
}