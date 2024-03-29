package com.example.newsAppChallenge.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun String.toFormattedDate(): String {
    val dateFormat = SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.getDefault())
    val date = dateFormat.parse(this)

    val today = Calendar.getInstance()
    val calendar =
        Calendar.getInstance().apply {
            time = date ?: today.time
        }
    if (today.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) &&
        today.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR)
    ) {
        return "Today"
    }

    val monthDayFormat = SimpleDateFormat("MMMM dd", Locale.getDefault())
    return monthDayFormat.format(date ?: today.time).uppercase(Locale.getDefault())
}

fun getGreeting(): String {
    val calendar = Calendar.getInstance()
    val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)

    return when (hourOfDay) {
        in 0..11 -> "Good morning"
        in 12..18 -> "Good afternoon"
        else -> "Good evening"
    }
}
