package com.jiangdg.jetpack

import android.app.Application
import com.jiangdg.jetpack.lifecycle.example.AppStatusObserver
import com.jiangdg.jetpack.lifecycle.example.AppStatusOwner

/**
 * author: jiangdg
 * date: 2021/1/8 3:14 PM
 * description:
 */
class JetpackApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        // 启动监听所有Activity的状态变化
        AppStatusOwner.init(this)
        // 注册观察者到宿主
        AppStatusOwner.lifecycle.addObserver(AppStatusObserver())
    }
}