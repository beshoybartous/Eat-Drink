package com.example.eatanddrink.adapter.meal

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.eatanddrink.model.meal.Category
import com.example.eatanddrink.ui.meal.MealsPageFragment


class MealsViewPagerAdapter(fragment: Fragment, val category: List<Category>) :
    FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = category.size

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        val fragment = MealsPageFragment()
        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
            putParcelable("object", category[position])
        }
        return fragment
    }


}