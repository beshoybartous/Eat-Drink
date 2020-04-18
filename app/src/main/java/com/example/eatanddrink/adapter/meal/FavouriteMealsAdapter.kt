package com.example.eatanddrink.adapter.meal

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eatanddrink.model.meal.Meal
import com.example.eatdrink.R
import com.example.eatdrink.databinding.MealsListItemBinding

class FavouriteMealsAdapter (private val clickListener: MealClickListener): ListAdapter<Meal, FavouriteMealsAdapter.ViewHolder>(
    MealDiffUtil()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=MealsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal=getItem(position)
        holder.bind(clickListener, meal)
    }

    class ViewHolder(private var binding : MealsListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: MealClickListener, meal: Meal){
            binding.favourite=true
            binding.meal=meal
            binding.favMealClickListener=clickListener
            binding.fav.setImageResource(R.drawable.ic_favorites)
            binding.executePendingBindings()
        }
    }
}

class MealDiffUtil: DiffUtil.ItemCallback<Meal>(){
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem.idMeal==newItem.idMeal
    }

}

class MealClickListener(val clickListener : (meal: Meal, view : ImageView? )->Unit){
    fun onClick(meal: Meal, view : ImageView?) =clickListener(meal ,view)
}