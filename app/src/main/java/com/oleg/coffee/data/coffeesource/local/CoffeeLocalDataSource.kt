package com.oleg.coffee.data.coffeesource.local

import com.oleg.coffee.data.Coffee
import com.oleg.coffee.data.Result
import com.oleg.coffee.data.coffeesource.CoffeeDataSource
import com.oleg.coffee.data.mapper.toCoffee
import com.oleg.coffee.data.mapper.toCoffeesLocal
import okio.IOException
import javax.inject.Inject

/**
 * Crafted by Lukman on 10/04/2021.
 **/
class CoffeeLocalDataSource @Inject constructor(
    private val coffeeDao: CoffeeDao
) : CoffeeDataSource {
    override suspend fun getCoffees(): Result<List<Coffee>> =
        try {
            val coffeesRemote = coffeeDao.getCoffees()
            Result.Success(coffeesRemote.toCoffee())
        } catch (e: IOException) {
            Result.Error(Exception("Something wrong with local database"))
        }

    override suspend fun getCoffee(coffeeId: Int): Result<Coffee> =
        try {
            val coffeesRemote = coffeeDao.getCoffee(coffeeId)
            Result.Success(coffeesRemote.toCoffee())
        } catch (e: IOException) {
            Result.Error(Exception("Something wrong with local database"))
        }

    fun saveCoffees(coffees: List<Coffee>){
        coffeeDao.saveCoffees(coffees.toCoffeesLocal())
    }
}