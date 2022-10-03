package com.example.interviewapp.model.local.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class States(
    val capitals: List<String>
)