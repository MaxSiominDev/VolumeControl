package dev.maxsiomin.volume.extensions

import android.content.Context
import androidx.preference.PreferenceManager
import dev.maxsiomin.volume.util.SharedPrefs

fun Context.getDefaultSharedPrefs(): SharedPrefs =
    PreferenceManager.getDefaultSharedPreferences(this)
