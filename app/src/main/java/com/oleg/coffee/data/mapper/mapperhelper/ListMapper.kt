package com.oleg.coffee.data.mapper.mapperhelper

/**
 * Crafted by Lukman on 10/04/2021.
 **/
interface ListMapper<I, O>: Mapper<List<I>, List<O>>

class ListMapperImpl<I, O>(
    private val mapper: Mapper<I, O>
) : ListMapper<I, O> {
    override fun map(input: List<I>): List<O> {
        return input.map { mapper.map(it) }
    }
}