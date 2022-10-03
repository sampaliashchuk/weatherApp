package com.example.interviewapp.utils

import java.text.SimpleDateFormat
import java.util.*

private const val DATE_TIME_FORMAT = "MMM dd,yyyy HH:mm"
private const val SIMPLE_DATE_FORMAT = "yyyy-MM-dd"

fun Long.toDateTime(): String {
    val dateFormat = SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault())
    return dateFormat.format(Date(this))
}

fun Long.toSimpleDateTime(daysToAdd: Int = 0): String {
    val millisInDay = 86400000
    val dateFormat = SimpleDateFormat(SIMPLE_DATE_FORMAT, Locale.getDefault())
    return dateFormat.format(Date(this + (daysToAdd * millisInDay)))
}