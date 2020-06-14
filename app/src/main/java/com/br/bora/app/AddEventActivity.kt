package com.br.bora.app

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.br.bora.app.model.Cep
import com.br.bora.app.model.Evento
import com.br.bora.app.request.RequestEvento
import com.br.bora.app.services.CepService
import com.br.bora.app.services.EventoService
import com.br.bora.app.services.config.RetrofitInitializer
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_criar_evento.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_add_event.*

class AddEventActivity : AppCompatActivity() {
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


    private val PERMISSION_CODE = 1000;
    var isPublic = true;
    var isPago = false;
    var preferencias: SharedPreferences? = null;
    var isAlterar = false;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_evento)
        //evento_etCep.setText("02042010");
        val idEvento = intent.extras?.getInt("idEvento",0)
        if(idEvento != 0){
            getEvento(idEvento);
            isAlterar = true;
        }

        inializaTela();

    }

    fun visibleValor(v: View) {
        if (evento_swIsPago.isChecked) {
            evento_tvLabelValor.visibility = View.VISIBLE;
            evento_etValor.visibility = View.VISIBLE;
            isPago = true;
        } else {
            evento_tvLabelValor.visibility = View.GONE;
            evento_etValor.visibility = View.GONE;
            isPago = true;
        }
    }

    fun preencheCamposEvento(evento: Evento?) {
        evento_etNomeEvento.setText(evento?.name)
        //evento_etCep.setText(evento.cep)
        //evento_etNumero.setText(evento.number);
        //evento_etQuantidade.setText(evento.quantity)
        //evento_etValor.setText(evento.valor);
        //evento_etData.setText(evento.startDay.toString().substring(0, 9))
        //evento_etHorario.setText(evento.startDay.toString().substring(11, 15))


        isPublic = evento!!.isPublic;
        if (evento!!.isPublic)
            evento_btnPublic.callOnClick()
        else
            evento_btnPrivate.callOnClick()
    }

    fun openCamera() {
        ImagePicker.with(this)
            //.crop(16f, 9f)    //Crop image with 16:9 aspect ratio
            .maxResultSize(1024, 1024)
            .start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val fileUri = data?.data
            evento_ivFoto.setImageURI(fileUri);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Operação Cancelada", Toast.LENGTH_SHORT).show()
        }
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

    fun inializaTela() {
        evento_swIsPago.setOnCheckedChangeListener { componente, ligado ->
            visibleValor(componente)
        }
        evento_btnPublic.setOnClickListener {
            evento_btnPublic.setBackgroundColor(getColor(R.color.cor_laranja))
            evento_btnPrivate.setBackgroundColor(getColor(R.color.cor_preto))
            evento_etSenha.visibility = (View.GONE)
            evento_tvLabelSenha.visibility = (View.GONE)
            isPublic = true
        }
        evento_btnPrivate.setOnClickListener {
            evento_btnPublic.setBackgroundColor(getColor(R.color.cor_preto))
            evento_btnPrivate.setBackgroundColor(getColor(R.color.cor_laranja))
            evento_etSenha.visibility = (View.VISIBLE)
            evento_tvLabelSenha.visibility = (View.VISIBLE)
            isPublic = false
        }
        evento_ivFoto.setOnClickListener {
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
        evento_btCadastrar.setOnClickListener {
            if (validacoes()) {
                if (isAlterar) {
                    //Pegar todos os campos e transformar em um obj de evento
                    //alterarEvento()
                } else {
                    //Pegar todos os campos e transformar em um obj de evento
                    //cadastrarEvento();
                }
            }
        }
        evento_btBuscaEndereco.setOnClickListener {
            if (!evento_etCep.text.toString().isEmpty()) {
                buscaEndereco(evento_etCep.text.toString())
            } else {
                evento_etCep.requestFocus();
                evento_etCep.error = getString(R.string.preencherCep);
            }
        }
    }

    fun validacoes(): Boolean {
        if (evento_etNomeEvento.text.isEmpty()) {
            evento_etNomeEvento.requestFocus();
            evento_etNomeEvento.error = getString(R.string.campoObrigatorio);
            return false;
        }
        if (evento_etCep.text!!.isEmpty()) {
            evento_etCep.requestFocus();
            evento_etCep.error = getString(R.string.campoObrigatorio);
            return false;
        }
        if (evento_etCep.text!!.length < 8) {
            evento_etCep.requestFocus();
            evento_etCep.error = getString(R.string.preenchecep);
            return false;
        }
        if (evento_etNumero.text.isEmpty()) {
            evento_etNumero.requestFocus();
            evento_etNumero.error = getString(R.string.campoObrigatorio);
            return false;
        }
        if (evento_etDataInicio.text.toString().isEmpty()) {
            evento_etDataInicio.requestFocus();
            evento_etDataInicio.error = getString(R.string.campoObrigatorio);
            return false;
        }
        if (evento_etHorarioTermino.text.toString().isEmpty()) {
            evento_etHorarioTermino.requestFocus();
            evento_etHorarioTermino.error = getString(R.string.campoObrigatorio);
            return false;
        }
        if (evento_etDataTermino.text.toString().isEmpty()) {
            evento_etDataTermino.requestFocus();
            evento_etDataTermino.error = getString(R.string.campoObrigatorio);
            return false;
        }
        if (evento_etHorarioInicio.text.toString().isEmpty()) {
            evento_etHorarioInicio.requestFocus();
            evento_etHorarioInicio.error = getString(R.string.campoObrigatorio);
            return false;
        }
        if (isPago) {
            evento_etValor.requestFocus();
            evento_etValor.error = getString(R.string.campoObrigatorio);
            return false;
        }
        if (!isPublic) {
            evento_etSenha.requestFocus();
            evento_etSenha.error = getString(R.string.campoObrigatorio);
            return false;
        }
        return true;
    }

    fun buscaEndereco(cep: String) {
        val retIn = RetrofitInitializer.getRetrofitInstance().create(CepService::class.java)
        retIn.getEndereco(cep.replace("-", "")).enqueue(object : Callback<Cep> {
            override fun onResponse(call: Call<Cep>, response: Response<Cep>) {
                if (response.code() == 200) {
                    if (response.body()?.logradouro != null) {
                        evento_tvEndereco.text =
                            response.body()?.logradouro + " - " + response.body()?.bairro + " - " + response.body()?.localidade
                        evento_tvLabelEndereco.visibility = (View.VISIBLE);
                        evento_tvEndereco.visibility = (View.VISIBLE);
                        evento_tvLabelNumero.visibility = (View.VISIBLE);
                        evento_etNumero.visibility = (View.VISIBLE);
                    } else {
                        evento_etCep.requestFocus();
                        evento_etCep.error = getString(R.string.enderecoNaoEncontra);
                    }

                }
            }

            override fun onFailure(call: Call<Cep>, t: Throwable) {
                Log.i("STATE", t.message.toString())
            }
        })
    }

    fun cadastrarEvento(evento: Evento) {
        val retIn = RetrofitInitializer.getRetrofitInstance().create(EventoService::class.java)
        val signInInfo = RequestEvento(evento);
        retIn.cadastraEvento(signInInfo).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 201) {

                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.i("STATE", t.message.toString())
            }
        })

    }

    fun alterarEvento(evento: Evento) {
        val retIn = RetrofitInitializer.getRetrofitInstance().create(EventoService::class.java)
        val signInInfo = RequestEvento(evento);
        retIn.changeEvento(signInInfo).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 200) {

                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.i("STATE", t.message.toString())
            }
        })
    }

    fun getEvento(idEvento: Int?){
        val retIn = RetrofitInitializer.getRetrofitInstance().create(EventoService::class.java)
        retIn.getEvento(idEvento).enqueue(object : Callback<Evento?> {
            override fun onResponse(call: Call<Evento?>, response: Response<Evento?>) {
                if (response.code() == 200) {
                    preencheCamposEvento(response.body());
                }
            }
            override fun onFailure(call: Call<Evento?>, t: Throwable) {
                Log.i("STATE", t.message.toString())
            }
        })
    }


        setContentView(R.layout.activity_add_event)
        setSupportActionBar(action_bar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        action_bar.title = getString(R.string.add_event)
        action_bar.setTitleTextColor(resources.getColor(R.color.colorText))

    }

}
