package dev.maxsiomin.volume.extensions

import android.media.MediaRouter
import android.media.MediaRouter.RouteInfo
import android.media.MediaRouter.RouteGroup
import android.media.MediaRouter.CALLBACK_FLAG_UNFILTERED_EVENTS
import android.media.MediaRouter.ROUTE_TYPE_USER

fun MediaRouter.addOnVolumeChangedCallback(action: () -> Unit) {
    val callback: MediaRouter.Callback = object : MediaRouter.Callback() {
        override fun onRouteSelected(router: MediaRouter?, type: Int, info: RouteInfo?) { }

        override fun onRouteUnselected(router: MediaRouter?, type: Int, info: RouteInfo?) { }

        override fun onRouteAdded(router: MediaRouter?, info: RouteInfo?) { }

        override fun onRouteRemoved(router: MediaRouter?, info: RouteInfo?) { }

        override fun onRouteChanged(router: MediaRouter?, info: RouteInfo?) { }

        override fun onRouteGrouped(router: MediaRouter?, info: RouteInfo?, group: RouteGroup?, index: Int) { }

        override fun onRouteUngrouped(router: MediaRouter?, info: RouteInfo?, group: RouteGroup?) { }

        override fun onRouteVolumeChanged(router: MediaRouter?, info: RouteInfo?) {
            action.invoke()
        }
    }

    addCallback(ROUTE_TYPE_USER, callback, CALLBACK_FLAG_UNFILTERED_EVENTS)
}
