package com.elka.foody.utils

import java.util.*

object WithDate {
  fun getDateString(): String {
    val calendar = Calendar.getInstance(TimeZone.getDefault())
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    return "$day $month, $year"
  }
}