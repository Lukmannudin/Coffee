package com.oleg.coffee.coffeelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oleg.coffee.R
import com.oleg.coffee.databinding.FragmentCoffeeListBinding

class CoffeeListFragment : Fragment() {

    private var binding: FragmentCoffeeListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_coffee_list, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}