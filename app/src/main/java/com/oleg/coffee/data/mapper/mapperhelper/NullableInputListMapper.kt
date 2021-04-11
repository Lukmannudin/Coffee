package com.oleg.coffee.data.mapper.mapperhelper

/**
 * Crafted by Lukman on 10/04/2021.
 **/

// Nullable to Non-nullable
interface NullableInputListMapper <I,O>: Mapper<List<I>?, List<O>>

class NullableInputListMapperImpl<I, O>(
    private val mapper: Mapper<I, O>
) : NullableInputListMapper<I, O> {
    override fun map(input: List<I>?): List<O> {
        return input?.map { mapper.map(it) }.orEmpty()
    }
}