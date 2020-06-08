package com.br.bora.app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.br.bora.app.model.User
import com.br.bora.app.request.RequestUser
import com.br.bora.app.services.UserService
import com.br.bora.app.services.config.RetrofitInitializer
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_cadastro_pf.*
import kotlinx.android.synthetic.main.activity_editar_usuario.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditarUsuarioActivity : AppCompatActivity() {

    private val PERMISSION_CODE = 1000;
    var preferencias: SharedPreferences? = null;
    var token: String? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_usuario)

        preferencias = getPreferences(Context.MODE_PRIVATE)
        token = preferencias?.getString("token", "");
        val usenamePreferences = preferencias?.getString("username", "");
        setQualificacoes(1.7)
        inicializaTela();

    }

    fun openCamera() {
        ImagePicker.with(this)
            .crop(16f, 9f)    //Crop image with 16:9 aspect ratio
            .maxResultSize(520, 520)
            .start()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val fileUri = data?.data
            editarUsuario_ivFoto.setImageURI(fileUri);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Operação Cancelada", Toast.LENGTH_SHORT).show()
        }
    }

    fun alterarCadastro(
        name: String,
        phone: String,
        mail: String,
        password: String,
        username: String
    ) {
        val retIn = RetrofitInitializer.getRetrofitInstance().create(UserService::class.java);
        val user = User(name, phone, mail, password, username);
        val signInInfo = RequestUser(user);
        retIn.userEdit(signInInfo).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 201) {

                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.i("STATE", t.message.toString())
            }
        })
    }

    fun getUser(username: String?) {
        val retIn = RetrofitInitializer.getRetrofitInstance().create(UserService::class.java)
        retIn.userByMail(username).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.code() == 201) {
                    val user: User? = response.body();
                    preencherCampos(user);
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun preencherCampos(user: User?) {
        editarUsuario_tvNome.text = user?.name;
        editarUsuario_tvLogin.text = user?.username;
        editarUsuario_etEmail.setText(user?.mail);
        editarUsuario_etCelular.setText(user?.phone);
        editarUsuario_etSenha.setText(user?.password)
    }

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

    fun setQualificacoes(valor: Double){
        if(valor < 0.5)
            editarUsuario_ivEstrelaUm.background = getDrawable(R.drawable.ic_star_half_yellow)
        else if(valor > 0.5 && valor < 1)
            editarUsuario_ivEstrelaUm.background = getDrawable(R.drawable.ic_star_yellow)
        if(valor < 1.5)
            editarUsuario_ivEstrelaDois.background = getDrawable(R.drawable.ic_star_half_yellow)
        else
            editarUsuario_ivEstrelaDois.background = getDrawable(R.drawable.ic_star_yellow)

        if(valor < 2.5)
            editarUsuario_ivEstrelaTres.background = getDrawable(R.drawable.ic_star_half_yellow)
        else
            editarUsuario_ivEstrelaTres.background = getDrawable(R.drawable.ic_star_yellow)

        if(valor < 3.5)
            editarUsuario_ivEstrelaQuatro.background = getDrawable(R.drawable.ic_star_half_yellow)
        else
            editarUsuario_ivEstrelaQuatro.background = getDrawable(R.drawable.ic_star_yellow)

        if(valor < 4.5)
            editarUsuario_ivEstrelaCinco.background = getDrawable(R.drawable.ic_star_half_yellow)
        else
            editarUsuario_ivEstrelaCinco.background = getDrawable(R.drawable.ic_star_yellow)
    }

    fun inicializaTela(){
        editarUsuario_btAlterar.setOnClickListener {
            if (validaCampos()) {
                alterarCadastro(
                    editarUsuario_tvNome.text.toString()
                    , editarUsuario_etCelular.text.toString()
                    , editarUsuario_etEmail.text.toString()
                    , editarUsuario_etSenha.text.toString()
                    , editarUsuario_tvLogin.text.toString()
                );
            }
        };
        editarUsuario_ivFoto.setOnClickListener {
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
