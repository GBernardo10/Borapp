package com.br.bora.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_tab_bar.*

class TabBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_bar)

        loadFragment(HomeActivity())

        main_nav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeActivity())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_search -> {
                    loadFragment(EventosPreferencias())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_notification -> {
                    loadFragment(NotificationFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }
    }

    private fun loadFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction().also {
            fragmentTransaction -> fragmentTransaction.replace(R.id.main_frame,fragment)
            fragmentTransaction.commit()
        }
    }
}

