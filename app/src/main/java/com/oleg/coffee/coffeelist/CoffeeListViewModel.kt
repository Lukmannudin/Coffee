package com.oleg.coffee.coffeelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oleg.coffee.data.Coffee
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * Crafted by Lukman on 03/04/2021.
 **/
@HiltViewModel
class CoffeeListViewModel @Inject constructor(): ViewModel() {
    val coffeeList  = MutableLiveData<List<Coffee>>()

    init {
        val coffee = mutableListOf<Coffee>()
//        for (i in 0 until 10){
//            coffee.add(Coffee(
//                i,"Americano","Coffee",1*1000, "thumbnail","description", 1L,1L, "address"
//            ))
//        }
//        coffeeList.value = coffee
    }
}