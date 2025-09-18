package com.bignerdranch.android.resoffice2.api

import com.google.gson.annotations.SerializedName

data class Office( // класс для получения переменных и сериализации их для приложения
    @SerializedName("SSHORTNAME")
    val SSHORTNAME: String,
    @SerializedName("SADDRESS")
    val SADDRESS: String,
    @SerializedName("SGRAF")
    val SGRAF: String
)