package com.oleg.coffee.coffeelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.oleg.coffee.R
import com.oleg.coffee.databinding.FragmentCoffeeListBinding
import com.oleg.coffee.helper.gone
import com.oleg.coffee.helper.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CoffeeListFragment : Fragment() {

    private var _binding: FragmentCoffeeListBinding? = null
    private val binding: FragmentCoffeeListBinding get() = _binding!!

    private lateinit var adapter: CoffeeListAdapter

    private val viewmodel: CoffeeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoffeeListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupObserver()
    }

    override fun onResume() {
        super.onResume()
        viewmodel.getCoffees()
    }

    private fun setupObserver() {
        viewmodel.coffeesState.observe(viewLifecycleOwner, { coffeesState ->
            when (coffeesState) {
                is CoffeeListViewModel.CoffeesState.Loaded -> {
                    onErrorStateView(false)
                    onLoadingStateView(false)

                    if (coffeesState.coffees.isNotEmpty()) {
                        onEmptyStateView(false)
                        adapter.coffees = coffeesState.coffees
                    } else {
                        onEmptyStateView(true)
                    }
                }

                is CoffeeListViewModel.CoffeesState.Failure -> {
                    onLoadingStateView(false)
                    onErrorStateView(true)
                }

                is CoffeeListViewModel.CoffeesState.OnLoading ->
                {
                    onLoadingStateView(true)
                }
            }
        })
    }

    private fun setupAdapter() {
        adapter = CoffeeListAdapter()
        with(binding) {
            rvCoffee.adapter = adapter
        }
    }

    private fun onErrorStateView(status: Boolean){
        with(binding.stateView) {
            if (status) {
                root.visible()
                lavLoadingState.setAnimation(R.raw.lottie_error_state)
                lavLoadingState.playAnimation()
                tvStateDescription.text = getString(R.string.error_state_description)
            } else {
                root.gone()
            }
        }
    }

    private fun onLoadingStateView(status: Boolean) {
        with(binding.stateView) {
            if (status) {
                root.visible()
                lavLoadingState.setAnimation(R.raw.lottie_loading_coffee)
                lavLoadingState.playAnimation()
                tvStateDescription.text = getString(R.string.loading_description)
            } else {
                root.gone()
            }
        }
    }

    private fun onEmptyStateView(status: Boolean) {
        with(binding.stateView) {
            if (status) {
                root.visible()
                lavLoadingState.setAnimation(R.raw.lottie_empty_state)
                lavLoadingState.playAnimation()
                tvStateDescription.text = getString(R.string.empty_state_description)
            } else {
                root.gone()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}