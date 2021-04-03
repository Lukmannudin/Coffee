package com.oleg.coffee.data

/**
 * Crafted by Lukman on 21/03/2021.
 **/

data class Coffee(
    val id: Int,
    val name: String,
    val type: String,
    val price: Long,
    val thumbnail: String,
    val description: String,
    val latitude: Long,
    val longitude: Long,
    val address: String,
)