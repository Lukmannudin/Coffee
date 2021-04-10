package com.oleg.coffee.data.coffeesource.remote

import com.oleg.coffee.data.Coffee
import com.oleg.coffee.data.Result
import com.oleg.coffee.data.api.ApiHelper
import com.oleg.coffee.data.coffeesource.CoffeeDataSource
import com.oleg.coffee.data.mapper.toCoffee
import com.oleg.coffee.data.mapper.toCoffees
import javax.inject.Inject

/**
 * Crafted by Lukman on 10/04/2021.
 **/
class CoffeeRemoteDataSource @Inject constructor(private val apiHelper: ApiHelper) :
    CoffeeDataSource {
    override suspend fun getCoffees(): Result<List<Coffee>> {
        val coffeeRequest = apiHelper.getCoffees()
        return if (coffeeRequest.isSuccessful) {
            Result.Success(coffeeRequest.body()?.data.toCoffees())
        } else {
            Result.Error(Exception(coffeeRequest.body()?.message))
        }
    }

    override suspend fun getCoffee(coffeeId: Int): Result<Coffee> {
        val coffeeRequest = apiHelper.getCoffee(coffeeId)
        return if (coffeeRequest.isSuccessful) {
            val coffeeRemote = coffeeRequest.body()?.data
            if (coffeeRemote != null) {
                Result.Success(coffeeRequest.body()?.data!!.toCoffee())
            } else {
                Result.Error(Exception("Coffee Data not found"))
            }
        } else {
            Result.Error(Exception(coffeeRequest.body()?.message))
        }
    }


}