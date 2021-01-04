package com.jiangdg.jetpack.lifecycle.observer

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * author: jiangdg
 * date: 2020/11/3 4:49 PM
 * description: 使用Java 8
 */
class MyLifecycleObserver03: DefaultLifecycleObserver {

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        Log.i("LifecycleObserver", "I'm observer03, activity now in--->onCreate")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Log.i("LifecycleObserver", "I'm observer03, activity now in--->onStart")
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Log.i("LifecycleObserver", "I'm observer03, activity now in--->onResume")
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        Log.i("LifecycleObserver", "I'm observer03, activity now in--->onPause")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.i("LifecycleObserver", "I'm observer03, activity now in--->onStop")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        Log.i("LifecycleObserver", "I'm observer03, activity now in--->onDestroy")
    }
}