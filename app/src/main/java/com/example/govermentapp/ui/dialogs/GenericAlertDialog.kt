package com.example.govermentapp.ui.dialogs

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.govermentapp.R
import com.example.govermentapp.databinding.DialogErrorBinding

class GenericAlertDialog(
    var code : String,
    var message : String,
    var res: () -> Unit
): DialogFragment(R.layout.dialog_error) {
    private lateinit var mBinding: DialogErrorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, com.airbnb.lottie.R.style.AlertDialog_AppCompat)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        mBinding = DialogErrorBinding.inflate(layoutInflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.transparent)))

        mBinding.tvCode.text = code
        mBinding.tvTitle.text = message
        mBinding.btnOk.setOnClickListener{
            dismiss()
        }
        return mBinding.root
    }
}