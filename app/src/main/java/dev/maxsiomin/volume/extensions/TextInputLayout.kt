package dev.maxsiomin.volume.extensions

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.clearError() {
    if (error != null) error = null
}
