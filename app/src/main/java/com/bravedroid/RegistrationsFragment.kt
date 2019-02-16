package com.bravedroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation


class RegistrationsFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_registrations, container, false)

        inflate.findViewById<Button>(R.id.buttonToGoContact).setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_registrationsFragment_to_contactFragment)
        )
        inflate.findViewById<Button>(R.id.buttonToGoHome).setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_registrationsFragment_to_homeFragment)
        )
        return inflate
    }


}
