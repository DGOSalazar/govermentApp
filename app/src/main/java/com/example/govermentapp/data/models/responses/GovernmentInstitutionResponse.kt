package com.example.govermentapp.data.models.responses

import com.example.govermentapp.data.models.GovernmentInstitutionModel
import com.google.gson.annotations.SerializedName

data class GovernmentInstitutionResponse(
    @SerializedName("results") val governmentInstitutions: List<GovernmentInstitutionModel>
)