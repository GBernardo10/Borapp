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
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_home.view.*
import java.util.function.Predicate

class HomeActivity : Fragment() {
    @Override
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_home, container, false)

        view.add_role.setOnClickListener{view ->
            startActivity(Intent(view.context,CriarEventoActivity::class.java))
        }
        view.my_role.setOnClickListener{view ->
            startActivity(Intent(view.context,MeusRolesActivity::class.java))
        }
        view.settings.setOnClickListener{view ->
            startActivity(Intent(view.context,CadastroPreferenciasActivity::class.java))
        }
        view.confirm.setOnClickListener{view ->
            startActivity(Intent(view.context,ConfirmarParticipanteActivity::class.java))
        }
        return view
    }
}
