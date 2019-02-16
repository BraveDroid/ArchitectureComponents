package com.bravedroid


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation


class ContactFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_contact, container, false)

        inflate.findViewById<Button>(R.id.buttonToGoHome).setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_contactFragment_to_homeFragment)
        )
        return inflate
    }


}
