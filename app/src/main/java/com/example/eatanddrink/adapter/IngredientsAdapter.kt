package com.example.eatanddrink.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eatanddrink.util.IMAGE_TYPE
import com.example.eatanddrink.util.INGREDIENT_IMAGE
import com.example.eatanddrink.util.bindImage
import com.example.eatdrink.databinding.IngredientsListItemBinding

class IngredientsAdapter (private val ingredients:List<Pair<String,String>>): RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= IngredientsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ingredients[position])
    }
    class ViewHolder (private var binding: IngredientsListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(ingredient:Pair<String,String>){
            Log.i("checkRV",ingredient.second)
            if(ingredient.second!=ingredient.first) {
                binding.measuresText.text = ingredient.second + " " + ingredient.first
            }
            else{
                binding.measuresText.text = ingredient.second
            }
            bindImage(binding.ingredientImg,INGREDIENT_IMAGE+ingredient.first+ IMAGE_TYPE)
        }

    }


}


//