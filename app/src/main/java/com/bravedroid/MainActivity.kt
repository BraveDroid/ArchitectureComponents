package com.bravedroid

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import timber.log.Timber

class MainActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        log("onCreate")
        JobTimer(lifecycle)
    }

    override fun onRestart() {
        super.onRestart()
        log("onRestart")
    }

    override fun onStart() {
        super.onStart()
        log("onStart")
    }

    override fun onResume() {
        super.onResume()
        log("onResume")
    }

    override fun onPause() {
        super.onPause()
        log("onPause")
    }

    override fun onStop() {
        super.onStop()
        log("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    private fun log(eventName: String) {
        Timber.i("current state ${lifecycle.currentState} for event $eventName")
    }
}

class JobTimer(lifecycle: Lifecycle) : LifecycleObserver {
    private var secondCount = 0
    private var handler = Handler()
    private lateinit var runnable: Runnable

    init {
        lifecycle.addObserver(this)
        runnable = Runnable {
            Timber.i("seconds passed are ${++secondCount}")
            handler.postDelayed(runnable, 1000)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        handler.postDelayed(runnable, 1000)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        handler.removeCallbacks(runnable)
    }

}
