package com.example.govermentapp.data.models.entities

import com.example.govermentapp.data.utils.Status
import com.google.firebase.auth.FirebaseUser
class FirebaseState<T> (
    val status: Status,
    val data : T? = null,
    val message: String? = null) {
    companion object {
        fun <T> success(data: T?) = FirebaseState(Status.SUCCESS, data)
        fun <T> error(msg: String? = null, data:T? =null) = FirebaseState(Status.ERROR, message = msg, data = data)
        fun <T> loading(msg: Int? = null) = FirebaseState<T>(Status.LOADING)
    }
 }