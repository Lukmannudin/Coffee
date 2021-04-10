package com.oleg.coffee.di

import com.oleg.coffee.data.coffeesource.CoffeeRepository
import com.oleg.coffee.data.coffeesource.CoffeeRepositoryImpl
import com.oleg.coffee.data.coffeesource.local.CoffeeLocalDataSource
import com.oleg.coffee.data.coffeesource.remote.CoffeeRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Crafted by Lukman on 10/04/2021.
 **/
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCoffeeRemoteDataSource(
        coffeeRemoteDataSource: CoffeeRemoteDataSource,
        coffeeLocalDataSource: CoffeeLocalDataSource
    ): CoffeeRepository = CoffeeRepositoryImpl(coffeeRemoteDataSource, coffeeLocalDataSource)


}
