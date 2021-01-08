package com.jiangdg.jetpack.lifecycle.example

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

/**
 * author: jiangdg
 * date: 2021/1/8 2:35 PM
 * description: 自定义宿主，拥有Start和Stop生命周期事件
 */
object AppStatusOwner:  LifecycleOwner {

    private val mLifecycleRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): Lifecycle = mLifecycleRegistry

    fun init(application: Application) {
        application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            private var activityFrontCount = 0

            override fun onActivityStarted(activity: Activity) {
                // 第一个activity置于前台时
                // 向外部发送Lifecycle.Event.ON_START事件，表示APP当前处于前台
                if (activityFrontCount == 0) {
                    mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
                }

                activityFrontCount++
            }

            override fun onActivityStopped(activity: Activity) {
                activityFrontCount--

                // 当前没有Activity置于前台时
                // 向外部发送Lifecycle.Event.ON_STOP事件，表示APP当前处于后台
                if (activityFrontCount == 0) {
                    mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
                }
            }

            override fun onActivityPaused(activity: Activity) {

            }

            override fun onActivityDestroyed(activity: Activity) {

            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

            }

            override fun onActivityResumed(activity: Activity) {

            }
        })
    }
}