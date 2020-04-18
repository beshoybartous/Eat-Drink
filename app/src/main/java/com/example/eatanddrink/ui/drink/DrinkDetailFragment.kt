package com.example.eatanddrink.ui.drink

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.eatanddrink.adapter.IngredientsAdapter

import com.example.eatanddrink.viewmodel.drink.DrinkViewModel
import com.example.eatdrink.R
import com.example.eatdrink.databinding.FragmentDrinkDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class DrinkDetailFragment : Fragment() {
    lateinit var binding : FragmentDrinkDetailBinding
    lateinit var adapter :IngredientsAdapter
    val viewModel : DrinkViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentDrinkDetailBinding.inflate(inflater)
        binding.viewModel=viewModel
        binding.lifecycleOwner=this

        binding.toolbar.setNavigationIcon(R.drawable.back4)
        binding.toolbar.setNavigationOnClickListener {
            this.findNavController().navigateUp()
        }
        viewModel.ingredients.observe(viewLifecycleOwner, Observer {
            adapter= IngredientsAdapter(it)
            adapter.notifyDataSetChanged()
            binding.ingredientsRv.adapter=adapter

        })



        val drinkRandom=args.random
        val drinkId= args.id
        val fav=args.favourite
        binding.random=drinkRandom
        binding.favourite=fav

        if(drinkRandom) {
            viewModel.getDrinkUtils(viewModel.randomDrink)
        }
        if(fav){
            viewModel.getRoomDrink(true,drinkId)
        }
        else{
            viewModel.getRoomDrink(false,drinkId)
        }
        viewModel.roomDrink.observe(viewLifecycleOwner, Observer {
            if(it!=null)
                binding.fab.setImageResource(R.drawable.ic_favorites)
            else
                binding.fab.setImageResource(R.drawable.ic_favorite_border)

            binding.fab.setOnClickListener {
                if(binding.fab.drawable!!.constantState== resources.getDrawable(R.drawable.ic_favorite_border).constantState ) {
                    if(drinkRandom) {
                        viewModel.insertDrink(viewModel.randomDrink.value!!)
                        Toast.makeText(context,viewModel.randomDrink.value!!.strDrink+" added to favourite",Toast.LENGTH_SHORT).show()

                    }
                    else {
                        viewModel.insertDrink(viewModel.drink.value!!)
                        Toast.makeText(context,viewModel.drink.value!!.strDrink+" added to favourite",Toast.LENGTH_SHORT).show()

                    }
                    binding.fab.setImageDrawable(resources.getDrawable(R.drawable.ic_favorites))
                }
                else{

                    viewModel.removeDrink(drinkId)
                    binding.fab.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_border))
                    Toast.makeText(context,"Removed from favourite",Toast.LENGTH_SHORT).show()


                }
            }

        })

        return binding.root
    }
    val args: DrinkDetailFragmentArgs by navArgs()

}
