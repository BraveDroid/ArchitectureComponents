package com.bravedroid


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

class ExitFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       //test with adb .
        // adb shell am start -a android.intent.action.VIEW -d appdemo://boat/2
        val id = arguments?.getString("id_dl")?.toInt() ?: ExitFragmentArgs.fromBundle(arguments!!).id
        val view = inflater.inflate(R.layout.fragment_exit, container, false)
        Toast.makeText(context, "yes ${id})", Toast.LENGTH_SHORT).show()
        return view
    }


}
