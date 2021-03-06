package com.oleg.coffee.data.mapper.mapperhelper

/**
 * Crafted by Lukman on 10/04/2021.
 **/
// Non-nullable to nullable
interface NullableOutputListMapper<I,O>: Mapper<List<I>, List<O>?>

class NullableOutputListMapperImpl<I, O>(
    private val mapper: Mapper<I, O>
) : NullableOutputListMapper<I, O> {
    override fun map(input: List<I>): List<O>? {
        return if (input.isEmpty()) null else input.map { mapper.map(it) }
    }
}