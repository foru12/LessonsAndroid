package com.example.architecturalpatterns

import java.text.SimpleDateFormat
import java.util.Locale

object Singlton {
    // этот метод везде доступен в рамках пакета
    fun convertDate(dateStr: String): String {
        val inputFormat = SimpleDateFormat("dd.MM.yyyy", Locale("ru"))
        val outputFormat = SimpleDateFormat("dd MMMM", Locale("ru"))
        val date = inputFormat.parse(dateStr)
        return outputFormat.format(date)
    }
}