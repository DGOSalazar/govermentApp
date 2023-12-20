package com.example.govermentapp.ui.presession

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.govermentapp.R
import com.example.govermentapp.data.utils.Status
import com.example.govermentapp.databinding.FragmentLoginBinding
import com.example.govermentapp.ui.GovernmentActivity

class LoginFragment : Fragment() {
    private lateinit var mBinding: FragmentLoginBinding
    private val viewModel by activityViewModels<PreSessionViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentLoginBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel.isBiometric()) setMainUser()
        setListeners()
        setLiveData()
    }

    private fun setMainUser() = with(mBinding) {
        clWelcome.visibility = View.VISIBLE
        clInputs.visibility = View.INVISIBLE
        btnRegister.visibility = View.INVISIBLE
        btnLogin.text = getString(R.string.login_biometric)
        tvWelcome.text = String.format(
                getString(R.string.welcome_message_biometric),
                viewModel.getEmail().split("@")[0].capitalize()
            )
    }
    private fun setListeners() {
        mBinding.btnLogin.setOnClickListener {
            if(mBinding.btnLogin.text != getString(R.string.login_biometric))
                viewModel.getLoginUser(
                    mBinding.inputMail.text.toString(),
                    mBinding.inputPass.text.toString()
                )
            else (activity as GovernmentActivity).showBiometricPrompt(
                success = {
                    viewModel.getLoginUser(
                        viewModel.getEmail(),
                        viewModel.getPass()
                    )
                },
                error = {

                }
            )
        }

        mBinding.btnRegister.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
    }
    private fun setLiveData() {
        viewModel.loginStatus.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> (activity as GovernmentActivity).showLoader()
                Status.SUCCESS -> {
                    (activity as GovernmentActivity).hideLoader()
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.welcome_message_login),
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                }

                Status.ERROR -> {
                    (activity as GovernmentActivity).hideLoader()
                    (activity as GovernmentActivity).showError(message = it.message!!)
                }
            }
        }
    }
}