package com.br.bora.app.utils

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {
    private val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
    private val timeFormatter = SimpleDateFormat("HH:mm", Locale.ITALIAN)
    private val cal: Calendar = Calendar.getInstance()

    fun changeStringToDate(str: String): Date? = sdf.parse(str)

    fun changeStringToTime(str: String): Date? = timeFormatter.parse(str)

    fun formatterDate() = sdf

    fun formatterTime() = timeFormatter

    fun getCurrentDate(): Date? = sdf.parse(sdf.format(Date()))

    fun getCurrentTime(): Date? = timeFormatter.parse(timeFormatter.format(Date()))

    fun getCalendar(): Calendar = cal
}