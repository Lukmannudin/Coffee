package com.oleg.coffee.data.coffeesource

import com.oleg.coffee.data.Coffee
import com.oleg.coffee.data.Result
import com.oleg.coffee.data.coffeesource.local.CoffeeLocalDataSource
import com.oleg.coffee.data.coffeesource.remote.CoffeeRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Crafted by Lukman on 10/04/2021.
 **/
@ExperimentalCoroutinesApi
class CoffeeRepositoryImpl @Inject constructor(
    private val coffeeRemoteDataSource: CoffeeRemoteDataSource,
    private val coffeeLocalDataSource: CoffeeLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : CoffeeRepository {
    override suspend fun getCoffees(): Flow<Result<List<Coffee>>> = flow {
        getCoffeeFromLocal(coffeeLocalDataSource).collect {
            emit(it)
        }
        getCoffeesAndSaveToLocal(coffeeRemoteDataSource).collect {
            emit(it)
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

    private suspend fun getCoffeesAndSaveToLocal(coffeeRemoteDataSource: CoffeeRemoteDataSource): Flow<Result<List<Coffee>>> {
        return withContext(Dispatchers.Main) {
            flow {
                emit(Result.Loading)
                when (val coffeeRemoteResult = coffeeRemoteDataSource.getCoffees()) {
                    is Result.Error -> emit(Result.Error(coffeeRemoteResult.exception))
                    is Result.Success -> {
                        saveToLocal(coffeeRemoteResult.data)
                        emit(Result.Success(coffeeRemoteResult.data))
                    }
                }
            }.flowOn(ioDispatcher)
        }
    }

    private suspend fun getCoffeeFromLocal(coffeeLocalDataSource: CoffeeLocalDataSource): Flow<Result<List<Coffee>>> {
        return withContext(Dispatchers.Main) {
            flow {
                emit(Result.Loading)
                when (val coffeeLocalResult = coffeeLocalDataSource.getCoffees()) {
                    is Result.Error -> emit(Result.Error(coffeeLocalResult.exception))
                    is Result.Success -> emit(Result.Success(coffeeLocalResult.data))
                }
            }.flowOn(ioDispatcher)
        }
    }

    private suspend fun saveToLocal(coffees: List<Coffee>) {
        withContext(ioDispatcher) {
            coffeeLocalDataSource.saveCoffees(coffees)
        }
    }
}
