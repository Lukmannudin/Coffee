package com.oleg.coffee.data


import com.google.gson.annotations.SerializedName

data class CoffeeRemote(
    @SerializedName("name")
    val name: String? = "",

    @SerializedName("address")
    val address: String? = "",

    @SerializedName("description")
    val description: String? = "",

    @SerializedName("id")
    val id: Int? = 0,

    @SerializedName("latitude")
    val latitude: Double? = 0.0,

    @SerializedName("longtitude")
    val longitude: Double? = 0.0,

    @SerializedName("price")
    val price: Long? = 0,

    @SerializedName("thumbnail")
    val thumbnail: String? = "",

    @SerializedName("type")
    val type: String? = ""
)