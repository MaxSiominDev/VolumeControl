package dev.maxsiomin.volume.fragments.home

import android.media.AudioManager.STREAM_MUSIC
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.maxsiomin.volume.fragments.base.BaseViewModel
import dev.maxsiomin.volume.util.UiActions
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(uiActions: UiActions) : BaseViewModel(uiActions) {

    val seekBarMax = 100

    var volume: Int
        get() = audioManager.getStreamVolume(STREAM_MUSIC)
        set(value) {
            audioManager.setStreamVolume(STREAM_MUSIC, value, 0)
        }
}
