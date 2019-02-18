package com.bravedroid

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI


class WelcomeFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_welcome, container, false)

        ////option1
        //inflate.findViewById<Button>(R.id.button_login).setOnClickListener {
        //    //it.findNavController().navigate(R.id.action_welcomeFragment_to_registrationsFragment)
        //    Navigation.findNavController(it).navigate(R.id.action_welcomeFragment_to_registrationsFragment)
        //}

        ////option2
        inflate.findViewById<Button>(R.id.button_go_to_registrations).setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_welcomeFragment_to_registrationsFragment)
        )

        setHasOptionsMenu(true)
        return inflate
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_options_welcome, menu)

        if (null == getShareIntent().resolveActivity(activity!!.packageManager)) {
            // hide the menu item if it doesn't resolve
            menu.findItem(R.id.share)?.setVisible(false)

        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.share -> {
            shareSuccess()
            true
        }
        R.id.contactFragment -> NavigationUI.onNavDestinationSelected(item, view!!.findNavController())
        else -> {
            super.onOptionsItemSelected(item)
        }
    }


     private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    private fun getShareIntent(): Intent {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
                .putExtra(Intent.EXTRA_TEXT, "hello!")
        return shareIntent
    }
}
