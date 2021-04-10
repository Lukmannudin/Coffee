package com.oleg.coffee.data.coffeesource

import com.oleg.coffee.data.Coffee
import com.oleg.coffee.data.Result
import com.oleg.coffee.data.coffeesource.local.CoffeeLocalDataSource
import com.oleg.coffee.data.coffeesource.remote.CoffeeRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Crafted by Lukman on 10/04/2021.
 **/
class CoffeeRepositoryImpl @Inject constructor(
    private val coffeeRemoteDataSource: CoffeeRemoteDataSource,
    private val coffeeLocalDataSource: CoffeeLocalDataSource
) : CoffeeRepository {
    override suspend fun getCoffees(): Flow<Result<List<Coffee>>> = flow {
        emit(Result.Loading)

        when (val coffeeLocalResult = coffeeLocalDataSource.getCoffees()) {
            is Result.Error -> emit(Result.Error(coffeeLocalResult.exception))
            is Result.Success -> emit(Result.Success(coffeeLocalResult.data))
        }

        emit(Result.Loading)
        when (val coffeesResult = coffeeRemoteDataSource.getCoffees()) {
            is Result.Error -> emit(Result.Error(coffeesResult.exception))
            is Result.Success -> emit(Result.Success(coffeesResult.data))
        }
    }

    override suspend fun getCoffee(coffeeId: Int): Flow<Result<Coffee>> = flow {
        emit(Result.Loading)
        when (val coffeeLocalResult = coffeeLocalDataSource.getCoffee(coffeeId)) {
            is Result.Error -> emit(Result.Error(coffeeLocalResult.exception))
            is Result.Success -> emit(Result.Success(coffeeLocalResult.data))
        }

        emit(Result.Loading)
        when (val coffeeRemoteResult = coffeeRemoteDataSource.getCoffee(coffeeId)) {
            is Result.Error -> emit(Result.Error(coffeeRemoteResult.exception))
            is Result.Success -> emit(Result.Success(coffeeRemoteResult.data))
        }
    }
}
