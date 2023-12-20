package com.example.govermentapp.data.models

import com.google.gson.annotations.SerializedName

data class GovernmentInstitutionModel(
    @SerializedName("_id") val id:String,
    @SerializedName("organization") val organization:String,
    @SerializedName("fact") val fact:String,
    @SerializedName("url") val url:String,
    @SerializedName("operations") val operations:String,
    @SerializedName("dataset") val dataset:String,
    @SerializedName("created_at") val created_at:String
)
