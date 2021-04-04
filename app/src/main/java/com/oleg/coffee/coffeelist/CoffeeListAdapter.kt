package com.oleg.coffee.coffeelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oleg.coffee.data.Coffee
import com.oleg.coffee.databinding.ItemCoffeeBinding
import com.oleg.coffee.helper.setImage

/**
 * Crafted by Lukman on 03/04/2021.
 **/
class CoffeeListAdapter : RecyclerView.Adapter<CoffeeListAdapter.ViewHolder>() {

    var coffees: List<Coffee> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = ItemCoffeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(coffees[position])
    }

    override fun getItemCount(): Int {
        return coffees.size
    }

    class ViewHolder constructor(
        private val binding: ItemCoffeeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(coffee: Coffee) {
            binding.coffeeName.text = coffee.name
            binding.coffeeType.text = coffee.type

            binding.coffeeThumbnail.setImage(coffee.thumbnail)
        }
    }
}