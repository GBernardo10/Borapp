package com.br.bora.app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import java.util.function.Predicate

class HomeActivity : Fragment() {
    @Override
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_home, container, false)
    }

}
//  fun IrPreferencias(v:View){
//        val telaEventosPreferenciais = Intent(this, ::class.java);
//      startActivity(telaEventosPreferenciais);
//    }
//
//    fun IrRoles(v:View){
//        val telaMeusRoles = Intent(this, MeusRoles::class.java);
//        startActivity(telaMeusRoles);
//    }
