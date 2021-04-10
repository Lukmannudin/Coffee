package com.oleg.coffee.data.coffeesource

import com.oleg.coffee.data.Coffee
import kotlinx.coroutines.flow.Flow
import com.oleg.coffee.data.Result

/**
 * Crafted by Lukman on 10/04/2021.
 **/
interface CoffeeRepository {

    suspend fun getCoffees() : Flow<Result<List<Coffee>>>

    suspend fun getCoffee(coffeeId: Int) : Flow<Result<Coffee>>

}