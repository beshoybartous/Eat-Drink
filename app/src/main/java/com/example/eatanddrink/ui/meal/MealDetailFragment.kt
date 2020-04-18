package com.example.eatanddrink.ui.meal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.eatanddrink.adapter.IngredientsAdapter
import com.example.eatanddrink.ui.MainActivity
import com.example.eatanddrink.util.ADDED_TO_FAVOURITE
import com.example.eatanddrink.util.REMOVED_FROM_FAVOURITE
import com.example.eatanddrink.util.YOUTUBE_API_KEY
import com.example.eatanddrink.viewmodel.meal.MealViewModel
import com.example.eatdrink.R
import com.example.eatdrink.databinding.FragmentMealDetailBinding
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment


/**
 * A simple [Fragment] subclass.
 */
class MealDetailFragment : Fragment() {
    lateinit var binding : FragmentMealDetailBinding
    lateinit var adapter :IngredientsAdapter
    lateinit var mainActivity : MainActivity
    val viewModel : MealViewModel by activityViewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding=FragmentMealDetailBinding.inflate(inflater)
        binding.viewModel=viewModel
        binding.lifecycleOwner=this


        binding.toolbar.setNavigationIcon(R.drawable.back4)
        binding.toolbar.setNavigationOnClickListener {
            this.findNavController().navigateUp()
        }

        viewModel.ingredients.observe(viewLifecycleOwner, Observer {
            adapter=IngredientsAdapter(it)
            adapter.notifyDataSetChanged()
            binding.ingredientsRv.adapter=adapter

        })

        val youTubePlayerFragment = YouTubePlayerSupportFragment()
        mainActivity= activity as MainActivity
        val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
        transaction.add(R.id.youtube_viewer, youTubePlayerFragment).commit()
        viewModel.youtubeCode.observe(viewLifecycleOwner, Observer { code->
            code?.let {
                Log.i("youtubeCode",code)
                binding.youtubeViewer.visibility=View.VISIBLE
                youTubePlayerFragment.initialize(YOUTUBE_API_KEY,object : YouTubePlayer.OnInitializedListener{
                    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean
                    ) {
                        p1?.cueVideo(code)
                        p1?.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
                        p1?.setOnFullscreenListener {
                            mainActivity.youTubePlayer = p1
                            mainActivity.isYouTubePlayerFullScreen=it
                        }
                    }
                    override fun onInitializationFailure(
                        p0: YouTubePlayer.Provider?,
                        p1: YouTubeInitializationResult?
                    ) {
                        Toast.makeText(context,"Something Wrong",Toast.LENGTH_SHORT).show()
                    }
                })
            }

        })

        val mealRandom=args.random
        val mealId= args.id
        val fav=args.favourite
        binding.random=mealRandom
        binding.favourite=fav
        if(mealRandom) {
            viewModel.getMealUtils(viewModel.randomMeal)
        }
        if(fav){
            viewModel.getRoomMeal(true,mealId)
        }
        else{
            viewModel.getRoomMeal(false,mealId)

        }
            viewModel.roomMeal.observe(viewLifecycleOwner, Observer {

                if(it!=null)
                    binding.fab.setImageResource(R.drawable.ic_favorites)
                else
                    binding.fab.setImageResource(R.drawable.ic_favorite_border)
            })
            binding.fab.setOnClickListener {
                if(binding.fab.drawable!!.constantState== resources.getDrawable(R.drawable.ic_favorite_border).constantState ) {
                    if(mealRandom){
                        viewModel.insertMeal(viewModel.randomMeal.value!!)
                        Toast.makeText(context,viewModel.randomMeal.value!!.strMeal+ ADDED_TO_FAVOURITE,Toast.LENGTH_SHORT).show()

                    }
                    else {
                        viewModel.insertMeal(viewModel.meal.value!!)
                        Toast.makeText(context,viewModel.meal.value!!.strMeal+ ADDED_TO_FAVOURITE,Toast.LENGTH_SHORT).show()

                    }
                    binding.fab.setImageDrawable(resources.getDrawable(R.drawable.ic_favorites))
                }
                else{
                    viewModel.removeMeal(mealId)
                    binding.fab.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_border))
                    Toast.makeText(context, REMOVED_FROM_FAVOURITE,Toast.LENGTH_SHORT).show()

                }
            }


        return binding.root
    }
    private val args: MealDetailFragmentArgs by navArgs()

    override fun onDetach() {
        super.onDetach()
        viewModel.removeYotube()
    }
}
