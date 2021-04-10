package com.oleg.coffee.di

import android.content.Context
import androidx.room.Room
import com.oleg.coffee.data.CoffeeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Crafted by Lukman on 10/04/2021.
 **/

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext, CoffeeDatabase::class.java, "coffee.db"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideCoffeeDao(coffeeDatabase: CoffeeDatabase) =
        coffeeDatabase.coffeeDao()
}