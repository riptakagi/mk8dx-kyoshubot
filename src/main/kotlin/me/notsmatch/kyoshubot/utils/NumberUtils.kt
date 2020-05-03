package me.notsmatch.kyoshubot.utils

object NumberUtils {

    fun isInteger(`val`: String): Boolean {
        try {
            Integer.parseInt(`val`)
            return true
        } catch (nfex: NumberFormatException) {
            return false
        }

    }

    fun isDouble(`val`: String): Boolean {
        try {
            java.lang.Double.parseDouble(`val`)
            return true
        } catch (nfex: NumberFormatException) {
            return false
        }

    }
}
