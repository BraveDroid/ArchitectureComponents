package com.bravedroid

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.navigation.ui.NavigationUI


class WelcomeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_welcome, container, false)

        //option1 use syntax to go to  des/action
        inflate.findViewById<Button>(R.id.button_go_to_registrations).setOnClickListener {
            //it.findNavController().navigate(R.id.action_welcomeFragment_to_registrationsFragment)
            //Navigation.findNavController(it).navigate(R.id.action_welcomeFragment_to_registrationsFragment)
            //Navigation.findNavController(it).navigate(R.id.registrations_dest)
        }

        ////option2 go to action or destination registrations_dest , best way
        inflate.findViewById<Button>(R.id.button_go_to_registrations).setOnClickListener(
                //Navigation.createNavigateOnClickListener(R.id.action_contactFragment_to_homeFragment)// KO because this action is not known for the NavController it should be defined for the welcomeFragment
                Navigation.createNavigateOnClickListener(R.id.action_welcomeFragment_to_registrationsFragment)
                //Navigation.createNavigateOnClickListener(R.id.registrations_dest)//OK but with no animation, should be avoided
                //Navigation.createNavigateOnClickListener(R.id.home_dest) //OK but not defined in the navGraph xml, should be avoided
        )

        ///option3 navigate to some destination with custom animation all these details are not in the navGraph xml, should be avoided
        // inflate.findViewById<Button>(R.id.button_go_to_registrations).setOnClickListener {
        //     findNavController()
        //             .navigate(R.id.home_dest, null, navOptions {
        //                 anim {
        //                     enter = R.anim.slide_in_right
        //                     exit = R.anim.slide_out_left
        //                     popEnter = R.anim.slide_in_left
        //                     popExit = R.anim.slide_out_right
        //                 }
        //             })
        // }

        return inflate
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_options_welcome, menu)

        if (null == getShareIntent().resolveActivity(activity!!.packageManager)) {
            // hide the menu item if it doesn't resolve
            menu.findItem(R.id.share)?.isVisible = false

        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.share -> {
            shareSuccess()
            true
        }
        R.id.contact_dest -> NavigationUI.onNavDestinationSelected(item, view!!.findNavController())
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
