package com.example.govermentapp.ui.splash

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.govermentapp.R
import com.example.govermentapp.databinding.ActivityMainBinding
import com.example.govermentapp.ui.GovernmentActivity
import androidx.biometric.BiometricManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
private const val SPLASH_DELAY: Long = 2000

@RequiresApi(Build.VERSION_CODES.Q)
@SuppressLint("ServiceCast", "CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    companion object {
        private const val BIOMETRIC_PERMISSION_REQUEST_CODE = 1001
        private const val STORAGE_PERMISSION_REQUEST_CODE = 1002
    }

    private lateinit var mBinding : ActivityMainBinding
    private val biometricManager: BiometricManager by lazy {
        getSystemService(BIOMETRIC_SERVICE) as BiometricManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setLottie()
        lifecycleScope.launch {
            delay(SPLASH_DELAY)
            if (checkBiometricPermission() && checkStoragePermission()) {
                goHomeActivity()
            } else {
                requestBiometricPermission()
            }
        }
    }
    private fun setLottie() = mBinding.apply {
            lottieSplash.setAnimation(R.raw.loading)
            lottieSplash.playAnimation()
        }
    private fun goHomeActivity() {
        startActivity(Intent(this, GovernmentActivity::class.java))
        finish()
    }
    private fun checkBiometricPermission(): Boolean {
        return checkSelfPermission(Manifest.permission.USE_BIOMETRIC) ==
                PackageManager.PERMISSION_GRANTED
    }
    private fun checkStoragePermission(): Boolean {
        return checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED
    }
    private fun requestBiometricPermission() {
        val permissionsToRequest = mutableListOf<String>()

        if (!checkBiometricPermission()) {
            permissionsToRequest.add(Manifest.permission.USE_BIOMETRIC)
        }

        if (!checkStoragePermission()) {
            permissionsToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        if (permissionsToRequest.isNotEmpty()) {
            requestPermissions(
                permissionsToRequest.toTypedArray(),
                BIOMETRIC_PERMISSION_REQUEST_CODE
            )
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == BIOMETRIC_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                goHomeActivity()
            } else {
                Toast.makeText(this, getString(R.string.conofiguration_error), Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}