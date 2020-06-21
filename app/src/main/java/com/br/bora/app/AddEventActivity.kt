package com.br.bora.app

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.loader.content.CursorLoader
import com.br.bora.app.databinding.ActivityAddEventBinding
import com.br.bora.app.model.viewmodel.EventViewModel
import com.br.bora.app.model.viewmodel.UploadViewModel
import com.br.bora.app.model.viewmodel.ZipCodeViewModel
import com.br.bora.app.request.CreateEvent
import com.br.bora.app.services.config.RetrofitInitializer
import com.br.bora.app.utils.DateUtils
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.radiobutton.MaterialRadioButton
import com.santalu.maskedittext.MaskEditText
import kotlinx.android.synthetic.main.activity_add_event.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*

class AddEventActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEventBinding
    private val permissionCode = 1000;
    private val dateUtils = DateUtils()
    private lateinit var scheduleStart: Date
    private lateinit var scheduleEnd: Date
    private lateinit var dateStart: Date
    private lateinit var dateEnd: Date
    private lateinit var cepInput: MaskEditText
    private lateinit var dateStartInput: EditText
    private lateinit var dateEndInput: EditText
    private lateinit var scheduleStartInput: EditText
    private lateinit var scheduleEndInput: EditText
    private lateinit var titleInput: EditText
    private lateinit var numberStreetInput: EditText
    private lateinit var typePublicInput: MaterialRadioButton
    private lateinit var typePrivateInput: MaterialRadioButton
    private lateinit var passwordInput: EditText
    private lateinit var isFreeInput: MaterialCheckBox
    private lateinit var priceInput: EditText
    private lateinit var labelPassword: TextView
    private lateinit var mBitmap: Bitmap
    private lateinit var fileUri: Uri
    private lateinit var body: MultipartBody.Part
    private lateinit var name: RequestBody
    private var hasPhotoEvent: Boolean = false


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEventBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.actionBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.actionBar.title = getString(R.string.add_event)
        binding.actionBar.setTitleTextColor(resources.getColor(R.color.colorText))
        cepInput = binding.cepEvent
        dateStartInput = binding.dateEventStart
        dateEndInput = binding.dateEventEnd
        scheduleStartInput = binding.scheduleStartEvent
        scheduleEndInput = binding.scheduleEndEvent
        titleInput = binding.titleEvent
        numberStreetInput = binding.numberStreetEvent
        typePublicInput = binding.typePublic
        typePrivateInput = binding.typePrivate
        passwordInput = binding.passwordEvent
        isFreeInput = binding.eventIsFree
        priceInput = binding.priceEvent
        labelPassword = binding.labelPasswordEvent

        with(dateStartInput) {
            showSoftInputOnFocus = false
            isFocusableInTouchMode = false
        }
        with(dateEndInput) {
            showSoftInputOnFocus = false
            isFocusableInTouchMode = false
        }
        with(scheduleStartInput) {
            showSoftInputOnFocus = false
            isFocusableInTouchMode = false
        }
        with(scheduleEndInput) {
            showSoftInputOnFocus = false
            isFocusableInTouchMode = false
        }

        dateStartInput.setOnClickListener {
            it.isFocusableInTouchMode = true
            it.isFocusable = true
            it.requestFocus()
            DatePickerDialog(
                this, dateSetListener(dateStartInput),
                dateUtils.getCalendar().get(Calendar.YEAR),
                dateUtils.getCalendar().get(Calendar.MONTH),
                dateUtils.getCalendar().get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        dateEndInput.setOnClickListener {
            it.isFocusableInTouchMode = true
            it.isFocusable = true
            it.requestFocus()
            DatePickerDialog(
                this, dateSetListener(dateEndInput),
                dateUtils.getCalendar().get(Calendar.YEAR),
                dateUtils.getCalendar().get(Calendar.MONTH),
                dateUtils.getCalendar().get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        scheduleStartInput.setOnClickListener {
            it.isFocusableInTouchMode = true
            it.isFocusable = true
            it.requestFocus()
            TimePickerDialog(
                this,
                timeSetListener(scheduleStartInput),
                dateUtils.getCalendar().get(Calendar.HOUR),
                dateUtils.getCalendar().get(Calendar.MINUTE),
                true
            ).show()
        }

        scheduleEndInput.setOnClickListener {
            it.isFocusableInTouchMode = true
            it.isFocusable = true
            it.requestFocus()
            TimePickerDialog(
                this,
                timeSetListener(scheduleEndInput),
                dateUtils.getCalendar().get(Calendar.HOUR),
                dateUtils.getCalendar().get(Calendar.MINUTE),
                true
            ).show()
        }

        dateStartInput.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                view.isFocusableInTouchMode = false
                view.isFocusable = false
                if (dateStartInput.text.toString().isNotEmpty()) {
                    dateStart = dateUtils.changeStringToDate(dateStartInput.text.toString())!!
                    dateStartInput.error =
                        if (!dateStart.before(dateUtils.getCurrentDate())) null
                        else getString(R.string.date_start_event_not_before_current_date)
                }
            }
        }

        dateEndInput.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                view.isFocusableInTouchMode = false
                view.isFocusable = false
                if (dateEndInput.text.toString().isNotEmpty()) {
                    dateEnd = dateUtils.changeStringToDate(dateEndInput.text.toString())!!
                    if (dateStartInput.text.isNotEmpty()) {
                        dateEndInput.error =
                            if (dateEnd.before(
                                    dateUtils.changeStringToDate(
                                        dateStartInput.text.toString()
                                    )
                                ) || dateEnd.before(
                                    dateUtils.getCurrentDate()
                                )
                            )
                                getString(R.string.date_end_event_not_before_date_start)
                            else
                                null
                    }
                }

            }
        }

        scheduleStartInput.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                view.isFocusableInTouchMode = false
                view.isFocusable = false
                if (scheduleStartInput.text.toString().isNotEmpty()) {
                    scheduleStart =
                        dateUtils.changeStringToTime(scheduleStartInput.text.toString())!!
                    dateStart = dateUtils.changeStringToDate(dateStartInput.text.toString())!!
                    if (!dateStart.before(dateUtils.getCurrentDate()) && !dateStart.after(dateUtils.getCurrentDate())) {
                        scheduleStartInput.error =
                            if (!scheduleStart.before(dateUtils.getCurrentTime())) null
                            else getString(R.string.schedule_start_not_before_current_time)
                    }
                }

            }
        }

        scheduleEndInput.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                view.isFocusableInTouchMode = false
                view.isFocusable = false
                if (scheduleStartInput.text.toString()
                        .isNotEmpty() && scheduleEndInput.text.toString().isNotEmpty()
                ) {
                    if (dateEndInput.text.toString().isNotEmpty() && dateStartInput.text.toString()
                            .isNotEmpty()
                    ) {
                        scheduleStart =
                            dateUtils.changeStringToTime(scheduleStartInput.text.toString())!!
                        dateEnd =
                            dateUtils.changeStringToDate(dateEndInput.text.toString())!!
                        dateStart =
                            dateUtils.changeStringToDate(dateStartInput.text.toString())!!
                        scheduleEnd =
                            dateUtils.changeStringToTime(scheduleEndInput.text.toString())!!
                        if (!dateStart.after(dateEnd) && !dateStart.before(dateEnd)) {
                            if (scheduleStartInput.text.isNotEmpty()) {
                                scheduleEndInput.error =
                                    if (scheduleEnd.before(scheduleStart)) getString(R.string.schedule_end_not_before_schedule_start)
                                    else null
                            }
                        }
                    }

                }

            }

        }

        cepInput.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (cepInput.text?.isNotEmpty()!!) {
                    ZipCodeViewModel().findZipCode(cepInput.rawText.toString(), this, v)
                }
            }
        }

        typePrivateInput.setOnClickListener {
            typePublicInput.isChecked = false
            typePrivateInput.isChecked = true
            labelPassword.isVisible = true
            passwordInput.isVisible = true
        }

        typePublicInput.setOnClickListener {
            typePrivateInput.isChecked = false
            typePublicInput.isChecked = true
            labelPassword.isVisible = false
            passwordInput.isVisible = false
        }

        isFreeInput.setOnCheckedChangeListener { _, isChecked ->
            priceInput.isVisible = !isChecked
        }

        binding.iconUploadImage.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_DENIED
                ) {
                    val permission = arrayOf(
                        android.Manifest.permission.CAMERA
                    )
                    requestPermissions(permission, permissionCode);
                } else {
                    openCamera();
                }
            } else {
                openCamera();
            }
        }


        binding.btnCreateEvent.setOnClickListener {
            if (hasPhotoEvent) {
                upload(fileUri)
                UploadViewModel().uploadFile(body, name)
            }

//            if (!validations()) {
//                Toast.makeText(this, "Erro", Toast.LENGTH_LONG).show()
//            }


//            val event = CreateEvent(
//                Event.Create(
//                    titleInput,
//                    "Gesuvs",
//                    dateStartInput,
//                    dateEndInput,
//                    cepInput.rawText.toString(),
//                    "Rua valença",
//                    numberStreetInput,
//                    "public",
//                    "",
//                    true
//                )
//            )
//            createEvent(event, it)
        }
    }

    private fun upload(uri: Uri) {
        val filesDir: File = applicationContext.filesDir
        val file = File(filesDir, uri.lastPathSegment!!)
        val bos = ByteArrayOutputStream()
        mBitmap.compress(Bitmap.CompressFormat.PNG, 0, bos)
        val bitmap: ByteArray = bos.toByteArray()
        val fos = FileOutputStream(file)
        fos.write(bitmap)
        fos.flush()
        fos.close()
        val reqFile = file.asRequestBody("image/${file.extension}".toMediaTypeOrNull())
        body = MultipartBody.Part.createFormData("file", file.name, reqFile)
        name = "file".toRequestBody("text/plain".toMediaTypeOrNull())
    }

    private fun createEvent(event: CreateEvent, v: View) {
        EventViewModel().createEvent(event, v)
    }

    private fun dateSetListener(editText: EditText): DatePickerDialog.OnDateSetListener? {
        return DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            dateUtils.getCalendar().set(Calendar.YEAR, year)
            dateUtils.getCalendar().set(Calendar.MONTH, monthOfYear)
            dateUtils.getCalendar().set(Calendar.DAY_OF_MONTH, dayOfMonth)
            editText.setText(dateUtils.formatterDate().format(dateUtils.getCalendar().time))
        }
    }

    private fun timeSetListener(editText: EditText): TimePickerDialog.OnTimeSetListener {
        return TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            dateUtils.getCalendar().set(Calendar.HOUR, hourOfDay)
            dateUtils.getCalendar().set(Calendar.MINUTE, minute)
            editText.setText(dateUtils.formatterTime().format(dateUtils.getCalendar().time))
        }
    }

    private fun openCamera() {
        ImagePicker.with(this)
            //.crop(16f, 9f)    //Crop image with 16:9 aspect ratio
            .maxResultSize(1024, 1024)
            .start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                fileUri = data?.data!!
                binding.iconUploadImage.setImageURI(fileUri)
                val path = fileUri.path
                mBitmap = BitmapFactory.decodeFile(path)
                hasPhotoEvent = true
            }
            ImagePicker.RESULT_ERROR -> {
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this, "Operação Cancelada", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            permissionCode -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera()
                } else {
                    Toast.makeText(this, "Permissão Negada", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun validations(): Boolean {

        if (titleInput.text.isEmpty()) {
            titleInput.requestFocus()
            titleInput.error = getString(R.string.campoObrigatorio)
            return false
        }
        if (cepInput.text!!.isEmpty()) {
            cepInput.requestFocus()
            cepInput.error = getString(R.string.campoObrigatorio)
            return false
        }
        if (cepInput.text!!.length < 8) {
            cepInput.requestFocus()
            cepInput.error = getString(R.string.preenchecep)
            return false
        }
        if (numberStreetInput.text.isEmpty()) {
            numberStreetInput.requestFocus()
            numberStreetInput.error = getString(R.string.campoObrigatorio)
            return false
        }
        if (dateStartInput.text.toString().isEmpty()) {
            dateStartInput.requestFocus()
            dateStartInput.error = getString(R.string.campoObrigatorio)
            return false
        }
        if (scheduleEndInput.text.toString().isEmpty()) {
            scheduleEndInput.requestFocus()
            scheduleEndInput.error = getString(R.string.campoObrigatorio)
            return false
        }
        if (dateEndInput.text.toString().isEmpty()) {
            dateEndInput.requestFocus()
            dateEndInput.error = getString(R.string.campoObrigatorio)
            return false
        }
        if (scheduleEndInput.text.toString().isEmpty()) {
            scheduleEndInput.requestFocus()
            scheduleEndInput.error = getString(R.string.campoObrigatorio)
            return false
        }
        if (!isFreeInput.isChecked) {
            priceInput.requestFocus()
            priceInput.error = getString(R.string.campoObrigatorio)
            return false
        }
        if (!typePublicInput.isChecked) {
            passwordInput.requestFocus()
            passwordInput.error = getString(R.string.campoObrigatorio)
            return false
        }
        return true
    }
}
