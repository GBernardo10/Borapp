package com.br.bora.app

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_LONG
import com.google.zxing.Result
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_scanner.*
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScannerActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    override fun handleResult(rawResult: Result?) {
        processRawResult(rawResult?.text)
    }

    private fun showSnack(){
        Snackbar.make(
            window.decorView.rootView,
            getString(R.string.permission_denied), LENGTH_LONG).show();
    }

    private fun processRawResult(text: String?) {
        if (!text.isNullOrBlank() || !text.isNullOrEmpty())
            startActivity(Intent(this,DetalheEventoActivity::class.java))
        else{
            showSnack()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)

        Dexter.withActivity(this)
            .withPermission(Manifest.permission.CAMERA)
            .withListener(object :PermissionListener{
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    scanner.startCamera()
                    scanner.setResultHandler(this@ScannerActivity)
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) {}

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    showSnack()
                }
            }).check()
    }

    override fun onPause() {
        super.onPause()
        scanner.stopCamera()
    }

    override fun onResume() {
        super.onResume()
        scanner.startCamera()
        scanner.setResultHandler(this@ScannerActivity)
    }

    override fun onDestroy() {
        super.onDestroy()
        scanner.stopCamera()
    }
}
