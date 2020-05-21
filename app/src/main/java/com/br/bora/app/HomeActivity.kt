package com.br.bora.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_cadastro.view.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.view.*

class HomeActivity : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
}
