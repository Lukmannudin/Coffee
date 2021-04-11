package com.oleg.coffee.coffeelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oleg.coffee.data.Coffee
import com.oleg.coffee.data.Result
import com.oleg.coffee.data.coffeesource.CoffeeRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Crafted by Lukman on 03/04/2021.
 **/
@ExperimentalCoroutinesApi
@HiltViewModel
class CoffeeListViewModel @Inject constructor(
    private val coffeeRepository: CoffeeRepositoryImpl
) : ViewModel() {
    val coffeesState = MutableLiveData<CoffeesState>()

    fun getCoffees() {
        viewModelScope.launch {
            coffeeRepository.getCoffees().collect { coffeeResponse ->
                when (coffeeResponse) {
                    is Result.Loading -> {
                        coffeesState.value = CoffeesState.OnLoading
                    }

                    is Result.Error -> {
                        coffeesState.value = CoffeesState.Failure
                    }

                    is Result.Success -> {
                        coffeesState.value = CoffeesState.Loaded(coffeeResponse.data)
                    }
                }
            }
        }
    }

    sealed class CoffeesState {
        object OnLoading : CoffeesState()
        data class Loaded(val coffees: List<Coffee>) : CoffeesState()
        object Failure : CoffeesState()
    }
}

