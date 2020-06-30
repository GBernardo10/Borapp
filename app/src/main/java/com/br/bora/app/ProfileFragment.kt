package com.br.bora.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.br.bora.app.utils.LoadFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {
    private lateinit var fragment: LoadFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragment = fragmentManager?.let { LoadFragment(R.id.frame_my_events, it) }!!

        fragment.loadFragment(MyEventsFragment())

        tab_layout_profile.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                when (tab?.position!!) {
                    0 -> {
                        fragment.loadFragment(MyEventsFragment())
                    }
                    1 -> {
                        fragment.loadFragment(EventsConfirmedFragment())
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                when (tab?.position!!) {
                    0 -> {
                        fragment.loadFragment(MyEventsFragment())
                    }
                    1 -> {
                        fragment.loadFragment(EventsConfirmedFragment())
                    }
                }
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position!!) {
                    0 -> {
                        fragment.loadFragment(MyEventsFragment())

                    }
                    1 -> {
                        fragment.loadFragment(EventsConfirmedFragment())
                    }
                }
            }
        })
    }
}
