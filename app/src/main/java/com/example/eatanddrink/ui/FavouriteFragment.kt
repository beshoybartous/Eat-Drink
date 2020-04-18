package com.example.eatanddrink.ui

import android.content.IntentFilter
import android.net.ConnectivityManager
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
import com.example.eatanddrink.util.ConnectivityReceiver
import com.example.eatanddrink.adapter.drink.FavouriteDrinkClickListener
import com.example.eatanddrink.adapter.drink.FavouriteDrinksAdapter
import com.example.eatanddrink.adapter.meal.FavouriteMealsAdapter
import com.example.eatanddrink.adapter.meal.MealClickListener
import com.example.eatanddrink.util.NO__CONNECTION
import com.example.eatanddrink.util.REMOVED_FROM_FAVOURITE
import com.example.eatanddrink.viewmodel.drink.DrinkViewModel
import com.example.eatanddrink.viewmodel.meal.MealViewModel
import com.example.eatdrink.R
import com.example.eatdrink.databinding.FragmentFavouriteBinding

/**
 * A simple [Fragment] subclass.
 */
class FavouriteFragment : Fragment() , ConnectivityReceiver.ConnectivityReceiverListener {
    private val mealViewModel : MealViewModel by activityViewModels()
    private val drinkViewModel : DrinkViewModel by activityViewModels()
    private lateinit var mealAdapter: FavouriteMealsAdapter
    private lateinit var drinkAdapter: FavouriteDrinksAdapter
    private lateinit var mainActivity : MainActivity

    lateinit var binding: FragmentFavouriteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentFavouriteBinding.inflate(inflater)
        mainActivity= activity as MainActivity

        binding.lifecycleOwner=this
        binding.mealViewModel=mealViewModel
        binding.drinkiewModel=drinkViewModel
        binding.newMealBtn.setOnClickListener {
            mealViewModel.getRandomMeal(true)
        }
        binding.newDrinkBtn.setOnClickListener {
            drinkViewModel.getRandomDrink(true)
        }
        binding.parentViewMeal.setOnClickListener {
            mealViewModel.randomMeal.observe(viewLifecycleOwner, Observer {
                it.let {
                    findNavController().navigate(FavouriteFragmentDirections.actionFavouriteFragmentToMealDetailFragment(it.idMeal,true))

                }
            })
        }
        binding.parentViewDrink.setOnClickListener {
            drinkViewModel.randomDrink.observe(viewLifecycleOwner, Observer {
                it.let {
                    findNavController().navigate(FavouriteFragmentDirections.actionFavouriteFragmentToDrinkDetailFragment(it.idDrink,true))
                }
            })
        }


        mealAdapter= FavouriteMealsAdapter(
            MealClickListener { it, view ->
                if (view?.id == R.id.fav) {
                    mealViewModel.removeMeal(it.idMeal)
                    Toast.makeText(
                        context,
                        it.strMeal + REMOVED_FROM_FAVOURITE,
                        Toast.LENGTH_SHORT
                    ).show()

                } else {

                    findNavController().navigate(
                        FavouriteFragmentDirections.actionFavouriteFragmentToMealDetailFragment(
                            it.idMeal, favourite = true
                        )
                    )
                }
            })
        drinkAdapter=
            FavouriteDrinksAdapter(
                FavouriteDrinkClickListener { it, view ->
                    if (view?.id == R.id.fav) {
                        drinkViewModel.removeDrink(it.idDrink)
                        Toast.makeText(
                            context,
                            it.strDrink + REMOVED_FROM_FAVOURITE,
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {
                        findNavController().navigate(
                            FavouriteFragmentDirections.actionFavouriteFragmentToDrinkDetailFragment(
                                it.idDrink, favourite = true
                            )
                        )
                    }
                })
        binding.mealRv.adapter=mealAdapter
        mealViewModel.getAllRoomMeals()
        mealViewModel.roomAllMeal.observe(viewLifecycleOwner, Observer {
            it.let {
                mealAdapter.submitList(it)
            }
        })
        binding.drinkRv.adapter=drinkAdapter
        drinkViewModel.getAllRoomDrinks()
        drinkViewModel.roomAllDrink.observe(viewLifecycleOwner, Observer {
            it.let {
                drinkAdapter.submitList(it)
            }
        })

        return binding.root
    }
    private fun showMessage(isConnected: Boolean) {
        if (!isConnected) {
            Toast.makeText(context, NO__CONNECTION, Toast.LENGTH_LONG).show()

        }
        else{

            if(  isConnected){
                drinkViewModel.getRandomDrink(false)
            }

            if(  isConnected){
                mealViewModel.getRandomMeal(false)
            }
        }
    }
    val br = ConnectivityReceiver()

    override fun onResume() {
        super.onResume()
        mainActivity.registerReceiver(br,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        br.connectivityReceiverListener = this
    }
    override fun onPause() {
        super.onPause()
        mainActivity.unregisterReceiver(br)
    }
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showMessage(isConnected)
    }
}
