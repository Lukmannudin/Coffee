package com.oleg.coffee.di

import com.oleg.coffee.coffeelist.CoffeeListAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Crafted by Lukman on 11/04/2021.
 **/
@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Provides
    fun provideCoffeeListAdapter(): CoffeeListAdapter = CoffeeListAdapter()
}