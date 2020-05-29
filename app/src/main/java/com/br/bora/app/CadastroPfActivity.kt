package com.br.bora.app
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class CadastroPfActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_pf)
    }

    fun irMain(v:View){
        val telaMain = Intent(this,MainActivity::class.java)
        startActivity(telaMain);
    }

    fun ImageSelect(v:View){
        val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    }
}
