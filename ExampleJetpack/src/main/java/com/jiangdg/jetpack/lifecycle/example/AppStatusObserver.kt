package com.jiangdg.jetpack.lifecycle.example

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * author: jiangdg
 * date: 2021/1/8 3:08 PM
 * description: APP前后台状态观察者
 */
class AppStatusObserver: DefaultLifecycleObserver {

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)

        Log.i("AppStatusObserver", "APP置于前台")

        // 发送APP置于前台广播
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)

        Log.i("AppStatusObserver", "APP置于后台")

        // 发送APP置于前台广播
    }
}