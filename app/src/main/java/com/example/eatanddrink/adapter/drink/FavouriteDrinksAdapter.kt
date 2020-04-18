package com.example.eatanddrink.adapter.drink

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eatanddrink.model.drink.DrinkDataX
import com.example.eatdrink.R
import com.example.eatdrink.databinding.DrinksListItemBinding

class FavouriteDrinksAdapter (private val clickListener: FavouriteDrinkClickListener): ListAdapter<DrinkDataX, FavouriteDrinksAdapter.ViewHolder>(
    FavouriteDrinksDiffUtil()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= DrinksListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val drink=getItem(position)
        holder.bind(clickListener, drink)
    }

    class ViewHolder(private var binding : DrinksListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: FavouriteDrinkClickListener, drink: DrinkDataX){
            binding.favourite=true
            binding.drink=drink
            binding.favDrinkClickListener=clickListener
            binding.fav.setImageResource(R.drawable.ic_favorites)
            binding.executePendingBindings()
        }
    }
}

class FavouriteDrinksDiffUtil: DiffUtil.ItemCallback<DrinkDataX>(){
    override fun areItemsTheSame(oldItem: DrinkDataX, newItem: DrinkDataX): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: DrinkDataX, newItem: DrinkDataX): Boolean {
        return oldItem.idDrink==newItem.idDrink
    }

}

class FavouriteDrinkClickListener(val clickListener : (drink: DrinkDataX, view : ImageView? )->Unit){
    fun onClick(drink: DrinkDataX, view : ImageView?) =clickListener(drink ,view)
}