package dev.maxsiomin.volume.extensions

/**
 * If string == null, returns ""
 */
fun String?.notNull(): String = this ?: ""
