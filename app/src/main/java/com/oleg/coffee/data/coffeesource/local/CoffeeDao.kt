package com.oleg.coffee.data.coffeesource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Crafted by Lukman on 10/04/2021.
 **/
@Dao
interface CoffeeDao {

    @Query("SELECT * FROM coffeelocal")
    fun getCoffees(): List<CoffeeLocal>

    @Query("SELECT * FROM coffeelocal WHERE id = :coffeeId")
    fun getCoffee(coffeeId: Int): CoffeeLocal

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoffee(vararg coffee: CoffeeLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCoffees(coffees: List<CoffeeLocal>)

}