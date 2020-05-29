package com.br.bora.app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_cadastro.view.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.view.*
=======
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_home.view.*
import java.util.function.Predicate
>>>>>>> feature/gesuvs

class HomeActivity : Fragment() {
    @Override
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
<<<<<<< HEAD
        val view:View = inflater.inflate(R.layout.activity_home, container, false)
        view.add_role?.setOnClickListener{
            startActivity(Intent(view.context,CadastroActivity::class.java))
        }
        return view
    }

//    fun IrPreferencias(v:View){
//        val telaEventosPreferenciais = Intent(v.context, EventosPreferencias::class.java);
//        startActivity(telaEventosPreferenciais);
//    }

//    fun IrRoles(v:View){
//        val telaMeusRoles = Intent(this, MeusRoles::class.java);
//        startActivity(telaMeusRoles);
//    }
=======
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
>>>>>>> feature/gesuvs
}
