package com.jiangdg.jetpack.lifecycle.observer

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * author: jiangdg
 * date: 2020/11/3 4:47 PM
 * description:
 */
class MyLifecycleObserver01: LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun create() {
        Log.i("LifecycleObserver", "I'm observer01, activity now in--->onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        Log.i("LifecycleObserver", "I'm observer01, activity now in--->onStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resume() {
        Log.i("LifecycleObserver", "I'm observer01, activity now in--->onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pause() {
        Log.i("LifecycleObserver", "I'm observer01, activity now in--->onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        Log.i("LifecycleObserver", "I'm observer01, activity now in--->onStop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy() {
        Log.i("LifecycleObserver", "I'm observer01, activity now in--->onDestory")
    }
}