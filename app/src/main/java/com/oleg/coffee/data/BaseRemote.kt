package com.oleg.coffee.data


import com.google.gson.annotations.SerializedName

data class BaseRemote<T>(
    @SerializedName("data")
    var `data`: T? = null,
    @SerializedName("message")
    val message: String = "",
    @SerializedName("status")
    val status: Int = 0
)