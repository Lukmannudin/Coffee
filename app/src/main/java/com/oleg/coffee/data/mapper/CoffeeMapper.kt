package com.oleg.coffee.data.mapper

import com.oleg.coffee.data.Coffee
import com.oleg.coffee.data.coffeesource.local.CoffeeLocal
import com.oleg.coffee.data.coffeesource.remote.CoffeeRemote
import com.oleg.coffee.data.mapper.mapperhelper.*

/**
 * Crafted by Lukman on 10/04/2021.
 **/
private val coffeeLocalToCoffeeMapper: Mapper<CoffeeLocal, Coffee> =
    object : Mapper<CoffeeLocal, Coffee> {
        override fun map(input: CoffeeLocal): Coffee {
            return Coffee(
                input.id,
                input.name ?: "undefined",
                input.type ?: "undefined",
                input.price ?: 0,
                input.thumbnail ?: "",
                input.description ?: "",
                input.latitude ?: 0.0,
                input.longitude ?: 0.0,
                input.address ?: "undefined"
            )
        }
    }

private val coffeeRemoteToCoffeeMapper: Mapper<CoffeeRemote, Coffee> =
    object : Mapper<CoffeeRemote, Coffee> {
        override fun map(input: CoffeeRemote): Coffee {
            return Coffee(
                input.id ?: -1,
                input.name ?: "undefined",
                input.type ?: "undefined",
                input.price ?: 0,
                input.thumbnail ?: "",
                input.description ?: "",
                input.latitude ?: 0.0,
                input.longitude ?: 0.0,
                input.address ?: "undefined"
            )
        }
    }

private val coffeeToCoffeeLocalMapper: Mapper<Coffee, CoffeeLocal> =
    object : Mapper<Coffee, CoffeeLocal> {
        override fun map(input: Coffee): CoffeeLocal {
            return CoffeeLocal(
                input.id,
                input.name,
                input.type,
                input.price,
                input.thumbnail,
                input.description,
                input.latitude,
                input.longitude,
                input.address
            )
        }
    }

private val coffeesToCoffeeLocal: ListMapper<Coffee, CoffeeLocal> =
    object : ListMapper<Coffee, CoffeeLocal> {
        override fun map(input: List<Coffee>): List<CoffeeLocal> {
            return ListMapperImpl(coffeeToCoffeeLocalMapper).map(input)
        }
    }


private val coffeesRemoteToCoffeesMapper: NullableInputListMapper<CoffeeRemote, Coffee> =
    object : NullableInputListMapper<CoffeeRemote, Coffee> {
        override fun map(input: List<CoffeeRemote>?): List<Coffee> {
            return NullableInputListMapperImpl(coffeeRemoteToCoffeeMapper)
                .map(input)
        }
    }

private val coffeesLocalToCoffeesMapper: NullableInputListMapper<CoffeeLocal, Coffee> =
    object : NullableInputListMapper<CoffeeLocal, Coffee> {
        override fun map(input: List<CoffeeLocal>?): List<Coffee> {
            return NullableInputListMapperImpl(coffeeLocalToCoffeeMapper)
                .map(input)
        }
    }

fun List<CoffeeRemote>?.toCoffees(): List<Coffee> {
    return coffeesRemoteToCoffeesMapper.map(this)
}

fun CoffeeRemote.toCoffee(): Coffee {
    return coffeeRemoteToCoffeeMapper.map(this)
}

fun List<CoffeeLocal>.toCoffee(): List<Coffee> {
    return coffeesLocalToCoffeesMapper.map(this)
}

fun CoffeeLocal.toCoffee(): Coffee {
    return coffeeLocalToCoffeeMapper.map(this)
}

fun List<Coffee>.toCoffeesLocal(): List<CoffeeLocal> {
    return coffeesToCoffeeLocal.map(this)
}