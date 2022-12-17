package com.example.doglist.model.dao

import com.google.gson.annotations.SerializedName

data class dogListEntity(
    @SerializedName("status")var statusRest: String,
    @SerializedName("message")var imagesDogs: List<String>)