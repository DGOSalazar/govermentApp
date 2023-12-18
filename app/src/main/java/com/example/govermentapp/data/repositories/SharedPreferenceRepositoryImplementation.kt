package com.example.govermentapp.data.repositories

import android.content.SharedPreferences
import com.example.govermentapp.GovernmentApp
import com.example.govermentapp.R
import com.example.govermentapp.domain.GovernmentRepository
import com.example.govermentapp.domain.SharedPreferenceRepository
import javax.inject.Inject
import javax.inject.Singleton
class SharedPreferenceRepositoryImplementation @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val editor: SharedPreferences.Editor
) : SharedPreferenceRepository {
    override fun clearAll() = editor.clear().apply()
    override fun saveUserData(email: String, pass: String, isBiometric: Boolean) = with(editor) {
        putString(GovernmentApp.instance.getString(R.string.email_key), email)
        putString(GovernmentApp.instance.getString(R.string.pass_key), pass)
        putBoolean(GovernmentApp.instance.getString(R.string.biometric_key), isBiometric)
        apply()
    }
    override fun getEmail() =
        sharedPreferences.getString(GovernmentApp.instance.getString(R.string.email_key),"") ?: ""
    override fun getPass() =
        sharedPreferences.getString(GovernmentApp.instance.getString(R.string.pass_key),"") ?: ""
    override fun isBiometric() =
        sharedPreferences.getBoolean(GovernmentApp.instance.getString(R.string.biometric_key), false)
}