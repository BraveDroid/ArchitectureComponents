package com.bravedroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController


class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_welcome, container, false)

       //inflate.findViewById<Button>(R.id.button_login).setOnClickListener {
       //    //it.findNavController().navigate(R.id.action_welcomeFragment_to_registrationsFragment)
       //    Navigation.findNavController(it).navigate(R.id.action_welcomeFragment_to_registrationsFragment)
       //}

        inflate.findViewById<Button>(R.id.button_go_to_registrations).setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_welcomeFragment_to_registrationsFragment)
        )

        return inflate
    }

}
