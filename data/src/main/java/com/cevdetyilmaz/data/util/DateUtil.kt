package com.cevdetyilmaz.data.util

object DateUtil {
    fun formatDate(timestamp: Any?): String {
        return timestamp.toString().replace('T', ' ').plus("\n").replace('Z', ' ')
    }
}