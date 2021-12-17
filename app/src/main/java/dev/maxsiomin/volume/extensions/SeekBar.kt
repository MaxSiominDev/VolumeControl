package dev.maxsiomin.volume.extensions

import android.widget.SeekBar

fun SeekBar.addValueChangedListener(onChanged: (Int, Boolean) -> Unit) {
    this.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            onChanged(progress, fromUser)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) { }

        override fun onStopTrackingTouch(seekBar: SeekBar?) { }
    })
}
