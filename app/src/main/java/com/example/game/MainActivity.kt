package com.example.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.game.game.GameFragment
import com.example.game.home.HomeFragment
import com.example.game.listeners.NavigationListener

class MainActivity : AppCompatActivity(), NavigationListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigateToHomeScreen()
    }

    override fun navigateBack() {
        supportFragmentManager.popBackStack()
    }

    override fun navigateToHomeScreen() {
        replaceFragment(HomeFragment.newInstance(), false)
    }

    override fun navigateToGameScreen() {
        replaceFragment(GameFragment.newInstance())
    }

    private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .apply { if (addToBackStack) addToBackStack(fragment::class.simpleName) }
            .commit()
    }

}