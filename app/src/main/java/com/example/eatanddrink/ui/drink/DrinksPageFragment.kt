package com.example.eatanddrink.ui.drink

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
import com.example.eatanddrink.adapter.drink.DrinkClickListener
import com.example.eatanddrink.adapter.drink.DrinksAdapter
import com.example.eatanddrink.model.drink.DrinkCategoryX
import com.example.eatanddrink.util.ADDED_TO_FAVOURITE
import com.example.eatanddrink.util.REMOVED_FROM_FAVOURITE
import com.example.eatanddrink.viewmodel.drink.DrinkViewModel

import com.example.eatdrink.R
import com.example.eatdrink.databinding.FragmentDrinksPageBinding

/**
 * A simple [Fragment] subclass.
 */
class DrinksPageFragment : Fragment() {
    private lateinit var binding : FragmentDrinksPageBinding
    private val viewModel: DrinkViewModel by activityViewModels()
    private lateinit var adapter: DrinksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDrinksPageBinding.inflate(inflater)


        binding.lifecycleOwner=viewLifecycleOwner


        binding.viewModel=viewModel

        adapter= DrinksAdapter(
            DrinkClickListener() { it, view ->
                if (view?.id == R.id.fav) {
                    if (view.drawable!!.constantState == resources.getDrawable(R.drawable.ic_favorite_border).constantState) {

                        viewModel.getDrink(true, it.idDrink)
                        view.setImageDrawable(resources.getDrawable(R.drawable.ic_favorites))
                        Toast.makeText(
                            context,
                            it.strDrink + ADDED_TO_FAVOURITE,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {

                        viewModel.removeDrink(it.idDrink)
                        view.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_border))
                        Toast.makeText(
                            context,
                            it.strDrink + REMOVED_FROM_FAVOURITE,
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                } else {
                    viewModel.getDrink(false, it.idDrink)
                    findNavController().navigate(
                        DrinksFragmentDirections.actionDrinksFragmentToDrinkDetailFragment(
                            it.idDrink
                        )
                    )
                }
            })

        binding.CategoryRv.adapter=adapter
        viewModel.drinksX.observe(viewLifecycleOwner, Observer {
            it.let {
                adapter.submitList(it)
            }
        })

        return binding.root    }
    override fun onResume() {
        super.onResume()

        val category=arguments?.getParcelable<DrinkCategoryX>("object")!!
        viewModel.setDrinkList(category.strCategory)
        viewModel.getAllRoomDrinks()
        viewModel.roomAllDrink.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.favIds=it
                adapter.notifyDataSetChanged()
            }
        })
    }





}
