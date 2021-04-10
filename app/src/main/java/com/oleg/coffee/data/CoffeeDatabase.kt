package com.oleg.coffee.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oleg.coffee.data.coffeesource.local.CoffeeDao
import com.oleg.coffee.data.coffeesource.local.CoffeeLocal

/**
 * Crafted by Lukman on 10/04/2021.
 **/
@Database(entities = [CoffeeLocal::class], version = 1)
abstract class CoffeeDatabase : RoomDatabase() {

    abstract fun coffeeDao(): CoffeeDao

}