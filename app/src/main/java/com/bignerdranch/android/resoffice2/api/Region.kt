package com.bignerdranch.android.resoffice2.api

import com.google.gson.annotations.SerializedName

data class Region( // // класс для получения переменных и сериализации их для приложения
    @SerializedName("SNAME")
    val SNAME: String,
    @SerializedName("ID")
    val ID: Int
)