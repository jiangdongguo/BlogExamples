package com.jiangdg.jetpack.lifecycle.observer

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

/**
 * author: jiangdg
 * date: 2020/11/3 4:49 PM
 * description:
 */
class MyLifecycleObserver02: LifecycleEventObserver {

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        Log.i("LifecycleObserver", "I'm observer02, activity now in--->$event")
    }
}