package com.br.bora.app

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.br.bora.app.model.User
import com.br.bora.app.model.viewmodel.UserViewModel
import com.br.bora.app.request.CreateUser
import com.github.dhaval2404.imagepicker.ImagePicker
import com.santalu.maskedittext.MaskEditText
import kotlinx.android.synthetic.main.activity_register_user.*

class RegisterUserActivity : AppCompatActivity() {

    private val PERMISSION_CODE = 1000;
    private val image_uri = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)
        setSupportActionBar(action_bar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        action_bar.title = getString(R.string.register)
        action_bar.setTitleTextColor(resources.getColor(R.color.colorText))

        upload_pic.setImageResource(R.drawable.ic_cloud_upload);
        upload_pic.setOnClickListener {
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

        btn_register_user.setOnClickListener {
            val nameInput = findViewById<EditText>(R.id.name_user).text.toString()
            val mailInput = findViewById<EditText>(R.id.mail_user).text.toString()
            val phoneInput = findViewById<MaskEditText>(R.id.phone_user).rawText.toString()
            val usernameInput = findViewById<EditText>(R.id.username).text.toString()
            val passwordInput = findViewById<EditText>(R.id.password_user).text.toString()
            val user = CreateUser(
                User.Create(
                    nameInput,
                    mailInput,
                    phoneInput,
                    usernameInput,
                    passwordInput
                )
            )
            createUser(user, it)
        }
    }

    private fun createUser(user: CreateUser, v: View) {
        return UserViewModel().createUser(user, v)
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
            upload_pic.setImageURI(fileUri);
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
        /*
        val phoneReplace = phone.replace("-","").replace("(","").replace(")","");
        val retIn = RetrofitInitializer.userService.user()
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
        })*/
    }


    fun irMain() {
        startActivity(Intent(this, MainActivity::class.java));
    }

    fun validaCampos(): Boolean {
        if (mail_user.text.toString().isEmpty()) {
            mail_user.requestFocus();
            mail_user.error = getString(R.string.campoObrigatorio)
            return false;
        }
        if (phone_user.text.toString().isEmpty()) {
            phone_user.requestFocus();
            phone_user.error = getString(R.string.campoObrigatorio);
            return false;
        }
        if (username.text.toString().isEmpty()) {
            username.requestFocus();
            username.error = getString(R.string.campoObrigatorio);
            return false;
        }
        if (password_user.text.toString().isEmpty()) {
            password_user.requestFocus();
            password_user.error = getString(R.string.campoObrigatorio);
            return false;
        }
        if (username.text.toString().isEmpty()) {
            username.requestFocus();
            username.error = getString(R.string.campoObrigatorio);
        }
        return true;
    }

    fun inicializaTela() {
        btn_register_user.setOnClickListener {
            if (validaCampos()) {
                cadastrarUsuario(
                    username.text.toString(), "+55" + phone_user.text.toString()
                    , mail_user.text.toString(), password_user.text.toString()
                    , username.text.toString()
                );
            }
        }
        upload_pic.setOnClickListener {
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

