package com.example.govermentapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.example.govermentapp.GovernmentApp
import com.example.govermentapp.R
import com.example.govermentapp.databinding.ActivityGovernmentBinding
import com.example.govermentapp.ui.dialogs.GenericAlertDialog
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.Executor

@AndroidEntryPoint
class GovernmentActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityGovernmentBinding
    private val navController by lazy { findNavController(R.id.government_nav_controller) }
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityGovernmentBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initNavigation()
    }

    private fun initNavigation() {
        navController.addOnDestinationChangedListener { _, _, _ ->
        }
    }
    fun showLoader() {
        mBinding.apply {
            vShadowBack.visibility = View.VISIBLE
            lottieLoaderGeneral.visibility = View.VISIBLE
            lottieLoaderGeneral.setAnimation(R.raw.loading)
            lottieLoaderGeneral.playAnimation()
        }
    }
    fun hideLoader() {
        mBinding.apply {
            vShadowBack.visibility = View.GONE
            lottieLoaderGeneral.cancelAnimation()
            lottieLoaderGeneral.visibility = View.GONE
        }
    }

    fun showError(code: String = "0081", message: String = "") =
            GenericAlertDialog(code, message) {}.show(this.supportFragmentManager, message)

    fun showBiometricPrompt(success:() -> Unit, error:()-> Unit) {
        val authenticationCallback = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                success()
            }
            override fun onAuthenticationError(
                errorCode: Int,
                errString: CharSequence
            ) {
                error()
                showError(
                    code = getString(R.string.error_12_code),
                    message = getString(R.string.error_12_msg)
                )
            }
        }

        val executor: Executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor, authenticationCallback)
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.biometric_title))
            .setSubtitle(getString(R.string.biometric_help_label))
            .setNegativeButtonText(getString(R.string.cancel_label))
            .build()
        biometricPrompt.authenticate(promptInfo)
    }
}