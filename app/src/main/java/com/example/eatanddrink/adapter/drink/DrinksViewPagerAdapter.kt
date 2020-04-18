package com.example.eatanddrink.adapter.drink

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.eatanddrink.model.drink.DrinkCategoryX
import com.example.eatanddrink.ui.drink.DrinksPageFragment

class DrinksViewPagerAdapter (fragment: Fragment, val category: List<DrinkCategoryX>) :
    FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = category.size

    override fun createFragment(position: Int): Fragment {
        val fragment = DrinksPageFragment()
        fragment.arguments = Bundle().apply {
            putParcelable("object", category[position])
        }
        return fragment
    }


}