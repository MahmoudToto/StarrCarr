package com.example.starrcarr

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.starrcarr.databinding.ActivityMainBinding
import com.example.starrcarr.ui.earnings.EarningsFragment
import com.example.starrcarr.ui.home.HomeFragment
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navView: NavigationView
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)


        drawerLayout = binding.drawerLayout
        navView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_earnings
            ), drawerLayout
        )
        //To set up the fragment on first open
        replaceFragment(HomeFragment())

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setNavigationItemSelectedListener(this)
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.main, menu)
//        return true
//    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                replaceFragment(HomeFragment())
                return true
            }
            R.id.nav_earnings -> {
                replaceFragment(EarningsFragment())
                return true
            }
            R.id.nav_whatWe -> {
                Toast.makeText(this, "Soon", Toast.LENGTH_SHORT).show()
                return false
            }

            R.id.nav_credit -> {
                Toast.makeText(this, "Soon", Toast.LENGTH_SHORT).show()
                return false
            }
            R.id.nav_travels -> {
                Toast.makeText(this, "Soon", Toast.LENGTH_SHORT).show()
                return false
            }
            R.id.nav_Notices -> {
                Toast.makeText(this, "Soon", Toast.LENGTH_SHORT).show()
                return false
            }
            R.id.nav_Help -> {
                Toast.makeText(this, "Soon", Toast.LENGTH_SHORT).show()
                return false
            }
            R.id.nav_signout -> {
                Toast.makeText(this, "Soon", Toast.LENGTH_SHORT).show()
                return false
            }

        }
        return true
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_content_main, fragment).commitNow()
        if (drawerLayout.isOpen)
            drawerLayout.closeDrawers()
    }
}