package com.example.govermentapp.domain
interface SharedPreferenceRepository {
    //sharedPreferences
    fun clearAll()
    fun saveUserData(email: String, pass: String, isBiometric: Boolean)
    fun getEmail() : String
    fun getPass() : String
    fun isBiometric() : Boolean
}