package com.djf.newsboss.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

fun calculateTimeSince(dateString: String, pattern: String = "yyyy-MM-dd HH:mm:ss"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    val inputDate: Date? = dateFormat.parse(dateString)

    // Ensure the inputDate is not null
    if (inputDate == null) {
        return "Invalid date format"
    }

    // Get the current date and time
    val currentDate = Date()

    // Calculate the difference in milliseconds
    val diffInMillis = currentDate.time - inputDate.time

    // Calculate the difference in different time units
    val days = TimeUnit.MILLISECONDS.toDays(diffInMillis)
    val hours = TimeUnit.MILLISECONDS.toHours(diffInMillis) % 24
    val minutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis) % 60
    val seconds = TimeUnit.MILLISECONDS.toSeconds(diffInMillis) % 60

    // Build a readable time difference string
    return when {
        days > 0 -> "$days days ago"
        hours > 0 -> "$hours hours ago"
        minutes > 0 -> "$minutes minutes ago"
        else -> "$seconds seconds ago"
    }
}