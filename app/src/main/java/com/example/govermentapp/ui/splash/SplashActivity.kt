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
import com.example.govermentapp.R
import com.example.govermentapp.databinding.ActivityMainBinding
import com.example.govermentapp.ui.GovernmentActivity
import androidx.biometric.BiometricManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

@RequiresApi(Build.VERSION_CODES.Q)
@SuppressLint("ServiceCast", "CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    companion object {
        private val REQUEST_LOCATION_PERMISSION = 1
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
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        } else goHomeActivity()
    }
    private fun setLottie() = mBinding.apply {
            lottieSplash.setAnimation(R.raw.loading)
            lottieSplash.playAnimation()
        }
    private fun goHomeActivity() {
        startActivity(Intent(this, GovernmentActivity::class.java))
        finish()
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                goHomeActivity()
            } else {
                Toast.makeText(this, getString(R.string.conofiguration_error), Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}