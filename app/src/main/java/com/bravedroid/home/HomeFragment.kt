package com.bravedroid.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.bravedroid.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val homeNavHostFragment = childFragmentManager.findFragmentById(R.id.homeFragmentContainer) as NavHostFragment
        // //option1
        // view.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        //         .setupWithNavController(homeNavHostFragment.navController)

        //option2
        NavigationUI.setupWithNavController(view.findViewById<BottomNavigationView>(R.id.bottomNavigationView), homeNavHostFragment.navController)


        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        homeNavHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            toolbar.title = destination.label
        }


        return view
    }


}
