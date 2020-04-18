package com.example.eatanddrink.adapter.meal

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eatanddrink.model.meal.Meal
import com.example.eatanddrink.model.meal.MealX
import com.example.eatdrink.R
import com.example.eatdrink.databinding.MealsListItemBinding

class MealsAdapter (private val clickListener: MealXClickListener): ListAdapter<MealX, MealsAdapter.ViewHolder>(
    MealsDiffUtil()
) {
    var favIds:List<Meal>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=MealsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mealX=getItem(position)
        holder.bind(clickListener,favIds, mealX)
    }

    class ViewHolder(private var binding : MealsListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: MealXClickListener, favIds:List<Meal>?, mealX: MealX){

            binding.mealX=mealX
            binding.mealClickListener=clickListener
            binding.favourite=false
            binding.fav.setImageResource(R.drawable.ic_favorite_border)

            favIds?.forEach {
                if (it.idMeal==mealX.idMeal){
                    binding.fav.setImageResource(R.drawable.ic_favorites)
                }

            }
            binding.executePendingBindings()
        }
    }
}

class MealsDiffUtil: DiffUtil.ItemCallback<MealX>(){
    override fun areItemsTheSame(oldItem: MealX, newItem: MealX): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: MealX, newItem: MealX): Boolean {
        return oldItem.idMeal==newItem.idMeal
    }

}

class MealXClickListener(val clickListener : (mealX: MealX, view : ImageView? )->Unit){
    fun onClick(mealX: MealX, view : ImageView?) =clickListener(mealX ,view)
}