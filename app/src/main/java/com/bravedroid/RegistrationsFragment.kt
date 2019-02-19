package com.bravedroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController


class RegistrationsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var i = 0
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_registrations, container, false)

        inflate.findViewById<Button>(R.id.buttonToGoContact).setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_registrationsFragment_to_contactFragment)
        )

        val buttonToGoExit = inflate.findViewById<Button>(R.id.buttonToGoExit)
        buttonToGoExit.setOnClickListener {
            if (i > 2) {
                ////navigate without args
                //findNavController().navigate(R.id.action_registrationsFragment_to_exitFragment)
                navigateAndPassArgs()
            }
            Toast.makeText(context, "OK more click again", Toast.LENGTH_SHORT).show()
            i++
        }
        inflate.findViewById<CheckBox>(R.id.checkbox).setOnClickListener {
            buttonToGoExit.isEnabled = (it as CheckBox).isChecked
        }


        inflate.findViewById<Button>(R.id.buttonToGoHome).setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_registrationsFragment_to_homeFragment)
        )
        return inflate
    }

    private fun navigateAndPassArgs() {
        val direction = RegistrationsFragmentDirections.actionRegistrationsFragmentToExitFragment(88)
        activity?.findNavController(R.id.mainFragmentContainer)?.navigate(direction)
    }

}
