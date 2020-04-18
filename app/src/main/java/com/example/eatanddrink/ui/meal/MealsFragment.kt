package com.example.eatanddrink.ui.meal

import android.annotation.SuppressLint
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.eatanddrink.util.ConnectivityReceiver
import com.example.eatanddrink.adapter.meal.MealsViewPagerAdapter
import com.example.eatanddrink.ui.MainActivity
import com.example.eatanddrink.viewmodel.meal.MealViewModel
import com.example.eatdrink.databinding.FragmentMealsBinding
import com.google.android.material.tabs.TabLayoutMediator


/**
 * A simple [Fragment] subclass.
 */
class MealsFragment : Fragment() , ConnectivityReceiver.ConnectivityReceiverListener{
    private val viewModel: MealViewModel by activityViewModels()
    private lateinit var binding : FragmentMealsBinding
    private lateinit var pagerAdapterMeals: MealsViewPagerAdapter
    private lateinit var mainActivity : MainActivity
    val br = ConnectivityReceiver()
    @SuppressLint("ResourceAsColor")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentMealsBinding.inflate(inflater)
        mainActivity= activity as MainActivity


        binding.lifecycleOwner=viewLifecycleOwner
        binding.viewModel=viewModel

        viewModel.categoryList.observe(viewLifecycleOwner, Observer { categories->
            pagerAdapterMeals =
                MealsViewPagerAdapter(
                    this,
                    categories
                )
            binding.pager.adapter = pagerAdapterMeals
            TabLayoutMediator(binding.tabLayout,  binding.pager) { tab, position ->
                tab.text=categories[position].strCategory
            }.attach()

        })

        return binding.root
    }


    private fun showMessage(isConnected: Boolean) {
        if (!isConnected) {
            val messageToUser = "No Connection"
            Toast.makeText(context, messageToUser, Toast.LENGTH_LONG).show()
            if (viewModel._loaded.value == true) {
                viewModel.setLoaded(false)
            }
        }
        else if( viewModel._loaded.value==false && isConnected){
            viewModel.setCategory()
            viewModel.getAllRoomMeals()
            viewModel.setLoaded(true)
        }
    }

    override fun onResume() {
        super.onResume()
        mainActivity.registerReceiver(br,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        br.connectivityReceiverListener=this
        viewModel.categoryList.observe(viewLifecycleOwner, Observer { categories->
            pagerAdapterMeals =
                MealsViewPagerAdapter(
                    this,
                    categories
                )
            binding.pager.adapter = pagerAdapterMeals
            TabLayoutMediator(binding.tabLayout,  binding.pager) { tab, position ->
                tab.text=categories[position].strCategory
            }.attach()

        })

    }

    override fun onPause() {
        super.onPause()
        mainActivity.unregisterReceiver(br)
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showMessage(isConnected)
    }

}
