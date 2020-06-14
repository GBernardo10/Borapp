package com.br.bora.app.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class LoadFragment(var id: Int, private var supportFragmentManager: FragmentManager) {

    fun loadFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction().also { fragmentTransaction ->
            fragmentTransaction.replace(id, fragment)
            fragmentTransaction.commit()
        }
    }
}