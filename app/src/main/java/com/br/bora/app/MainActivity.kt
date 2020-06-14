package com.br.bora.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.br.bora.app.utils.LoadFragment
import com.br.bora.app.utils.SaveData
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_drawer_layout.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer_layout)
        val fragment = LoadFragment(R.id.main_frame, supportFragmentManager)

        val toolbar: MaterialToolbar = findViewById(R.id.action_bar)
        setSupportActionBar(toolbar)
        val drawerToggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            action_bar,
            (R.string.open),
            (R.string.close)
        )
        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        action_bar.setNavigationIcon(R.drawable.ic_settings)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeButtonEnabled(true)
        nav_view.setNavigationItemSelectedListener(this)

        fragment.loadFragment(HomeActivity())

        add_role.setOnClickListener {
            startActivity(Intent(this, AddEventActivity::class.java))
        }

        main_nav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    fragment.loadFragment(HomeActivity())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_search -> {
                    fragment.loadFragment(EventSearch())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_notification -> {
                    fragment.loadFragment(NotificationFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.d_profile -> {
                Toast.makeText(this, "TESTE", Toast.LENGTH_SHORT).show()
            }
            R.id.d_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START))
            drawer_layout.closeDrawer(GravityCompat.START)
        else {
            super.onBackPressed()
        }

    }
}

/* saveData = SaveData(this)
        if (saveData.loadDarkModeState() == true)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        if (saveData.loadDarkModeState() == true)
            switch_theme.isChecked = true*/


/* switch_theme.setOnCheckedChangeListener { _, isChecked ->
     if (isChecked) {
         AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
         saveData.setDarkModeState(true)
     } else {
         AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
         saveData.setDarkModeState(false)
     }
 }*/