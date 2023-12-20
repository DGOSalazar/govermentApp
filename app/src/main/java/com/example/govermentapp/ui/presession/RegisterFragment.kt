package com.example.govermentapp.ui.presession

import androidx.biometric.BiometricPrompt
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.govermentapp.R
import com.example.govermentapp.data.utils.Status
import com.example.govermentapp.databinding.FragmentRegisterBinding
import com.example.govermentapp.ui.GovernmentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.Executor

class RegisterFragment : Fragment() {
    private lateinit var mBinding : FragmentRegisterBinding
    private lateinit var auth : FirebaseAuth
    private val viewModel by activityViewModels<PreSessionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentRegisterBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        setListeners()
        setObservers()
    }

    private fun setListeners() {
        mBinding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
        mBinding.btnRegister.setOnClickListener {
            if (validateFields()) viewModel.setRegisterUser(
                mBinding.inputMail.text.toString(),
                mBinding.inputPass.text.toString()
            )
            else putErrorLabels()
        }
        mBinding.switchFingerprint.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) (activity as GovernmentActivity).showBiometricPrompt(
                success = {
                    viewModel.saveLocalInfoUser(
                        email = mBinding.inputMail.text.toString(),
                        pass = mBinding.inputPassConfirm.text.toString(),
                        isBiometric = true
                    )
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.biometric_success),
                        Toast.LENGTH_SHORT
                    ).show()
                },
                error = {
                    mBinding.switchFingerprint.isChecked = false
                }
            )
            else viewModel.clearSharedPreference()
        }
    }

    private fun setObservers() {
        viewModel.registerStatus.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> (activity as GovernmentActivity).showLoader()
                Status.SUCCESS -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.welcome_message), Toast.LENGTH_SHORT
                    ).show()
                    (activity as GovernmentActivity).hideLoader()
                    findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToHomeFragment())
                }
                Status.ERROR -> {
                    (activity as GovernmentActivity).hideLoader()
                    (activity as GovernmentActivity).showError(message = it.message ?: getString(R.string.error_general_title))
                }
            }
        }
    }
    private fun validateFields() =
                mBinding.inputMail.text.toString().contains(".com") &&
                mBinding.inputPass.text.toString().length > 7 &&
                mBinding.inputPassConfirm.text.toString() == mBinding.inputPass.text.toString()

    private fun putErrorLabels() {
        if(mBinding.inputMail.text.toString().contains(".com")) mBinding.ilMail.error = ""
        else mBinding.ilMail.error = getString(R.string.error_label_mail)

        if(mBinding.inputPass.text.toString().length > 7) mBinding.ilPass.error = ""
        else mBinding.ilPass.error = getString(R.string.error_label_pass)

        if(mBinding.inputPassConfirm.text.toString().length > 7) mBinding.inputPassConfirm.error = ""
        else mBinding.inputPassConfirm.error = getString(R.string.error_label_pass)
    }

}