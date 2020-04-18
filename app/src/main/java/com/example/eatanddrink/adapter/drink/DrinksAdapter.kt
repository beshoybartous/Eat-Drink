package com.example.eatanddrink.adapter.drink

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eatanddrink.model.drink.DrinkDataX
import com.example.eatanddrink.model.drink.DrinksX
import com.example.eatdrink.R
import com.example.eatdrink.databinding.DrinksListItemBinding

class DrinksAdapter (private val clickListener: DrinkClickListener): ListAdapter<DrinksX, DrinksAdapter.ViewHolder>(
    DrinksDiffUtil()
) {
    var favIds:List<DrinkDataX>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= DrinksListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val drinkX=getItem(position)
        holder.bind(clickListener,favIds, drinkX)
    }

    class ViewHolder(private var binding : DrinksListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: DrinkClickListener, favIds:List<DrinkDataX>?, drinksX: DrinksX){

            binding.drinksX=drinksX
            binding.clickListener=clickListener
            binding.favourite=false
            binding.fav.setImageResource(R.drawable.ic_favorite_border)

            favIds?.forEach {
                if (it.idDrink==drinksX.idDrink){
                    binding.fav.setImageResource(R.drawable.ic_favorites)
                }

            }
            binding.executePendingBindings()
        }
    }
}

class DrinksDiffUtil: DiffUtil.ItemCallback<DrinksX>(){
    override fun areItemsTheSame(oldItem: DrinksX, newItem: DrinksX): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: DrinksX, newItem: DrinksX): Boolean {
        return oldItem.idDrink==newItem.idDrink
    }

}

class DrinkClickListener(val clickListener : (drinkX: DrinksX, view : ImageView? )->Unit){
    fun onClick(drinkX: DrinksX, view : ImageView?) =clickListener(drinkX ,view)
}