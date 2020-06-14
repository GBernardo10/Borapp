package com.br.bora.app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_home.view.*
import kotlinx.android.synthetic.main.activity_tab_bar.*
import kotlinx.android.synthetic.main.activity_tab_bar.view.*
import kotlinx.android.synthetic.main.activity_tab_bar.view.action_bar
import java.util.function.Predicate

class HomeActivity : Fragment() {
    @Override
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_home, container, false)
        view.add_role.setOnClickListener{
            startActivity(Intent(context,CriarEventoActivity::class.java))
        }
        view.my_role.setOnClickListener{
            startActivity(Intent(context,MeusRolesActivity::class.java))
        }
        view.settings.setOnClickListener{
            startActivity(Intent(context,CadastroPreferenciasActivity::class.java))
        }
        view.confirm.setOnClickListener{
            startActivity(Intent(context,ScannerActivity::class.java))
        }

        view.settingsIcon.setOnClickListener{
            startActivity(Intent(context,ConfiguracoesActivity::class.java))
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
