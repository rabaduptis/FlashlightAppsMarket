package com.root14.flashlightappsmarket

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import com.root14.flashlightappsmarket.databinding.ActivityMainBinding
import com.root14.flashlightappsmarket.model.CategoryType
import com.root14.flashlightappsmarket.view.ui.applicationFragment.ApplicationFragmentDirections
import com.root14.flashlightappsmarket.view.ui.mainFragment.MainFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //drawer layout
        drawerLayout = binding.myDrawerLayout
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        //action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        val action = ApplicationFragmentDirections.actionApplicationFragmentToMainFragment()
        //floating action button
        binding.fab.setOnClickListener {
            //case Add “refresh” option to make a new network query
            //I understood it as choosing a new category
            navController.navigate(action)
        }

        //drawer menu
        binding.navigationView.setNavigationItemSelectedListener { item ->
            val action = MainFragmentDirections.actionMainFragmentToApplicationFragment()
            val actionSelf = ApplicationFragmentDirections.actionApplicationFragmentSelf()
            when (item.itemId) {
                R.id.menu_flashlights -> {


                    if (navController.currentDestination?.id == R.id.applicationFragment) {
                        actionSelf.categoryType = CategoryType.FLASHLIGHTS
                        navController.navigate(actionSelf)
                    } else {
                        action.categoryType = CategoryType.FLASHLIGHTS
                        navController.navigate(action)
                    }

                    drawerLayout.closeDrawers()
                    true
                }

                R.id.menu_colored_lights -> {

                    if (navController.currentDestination?.id == R.id.applicationFragment) {
                        actionSelf.categoryType = CategoryType.COLOREDLIGHTS
                        navController.navigate(actionSelf)
                    } else {
                        action.categoryType = CategoryType.COLOREDLIGHTS
                        navController.navigate(action)
                    }
                    drawerLayout.closeDrawers()
                    true
                }

                R.id.menu_sos_alerts -> {

                    if (navController.currentDestination?.id == R.id.applicationFragment) {
                        actionSelf.categoryType = CategoryType.SOSALERTS
                        navController.navigate(actionSelf)
                    } else {
                        action.categoryType = CategoryType.SOSALERTS
                        navController.navigate(action)
                    }
                    drawerLayout.closeDrawers()
                    true
                }

                else -> {
                    //TODO: not implemented yet
                    true
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    //ActionBarDrawerToggle
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
}