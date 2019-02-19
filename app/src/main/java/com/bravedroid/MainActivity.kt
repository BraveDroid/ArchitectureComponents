package com.bravedroid

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NotificationCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        log("onCreate")
        JobTimer(lifecycle)


        val toolbar = findViewById<Toolbar>(R.id.mainToolbar)
        setSupportActionBar(toolbar)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val sideNavView = findViewById<NavigationView>(R.id.navView)

      //option1
      // val host: NavHostFragment = supportFragmentManager
      //         .findFragmentById(R.id.mainFragmentContainer) as NavHostFragment? ?: return
      // // Set up Action Bar
      // val navController = host.navController

        navController = findNavController(R.id.mainFragmentContainer)

        // appBarConfiguration = AppBarConfiguration(navController.graph)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.welcome_dest, R.id.registrations_dest), drawerLayout)

        setupActionBarWithNavController(navController, appBarConfiguration)
        //because I used an action bar
       // setupActionBarWithNavController(navController, drawerLayout)

        // NavigationUI.setupActionBarWithNavController(this, navController)
        setupWithNavController(sideNavView, navController)

        //when I may use the tool bar an get ride of the action bar
        //NavigationUI.setupWithNavController(toolbar, navController)
        //NavigationUI.setupWithNavController(toolbar, navController,drawerLayout)

        // prevent nav gesture if not on start destination(swiping)
        navController.addOnDestinationChangedListener { controller: NavController, destination: NavDestination, _ ->
            // if (destination.id == controller.graph.startDestination) {

            if (destination.id in setOf(R.id.welcome_dest, R.id.registrations_dest)) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }

        }

        sendFakeNotification()
    }

    private fun sendFakeNotification() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelName = "channel-0"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelName, "name", importance)
            notificationManager.createNotificationChannel(channel)
        }

        val args = ExitFragmentArgs(8).toBundle()

        val pendingIntent = NavDeepLinkBuilder(this)
                .setGraph(R.navigation.nav_graph_main)
                .setDestination(R.id.exitFragment)
                .setArguments(args)
                .createPendingIntent()


        val notificationBuilder = NotificationCompat.Builder(this)
                .setContentTitle("Alert !")
                .setAutoCancel(true)
                .setSmallIcon(android.R.drawable.btn_plus)
                .setContentIntent(pendingIntent)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationBuilder.setChannelId(channelName)
        }
        notificationManager.notify(0, notificationBuilder.build())
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.mainFragmentContainer)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        // return NavigationUI.navigateUp(navController, drawerLayout)
        return navController.navigateUp(appBarConfiguration)
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
