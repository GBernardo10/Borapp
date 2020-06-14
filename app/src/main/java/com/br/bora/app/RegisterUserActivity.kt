package com.br.bora.app

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.br.bora.app.model.User
import com.br.bora.app.request.RequestUser
import com.br.bora.app.services.UserService
import com.br.bora.app.services.config.RetrofitInitializer
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_cadastro_pf.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.activity_editar_usuario.etCelular
import kotlinx.android.synthetic.main.activity_editar_usuario.etEmail
import kotlinx.android.synthetic.main.activity_editar_usuario.ivFoto

class CadastroPfActivity : AppCompatActivity() {

    private val PERMISSION_CODE = 1000;
    private val image_uri = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_pf)
        inicializaTela();
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                } else {
                    Toast.makeText(this, "Permissão Negada", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    fun openCamera() {
        ImagePicker.with(this)
            //.crop(16f, 9f)    //Crop image with 16:9 aspect ratio
            .maxResultSize(520, 520)
            .start()
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

    fun cadastrarUsuario(
        name: String,
        phone: String,
        mail: String,
        password: String,
        username: String
    ) {
        val phoneReplace = phone.replace("-","").replace("(","").replace(")","");
        val retIn = RetrofitInitializer.getRetrofitInstance().create(UserService::class.java)
        val user = User(name,phoneReplace, mail, password, username)
        val signInInfo = RequestUser(user);
        retIn.user(signInInfo).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 201) {
                    irMain();
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.i("STATE", t.message.toString())
            }
        })
    }


    fun irMain() {
        startActivity(Intent(this, MainActivity::class.java));
    }

    fun validaCampos(): Boolean {
        if (cadastropf_etEmail.text.toString().isEmpty()) {
            cadastropf_etEmail.requestFocus();
            cadastropf_etEmail.error = getString(R.string.campoObrigatorio)
            return false;
        }
        if (cadastropf_etCelular.text.toString().isEmpty()) {
            cadastropf_etCelular.requestFocus();
            cadastropf_etCelular.error = getString(R.string.campoObrigatorio);
            return false;
        }
        if (cadastropf_etLogin.text.toString().isEmpty()) {
            cadastropf_etLogin.requestFocus();
            cadastropf_etLogin.error = getString(R.string.campoObrigatorio);
            return false;
        }
        if (cadastropf_etSenha.text.toString().isEmpty()) {
            cadastropf_etSenha.requestFocus();
            cadastropf_etSenha.error = getString(R.string.campoObrigatorio);
            return false;
        }
        if (cadastropf_etNome.text.toString().isEmpty()) {
            cadastropf_etNome.requestFocus();
            cadastropf_etNome.error = getString(R.string.campoObrigatorio);
        }
        return true;
    }

    fun inicializaTela(){
        cadastropf_btCadastrar.setOnClickListener {
            if (validaCampos()) {
                cadastrarUsuario(
                    cadastropf_etNome.text.toString(), "+55" + cadastropf_etCelular.text.toString()
                    , cadastropf_etEmail.text.toString(), cadastropf_etSenha.text.toString()
                    , cadastropf_etLogin.text.toString()
                );
            }
        }
        cadastropf_ivFoto.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_DENIED
                ) {
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
}

