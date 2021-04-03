package com.oleg.coffee.coffeelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.oleg.coffee.databinding.FragmentCoffeeListBinding
import dagger.hilt.android.AndroidEntryPoint

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

    private fun setupObserver() {
        viewmodel.coffeeList.observe(viewLifecycleOwner, { coffees ->
            if (coffees.isNotEmpty()){
                adapter.coffees = coffees
            }
        })
    }

    private fun setupAdapter() {
        adapter = CoffeeListAdapter()
        binding.rvCoffee.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCoffee.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}