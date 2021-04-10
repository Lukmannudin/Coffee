package com.oleg.coffee.coffeelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.oleg.coffee.MainActivity
import com.oleg.coffee.databinding.FragmentCoffeeListBinding
import com.oleg.coffee.helper.RecyclerViewVerticalItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoffeeListFragment : Fragment() {

    private var _binding: FragmentCoffeeListBinding? = null
    private val binding: FragmentCoffeeListBinding get() = _binding!!

    private lateinit var adapter: CoffeeListAdapter

    private val viewmodel: CoffeeListViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
            when (coffeesState){
                is CoffeeListViewModel.CoffeesState.Loaded -> {
                    adapter.coffees = coffeesState.coffees
                }

                is CoffeeListViewModel.CoffeesState.Failure -> {
                    Toast.makeText(requireContext(), "Something wrong from our Server, but our engineers are looking for the cause", Toast.LENGTH_SHORT)
                        .show()
                }

                is CoffeeListViewModel.CoffeesState.OnLoading -> {

                }
            }
        })
    }

    private fun setupAdapter() {
        adapter = CoffeeListAdapter()
        with(binding){
            rvCoffee.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}