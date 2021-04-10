package com.oleg.coffee.data.coffeesource

import com.oleg.coffee.data.Coffee
import com.oleg.coffee.data.Result


/**
 * Crafted by Lukman on 10/04/2021.
 **/
interface CoffeeDataSource {

    suspend fun getCoffees() : Result<List<Coffee>>

    suspend fun getCoffee(coffeeId: Int) : Result<Coffee>
}