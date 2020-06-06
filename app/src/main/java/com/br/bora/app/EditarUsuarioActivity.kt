package com.br.bora.app
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.br.bora.app.model.User
import com.br.bora.app.services.UserService
import com.br.bora.app.services.config.RetrofitInitializer
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_cadastro_pf.*
import kotlinx.android.synthetic.main.activity_editar_usuario.*

class EditarUsuarioActivity : AppCompatActivity() {

    private val PERMISSION_CODE = 1000;
    var preferencias: SharedPreferences? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_usuario)

        cadastropf_ivFoto.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_DENIED) {
                    val permission = arrayOf(
                        android.Manifest.permission.CAMERA
                    )
                    requestPermissions(permission, PERMISSION_CODE);
                } else {
                    openCamera();
                }
            } else {
                openCamera();
            }
        }
    }
    fun openCamera() {
        ImagePicker.with(this)
            .crop(16f, 9f)	//Crop image with 16:9 aspect ratio
            .maxResultSize(520,520)
            .start()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openCamera();
                }
                else {
                    Toast.makeText(this,"Permissão Negada",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val fileUri = data?.data
            cadastropf_ivFoto.setImageURI(fileUri);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Operação Cancelada", Toast.LENGTH_SHORT).show()
        }
    }

    fun alterarCadastro(v:View){

    }

//    fun getUser(username:String) :User {
//        val retIn = RetrofitInitializer.getRetrofitInstance().create(UserService::class.java)
//        retIn.userByMail(username.toUpperCase())
//
//
//    }

    fun validaCampos(): Boolean {
        if (editarUsuario_etCelular.text.toString().isEmpty()) {
            editarUsuario_etCelular.requestFocus();
            editarUsuario_etCelular.error = getString(R.string.celularError);
            return false;
        }
        if (editarUsuario_etEmail.text.toString().isEmpty()) {
            editarUsuario_etEmail.requestFocus();
            editarUsuario_etEmail.error = getString(R.string.loginError);
            return false;
        }
        if (editarUsuario_etSenha.text.toString().isEmpty()) {
            editarUsuario_etSenha.requestFocus();
            editarUsuario_etSenha.error = getString(R.string.senhaError);
            return false;
        }
        return true;
    }
}
