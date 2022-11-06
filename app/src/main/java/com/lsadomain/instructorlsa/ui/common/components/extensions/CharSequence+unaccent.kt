package com.lsadomain.instructorlsa.ui.common.components.extensions

import java.text.Normalizer

private val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()

fun String.unaccent(): String {
    val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
    return REGEX_UNACCENT.replace(temp, "")
}

fun String.lowercaseAndUnaccent(): String {
    return this.lowercase().unaccent()
}

fun String.uppercaseAndUnaccent(): String {
    return this.uppercase().unaccent()
}