package com.example.eatanddrink.ui.meal

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.eatanddrink.adapter.meal.MealXClickListener
import com.example.eatanddrink.adapter.meal.MealsAdapter
import com.example.eatanddrink.model.meal.Category
import com.example.eatanddrink.util.ADDED_TO_FAVOURITE
import com.example.eatanddrink.util.REMOVED_FROM_FAVOURITE
import com.example.eatanddrink.viewmodel.meal.MealViewModel
import com.example.eatdrink.R
import com.example.eatdrink.databinding.FragmentMealsPageBinding

/**
 * A simple [Fragment] subclass.
 */
class MealsPageFragment : Fragment() {
   private lateinit var binding : FragmentMealsPageBinding
    private val modelMeal: MealViewModel by activityViewModels()
    private lateinit var adapter: MealsAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentMealsPageBinding.inflate(inflater)
        binding.lifecycleOwner=viewLifecycleOwner
        binding.viewModel=modelMeal

         adapter=
             MealsAdapter(MealXClickListener { it, view ->
                 if (view?.id == R.id.fav) {
                     if (view.drawable!!.constantState == resources.getDrawable(R.drawable.ic_favorite_border).constantState) {

                         modelMeal.getMeal(true, it.idMeal)
                         view.setImageDrawable(resources.getDrawable(R.drawable.ic_favorites))
                         Toast.makeText(context, it.strMeal + ADDED_TO_FAVOURITE, Toast.LENGTH_SHORT).show()

                     } else {

                         modelMeal.removeMeal(it.idMeal)
                         view.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_border))
                         Toast.makeText(context, it.strMeal + REMOVED_FROM_FAVOURITE, Toast.LENGTH_SHORT).show()
                     }
                 } else {
                     modelMeal.getMeal(false, it.idMeal)
                     findNavController().navigate(
                         MealsFragmentDirections.actionMealsFragmentToMealDetailFragment(
                             it.idMeal
                         )
                     )
                 }
             })

        binding.CategoryRv.adapter=adapter
        modelMeal.mealX.observe(viewLifecycleOwner, Observer {
            it.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
            val category=arguments?.getParcelable<Category>("object")!!
            modelMeal.setMealList(category.strCategory)
            modelMeal.getAllRoomMeals()
            modelMeal.roomAllMeal.observe(viewLifecycleOwner, Observer {
                it?.let {
                    adapter.favIds=it
                    adapter.notifyDataSetChanged()
                }

        })
    }
}