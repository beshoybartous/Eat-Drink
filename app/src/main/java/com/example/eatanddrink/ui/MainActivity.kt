package com.example.eatanddrink.ui

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import com.example.eatdrink.R
import com.example.eatdrink.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.android.youtube.player.YouTubePlayer


class MainActivity : AppCompatActivity() {
   lateinit var binding : ActivityMainBinding
    var youTubePlayer : YouTubePlayer?=null
    var isYouTubePlayerFullScreen = false
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
      binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
        val navController =findNavController(R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(binding.bottomNav,navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id==R.id.drinksFragment
                ||destination.id==R.id.mealsFragment
                ||destination.id==R.id.favouriteFragment){
                binding.bottomNav.visibility=View.VISIBLE
            }
            else{
                binding.bottomNav.visibility=View.GONE
            }
        }
        binding.bottomNav.setOnNavigationItemReselectedListener {
            // Do nothing to ignore the reselection
        }

    }

/*
*
* for status bar
*
* */
    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    override fun onBackPressed() {
        if (youTubePlayer != null && isYouTubePlayerFullScreen) {
            youTubePlayer!!.setFullscreen(false)
        } else {
            super.onBackPressed()
        }
    }
}
