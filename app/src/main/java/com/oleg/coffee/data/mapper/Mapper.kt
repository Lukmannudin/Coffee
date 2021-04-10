package com.oleg.coffee.data.mapper

/**
 * Crafted by Lukman on 10/04/2021.
 **/
interface Mapper<I, O> {
    fun map(input: I): O
}