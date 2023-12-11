package com.example.govermentapp.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.govermentapp.R
import com.example.govermentapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var mBinding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentLoginBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return mBinding.root
    }
}