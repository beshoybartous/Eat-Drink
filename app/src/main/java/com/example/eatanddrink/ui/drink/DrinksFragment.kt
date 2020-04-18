package com.example.eatanddrink.ui.drink

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.eatanddrink.util.ConnectivityReceiver
import com.example.eatanddrink.adapter.drink.DrinksViewPagerAdapter
import com.example.eatanddrink.ui.MainActivity
import com.example.eatanddrink.util.NO__CONNECTION
import com.example.eatanddrink.viewmodel.drink.DrinkViewModel

import com.example.eatdrink.databinding.FragmentDrinksBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * A simple [Fragment] subclass.
 */
class DrinksFragment : Fragment() , ConnectivityReceiver.ConnectivityReceiverListener {
    private val viewModel: DrinkViewModel by activityViewModels()
    private lateinit var binding : FragmentDrinksBinding
    private lateinit var pagerAdapterMeals: DrinksViewPagerAdapter
    private lateinit var mainActivity : MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDrinksBinding.inflate(inflater)
        mainActivity= activity as MainActivity


        binding.lifecycleOwner=viewLifecycleOwner
        binding.viewModel=viewModel

        return binding.root

    }
    private fun showMessage(isConnected: Boolean) {
            if (!isConnected) {

                Toast.makeText(context, NO__CONNECTION, Toast.LENGTH_LONG).show()
                if (viewModel._loaded.value == true) {
                    viewModel.setLoaded(false)
                }
            }
            else if( viewModel._loaded.value==false && isConnected){
                viewModel.setCategory()
                viewModel.getAllRoomDrinks()
                viewModel.setLoaded(true)
            }
        }
    val br = ConnectivityReceiver()

    override fun onResume() {
        super.onResume()
        mainActivity.registerReceiver(br,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        br.connectivityReceiverListener = this
        viewModel.categoryList.observe(viewLifecycleOwner, Observer { categories ->
            pagerAdapterMeals =
                DrinksViewPagerAdapter(
                    this,
                    categories
                )
            binding.pager.adapter = pagerAdapterMeals
            TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
                tab.text = categories[position].strCategory
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
