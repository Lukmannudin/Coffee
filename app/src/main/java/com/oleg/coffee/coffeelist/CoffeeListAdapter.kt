package com.oleg.coffee.coffeelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oleg.coffee.data.Coffee
import com.oleg.coffee.databinding.ItemCoffeeBinding

/**
 * Crafted by Lukman on 03/04/2021.
 **/
class CoffeeListAdapter : RecyclerView.Adapter<CoffeeListAdapter.ViewHolder>() {

    var coffee: List<Coffee> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = ItemCoffeeBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return coffee.size
    }

    class ViewHolder constructor(val itemView: View) :
        RecyclerView.ViewHolder(itemView)
}